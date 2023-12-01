package com.ua.repository;

import com.ua.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User getUserByUsernameAndPassword(String username, String password);
    User getUserById(Long id);

    User getUserByUsername(String username);
}
