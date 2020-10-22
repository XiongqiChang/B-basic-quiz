package com.thoughtworks.basictest.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// TODO GTB-4: - 尽量不要加注释，何况这种自动生成的模板文档注释
/**
 * @Author: xqc
 * @Date: 2020/10/21 - 10 - 21 - 13:27
 * @Description: com.thoughtworks.basictest.controller
 * @version: 1.0
 */
// TODO GTB-4: - 无用的代码应该及时清理
@RestController
@CrossOrigin
public class HelloController {

    @GetMapping("/hello")
    public String sayHello(){
        return  "hello";
    }

}
