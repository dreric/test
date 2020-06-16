package com.lxcm.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lxcm.common.model.Results;
import com.lxcm.sys.entity.RoleEntity;
import com.lxcm.sys.entity.UserEntity;
import com.lxcm.sys.service.IRoleResourceService;
import com.lxcm.sys.service.IRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * 跳转到新增页面
     * @return
     */
    @RequiresPermissions("sys:role:add")
    @GetMapping("/add")
    public String add() {
        return "sys/role/add";
    }

    /**
     * 新增
     * @param role
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    @RequiresPermissions("sys:role:add")
    public Results add(RoleEntity role) {
        roleService.save(role);
        return Results.success();
    }
    /**
     * 跳转到更新页面
     *
     * @return
     */
    @GetMapping("/update/{id}")
    @RequiresPermissions("sys:role:update")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("role", roleService.getById(id));
        return "sys/user/edit";
    }

    /**
     * 更新
     *
     * @param role
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    @RequiresPermissions("sys:role:update")
    public Results update(RoleEntity role) {
        roleService.updateById(role);
        return Results.success();
    }
    /**
     * 删除
     * @param roleId
     * @return
     */
    @GetMapping("/delete/{roleId}")
    @ResponseBody
    @RequiresPermissions("sys:role:delete")
    public Results delete(@PathVariable Long roleId) {
        roleService.removeById(roleId);
        return Results.success();
    }
    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping("/deletebatch")
    @ResponseBody
    @RequiresPermissions("sys:role:delete")
    public Results deletebatch(@RequestBody List<Long> ids) {
        roleService.removeByIds(ids);
        return Results.success();
    }
}
