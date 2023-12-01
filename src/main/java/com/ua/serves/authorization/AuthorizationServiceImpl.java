package com.ua.serves.authorization;

import com.ua.model.User;
import com.ua.repository.UserRepository;
import com.ua.transport.dto.UserIncomeDTO;
import com.ua.transport.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public boolean login(UserIncomeDTO dto) {
        User incomingUser = userMapper.dtoToEntity(dto);
        User dbUser= userRepository.getUserByUsernameAndPassword(incomingUser.getUsername(), incomingUser.getPassword());
        if(dbUser!=null){dbUser.setAuthorized(true);
            return true;
        }else {
            return false;
        }
    }
    @Override
    public void logout(UserIncomeDTO dto) {
        User incomingUser = userMapper.dtoToEntity(dto);
        User dbUser = userRepository.getUserByUsername(incomingUser.getUsername());
        dbUser.setAuthorized(false);
    }

    @Override
    public boolean singup(UserIncomeDTO dto) {
        User incomingUser = userMapper.dtoToEntity(dto);
        userRepository.save(incomingUser);
        return true;
    }
}
