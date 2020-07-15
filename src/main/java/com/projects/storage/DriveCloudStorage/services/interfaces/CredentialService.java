package com.projects.storage.DriveCloudStorage.services.interfaces;

import com.projects.storage.DriveCloudStorage.model.Credential;

import java.util.List;

public interface CredentialService {

    int create(Credential credential);

    int update(Credential credential);

    List<Credential> getUserCredentials(Integer userId);

    Integer getUserId(Integer credentialId);

    void delete(Integer credentialId);
}
