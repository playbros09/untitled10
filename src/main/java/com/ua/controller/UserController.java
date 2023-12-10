package com.ua.controller;

import com.ua.serves.authorization.AuthorizationService;
import com.ua.serves.user.UserService;
import com.ua.transport.dto.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;
@Api(value ="User Controller Api")
@RestController
@RequestMapping("/welcome")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthorizationService authorizationService;

    @PostMapping("/create")
    public void create(@RequestBody UserDTO user,
                       @RequestHeader("password")String password,
                       @RequestParam("username") String username){
        authorizationService.isAuthorize(username,password);
        userService.create(user);
    }
   // @GetMapping("/get/{userId}")
  //  public void getById(
  //          @RequestHeader("password")String password,
  //          @RequestParam("username") String username,
  //          @PathVariable Long userId){
  //      authorizationService.isAuthorize(username,password);
  //      userService.getById(userId);
   // }

    @Operation(method = "Get users",description = "Getting user by id")
    @GetMapping("/get/{userId}")
    public ResponseEntity<Long> getById(@PathVariable Long userId){
        UserDTO user= userService.getById(userId);
        if(user == null){
            throw new RuntimeException("User not found");
        }
       return ResponseEntity.ok(userId);
    }
    @GetMapping("/get")
    public ResponseEntity<Page<UserDTO>> getAll(Pageable pageable){
        return ResponseEntity.ok(userService.getAll(pageable));
    }
    @GetMapping("/get/soarted")
    public List<UserDTO> getAll(){
        return userService.getAllSoarted();
    }
    @PutMapping("/update/{userId}")
    public void update(@PathVariable Long userId,
                       @RequestBody UserDTO user,@RequestHeader("password")String password,
                       @RequestParam("username") String username){
        authorizationService.isAuthorize(username, password);
        userService.update(userId, user);
    }
    @DeleteMapping ("/delete/{userId}")
    public void delete(@PathVariable Long userId,
                       @RequestHeader("password")String password,
                       @RequestParam("username") String username){
      authorizationService.isAuthorize(username, password);
        userService.delete(userId);
    }


}

