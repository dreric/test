package com.lxcm.sys.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Classname Menu
 * @Description TODO 菜单对象
 * @Date 2020-03-17 11:58
 * @Created by lx
 */
@Data
public class MenuEntity implements Serializable {

    private Long resourceId;
    private Long parentId;
    private String name;
    private String url;
    private String icon;
}
