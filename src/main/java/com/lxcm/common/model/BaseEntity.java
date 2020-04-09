package com.lxcm.common.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author by lx
 * @Classname BaseEntity
 * @Description 基础模型
 * @Date 2020-01-11 10:28
 * @Created by lx
 */
@Data
@MappedSuperclass
public class BaseEntity<ID extends Serializable> implements Serializable {
    private static final long serialVersionUID = -1616563038042435826L;

}
