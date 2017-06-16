package com.apssouza.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Data not found exception. It will return the Not found HTTP status code
 *
 * @author Apssouza
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(String msg) {
        super(msg);
    }
}
