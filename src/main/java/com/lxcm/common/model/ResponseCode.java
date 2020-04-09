package com.lxcm.common.model;

/**
 * @Classname ResponseCode
 * @Description 返回结果 状态封装
 * @Date 2020-03-02 16:29
 * @Created by lx
 */
public enum ResponseCode {
    //公共请求消息
    SUCCESS(200,"请求成功"),
    TABLE_SUCCESS(0,"请求成功"),
    FATL(500,"请求失败"),
    PARAMETER_MISSING(600,"参数缺失"),
    UNAUTHORIZED(401,"未授权"),

    UPLOUD_FATL(801,"上传失败"),
    ;
    private Integer code;
    private String message;

    ResponseCode(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static String getMessage(String name){
        for(ResponseCode item: ResponseCode.values()){
            if(item.name().equals(name)){
                return item.message;
            }
        }
        return null;
    }

    public static Integer getCode(String name){
        for(ResponseCode item: ResponseCode.values()){
            if(item.name().equals(name)){
                return item.code;
            }
        }
        return null;
    }
}
