package com.projects.storage.DriveCloudStorage.controller;

import com.projects.storage.DriveCloudStorage.model.Credential;
import com.projects.storage.DriveCloudStorage.model.Note;
import com.projects.storage.DriveCloudStorage.services.CredentialService;
import com.projects.storage.DriveCloudStorage.services.NoteService;
import com.projects.storage.DriveCloudStorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class NoteController {

    private final NoteService noteService;
    private final UserService userService;
    private final CredentialService credentialService;

    @Autowired
    public NoteController(NoteService noteService, UserService userService, CredentialService credentialService) {
        this.noteService = noteService;
        this.userService = userService;
        this.credentialService = credentialService;
    }

    @PostMapping("/addNote")
    public String createNote(Authentication authentication, @ModelAttribute Note note) {
        note.setUserId(userService.getUserId(authentication.getName()));
        noteService.createNote(note);
        return "redirect:/home";
    }

    @PostMapping("/addCredential")
    public String createCredentials(Authentication authentication, @ModelAttribute Credential credential) {
        credential.setUserId(userService.getUserId(authentication.getName()));
        credentialService.createCredential(credential);
        return "redirect:/home";
    }
}
