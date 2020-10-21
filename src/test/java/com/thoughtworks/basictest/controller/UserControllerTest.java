package com.thoughtworks.basictest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.basictest.dto.EducationDto;
import com.thoughtworks.basictest.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
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
    }
}
