package com.youthcon.start.config;

import com.youthcon.start.dto.ErrorResponseDto;
import com.youthcon.start.errors.DuplicateSendGiftException;
import com.youthcon.start.errors.ReviewNotFoundException;
import com.youthcon.start.errors.SendGiftInternalException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)

    @ExceptionHandler(DuplicateSendGiftException.class)
    public ErrorResponseDto handleDuplicateSendGiftException(DuplicateSendGiftException ex){
        return new ErrorResponseDto(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ReviewNotFoundException.class)
    public ErrorResponseDto handleReviewNotFoundException(ReviewNotFoundException ex){
        return new ErrorResponseDto(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SendGiftInternalException.class)
    public ErrorResponseDto handleSendGiftInternalException(SendGiftInternalException ex){
        return new ErrorResponseDto(ex.getMessage());
    }

}
