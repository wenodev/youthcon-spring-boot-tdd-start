package com.youthcon.start.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicateSendGiftException extends RuntimeException {
    public DuplicateSendGiftException(String message) {
        super(message);
    }
}
