package com.PFI.mainService.service;

import com.PFI.mainService.domain.Member;
import com.PFI.mainService.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserService implements UserDetailsService {
    public final MemberRepository memberRepository;

    @Autowired
    public UserService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public int join(Member member){
        //중복검사
        validateDuplicateUserEmail(member);
        // 가입
        return memberRepository.save(member).getId();
        // 아이디 값 구해서 리턴
    }

    private void validateDuplicateUserEmail(Member member) {
        memberRepository.findByEmail(member.getUsername()).ifPresent(user1 -> {throw new IllegalStateException("이미 존재하는 회원입니다.");});
    }

    public void changePassword(Member member, String newPassword){
        memberRepository.update(member, newPassword);
    }

    public void detach(Member member){
        memberRepository.delete(member);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> user = memberRepository.findByEmail(username);
        if(!user.isPresent()) new UsernameNotFoundException(username);
        return user.get();
    }
}