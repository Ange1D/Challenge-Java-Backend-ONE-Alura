package org.example.jdbc.controller;

import org.example.jdbc.model.User;

public class UserController {

    public boolean Login (String user, String password) {
        return User.validateUser(user, password);
    }

}
