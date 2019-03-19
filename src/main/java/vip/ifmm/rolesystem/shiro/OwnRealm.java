package vip.ifmm.rolesystem.shiro;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import vip.ifmm.rolesystem.entity.SysAcl;
import vip.ifmm.rolesystem.entity.SysRole;
import vip.ifmm.rolesystem.entity.SysUser;
import vip.ifmm.rolesystem.service.UserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: MackyHuang
 * @eamil: 973151766@qq.com
 * @createTime: 2019/2/11 16:31
 */
public class OwnRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 负责权限授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SysUser user = (SysUser) principalCollection.fromRealm(this.getClass().getName()).iterator().next();
//      用户和角色 一对多 角色和权限一对多
        Set<String> hisAcls = new HashSet<>();
        Set<String> hisRoles = new HashSet<>();
        System.out.println(user);
        Set<SysRole> roles = user.getRoles();
        if (CollectionUtils.isNotEmpty(roles)){
            for (SysRole role : roles){
                hisRoles.add(role.getName());
                Set<SysAcl> acls = role.getAcls();
                if (CollectionUtils.isNotEmpty(acls)){
                    for (SysAcl acl : acls){
                        hisAcls.add(acl.getUrl());
                        System.out.println(acl.getUrl());
                    }
                }
            }
        }
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermissions(hisAcls);
        simpleAuthorizationInfo.addRoles(hisRoles);
        return simpleAuthorizationInfo;
    }

    /**
     * 负责认证登陆
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;
        String username = userToken.getUsername();
        SysUser user = userService.getUserByUsername(username);
        // 当验证都通过后，把用户信息放在session里
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("userSession", user);
        session.setAttribute("userSessionId", user.getuId());
        return new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getName());
    }
}
