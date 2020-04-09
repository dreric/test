package com.lxcm.common.exception;

/**
 * @Classname BizException
 * @Description TODO 自定义业务异常
 * @Date 2020-03-16 16:25
 * @Created by lx
 */
public class CizException extends RuntimeException {
    public CizException(String message) {
        super(message);
    }
}
