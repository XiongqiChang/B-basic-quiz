package com.thoughtworks.basictest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: xqc
 * @Date: 2020/10/21 - 10 - 21 - 14:35
 * @Description: com.thoughtworks.basictest.exception
 * @version: 1.0
 */
@ControllerAdvice
public class GlobalException {

    @ExceptionHandler({MethodArgumentNotValidException.class,ConstraintViolationException.class})
    // TODO GTB-4: - 未使用的参数e
    public ResponseEntity<ErrorResult> handle(Exception e){
        // TODO GTB-4: - 一般需要明确是哪个字段不对
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
