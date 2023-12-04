package com.ua.transport.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    public String password;
    private boolean isAuthorized;
    private List<TicketDTO> tickets;
}
