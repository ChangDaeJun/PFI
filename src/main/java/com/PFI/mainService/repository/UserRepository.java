package com.PFI.mainService.repository;

import com.PFI.mainService.domain.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(int id);
    Optional<User> findByEmail(String email);
    User update(User user, String newPassword);
    void delete(User user);
}
