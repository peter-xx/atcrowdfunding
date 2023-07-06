package com.atguigu.crowd.exception;

/**
 * ClassName: LoginFailedException
 * Package: com.atguigu.crowd.exception
 * Description:
 *
 * @Author peter
 * @Create 2023/5/17 14:46
 * @Version 1.0
 */
public class LoginFailedException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public LoginFailedException() {
        super();
    }
    public LoginFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    public LoginFailedException(String message, Throwable cause) {
        super(message, cause);
    }
    public LoginFailedException(String message) {
        super(message);
    }
    public LoginFailedException(Throwable cause) {
        super(cause);
    }
}
