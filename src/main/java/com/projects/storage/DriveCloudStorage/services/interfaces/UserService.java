package com.projects.storage.DriveCloudStorage.services.interfaces;

import com.projects.storage.DriveCloudStorage.model.User;

public interface UserService {

    boolean isUsernameAvailable(String username);

    int create(User user);

    User get(String username);

    Integer getUserId(String username);
}
