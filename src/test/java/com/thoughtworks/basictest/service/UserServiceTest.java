package com.thoughtworks.basictest.service;

import com.thoughtworks.basictest.dto.EducationDto;
import com.thoughtworks.basictest.dto.UserDto;
import com.thoughtworks.basictest.exception.UserNotExistException;
import com.thoughtworks.basictest.pojo.Education;
import com.thoughtworks.basictest.pojo.User;
import com.thoughtworks.basictest.repository.EducationRepository;
import com.thoughtworks.basictest.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


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
        UserDto byId = userService.findById(123L);
        assertThat(byId).isEqualTo(User.builder().id(123L).age(25L).name("这是一个测试").avatar("http://xxxx")
                .description("测试").build());
    }


    @Test
    void should_return_exception_when_id_is_not_found(){
        when(userRepository.findById(223L)).thenReturn(Optional.empty());
        UserNotExistException thrownException = assertThrows(UserNotExistException.class, () -> {
            userService.findById(223L);
        });
        assertThat(thrownException.getErrorResponse().getCode()).isEqualTo(404);
    }

    @Test
    void should_return_education_by_id(){

        List<Education> educationList = new ArrayList<>();
        educationList.add(Education.builder().user(User.builder().description("测试").avatar("ce").name("xqc").id(1L).age(25L).build())
                .title("test1").description("这是测试").year(2005L).build());
        educationList.add(Education.builder().user(User.builder().description("测试").avatar("ce").name("xqc").id(1L).age(25L).build())
                .title("test1").description("这是测试").year(2005L).build());
        educationList.add(Education.builder().user(User.builder().description("测试").avatar("ce").name("xqc").id(1L).age(25L).build())
                .title("test1").description("这是测试").year(2005L).build());
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(educationRepository.findByUserId(1L)).thenReturn(educationList);
        List<EducationDto> userEducation = userService.getUserEducation(1L);
        assertThat(userEducation.size()).isEqualTo(3);
    }

    @Test
    void should_save_user(){

       UserDto firstUser = UserDto.builder().userId(123L).age(23L).avatar("http://xxxx.com")
                .description("这个是测试数据").name("xqc").build();
        userService.saveUser(firstUser);
        verify(userRepository,times(1)).save(any());

    }


}
