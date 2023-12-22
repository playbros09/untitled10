package com.ua.serves.authorization;

import com.ua.model.User;
import com.ua.repository.UserRepository;
import com.ua.serves.user.UserService;
import liquibase.pro.packaged.I;
import liquibase.pro.packaged.S;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static com.ua.serves.authorization.util.MockGenerator.generateUser;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
public class AuthorizationServiseIT {
    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private UserRepository userRepository;
    @Test
    public void isAuthorizeWhenUserValid(){
        String username = "admin";
        String password = "admin";

        userRepository.save(generateUser(username, password));
        assertDoesNotThrow(() -> authorizationService.isAuthorize(username, password));
    }
    @Test
    public void isAuthorizeWhenUserInvalid(){
        String username = "admin";
        String password = "admin";
        String InvalidPassword = "invalid";

        userRepository.save(generateUser(username, password));
        assertThrows(RuntimeException.class, () -> authorizationService.isAuthorize(username, InvalidPassword));
    }
    @Test
    public void isAuthorizeWhenUsernameAndPasswordAreEmpty(){
        String username = "admin";
        String password = "admin";
        String EmptyUsername = "";
        String EmptyPassword = "";

        userRepository.save(generateUser(username, password));
        assertThrows(RuntimeException.class, () -> authorizationService.isAuthorize(username, EmptyPassword));
    }
}
