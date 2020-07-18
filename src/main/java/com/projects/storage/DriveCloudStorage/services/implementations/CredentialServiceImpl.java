package com.projects.storage.DriveCloudStorage.services.implementations;

import com.projects.storage.DriveCloudStorage.mapper.CredentialMapper;
import com.projects.storage.DriveCloudStorage.model.Credential;
import com.projects.storage.DriveCloudStorage.services.interfaces.CredentialService;
import com.projects.storage.DriveCloudStorage.services.security.EncryptionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CredentialServiceImpl implements CredentialService {

    private final CredentialMapper credentialMapper;
    private final EncryptionService encryptionService;
    private final String encryptionkey;

    public CredentialServiceImpl(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
        this.encryptionkey = UUID.randomUUID().toString().substring(1,17);
    }
    
    @Override
    public int create(Credential credential) {
        credential.setEncryptionkey(encryptionkey);
        credential.setPassword(encryptionService.encryptValue(credential.getPassword(), encryptionkey));
        return credentialMapper.insert(credential);
    }

    @Override
    public int update(Credential credential) {
        credential.setPassword(encryptionService.encryptValue(credential.getPassword(), credential.getEncryptionkey()));
        return credentialMapper.update(credential);
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
    public Credential getDecryptedCredential(Integer credentialId) {
        Credential decryptedCredential = credentialMapper.getCredential(credentialId);
//        decryptedCredential.setEncryptionkey(encryptionService.encryptValue(decryptedCredential.getEncryptionkey(), decryptedCredential.getEncryptionkey()));
        decryptedCredential.setPassword(encryptionService
                .decryptValue(decryptedCredential.getPassword(),decryptedCredential.getEncryptionkey()));
        return decryptedCredential;
    }

    @Override
    public void delete(Integer credentialId) {
        credentialMapper.delete(credentialId);
    }
}
