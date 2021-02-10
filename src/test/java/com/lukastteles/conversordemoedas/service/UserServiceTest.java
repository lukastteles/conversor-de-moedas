package com.lukastteles.conversordemoedas.service;

import com.lukastteles.conversordemoedas.model.TO.UserRequestTO;
import com.lukastteles.conversordemoedas.model.entity.User;
import com.lukastteles.conversordemoedas.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveTest(){
        User user = userService.save(new UserRequestTO("test"));
        Assertions.assertThat(user.getId()).isNotNull();
        userRepository.delete(user);
    }

    @Test
    public void getAllTest(){
        User user = userService.save(new UserRequestTO("test"));
        List userList = userService.getAll();
        Assertions.assertThat(userList.size()).isEqualTo(1);
        userRepository.delete(user);
    }

}
