package com.thoughtworks.basictest.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: xqc
 * @Date: 2020/10/21 - 10 - 21 - 13:58
 * @Description: com.thoughtworks.basictest.pojo
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Education {

    private Long userId;

    private Long year;

    private String title;

    private String description;
}
