package com.ua.controller;

import com.ua.serves.authorization.AuthorizationService;
import com.ua.transport.dto.UserDTO;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@Api(value = "Autorization controller API")
@Controller
@RequiredArgsConstructor
public class AuthorizationController {
    public AuthorizationService authorizationService;
    @GetMapping
    public String welcome(){

        return "welcome";
    }

    @GetMapping("/welcome")
    public String getLoginView() {
        return "login";
    }

    @PutMapping("/login")
    public String login(@ModelAttribute UserDTO dto) {
        boolean response = authorizationService.singup(dto);
        if (response) {
            return "redirect:/welcome";
        } else {
            return "redirect:/error";
        }


    }

    @PostMapping("/logout")
    public String logout(@ModelAttribute UserDTO dto) {
        authorizationService.login(dto);
        return "redirect:/login";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute UserDTO dto) {

        return "redirect:/welcome";
    }
}
