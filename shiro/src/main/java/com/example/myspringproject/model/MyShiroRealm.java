package com.example.myspringproject.model;

import com.example.myspringproject.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author ppbear xuzheng/ppbeartoxuzheng@163.com
 * @Description 自定义realm
 * @Date 21:42 2019/6/5
 * @Param
 * @return
 **/
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("用户权限配置。。。。。。。。。。");
        //访问@RequirePermission注解的url时触发
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User userInfo = (User) principals.getPrimaryPrincipal();
        //获得用户的角色，及权限进行绑定
        for (Role role : userInfo.getRoleList()) {
            authorizationInfo.addRole(role.getName());
            for (Permission p : role.getPermissions()) {
                authorizationInfo.addStringPermission(p.getName());
            }
        }
        return authorizationInfo;
    }

    //验证用户登录信息

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("验证用户登录信息");
        String username = (String) authenticationToken.getPrincipal();
        System.out.println("登录用户名： " + username);
        System.out.println(authenticationToken.getCredentials());
        //从数据库查询出User信息及用户关联的角色，权限信息，以备权限分配时使用
        User userSo = new User();
        userSo.setUsername(username);
        User user = userService.findUserByName(userSo);
        if (null == user) {
            return null;
        }
        System.out.println("username: " + user.getUsername() + " ; password : " + user.getPassword());
        return new SimpleAuthenticationInfo(
                user,
                user.getPassword(),
                getName()
        );
    }
}
