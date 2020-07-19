package com.projects.storage.DriveCloudStorage.services.interfaces;

import com.projects.storage.DriveCloudStorage.model.Credential;

import java.util.List;

public interface CredentialService {

    int create(Credential credential);

    int update(Credential credential);

    boolean isUsernameAvailable(String credentialname, Integer userId);

    List<Credential> getUserCredentials(Integer userId);

    Integer getUserId(Integer credentialId);

    Credential getDecryptedCredential(Integer credentialId);

    void delete(Integer credentialId);
}
