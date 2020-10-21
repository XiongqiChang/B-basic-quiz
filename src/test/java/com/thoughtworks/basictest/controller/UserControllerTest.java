package com.thoughtworks.basictest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.basictest.dto.UserDto;
import com.thoughtworks.basictest.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;


    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void getUserById() throws Exception {
        mockMvc.perform(get("/users/1" ))
               /* .andExpect(jsonPath("$.name", is("xqc")))
                .andExpect(jsonPath("$.age",is(20)))
                .andExpect(jsonPath("$.avatar",is("https://i.dlpng.com/static/png/6681915_preview.png")))
                .andExpect(jsonPath("$.description",is("Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit")))
                .andExpect(jsonPath("$.id",is(1)))*/
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
                .andExpect(status().isOk());
    }

    @Test
    void addUser_parameters_not_valid() throws Exception {

        UserDto userDto = UserDto.builder().avatar("http:localhost:8282/dsfds").description("这是一个测试")
                .name("zmt").build();
        String userDtoId = objectMapper.writeValueAsString(userDto);
        mockMvc.perform(post("/users").content(userDtoId).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
