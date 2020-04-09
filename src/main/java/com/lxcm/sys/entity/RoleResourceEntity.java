package com.lxcm.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lxcm.common.model.BaseEntity;
import lombok.Data;

/**
 * @Classname RoleResourceEntity
 * @Description TODO
 * @Date 2020-03-17 14:22
 * @Created by lx
 */
@Data
@TableName("sys_role_resource")
public class RoleResourceEntity extends BaseEntity {

    private static final long serialVersionUID = -1616563038042435826L;
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long roleId;//角色ID
    private Long resourceId;//资源ID

    private String name; //资源名称
    private Long parentId; //上级资源的id
}
