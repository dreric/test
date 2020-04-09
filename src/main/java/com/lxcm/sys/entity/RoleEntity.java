package com.lxcm.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lxcm.common.model.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * @Classname RoleEntity
 * @Description TODO 角色
 * @Date 2020-03-17 12:09
 * @Created by lx
 */
@Data
@TableName("sys_role")
public class RoleEntity extends BaseEntity {
    private static final long serialVersionUID = -1616563038042435826L;

    @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 备注
     */
    private String remark;
    /**
     * 部门ID
     */
    private Integer deptId;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;
}
