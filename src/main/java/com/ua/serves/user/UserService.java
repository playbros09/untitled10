package com.ua.serves.user;

import com.ua.model.User;
import com.ua.transport.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;

public interface UserService {
    void create(UserDTO dto);


    UserDTO getById(Long userId);

    Page<UserDTO> getAll(Pageable pageable);

    @Transactional(readOnly = true)
    List<UserDTO> getAllSoarted();

    void update(Long userId, UserDTO user);

    void delete(Long userId);
}
