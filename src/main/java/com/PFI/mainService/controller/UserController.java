package com.PFI.mainService.controller;

import com.PFI.mainService.domain.User;
import com.PFI.mainService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/users/create")
    public String createForm(){
        System.out.println("!!!!");
        return "/users/createUserForm";
    }
    @PostMapping("/users/create")
    public String create(UserForm userForm){
        System.out.println("@@@@@");
        User user = new User();
        user.setEmail(userForm.getEmail());
        user.setPassword(passwordEncoder.encode(userForm.getPassword()));
        userRepository.save(user);
        return "/home";
    }
    @GetMapping("/users/login")
    public String login(){
        return "users/login";
    }

}
