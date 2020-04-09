package com.lxcm.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxcm.common.exception.CizException;
import com.lxcm.common.model.TreeNode;
import com.lxcm.common.util.MD5Util;
import com.lxcm.common.util.TreeUtil;
import com.lxcm.sys.dao.UserDao;
import com.lxcm.sys.dao.UserRoleDao;
import com.lxcm.sys.entity.MenuEntity;
import com.lxcm.sys.entity.UserEntity;
import com.lxcm.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * @Classname UserServiceImpl
 * @Description TODO
 * @Date 2020-03-17 10:51
 * @Created by lx
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao,UserEntity> implements IUserService {

    @Autowired
    private UserDao dao;

    @Autowired
    private UserRoleDao userRoleDao;
    @Override
    public boolean save(UserEntity entity) {
        String username = entity.getUsername();
        String password = entity.getPassword();
        String email = entity.getEmail();
        String mobile = entity.getPhone();
        // 校验用户名不能为空
        if (StringUtils.isEmpty(username)) {
            throw new CizException("用户名不能为空");
        }
        // 校验密码不能为空
        if (StringUtils.isEmpty(password)) {
            throw new CizException("密码不能为空");
        }

        // 校验用户名是否被占用
        QueryWrapper queryUsername = new QueryWrapper();
        queryUsername.eq("username", username);
        if (this.count(queryUsername) > 0) {
            throw new CizException("用户名已存在");
        }
        // 校验手机号是否被占用
        if (!StringUtils.isEmpty(mobile)) {
            QueryWrapper queryMobile = new QueryWrapper();
            queryMobile.eq("phone", entity.getPhone());
            if (this.count(queryMobile) > 0) {
                throw new CizException("手机号已经被使用");
            }
        }
        if (!StringUtils.isEmpty(email)) {
            QueryWrapper queryEmail = new QueryWrapper();
            queryEmail.eq("email", entity.getEmail());
            if (this.count(queryEmail) > 0) {
                throw new CizException("邮箱已经被使用");
            }
        }
        String salt = UUID.randomUUID().toString().toUpperCase().replace("-", "");
        password = MD5Util.md5_private_salt(password, salt);
        entity.setPassword(password);
        entity.setSalt(salt);
        return super.save(entity);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public boolean removeById(Serializable id) {
        // 删除用户已经分配的角色信息
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        userRoleDao.delete(queryWrapper);
        return super.removeById(id);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        // 删除用户已经分配的角色信息
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.in("id", idList);
        userRoleDao.delete(queryWrapper);
        // delete from sys_user_role where user_id id (5,6);
        return super.removeByIds(idList);
    }

    @Override
    public boolean updateById(UserEntity entity) {
        String email = entity.getEmail();
        String mobile = entity.getPhone();

        // 不是当前用户使用了该手机号，表示手机号被占用
        if (!StringUtils.isEmpty(mobile)) {
            QueryWrapper queryMobile = new QueryWrapper();
            queryMobile.eq("phone", entity.getPhone());
            queryMobile.ne("id",entity.getId());
            // where mobile="13800000000" and user_id != 1;
            if (this.count(queryMobile) > 0) {
                throw new CizException("手机号已经被使用");
            }
        }
        if (!StringUtils.isEmpty(email)) {
            QueryWrapper queryEmail = new QueryWrapper();
            queryEmail.eq("email", entity.getEmail());
            queryEmail.ne("id",entity.getId());
            if (this.count(queryEmail) > 0) {
                throw new CizException("邮箱已经被使用");
            }
        }
        return super.updateById(entity);
    }

    @Override
    public List<TreeNode> getMenuTreeByUserId(Long id) {
        // 查询用户拥有的菜单资源
        List<MenuEntity> menuList = dao.selectMenuList(id);
        if(menuList.isEmpty()){
            return new ArrayList<TreeNode>();
        }

        // 存储父id是0的节点的id
        List<Long> nodeIds = new ArrayList<Long>();
        List<TreeNode> treeNodeList = new ArrayList<TreeNode>();
        for (MenuEntity menu : menuList) {
            TreeNode treeNode = new TreeNode();
            treeNode.setId(menu.getResourceId());
            treeNode.setName(menu.getName());
            treeNode.setParentId(menu.getParentId());
            treeNode.setUrl(menu.getUrl());
            treeNode.setIcon(menu.getIcon());
            treeNodeList.add(treeNode);
            if(treeNode.getParentId() == 0) {
                nodeIds.add(treeNode.getId());
            }
        }
        TreeUtil treeUtil = new TreeUtil(treeNodeList);
        List<TreeNode> treeNodeData = new ArrayList<TreeNode>();
        for (Long nodeId : nodeIds) {
            treeNodeData.add(treeUtil.generateTree(nodeId));
        }
        return treeNodeData;
    }
}
