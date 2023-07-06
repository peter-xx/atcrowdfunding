package com.atguigu.crowd.exception;

/**
 * ClassName: AccessForbiddenException
 * Package: com.atguigu.crowd.exception
 * Description:
 *
 * @Author peter
 * @Create 2023/5/17 20:35
 * @Version 1.0
 */
public class AccessForbiddenException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public AccessForbiddenException() {
        super();
    }

    public AccessForbiddenException(String message, Throwable cause, boolean enableSuppression,
                                    boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public AccessForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessForbiddenException(String message) {
        super(message);
    }

    public AccessForbiddenException(Throwable cause) {
        super(cause);
    }

}
