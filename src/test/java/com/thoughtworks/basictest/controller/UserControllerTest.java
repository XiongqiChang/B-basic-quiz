package com.thoughtworks.basictest.controller;

import com.thoughtworks.basictest.dto.EducationDto;
import com.thoughtworks.basictest.dto.UserDto;
import com.thoughtworks.basictest.exception.ErrorResponse;
import com.thoughtworks.basictest.exception.UserNotExistException;
import com.thoughtworks.basictest.pojo.User;
import com.thoughtworks.basictest.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@AutoConfigureJsonTesters
@AutoConfigureMockMvc
class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<Object> userJson;

    private UserDto firstUser;
    @BeforeEach
    void setUp() {
        firstUser = UserDto.builder().userId(123L).age(23L).avatar("http://xxxx.com")
                .description("这个是测试数据").name("xqc").build();
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(userService);
    }


    @Test
    void should_get_user_by_id() throws Exception {
        when(userService.findById(123L)).thenReturn(firstUser);
        mockMvc.perform(get("/users/123" ))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is("xqc")));
        verify(userService).findById(123L);
    }

    @Test
    void should_get_education_by_id() throws Exception {
        List<EducationDto> educationDtos = new ArrayList<>();
        educationDtos.add(EducationDto.builder().userId(1L).title("test1").description("这是测试").year(2005L).build());
        educationDtos.add(EducationDto.builder().userId(1L).title("test2").description("这是测试2").year(2006L).build());
        educationDtos.add(EducationDto.builder().userId(1L).title("test3").description("这是测试3").year(2007L).build());
        when(userService.getUserEducation(1L)).thenReturn(educationDtos);
        mockMvc.perform(get("/users/1/educations"))
                .andExpect(jsonPath("$",hasSize(educationDtos.size())))
                .andExpect(jsonPath("$[0].description",is("这是测试")))
                .andExpect(jsonPath("$[1].title",is("test2")))
                .andExpect(status().isOk());
    }

    @Test
    void should_get_exception_when_user_is_not_exist() throws Exception {
        when(userService.findById(12L)).thenThrow(new UserNotExistException(ErrorResponse.USER_NOT_FOUND));
        mockMvc.perform(get("/users/12"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(userService).findById(12);
    }

    @Test
    void should_add_user() throws Exception {
        UserDto userDto = UserDto.builder().age(23L).avatar("http:localhost:8282/dsfds").description("这是一个测试")
                .name("xqc").build();
        User user = User.builder().description(userDto.getDescription()).avatar(userDto.getAvatar())
                .name(userDto.getName()).age(userDto.getAge()).id(25L).build();

        when(userService.saveUser(userDto)).thenReturn(user);
        MockHttpServletRequestBuilder mockHttpServletRequest = post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson.write(userDto).getJson());
        MockHttpServletResponse mockHttpServletResponse = mockMvc.perform(mockHttpServletRequest)
                .andReturn()
                .getResponse();
        assertThat(mockHttpServletResponse.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        verify(userService).saveUser(userDto);
    }

    @Test
    void should_add_education() throws Exception {
        EducationDto educationDto = EducationDto.builder().title("这是个测试").year(2006L).description("我是在测试")
                .userId(1L).build();
        mockMvc.perform(post("/users/1/educations").contentType(MediaType.APPLICATION_JSON).content(userJson.write(educationDto).getJson()))
                .andExpect(status().isCreated());
        verify(userService,times(1)).addEducation(1L,educationDto);

    }

    @Test
    void should_update_user_by_id() throws Exception {

        when(userService.updateUser(1L,"zmt")).thenReturn(1);

        mockMvc.perform(patch("/users/1").param("name","zmt"))
                .andExpect(status().isOk());
        verify(userService,times(1)).updateUser(1L,"zmt");

    }
    /*@Test
    void getUserById() throws Exception {
        mockMvc.perform(get("/users/1" ))
                .andExpect(jsonPath("$.name", is("xqc")))
                .andExpect(jsonPath("$.age",is(20)))
                .andExpect(jsonPath("$.avatar",is("https://i.dlpng.com/static/png/6681915_preview.png")))
                .andExpect(jsonPath("$.description",is("Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit")))
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(status().isOk());
    }

    @Test
    void notGetUserById() throws Exception {
        mockMvc.perform(get("/users/15" ))
                .andExpect(status().isNotFound());
    }


    @Test
    void addUser() throws Exception {

        UserDto userDto = UserDto.builder().age(23L).avatar("http:localhost:8282/dsfds").description("这是一个测试")
                .name("xqc").build();
        String userDtoId = objectMapper.writeValueAsString(userDto);
        mockMvc.perform(post("/users").content(userDtoId).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void addUser_parameters_not_valid() throws Exception {

        UserDto userDto = UserDto.builder().avatar("http:localhost:8282/dsfds").description("这是一个测试")
                .name("zmt").build();
        String userDtoId = objectMapper.writeValueAsString(userDto);
        mockMvc.perform(post("/users").content(userDtoId).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


    @Test
    void  getEducationList() throws Exception {
        mockMvc.perform(get("/users/1/educations" ))
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].title",is("Eos, explicabo, nam, tenetur et ab eius deserunt aspernatur ipsum ducimus quibusdam quis voluptatibus.")))
                .andExpect(status().isOk());
    }

    @Test
    void should_post_education() throws Exception {
        EducationDto educationDto = EducationDto.builder().description("这个还是测试").title("测试").year(2005L).build();
        String education = objectMapper.writeValueAsString(educationDto);
        mockMvc.perform(post("/users/2/educations").content(education).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        mockMvc.perform(get("/users/2/educations" ))
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].title",is("测试")))
                .andExpect(status().isOk());
    }*/


}
