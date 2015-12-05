package com.aly.exception;

/**
 * 无效的用户名或密码。
 */
public class InvalidUserInfoException extends RuntimeException{
    public static final String INVALID_USER_INFO_MESSAGE = "无效的用户名或密码";

    public InvalidUserInfoException() {
        super(INVALID_USER_INFO_MESSAGE);
    }
}
