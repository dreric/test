package com.lxcm.common.model;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Classname Results
 * @Description 返回 结构数据 封装 基础类
 * @Date 2020-01-19 17:54
 * @Created by lx
 */
@Data
public class Results<T> implements Serializable {
    Integer count; //数据数量
    Integer code; //代码
    String msg;//信息
    List<T> data;//返回数据
    T only;//任何类型条件

    public Results(){}

    public Results(Integer code, String msg){
        super();
        this.code = code;
        this.msg = msg;
    }
    public Results(Integer code, String msg, T only, Integer count, List<T> data){
        this.code=code;
        this.msg = msg;
        this.only = only;
        this.count = count;
        this.data =data;
    }

    /**
     * 无数据传输的 成功返回
     * @return
     */
    public static <T> Results<T> success(){
        return new Results<T>(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMessage());
    }
    /**
     * 自定义返回信息的 成功返回
     * @param msg
     * @return
     */
    public static <T> Results<T> success(String msg){
        return new Results<T>(ResponseCode.SUCCESS.getCode(),msg);
    }
    /**
     * 自定义返回代码和信息的 成功返回
     * @param resultCode
     * @return
     */
    public static <T> Results<T> success(ResponseCode resultCode){
        return new Results<T>(resultCode.getCode(),resultCode.getMessage());
    }

    /**
     * 单条数据传输的 成功返回
     * @param only
     * @return
     */
    public static <T> Results<T> success(T only){
        return new Results<T>(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMessage(),only,0,null);
    }
    /**
     * 单条数据传输及返回信息的 成功返回
     * @param msg
     * @param only
     * @return
     */
    public static <T> Results<T> success(String msg, T only){
        return new Results<T>(ResponseCode.SUCCESS.getCode(),msg,only,0,null);
    }
    /**
     * 单条数据传输及返回信息的 成功返回
     * @param resultCode
     * @param only
     * @return
     */
    public static <T> Results<T> success(ResponseCode resultCode,T only){
        return new Results<T>(resultCode.getCode(),resultCode.getMessage(),only,0,null);
    }
    /**
     * 分页传输的 成功返回
     * @param count
     * @param data
     * @return
     */
    public static <T> Results<T> success(Integer count, List<T> data){
        return new Results<T>(ResponseCode.TABLE_SUCCESS.getCode(),ResponseCode.SUCCESS.getMessage(),null,count,data);
    }

    public static <T> Results<T> success(String msg, Integer count, List<T> data){
        return new Results<T>(ResponseCode.TABLE_SUCCESS.getCode(),msg,null,count,data);
    }

    public static <T> Results<T> success(ResponseCode resultCode, Integer count, List<T> data){
        return new Results<T>(resultCode.getCode(),resultCode.getMessage(),null,count,data);
    }

    public static <T> Results<T> success(Page<T> page) {
        // 获取当前页的数据集
        List<T> rows = page.getRecords();
        // 获取满足条件的总记录数
        Long total = page.getTotal();
        return Results.success(Integer.valueOf(total.toString()),rows);
    }
    /**
     * 无数据传输的 失败返回
     * @return
     */
    public static <T> Results<T> failure(){
        return new Results<T>(ResponseCode.FATL.getCode(),ResponseCode.FATL.getMessage());
    }
    /**
     * 自定义返回信息的 失败返回
     * @param msg
     * @return
     */
    public static <T> Results<T> failure(Integer code, String msg){
        return new Results<T>(code,msg);
    }
    /**
     * 自定义返回代码和信息的 失败返回
     * @param resultCode
     * @return
     */
    public static <T> Results<T> failure(ResponseCode resultCode){
        return new Results<T>(resultCode.getCode(),resultCode.getMessage());
    }

    public static Results ok(){
        return new Results();
    }
}
