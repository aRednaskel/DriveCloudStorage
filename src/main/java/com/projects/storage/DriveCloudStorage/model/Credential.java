package com.projects.storage.DriveCloudStorage.model;

public class Credential {

    private Integer credentialId;
    private String url;
    private String username;
    private String encryptionkey;
    private String password;
    private Integer userId;

    public Credential(Integer credentialId, String url, String username, String encryptionkey, String password, Integer userId) {
        this.credentialId = credentialId;
        this.url = url;
        this.encryptionkey = encryptionkey;
        this.username = username;
        this.password = password;
        this.userId = userId;
    }

    public Integer getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Integer credentialId) {
        this.credentialId = credentialId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEncryptionkey() {
        return encryptionkey;
    }

    public void setEncryptionkey(String encryptionkey) {
        this.encryptionkey = encryptionkey;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
