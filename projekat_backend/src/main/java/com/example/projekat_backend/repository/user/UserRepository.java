package com.example.projekat_backend.repository.user;

import com.example.projekat_backend.model.User;

import java.util.List;

public interface UserRepository {
    List<User> allUser();
    User findUser(String email);
    User addUser(User user);
    void userActivity(String email);
    User updateUser(User user, String email);
    void deleteUser(String email);
}
