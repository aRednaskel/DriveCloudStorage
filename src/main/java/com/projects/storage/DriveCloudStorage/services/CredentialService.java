package com.projects.storage.DriveCloudStorage.services;

import com.projects.storage.DriveCloudStorage.mapper.CredentialMapper;
import com.projects.storage.DriveCloudStorage.model.Credential;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {

    private final CredentialMapper credentialMapper;

    public CredentialService(CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }

    public boolean isCredentialAvailable(String url) {
        return credentialMapper.getUrl(url) == null;
    }

    public int createCredential(Credential credential) {
        return credentialMapper.insert(credential);
    }

    public Credential getCredential(String url) {
        return credentialMapper.getUrl(url);
    }

    public List<Credential> getUserCredentials(Integer userId) {
        return credentialMapper.getUsetNotes(userId);
    }
}
