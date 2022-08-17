package com.PFI.mainService.controller;

import com.PFI.mainService.domain.Member;
import com.PFI.mainService.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/users/create")
    public String createForm(){
        return "/users/createUserForm";
    }

    @PostMapping("/users/create")
    public String create(UserForm userForm){
        Member member = new Member();
        member.setUsername(userForm.getEmail());
        member.setPassword(passwordEncoder.encode(userForm.getPassword()));
        memberRepository.save(member);
        return "/home";
    }
    @GetMapping("/users/login")
    public String login(){
        return "users/login";
    }

}
