package com.thoughtworks.basictest.exception;

import lombok.Getter;

/**
 * @Author: xqc
 * @Date: 2020/10/21 - 10 - 21 - 14:30
 * @Description: com.thoughtworks.basictest.exception
 * @version: 1.0
 */
@Getter
public class UserNotExistException extends  RuntimeException {

    private ErrorResult errorResult;
    public UserNotExistException(ErrorResult errorResult){
        this.errorResult = errorResult;
    }
}
