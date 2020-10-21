package com.thoughtworks.basictest.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xqc
 * @Date: 2020/10/21 - 10 - 21 - 13:27
 * @Description: com.thoughtworks.basictest.controller
 * @version: 1.0
 */
@RestController
@CrossOrigin
public class HelloController {

    @GetMapping("/hello")
    public String sayHello(){
        return  "hello";
    }

}
