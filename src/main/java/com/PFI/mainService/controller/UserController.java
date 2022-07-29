package com.PFI.mainService.controller;

import com.PFI.mainService.domain.User;
import com.PFI.mainService.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @GetMapping("/users/create")
    public String createForm(){
        return "users/createUserForm";
    }
    @PostMapping("/users/create")
    public String create(UserForm userForm){

       return "redirect:/";
    }
}
