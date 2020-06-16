package com.lxcm.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lxcm.common.model.Results;
import com.lxcm.sys.entity.ResourceEntity;
import com.lxcm.sys.entity.UserEntity;
import com.lxcm.sys.service.IResourceService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Classname ResourceController
 * @Description 资源
 * @Date 2020-06-15 17:35
 * @Created by lx
 */
@Controller
@RequestMapping("/sys/resource")
public class ResourceController {

    @Autowired
    IResourceService resourceService;
    /**
     * 跳转到列表
     *
     * @return
     */
    @GetMapping
    @RequiresPermissions("sys:resource:list")
    public String list() {
        return "sys/resource/list";
    }

    /**
     * 获取列表数据
     */
    @GetMapping("/data")
    @ResponseBody
    @RequiresPermissions("sys:resource:list")
    public Results data(String name) {

        QueryWrapper queryWrapper = new QueryWrapper();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }
        List<ResourceEntity> lst = resourceService.list(queryWrapper);
        return Results.success(lst.size(),lst);
    }

}
