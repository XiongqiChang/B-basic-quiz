package com.thoughtworks.basictest.dto;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @Author: xqc
 * @Date: 2020/10/21 - 10 - 21 - 14:01
 * @Description: com.thoughtworks.basictest.dto
 * @version: 1.0
 */
@Data
@Builder
public class EducationDto {

    // TODO GTB-1: - 没有对year进行校验
    @NotNull
    private Long year;

    @NotNull
    @Length(min = 1,max = 256)
    private String title;

    @NotNull
    @Length(min = 1,max = 4096)
    private String description;
}
