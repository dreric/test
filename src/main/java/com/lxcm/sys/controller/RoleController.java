package com.lxcm.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lxcm.common.model.Results;
import com.lxcm.sys.entity.RoleEntity;
import com.lxcm.sys.service.IRoleResourceService;
import com.lxcm.sys.service.IRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Classname RoleController
 * @Description TODO
 * @Date 2020-03-31 15:52
 * @Created by lx
 */
@Controller
@RequestMapping("/sys/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @Autowired
    private IRoleResourceService roleResourceService;

    /**
     * 跳转到列表
     *
     * @return
     */
    @GetMapping
    @RequiresPermissions("sys:role:list")
    public String list() {
        return "sys/role/list";
    }

    /**
     * 获取列表数据
     */
    @GetMapping("/data")
    @ResponseBody
    @RequiresPermissions("sys:role:list")
    public Results data(String roleName, Page<RoleEntity> page) {
        //1.构造查询条件构造器
        QueryWrapper queryWrapper = new QueryWrapper();
        if (!StringUtils.isEmpty(roleName)) {
            queryWrapper.like("name", roleName);
        }
        //2.分页查询
        roleService.page(page, queryWrapper);
        //3.返回分页数据
        return Results.success(page);
    }
}
