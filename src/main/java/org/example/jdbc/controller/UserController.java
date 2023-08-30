package org.example.jdbc.controller;

import org.example.jdbc.model.User;
import org.example.views.Login;

public class UserController {

    public boolean Login (String user, String password) {
        return User.validateUser(user, password);
    }

}
