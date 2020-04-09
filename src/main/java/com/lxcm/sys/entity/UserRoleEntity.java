package com.lxcm.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lxcm.common.model.BaseEntity;
import lombok.Data;

/**
 * @Classname UserRoleEntity
 * @Description TODO
 * @Date 2020-03-17 14:24
 * @Created by lx
 */
@Data
@TableName("sys_user_role")
public class UserRoleEntity extends BaseEntity {
    private static final long serialVersionUID = -1616563038042435826L;
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 角色名称
     * 和数据库中的列没有对应关系
     */
    private String name;
}
