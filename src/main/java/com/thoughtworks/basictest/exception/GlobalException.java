package com.thoughtworks.basictest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException e){
        String message = e.getBindingResult().getFieldError().getDefaultMessage();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResult> handle(ConstraintViolationException ex){
        String message = "";
        for (ConstraintViolation<?> constraint : ex.getConstraintViolations()) {
            message = constraint.getMessage();
            break;
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:SSS");
        String errorDate = simpleDateFormat.format(date);
        System.out.println("出错了");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult(errorDate,HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.getReasonPhrase(),message));

    }
    @ExceptionHandler(UserNotExistException.class)
    public ResponseEntity<ErrorResponse> handle(UserNotExistException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getErrorResponse());
    }
}
