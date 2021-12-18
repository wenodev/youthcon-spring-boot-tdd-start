package com.youthcon.start.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class SendGiftInternalException extends RuntimeException {
    public SendGiftInternalException(String message) {
        super(message);
    }
}
