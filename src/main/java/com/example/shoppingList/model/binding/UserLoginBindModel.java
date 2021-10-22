package com.example.shoppingList.model.binding;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserLoginBindModel {
    @NotEmpty(message = "")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters!\n")
    private String username;
    @NotEmpty(message = "")
    @Size(min = 3, max = 20, message = "Password must be between 3 and 20 characters!")
    private String password;

    public String getUsername() {
        return username;
    }

    public UserLoginBindModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserLoginBindModel setPassword(String password) {
        this.password = password;
        return this;
    }
}
