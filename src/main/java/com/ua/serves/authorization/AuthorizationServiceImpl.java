package com.ua.serves.authorization;

import com.ua.model.User;
import com.ua.repository.UserRepository;
import com.ua.transport.dto.UserDTO;
import com.ua.transport.mapper.UserMapper;
import com.ua.transport.mapper.UserMapperImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.ua.util.Exceptions.UNAUTHORIZED;
import static com.ua.util.Exceptions.NOT_EXIST;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {



    private final UserRepository userRepository;

    private final UserMapper userMapper;

@Override
    public void isAuthorize(String username, String password){
        User user = userRepository.getUserByUsernameAndPassword(username, password);
        if(!isUserExist(username,password)){
            throw new RuntimeException(UNAUTHORIZED);
        }
        Optional.of(userRepository.getUserByUsernameAndPassword(username, password)).orElseThrow(RuntimeException::new);
    }
    @Override
    public void login(String username, String password){
        User user = userRepository.getUserByUsernameAndPassword(username, password);
        if(user == null){
            throw new RuntimeException(NOT_EXIST);
        }
}
private boolean isUserExist(String username, String password){
    return userRepository.getUserByUsernameAndPassword(username,password) != null;
}
    @Override
    public boolean login(UserDTO dto) {
        User incomingUser = userMapper.toEntity(dto);
        User dbUser= userRepository.getUserByUsernameAndPassword(incomingUser.getUsername(), incomingUser.getPassword());
        if(dbUser!=null){dbUser.setAuthorized(true);
            return true;
        }else {
            return false;
        }
    }
    @Override
    public void logout(UserDTO dto) {
        User incomingUser = userMapper.toEntity(dto);
        User dbUser = userRepository.getUserByUsername(incomingUser.getUsername());
        dbUser.setAuthorized(false);
    }

    @Override
    public boolean singup(UserDTO dto) {
        User incomingUser = userMapper.toEntity(dto);
        userRepository.save(incomingUser);
        return true;
    }
}
