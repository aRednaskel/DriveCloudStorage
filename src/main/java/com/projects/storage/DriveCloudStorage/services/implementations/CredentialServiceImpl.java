package com.projects.storage.DriveCloudStorage.services.implementations;

import com.projects.storage.DriveCloudStorage.mapper.CredentialMapper;
import com.projects.storage.DriveCloudStorage.model.Credential;
import com.projects.storage.DriveCloudStorage.services.interfaces.CredentialService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialServiceImpl implements CredentialService {

    private final CredentialMapper credentialMapper;

    public CredentialServiceImpl(CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }
    
    @Override
    public int create(Credential credential) {
        return credentialMapper.insert(credential);
    }

    @Override
    public int update(Credential credential) {
        return credentialMapper.update(credential);
    }

    @Override
    public Credential get(Integer credentialId) {
        return credentialMapper.getCredential(credentialId);
    }

    @Override
    public List<Credential> getUserCredentials(Integer userId) {
        return credentialMapper.getUserCredentials(userId);
    }

    @Override
    public Integer getUserId(Integer credentialId) {
        return credentialMapper.getUserId(credentialId);
    }

    @Override
    public void delete(Integer credentialId) {
        credentialMapper.delete(credentialId);
    }
}
