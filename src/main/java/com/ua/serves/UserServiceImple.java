package com.ua.serves;

import com.ua.model.User;
import com.ua.repository.UserRepository;
import com.ua.serves.user.UserService;
import com.ua.transport.dto.UserDTO;
import com.ua.transport.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.awt.print.Pageable;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImple implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void create(UserDTO dto) {
        User user = userMapper.toEntity(dto);
        userRepository.save(user);
    }

    @Override
    public UserDTO getById(Long userId) {
        User user = userRepository.getUserById(userId);
        return userMapper.toDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserDTO> getAll(Pageable pageable) {
        Page<User> userPage = userRepository.findAll((org.springframework.data.domain.Pageable) pageable);
//    return userMapper.toPageDto(userPage);
   return null;
    }
    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getAllSoarted() {
        Sort sort = Sort.by(Sort.Order.asc("id"));
        return userRepository.findAll(sort).stream().map(userMapper::toDto).toList();
    }


    @Override
    public void update(Long userId, UserDTO incomingUser) {
        User user = userRepository.getUserById(userId);
        userMapper.update(incomingUser, user);
    }

    @Override
    public void delete(Long userId){
        userRepository.deleteById(userId);
    }
}
