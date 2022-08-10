package com.PFI.mainService.service;

import com.PFI.mainService.domain.SecurityUserDetails;
import com.PFI.mainService.domain.User;
import com.PFI.mainService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserService implements UserDetailsService {
    public final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public int join(User user){
        //중복검사
        validateDuplicateUserEmail(user);
        // 가입
        return userRepository.save(user).getId();
        // 아이디 값 구해서 리턴
    }

    private void validateDuplicateUserEmail(User user) {
        userRepository.findByEmail(user.getEmail()).ifPresent(user1 -> {throw new IllegalStateException("이미 존재하는 회원입니다.");});
    }

    public void changePassword(User user, String newPassword){
        userRepository.update(user, newPassword);
    }

    public void detach(User user){
        userRepository.delete(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        if(!user.isPresent()) new UsernameNotFoundException(username);
        return new SecurityUserDetails(user.get());
    }
}