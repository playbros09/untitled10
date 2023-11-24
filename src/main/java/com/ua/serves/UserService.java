package com.ua.serves;

import com.ua.model.User;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;

public interface UserService {
    void create(User user);


    User getById(Long userId);

    Page<User> getAll(Pageable pageable);

    @Transactional(readOnly = true)
    List<User> getAllSoarted();

    User update(Long userId, User user);

    void delete(Long userId);
}
