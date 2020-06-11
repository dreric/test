package com.lxcm.sys.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lxcm.common.util.MD5Util;
import com.lxcm.sys.dao.UserDao;
import com.lxcm.sys.entity.UserEntity;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * @Classname ShiroRealm
 * @Description TODO 重写登录认证
 * @Date 2020-03-15 12:46
 * @Created by lx
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserDao dao;
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String password = new String(token.getPassword());


        // 3.根据用户名去DB查询对应的用户信息
        QueryWrapper<UserEntity> param = new QueryWrapper<UserEntity>();
        param.eq("username",username);
        UserEntity user = dao.selectOne(param);
        if(user==null){
            QueryWrapper<UserEntity> param2 = new QueryWrapper<UserEntity>();
            param2.eq("email",username);
            UserEntity byUserEmail = dao.selectOne(param2);
            if(byUserEmail==null){
                QueryWrapper<UserEntity> param3 = new QueryWrapper<UserEntity>();
                param3.eq("phone",username);
                UserEntity byUserPhone = dao.selectOne(param3);
                if(byUserPhone==null){
                    throw new UnknownAccountException("账号不存在");
                }else{
                    password = MD5Util.md5_private_salt(password,byUserPhone.getSalt());
                    // 两个密码的密文进行比对
                    if (!byUserPhone.getPassword().equals(password)) {
                        throw new CredentialsException("密码错误");
                    }
                    if (byUserPhone.getStatus() == 0) {
                        throw new DisabledAccountException("账号被禁用");
                    }
                    if (byUserPhone.getStatus() == 2) {
                        throw new LockedAccountException("账号被锁定");
                    }
                    System.out.println("认证成功...");
                    // 创建简单认证信息对象
                    SimpleAuthenticationInfo info =
                            new SimpleAuthenticationInfo(byUserPhone, token.getCredentials(), getName());
                    return info;
                }
            }else{
                password = MD5Util.md5_private_salt(password,byUserEmail.getSalt());
                // 两个密码的密文进行比对
                if (!byUserEmail.getPassword().equals(password)) {
                    throw new CredentialsException("密码错误");
                }
                if (byUserEmail.getStatus() == 0) {
                    throw new DisabledAccountException("账号被禁用");
                }
                if (byUserEmail.getStatus() == 2) {
                    throw new LockedAccountException("账号被锁定");
                }
                System.out.println("认证成功...");
                // 创建简单认证信息对象
                SimpleAuthenticationInfo info =
                        new SimpleAuthenticationInfo(byUserEmail, token.getCredentials(), getName());
                return info;
            }
        }else{
            password = MD5Util.md5_private_salt(password,user.getSalt());
            // 两个密码的密文进行比对
            if (!user.getPassword().equals(password)) {
                throw new CredentialsException("密码错误");
            }
            if (user.getStatus() == 0) {
                throw new DisabledAccountException("账号被禁用");
            }
            if (user.getStatus() == 2) {
                throw new LockedAccountException("账号被锁定");
            }
            System.out.println("认证成功...");
            // 创建简单认证信息对象
            SimpleAuthenticationInfo info =
                    new SimpleAuthenticationInfo(user, token.getCredentials(), getName());
            return info;
        }
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        UserEntity user = (UserEntity) principalCollection.getPrimaryPrincipal();
        // 简单授权信息对象，对象中包含用户的角色和权限信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //从数据库获取当前用户的角色 通过用户名查询该用户拥有的角色名称
        Set<String> roleNameSet = dao.selectUserRoleNameSet(user.getId());
        info.addRoles(roleNameSet);

        //从数据库获取当前用户的权限 通过用户名查询该用户拥有的权限名称
        Set<String> permissionNameSet = dao.selectUserPermissionNameSet(user.getId());

        Set<String> permissions = new HashSet<String>();
        for(String name : permissionNameSet) {
            for(String permission : name.split(",")){
                permissions.add(permission);
            }
        }
        info.addStringPermissions(permissions);

        System.out.println("授权完成....");
        return info;
    }


}
