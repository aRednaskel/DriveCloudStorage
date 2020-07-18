package com.projects.storage.DriveCloudStorage.services.implementations;

import com.projects.storage.DriveCloudStorage.errorhandlers.StorageException;
import com.projects.storage.DriveCloudStorage.mapper.FileMapper;
import com.projects.storage.DriveCloudStorage.model.StoredFile;
import com.projects.storage.DriveCloudStorage.services.interfaces.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@Service
public class FileSystemStorageService implements StorageService {

    private final FileMapper fileMapper;

    @Autowired
    public FileSystemStorageService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    @Override
    public void store(MultipartFile file, Integer userId) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                throw new StorageException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }

            if (!fileMapper.getUserFiles(userId).contains(filename)) {
                StoredFile storedFile = new StoredFile(filename,
                        file.getContentType(), String.valueOf(file.getSize()), userId,
                        file.getBytes());
                fileMapper.insert(storedFile);
            } else {
                throw new StorageException("File already exists");
            }

        }
        catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
    }

    public List<String> getUserFiles(Integer userId) {
        return fileMapper.getUserFiles(userId);
    }


    @Override
    public File load(String filename, Integer userId) {
        File fileToDownload = new File(filename);
        try {
            FileOutputStream fos = new FileOutputStream(fileToDownload);
            fos.write(fileMapper.getFile(filename, userId));
            fos.flush();
            fos.close();
        } catch (IOException e) {
            throw new StorageException("I/O error has occured: " + filename, e);
        }

        return fileToDownload;
    }

    @Override
    public Resource loadAsResource(String filename, Integer userId) {
        try {
            File file = load(filename, userId);
            Resource resource = new UrlResource(file.toURI());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageException(
                        "Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new StorageException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteFile(String filename, Integer userId) {
        fileMapper.delete(filename);
    }


}
