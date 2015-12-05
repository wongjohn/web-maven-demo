package com.aly.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * 处理全局性地异常抛出事件.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    public String handleInvalidUserInfoException(InvalidUserInfoException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    public String handleAuthenticationException(AuthenticationException exception) {
        return exception.getMessage();
    }

}
