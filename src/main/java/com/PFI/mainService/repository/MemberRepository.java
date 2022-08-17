package com.PFI.mainService.repository;

import com.PFI.mainService.domain.Member;

import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(int id);
    Optional<Member> findByEmail(String email);
    Member update(Member member, String newPassword);
    void delete(Member member);
}
