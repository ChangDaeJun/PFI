package com.PFI.mainService.repository;

import com.PFI.mainService.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@SpringBootTest // 스프링 컨테이너 들고와서 테스트
@Transactional // 데이터베이스 터치 안하고 테스트
public class JdbcUserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void save() throws Exception{
        User user = new User();
        user.setEmail("test1234");
        user.setPassword("12345");

        int saveId = userRepository.save(user).getId();

        User result = userRepository.findByEmail(user.getEmail()).get();
        Assertions.assertEquals(user.getEmail(), result.getEmail());
        Assertions.assertEquals(user.getPassword(), result.getPassword());
        Assertions.assertEquals(saveId, result.getId());
    }

    @Test
    public void delete() throws Exception{
        User user = userRepository.findById(1).get();
        userRepository.delete(user);
        Optional<User> nullUser = userRepository.findById(1);
        Assertions.assertEquals(Optional.empty(), nullUser);
    }

    @Test
    public void findById() throws Exception{
        User result = userRepository.findById(1).get();
        Assertions.assertEquals("eowns1209@gmail.com", result.getEmail());
        Assertions.assertEquals("123456", result.getPassword());
    }

    @Test
    public void findByEmail() throws  Exception{
        User result = userRepository.findByEmail("eowns1209@gmail.com").get();
        Assertions.assertEquals(1, result.getId());
        Assertions.assertEquals("123456", result.getPassword());
    }

    @Test
    public void update() throws Exception{
        User user = userRepository.findById(1).get();
        userRepository.update(user, "isNewPassword");
        User updatedUser = userRepository.findById(1).get();
        Assertions.assertEquals("isNewPassword", updatedUser.getPassword());
    }
}
