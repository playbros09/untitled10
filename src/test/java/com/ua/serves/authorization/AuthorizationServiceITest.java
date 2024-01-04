package com.ua.serves.authorization;

import com.ua.model.User;
import com.ua.repository.UserRepository;
import com.ua.transport.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.ua.serves.authorization.util.MockGenerator.generateUser;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class AuthorizationServiceITest {
    private AuthorizationService authorizationService;
    private UserRepository userRepository;

    private UserMapper userMapper;

    @BeforeEach
    public void setup(){
        userRepository = mock(UserRepository.class);
        authorizationService = new AuthorizationServiceImpl(userRepository, userMapper);
    }
    @Test
    public void isAuthorizeIfUserExists(){
        // given
        String username = "admin";
        String password = "admin";
        User user = generateUser(username, password);


        // when
        when(userRepository.getUserByUsernameAndPassword(username, password)).thenReturn(user);

        // then

        assertThatCode(() -> authorizationService.isAuthorize(username, password)).doesNotThrowAnyException();

        verify(userRepository, times(1)).getUserByUsernameAndPassword(username, password);
    }

    @Test
    public void isAuthorizeIfUserDoesntExists(){
        // given
        String username = "invalid";
        String password = "invalid";

        // when
        when(userRepository.getUserByUsernameAndPassword(username, password)).thenReturn(null);

        // then
        assertThrows(RuntimeException.class, () -> authorizationService.isAuthorize(username, password));
        verify(userRepository, times(1)).getUserByUsernameAndPassword(username, password);
    }
    @Test
    public void isAuthorizeIfUserAndPasswordAreNull(){
        // given
        String username = null;
        String password = null;

        // when
        when(userRepository.getUserByUsernameAndPassword(any(), any())).thenReturn(null);

        // then
        assertThrows(RuntimeException.class, () -> authorizationService.isAuthorize(null, null));
        verify(userRepository, times(1)).getUserByUsernameAndPassword(any(), any());

    }
}
