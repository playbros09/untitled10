package com.ua.serves.authorization;

import com.ua.repository.UserRepository;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static com.ua.serves.authorization.util.MockGenerator.generateUser;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
public class AuthorizationServiceIT {
    @Autowired
    private AuthorizationService authorizationService;

    @Mock
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
        assertThrows(RuntimeException.class, () -> authorizationService.isAuthorize(EmptyUsername, EmptyPassword));
    }
}
