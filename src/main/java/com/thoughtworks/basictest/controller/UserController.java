package com.thoughtworks.basictest.controller;

import com.thoughtworks.basictest.dto.UserDto;
import com.thoughtworks.basictest.pojo.User;
import com.thoughtworks.basictest.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author: xqc
 * @Date: 2020/10/21 - 10 - 21 - 14:13
 * @Description: com.thoughtworks.basictest.controller
 * @version: 1.0
 */
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }


    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable("id") Long id){
       return userService.getUser(id);
    }

    @PostMapping("/users")
    public Long addUser(@RequestBody  @Valid UserDto userDto){
       return userService.add(userDto);
    }

}
