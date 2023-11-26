package com.ua.controller;

import com.ua.model.User;
import com.ua.serves.authorization.AuthorizationService;
import com.ua.transport.dto.UserIncomeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthorizationController {

private final AuthorizationService authorizationService;
  @GetMapping("/welcome")
    public String getLoginView(){
      return "login";
    }
  @GetMapping("/welcome")
  public String getWelcomeView(){
    return "welcome";
  }
    @PutMapping("/login")
    public String login(@ModelAttribute UserIncomeDTO dto){
      boolean response = authorizationService.singup(dto);
      if(response){
      return "redirect:/welcome";
    }else {
      return "redirect:/error";
    }


  }
  @PostMapping("/logout")
  public String logout(@ModelAttribute UserIncomeDTO dto){
     authorizationService.login(dto);
    return "redirect:/login";
  }
  @PostMapping ("/signup")
  public String signup(@ModelAttribute UserIncomeDTO dto){

    return "redirect:/welcome";
  }
}
