package com.thoughtworks.basictest.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @Author: xqc
 * @Date: 2020/10/21 - 10 - 21 - 14:01
 * @Description: com.thoughtworks.basictest.dto
 * @version: 1.0
 */
@Data
public class EducationDto {

    private Long year;

    @Length(min = 1,max = 256)
    private String title;

    @Length(min = 1,max = 4096)
    private String description;
}
