package com.example.shoppingList.service.impl;

import com.example.shoppingList.model.entity.UserEntity;
import com.example.shoppingList.model.service.UserServiceModel;
import com.example.shoppingList.repo.UserRepo;
import com.example.shoppingList.security.CurrentUser;
import com.example.shoppingList.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final CurrentUser currentUser;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepo userRepo, CurrentUser currentUser, ModelMapper modelMapper) {
        this.userRepo = userRepo;
        this.currentUser = currentUser;
        this.modelMapper = modelMapper;
    }
    // Methods for REGISTER
    @Override
    public boolean checkExistsByUsername(String username) {
        return userRepo.findByUsername(username).isPresent();
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {
        UserEntity userEntity = modelMapper.map(userServiceModel, UserEntity.class);
        UserEntity savedUser = userRepo.save(userEntity);
        return modelMapper.map(savedUser, UserServiceModel.class);
    }
    // Methods for LOGIN
    @Override
    public boolean authenticate(String username, String password) {
        Optional<UserEntity> userEntityOpt = userRepo.findByUsername(username);
        if (userEntityOpt.isEmpty()) {
            return false;
        }
        return userEntityOpt.get().getPassword().equals(password);
    }

    @Override
    public void login(String username) {
        UserEntity loggedInUser = userRepo.findByUsername(username).orElseThrow();
        currentUser.setId(loggedInUser.getId());
        currentUser.setUsername(loggedInUser.getUsername());
    }
}
