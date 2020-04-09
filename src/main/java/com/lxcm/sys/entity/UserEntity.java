package com.lxcm.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lxcm.common.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import java.util.Date;

/**
 * @Classname UserEntity
 * @Description TODO 用户
 * @Date 2020-03-15 12:55
 * @Created by lx
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@TableName("sys_user")
public class UserEntity extends BaseEntity<Long> {
    private static final long    serialVersionUID = -1616563038042435826L;
    @TableId(value="id",type= IdType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String headImgUrl;
    private Long deptId;
    private String phone;
    private String salt;
    private String telephone;
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private Integer sex;
    private Integer status=1;
    private String intro;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;
    public interface Status{
        int DISABLED =0;
        int VALID =1;
        int LOCKED =2;
    }
}
