package com.thoughtworks.basictest.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: xqc
 * @Date: 2020/10/21 - 10 - 21 - 14:02
 * @Description: com.thoughtworks.basictest.exception
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResult {

    private String timestamp;

    private Integer status;

    private String error;

    private String message;
}
