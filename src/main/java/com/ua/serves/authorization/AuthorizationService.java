package com.ua.serves.authorization;

import com.ua.model.User;
import com.ua.transport.dto.UserIncomeDTO;

public interface AuthorizationService {
    boolean login(UserIncomeDTO dto);
    void logout(UserIncomeDTO dto);
    boolean singup(UserIncomeDTO dto);
}
