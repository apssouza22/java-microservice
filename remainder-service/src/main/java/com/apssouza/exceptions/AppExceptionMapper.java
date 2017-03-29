package com.apssouza.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author airhacks.com
 */
@ControllerAdvice
public class AppExceptionMapper {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void userNotFoundExceptionHandler( ) {
        
    }

}
