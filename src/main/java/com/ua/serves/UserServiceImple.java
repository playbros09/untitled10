package com.ua.serves;

import com.ua.model.User;
import com.ua.repository.UserRepository;
import com.ua.serves.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImple implements UserService {

    private final UserRepository userRepository;
    @Override
    public void create(User user) {
        userRepository.save(user);
    }

    @Override
    public User getById(Long userId) {
        return userRepository.getUserById(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll((org.springframework.data.domain.Pageable) pageable);
    }
    @Override
    @Transactional(readOnly = true)
    public List<User> getAllSoarted() {
        Sort sort = Sort.by(Sort.Order.desc("id"));
        return userRepository.findAll(sort);
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User update(Long userId, User incomingUser) {
        User user = getById(userId);
        return userRepository.save(user);
    }

    @Override
    public void delete(Long userId){
        User user = getById(userId);
        userRepository.delete(user);
    }
}
