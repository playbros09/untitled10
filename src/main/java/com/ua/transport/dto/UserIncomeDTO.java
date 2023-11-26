package com.ua.transport.dto;

import com.ua.model.Ticket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.User;
import org.springframework.stereotype.Component;


@Getter
@Setter
@AllArgsConstructor
public class UserIncomeDTO {
    public String getPassword;
    private String username;
    public String password;

}
