package com.qf.oa.realm;



import com.qf.oa.entity.SysMenu;
import com.qf.oa.entity.SysUser;
import com.qf.oa.service.ISysMenuService;
import com.qf.oa.service.ISysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author : FuHan
 * @description : ***
 * @date: 2019/9/29
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private ISysUserService service;
    @Autowired
    private ISysMenuService sysMenuService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权处理");
        Set<String> permissions = new HashSet<>();
        SysUser sysUser = (SysUser) principalCollection.getPrimaryPrincipal();
        long userId = sysUser.getUserId();
        List<SysMenu> list = sysMenuService.getMenuByUserId(userId);
        for(SysMenu menu : list){
            if(menu.getMenuType() == 3){
                System.out.println("path:"+menu.getMenuPath());
                permissions.add(menu.getMenuPath());
            }
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;

    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("认证处理");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        SysUser sysUser =service.getUserByName(username);
        System.out.println(sysUser);
        if(sysUser == null){
            return null;
        }
        ByteSource bytes = ByteSource.Util.bytes(username);
        return new SimpleAuthenticationInfo(sysUser, sysUser.getUserPassword(),bytes, this.getName());
    }
}
