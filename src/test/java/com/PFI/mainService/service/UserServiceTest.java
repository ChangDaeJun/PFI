package com.PFI.mainService.service;

import com.PFI.mainService.domain.User;
import com.PFI.mainService.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class UserServiceTest {

    public final UserRepository userRepository;
    public final UserService userService;

    @Autowired
    public UserServiceTest(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.userService = new UserService(userRepository);
    }

    @Test
    void 회원가입_일반() {
        //일반적인 아이디 생성
        User testUser = new User();
        testUser.setEmail("test1212");
        testUser.setPassword("1234");

        //아이디 가입
        int saveId = userService.join(testUser);

        //가입된 User 확인
        User findByIdUser = userRepository.findById(saveId).get();
        Assertions.assertEquals(findByIdUser.getEmail(), testUser.getEmail());
        Assertions.assertEquals(findByIdUser.getPassword(), testUser.getPassword());

        User findByEmailUser = userRepository.findByEmail(testUser.getEmail()).get();
        Assertions.assertEquals(saveId, testUser.getId());
        Assertions.assertEquals(findByIdUser.getPassword(), testUser.getPassword());
    }

    @Test
    void 회원가입_중복확인(){
        User testUser = new User();
        testUser.setEmail("test1212");
        testUser.setPassword("1234");

        User testUser1 = new User();
        testUser1.setEmail("test1212");
        testUser1.setPassword("12345656");

        userService.join(testUser);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> userService.join(testUser1));//예외가 발생해야 한다.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void 비밀번호변경_일반(){
        User testUser = new User();
        testUser.setEmail("test1212");
        testUser.setPassword("1234");

        int id = userService.join(testUser);
        testUser.setId(id);
        userService.changePassword(testUser, "changePassword");

        String newPassword = userRepository.findById(id).get().getPassword();
        assertThat("changePassword").isEqualTo(newPassword);
    }

    @Test
    void 회원탈퇴_일반() {
        User testUser = new User();
        testUser.setEmail("test1212");
        testUser.setPassword("1234");

        int id = userService.join(testUser);
        testUser.setId(id);

        userService.detach(testUser);
        Optional<User> nullUser = userRepository.findById(id);
        assertThat(nullUser).isEqualTo(Optional.empty());
    }
}
