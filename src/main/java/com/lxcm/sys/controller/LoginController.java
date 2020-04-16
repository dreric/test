package com.lxcm.sys.controller;

import com.lxcm.common.model.ResponseCode;
import com.lxcm.common.model.Results;
import com.lxcm.common.model.TreeNode;
import com.lxcm.sys.entity.UserEntity;
import com.lxcm.sys.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Classname LoginController
 * @Description 登录 菜单 首页 登出
 * @Date 2020-03-17 9:55
 * @author by lx
 */
@Controller
@RequestMapping
public class LoginController {

    @Autowired
    private IUserService userService;
    /**
     * 跳转到系统登录页面
     * @return
     */
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public Results login(String username, String password) {
        UsernamePasswordToken token =
                new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        return Results.success(ResponseCode.TABLE_SUCCESS);
    }
    /**
     * 跳转到系统初始化页面
     * @return
     */
    @GetMapping("/index")
    public String index(){
        return "index";
    }

    /**
     * 跳转到系统主页
     * @return
     */
    @GetMapping("/main")
    public String main(){
        return "main";
    }

    /**
     * 获取资源菜单树
     * @return
     */
    @GetMapping("/menu")
    @ResponseBody
    public Results menu(){
        Subject subject = SecurityUtils.getSubject();
        UserEntity user = (UserEntity) subject.getPrincipal();
        List<TreeNode> treeNodeList
                = userService.getMenuTreeByUserId(user.getId());
        return Results.success("请求成功",treeNodeList.size(),treeNodeList);
    }

    @GetMapping("/logout")
    public String logout(){
        // 销毁会话
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login";
    }
}
