package com.lxcm.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lxcm.common.model.Results;
import com.lxcm.sys.entity.RoleEntity;
import com.lxcm.sys.entity.UserEntity;
import com.lxcm.sys.entity.UserRoleEntity;
import com.lxcm.sys.service.IUserRoleService;
import com.lxcm.sys.service.IUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Classname UserController
 * @Description TODO
 * @Date 2020-03-19 14:35
 * @Created by lx
 */
@Controller
@RequestMapping("/sys/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserRoleService userRoleService;
    /**
     * 跳转到列表页面
     *
     * @return
     */
    @GetMapping
    @RequiresPermissions("sys:user:list")
    public String list() {
        return "sys/user/list";
    }

    /**
     * 获取列表数据
     *
     * @param username
     * @return
     */
    @GetMapping("/data")
    @ResponseBody
    @RequiresPermissions("sys:user:list")
    public Results data(String username, Page<UserEntity> page) {

        QueryWrapper<UserEntity> param = new QueryWrapper<UserEntity>();
        if (!StringUtils.isEmpty(username)) {
            param.like("username", username);
        }
        userService.page(page,param);
        return Results.success(page);
    }
    /**
     * 跳转到新增页面
     * @return
     */
    @RequiresPermissions("sys:user:add")
    @GetMapping("/add")
    public String add() {
        return "sys/user/add";
    }
    /**
     * 新增
     * @param user
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    @RequiresPermissions("sys:user:add")
    public Results add(UserEntity user) {
        userService.save(user);
        return Results.success();
    }
    /**
     * 跳转到更新页面
     *
     * @return
     */
    @GetMapping("/update/{id}")
    @RequiresPermissions("sys:user:update")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "sys/user/edit";
    }

    /**
     * 更新
     *
     * @param user
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    @RequiresPermissions("sys:user:update")
    public Results update(UserEntity user) {
        userService.updateById(user);
        return Results.success();
    }
    /**
     * 删除
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    @ResponseBody
    @RequiresPermissions("sys:user:delete")
    public Results delete(@PathVariable Long id) {
        userService.removeById(id);
        return Results.success();
    }
    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping("/deletebatch")
    @ResponseBody
    @RequiresPermissions("sys:user:delete")
    public Results deletebatch(@RequestBody List<Long> ids) {
        userService.removeByIds(ids);
        return Results.success();
    }
    /**
     * 跳转到给用户分配角色的页面
     *
     * @param userId
     * @return
     */
    @GetMapping("/assign/role/{userId}")
    @RequiresPermissions("sys:user:assign:role")
    public String assignRole(@PathVariable Long userId, Model model) {
        List<UserRoleEntity> userRoleList = userRoleService.getByUserId(userId);
        model.addAttribute("userRoleList", userRoleList);
        model.addAttribute("userId", userId);
        return "sys/assign_role";
    }

    /**
     * 给用户分配角色
     *
     * @param userId
     * @return
     */
    @PostMapping("/assign/role")
    @ResponseBody
    @RequiresPermissions("sys:user:assign:role")
    public Results assignRole(Long userId,
                        @RequestParam(name = "roleId",required = false) List<Long> roleIdList) {
        userRoleService.save(userId,roleIdList);
        return Results.success();
    }
    /**
     * 状态修改 启用 禁用
     * @param id
     * @return
     */
    @PostMapping("/status")
    @ResponseBody
    @RequiresPermissions("sys:user:status")
    public Results stutas(Long id) {
        UserEntity user =userService.getById(id);
        if(user.getStatus()==UserEntity.Status.VALID){
            user.setStatus(UserEntity.Status.DISABLED);
        }else{
            user.setStatus(UserEntity.Status.VALID);
        }
        userService.updateById(user);
        return Results.success();
    }
}
