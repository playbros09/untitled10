package com.ua.serves.authorization;

import com.ua.transport.dto.UserDTO;

public interface AuthorizationService {
    void isAuthorize(String username, String password);

    void login(String username, String password);

    boolean login(UserDTO dto);
    void logout(UserDTO dto);
    boolean singup(UserDTO dto);
}
