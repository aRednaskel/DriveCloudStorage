package com.projects.storage.DriveCloudStorage.services.interfaces;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface StorageService {

    void store(MultipartFile file, Integer userId);

    File load(String filename,  Integer userId);

    Resource loadAsResource(String filename, Integer userId);

    List<String> getUserFiles(Integer userId);

    void deleteFile(String filename, Integer userId);

}
