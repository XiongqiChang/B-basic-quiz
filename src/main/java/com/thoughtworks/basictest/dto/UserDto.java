package com.thoughtworks.basictest.dto;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @Author: xqc
 * @Date: 2020/10/21 - 10 - 21 - 14:00
 * @Description: com.thoughtworks.basictest.dto
 * @version: 1.0
 */
@Data
@Builder
public class UserDto {


    @Length(min = 1,max = 128,message = "长度不符合规范")
    @NotNull(message = "名称不能为空")
    private  String name;


    @Min(value = 16,message = "最小年龄为16岁")
    @NotNull(message = "年龄不能为空")
    private Long age;

    @Length(min = 8,max = 512,message = "长度不符合规范")
    @NotNull(message = "头像不能为空")
    private String avatar;


    @Length(max = 1024, message = "长度不符合规范")
    private String description;
}
