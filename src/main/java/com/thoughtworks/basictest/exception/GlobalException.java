package com.thoughtworks.basictest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: xqc
 * @Date: 2020/10/21 - 10 - 21 - 14:35
 * @Description: com.thoughtworks.basictest.exception
 * @version: 1.0
 */
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler({MethodArgumentNotValidException.class,ConstraintViolationException.class})
    public ResponseEntity<ErrorResult> handle(Exception e){
        String message = "参数不对";
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:SSS'Z'");
        String errorDate = simpleDateFormat.format(date);
        ErrorResult error = new ErrorResult(errorDate,HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.getReasonPhrase(),message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(UserNotExistException.class)
    public ResponseEntity<ErrorResult> handle(UserNotExistException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getErrorResult());
    }




}
