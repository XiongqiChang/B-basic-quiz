package com.thoughtworks.basictest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO GTB-4: * 分包比较合理
// TODO GTB-3: * 使用了三层架构 
// TODO GTB-4: * git有小步提交
// TODO GTB-3: * 使用了Lombok
// TODO GTB-3: * 对Java8 Stream有不错的使用
// TODO GTB-3: * 建议再熟悉下Optional的使用
// TODO GTB-1: * 功能都完成了，good
// TODO GTB-2: * 有Controller层的测试，且测试了大部分逻辑
@SpringBootApplication
public class BasictestApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasictestApplication.class, args);
	}

}
