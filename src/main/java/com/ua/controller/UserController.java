package com.ua.controller;

import com.ua.model.User;
import com.ua.serves.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.config.RepositoryBeanDefinitionRegistrarSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/welcome")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/create")
    public void create(@RequestBody User user){
        userService.create(user);
    }
    @GetMapping("/get/{userId}")
    public User getById(@PathVariable Long userId){
       return userService.getById(userId);
    }
    @GetMapping("/get")
    public ResponseEntity<Page<User>> getAll(Pageable pageable){
       return ResponseEntity.ok(userService.getAll(pageable));
    }
    @GetMapping("/get/soarted")
    public List<User> getAll(){
        return userService.getAllSoarted();
    }
    @PutMapping("/update/{userId}")
    public User update(@PathVariable Long userId,@RequestBody User user){
        return userService.update(userId,user);
    }
    @DeleteMapping ("/delete/{userId}")
    public void delete(@PathVariable Long userId){
        userService.delete(userId);
    }


}

