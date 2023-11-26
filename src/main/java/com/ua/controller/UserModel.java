package com.ua.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModel {
    public String username;
    public String password;

    @Override
    public String toString(){
        return "User: " + username+", "+password;
    }
}
