package com.thoughtworks.basictest.service;

import com.thoughtworks.basictest.pojo.User;
import com.thoughtworks.basictest.repository.EducationRepository;
import com.thoughtworks.basictest.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private EducationRepository educationRepository;

    private User user;

    @BeforeEach
    void setUp(){
        userService = new UserService(userRepository,educationRepository);
        user = User.builder().id(123L).age(25L).name("这是一个测试").avatar("http://xxxx")
                .description("测试").build();
    }
    @Test
    void findById() {

        when(userRepository.findById(123L)).thenReturn(Optional.of(user));

        User byId = userService.findById(123L);
        assertThat(byId).isEqualTo(User.builder().id(123L).age(25L).name("这是一个测试").avatar("http://xxxx")
                .description("测试").build());
    }


}
