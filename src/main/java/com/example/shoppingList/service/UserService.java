package com.example.shoppingList.service;

import com.example.shoppingList.model.service.UserServiceModel;

public interface UserService {

    // Methods for REGISTER
    boolean checkExistsByUsername(String username);

    UserServiceModel registerUser(UserServiceModel userServiceModel);

    // Methods for LOGIN
    boolean authenticate(String username, String password);

    void login(String username);
}
