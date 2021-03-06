package com.thoughtworks.basictest.controller;

import com.thoughtworks.basictest.dto.EducationDto;
import com.thoughtworks.basictest.dto.UserDto;
import com.thoughtworks.basictest.pojo.Education;
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
// TODO GTB-3: - 建议使用类级别的@RequestMapping提取路径前缀
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }


    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable("id") Long id){
       return userService.getUser(id);
    }

    @GetMapping("/users/{id}/educations")
    public List<Education> educationList(@PathVariable("id") Long id){
         return   userService.getUserEducations(id);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    // TODO GTB-3: - 违反Restful实践，Post接口应返回创建成功的对象
    public Long addUser(@RequestBody  @Valid UserDto userDto){
       return userService.add(userDto);
    }

    @PostMapping("/users/{id}/educations")
    @ResponseStatus(HttpStatus.CREATED)
    // TODO GTB-4: - 注意方法名
    public void  addUser(@PathVariable("id") Long id,
                        @RequestBody  @Valid EducationDto educationDto){
           userService.addEducation(id,educationDto);
    }
}
