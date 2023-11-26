package com.ua.controller;

import com.ua.model.User;
import com.ua.serves.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;
@Api(value ="User Controller Api", description = "Hello, this is my description")
@RestController
@RequestMapping("/welcome")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/create/{language} ")
    public void create(@RequestBody User user,
                       @RequestHeader("Autorization")String password,
                       @RequestParam(name = "username") String username,
                       @PathVariable String language){
        System.out.println("We are create a new user in "+language+" language");
        user.setUsername(username);
        user.setPassword(password);
        userService.create(user);
    }
    @Operation(method = "Get users",description = "Getting user by id")
    @GetMapping("/get/{userId}")
    public ResponseEntity<User> getById(@PathVariable Long userId){
        User user= userService.getById(userId);
        if(user == null){
            throw new RuntimeException("User not found");
        }
       return ResponseEntity.ok(user);
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

