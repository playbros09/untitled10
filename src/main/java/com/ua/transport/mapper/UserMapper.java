package com.ua.transport.mapper;

import com.ua.model.User;
import com.ua.transport.dto.UserIncomeDTO;

public class UserMapper {
    public User dtoToEntity(UserIncomeDTO dto){
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword);

        return user;
    }

}
