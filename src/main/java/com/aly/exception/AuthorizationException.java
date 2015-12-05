package com.aly.exception;

/**
 * 授权相关异常.
 */
public class AuthorizationException extends RuntimeException{
    public static final String AUTHORIZATION_EXCEPTION = "您没有操作的权限";

    public AuthorizationException(String message) {
        super(message);
    }

    public AuthorizationException() {
        super(AUTHORIZATION_EXCEPTION);
    }
}
