/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.supercarrent.helper;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.wteamfly.supercarrent.biz.dao.SuperDaoProxy;
import com.wteamfly.supercarrent.entity.po.UserPo;

/**
 * HibernateRealm类.
 * 
 * @author 侯骏雄
 * @since 3.0.0
 */
public class HibernateRealm extends AuthorizingRealm {

    /**
     * 初始化HibernateRealm.
     * 
     */
    public HibernateRealm() {
        // 这个名字必须匹配那个在用户类的getPrincipals()方法中的名字
        setName("HibernateRealm");
        setCacheManager(new MemoryConstrainedCacheManager());
    }

    /**
     * 用户登录验证.
     * 
     * @param authcToken
     *            用户登录验证信息
     * @return 验证结果信息
     */
    protected final AuthenticationInfo doGetAuthenticationInfo(
            final AuthenticationToken authcToken) {
        AuthenticationInfo result = null;
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        SuperDaoProxy proxy = SuperDaoProxy.getInstance();
        UserPo temp = new UserPo();
        temp.setLoginName(token.getUsername());
        UserPo user = (UserPo) proxy.queryList(temp).get(0);
        if (user != null) {
            result = new SimpleAuthenticationInfo(user.getUserId(),
                    user.getPassword(), getName());
        }
        return result;
    }

    /**
     * 用户权限验证.
     * 
     * @param principals
     *            权限
     * @return 验证结果信息
     */
    protected final AuthorizationInfo doGetAuthorizationInfo(
            final PrincipalCollection principals) {
        throw new UnsupportedOperationException();
    }

}
