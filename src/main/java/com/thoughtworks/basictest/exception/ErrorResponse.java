package com.thoughtworks.basictest.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorResponse {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.getReasonPhrase()),
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.getReasonPhrase());


    private Integer code;
    private  String message;

     ErrorResponse(Integer code,String message){
        this.code = code;
        this.message = message;
    }
}
