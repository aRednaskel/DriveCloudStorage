package com.projects.storage.DriveCloudStorage.controller;

import com.projects.storage.DriveCloudStorage.errorhandlers.StorageException;
import com.projects.storage.DriveCloudStorage.errorhandlers.StorageFileNotFoundException;
import com.projects.storage.DriveCloudStorage.model.Credential;
import com.projects.storage.DriveCloudStorage.model.Note;
import com.projects.storage.DriveCloudStorage.services.interfaces.CredentialService;
import com.projects.storage.DriveCloudStorage.services.interfaces.NoteService;
import com.projects.storage.DriveCloudStorage.services.interfaces.StorageService;
import com.projects.storage.DriveCloudStorage.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final StorageService storageService;
    private final NoteService noteService;
    private final UserService userService;
    private final CredentialService credentialService;

    @Autowired
    public HomeController(StorageService storageService, NoteService noteService, UserService userService,
                          CredentialService credentialService) {
        this.storageService = storageService;
        this.noteService = noteService;
        this.userService = userService;
        this.credentialService = credentialService;
    }

    @GetMapping
    public String uploadAllLists(Authentication authentication, Model model) throws IOException {

        int userId = userService.getUserId(authentication.getName());

        model.addAttribute("notes",
                noteService.getUserNotes(userId));
        model.addAttribute("credentials",
                credentialService.getUserCredentials(userId));
        model.addAttribute("files",
                storageService.getUserFiles(userId));
        return "home";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(Authentication authentication, @PathVariable String filename)  {

        Resource file = storageService.loadAsResource(filename, userService.getUserId(authentication.getName()));
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/uploadFile")
    public String handleFileUpload(Authentication authentication, @RequestParam("fileUpload") MultipartFile fileUpload,
                                   RedirectAttributes redirectAttributes) {
        if (fileUpload != null) {
            storageService.store(fileUpload, userService.getUserId(authentication.getName()));
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded " + fileUpload.getOriginalFilename() + "!");
        } else {
            redirectAttributes.addFlashAttribute("message",
                    "There was no file to upload!");
        }

        return "redirect:/home";
    }

    @GetMapping("/delete/{filename}")
    public String deleteFile(Authentication authentication, @PathVariable String filename) {
        storageService.deleteFile(filename, userService.getUserId(authentication.getName()));
        return "redirect:/home";
    }

    @PostMapping("/note")
    public String createOrUpdateNote(Authentication authentication, @ModelAttribute Note note, RedirectAttributes redirectAttributes) {
        if (note.getNoteId() != null) {
            noteService.update(note);
            redirectAttributes.addFlashAttribute("message",
                    "You successfully edited your note!");
        } else {
            note.setUserId(userService.getUserId(authentication.getName()));
            noteService.create(note);
            redirectAttributes.addFlashAttribute("message",
                    "You successfully added new note!");
        }
        return "redirect:/home";
    }

    @PostMapping("/credential")
    public String createOrUpdateCredentials(Authentication authentication, @ModelAttribute Credential credential, RedirectAttributes redirectAttributes) {
        if (credential.getCredentialId() != null
                && credentialService.get(credential.getCredentialId()).getUserId()
                .equals(userService.getUserId(authentication.getName()))) {
            credentialService.update(credential);
            redirectAttributes.addFlashAttribute("message",
                    "You successfully edited your credential!");
        } else {
            credential.setUserId(userService.getUserId(authentication.getName()));
            credentialService.create(credential);
            redirectAttributes.addFlashAttribute("message",
                    "You successfully added new credential!");
        }

        return "redirect:/home";
    }

    @GetMapping("/deleteNote")
    public String deleteNote(@RequestParam Integer noteId, Authentication authentication, RedirectAttributes redirectAttributes) {
        if (noteService.getUserId(noteId).equals(userService.getUserId(authentication.getName()))) {
            noteService.delete(noteId);
            redirectAttributes.addFlashAttribute("message",
                    "The note was successfully deleted!");
        } else {
            redirectAttributes.addFlashAttribute("message",
                    "You are not authorized to delete this note");
        }

        return "redirect:/home";
    }


    @GetMapping("/deleteCredential")
    public String deleteCredential(@RequestParam Integer credentialId, Authentication authentication, RedirectAttributes redirectAttributes) {
        if (credentialService.getUserId(credentialId).equals(userService.getUserId(authentication.getName()))) {
            credentialService.delete(credentialId);
            redirectAttributes.addFlashAttribute("message",
                    "The credential was successfully deleted!");
        } else {
            redirectAttributes.addFlashAttribute("message",
                    "You are not authorized to delete this credential");
        }
        return "redirect:/home";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(StorageException.class)
    public ResponseEntity<?> handleStorageException(StorageException exc) {
        return ResponseEntity.status(400).body(exc.getMessage());
    }

}
