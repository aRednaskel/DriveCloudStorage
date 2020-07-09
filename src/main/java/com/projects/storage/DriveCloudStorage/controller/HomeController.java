package com.projects.storage.DriveCloudStorage.controller;

import com.projects.storage.DriveCloudStorage.errorhandlers.StorageFileNotFoundException;
import com.projects.storage.DriveCloudStorage.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final StorageService storageService;

    @Autowired
    public HomeController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService.loadAll().map(
                path -> path.getFileName().toString())
                .collect(Collectors.toList()));
        return "home";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping
    public String handleFileUpload(@RequestParam("fileUpload") MultipartFile fileUpload,
                                   RedirectAttributes redirectAttributes) {

        storageService.store(fileUpload);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + fileUpload.getOriginalFilename() + "!");

        return "redirect:/home";
    }

    @GetMapping("/delete/{filename}")
    public String deleteFile(@PathVariable String filename) {
        storageService.deleteFile(filename);
        return "redirect:/home";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
