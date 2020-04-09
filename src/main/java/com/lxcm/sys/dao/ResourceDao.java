package com.lxcm.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxcm.sys.entity.ResourceEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Classname ResourceDao
 * @Description TODO 资源
 * @Date 2020-03-19 10:03
 * @Created by lx
 */
@Mapper
public interface ResourceDao  extends BaseMapper<ResourceEntity> {
}
