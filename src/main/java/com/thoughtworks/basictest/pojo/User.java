package com.thoughtworks.basictest.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: xqc
 * @Date: 2020/10/21 - 10 - 21 - 13:56
 * @Description: com.thoughtworks.basictest.pojo
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private Long  id;

    private  String name;

    private Long age;

    private String avatar;

    private String description;

}
