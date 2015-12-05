package com.aly.exception;

/**
 * 验证用户Token信息时，如果信息不通过，随即抛出此异常。
 */
public class AuthenticationException extends RuntimeException{
    public static final String VALIDATION_FAILED_MESSAGE = "无效的通行证";

    public AuthenticationException() {
        super(VALIDATION_FAILED_MESSAGE);
    }

    public AuthenticationException(Exception e) {
        super(VALIDATION_FAILED_MESSAGE, e);
    }

    public AuthenticationException(String message) {
        super(message);
    }
}
