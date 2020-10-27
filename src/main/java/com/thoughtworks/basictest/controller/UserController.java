package com.thoughtworks.basictest.controller;

import com.thoughtworks.basictest.dto.EducationDto;
import com.thoughtworks.basictest.dto.UserDto;
import com.thoughtworks.basictest.pojo.User;
import com.thoughtworks.basictest.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author: xqc
 * @Date: 2020/10/21 - 10 - 21 - 14:13
 * @Description: com.thoughtworks.basictest.controller
 * @version: 1.0
 */
@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable("id") Long id){
        return userService.findById(id);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody @Valid UserDto userDto){
      return   userService.saveUser(userDto);
    }

    @GetMapping()
    public List<UserDto> getUserList(@RequestParam Integer pageIndex,
                                     @RequestParam Integer pageSize){
        return userService.getUserList(pageIndex,pageSize);
    }

    @GetMapping("/{id}/educations")
    public List<EducationDto> getEducationList(@PathVariable Long id){
        return  userService.getUserEducation(id);
    }

    @PostMapping("/{id}/educations")
    @ResponseStatus(HttpStatus.CREATED)
    public void addEducation(@PathVariable Long id, @RequestBody @Valid EducationDto educationDto){
        userService.addEducation(id,educationDto);
    }

    @PatchMapping("/{id}")
    public int updateUsername(@PathVariable("id") Long id, @RequestParam String  name){
       return userService.updateUser(id,name);
    }
}
