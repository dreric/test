package com.lxcm.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lxcm.common.model.BaseEntity;
import lombok.Data;

/**
 * @Classname ResourceEntity
 * @Description TODO 资源
 * @Date 2020-03-17 12:05
 * @Created by lx
 */
@Data
@TableName("sys_resource")
public class ResourceEntity extends BaseEntity {
    private static final long serialVersionUID = -1616563038042435826L;

    @TableId(value = "resource_id", type = IdType.AUTO)
    private Long resourceId;
    /**
     * 父资源ID，一级资源ID为0
     */
    private Long parentId;
    /**
     * 资源名称
     */
    private String name;

    private String path;
    /**
     * 菜单URL
     */
    private String url;
    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
    private String permission;
    /**
     * 类型  -1：根目录  0：目录   1：菜单   2：按钮
     */
    private Integer type;
    /**
     * 资源图标
     */
    private String icon;
    /**
     * 排序
     */
    private Integer orderNum;
}
