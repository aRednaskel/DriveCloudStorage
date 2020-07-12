package com.projects.storage.DriveCloudStorage.services.implementations;

import com.projects.storage.DriveCloudStorage.mapper.UserMapper;
import com.projects.storage.DriveCloudStorage.model.User;
import com.projects.storage.DriveCloudStorage.services.interfaces.UserService;
import com.projects.storage.DriveCloudStorage.services.security.HashService;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final HashService hashService;

    public UserServiceImpl(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    @Override
    public boolean isUsernameAvailable(String username) {
        return userMapper.getUser(username) == null;
    }

    @Override
    public int create(User user) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
        return userMapper.insert(new User(null, user.getUsername(), encodedSalt, hashedPassword, user.getFirstName(), user.getLastName()));
    }

    @Override
    public User get(String username) {
        return userMapper.getUser(username);
    }

    @Override
    public Integer getUserId(String username) {
        return userMapper.getUser(username).getUserId();
    }
}
