/**
 * Copyright (c) 2007-2015 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.supercarrent.helper;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.servlet.ShiroHttpServletResponse;
import org.apache.shiro.web.subject.WebSubject;
import org.apache.struts2.ServletActionContext;

/**
 * shiro权限认证工具类.
 * 
 * @author 侯骏雄
 * @since 3.0.0
 */
public final class ShiroHelper {
    /**
     * log4j实例对象.
     */
    private static Logger logger = LogManager.getLogger(ShiroHelper.class);

    /**
     * 权限管理中心.
     */
    private static WebSecurityManager securityManager;

    /**
     * 初始化Shiro数据源.
     */
    static {
        logger.info("开始初始化Shiro数据源");

        HibernateRealm myRealm = new HibernateRealm();
        securityManager = new DefaultWebSecurityManager(myRealm);
        SecurityUtils.setSecurityManager(securityManager);
        logger.info("结束初始化Shiro数据源");
    }

    /**
     * shiro权限认证工具类的私有构造方法，其作用是为了防止用户显式生成工具类的实例对象.
     * 
     */
    private ShiroHelper() {
    }

    /**
     * 获取用户.
     * 
     * @param request
     *            ServletRequest
     * @param response
     *            ServletResponse
     * @return 当前用户
     */
    public static Subject getSubject(final ServletRequest request,
            final ServletResponse response) {
        logger.debug("进入getSubject方法");
        ServletRequest toUseRequest = request;
        if (request instanceof HttpServletRequest) {
            HttpServletRequest http = (HttpServletRequest) request;
            toUseRequest = new ShiroHttpServletRequest(http,
                    ServletActionContext.getServletContext(),
                    securityManager.isHttpSessionMode());
        }
        ServletResponse toUseResponse = response;
        if (!securityManager.isHttpSessionMode()
                && (request instanceof ShiroHttpServletRequest)
                && (response instanceof HttpServletResponse)) {
            // ShiroHttpServletResponse是为了支持基于session的ids的URL重定向而设计的.
            // 只有在使用ShiroSessions(不是HttpSession)的时候才需要此类对象
            toUseResponse = new ShiroHttpServletResponse(
                    (HttpServletResponse) response,
                    ServletActionContext.getServletContext(),
                    (ShiroHttpServletRequest) request);
        }
        Subject subject = new WebSubject.Builder(securityManager, toUseRequest,
                toUseResponse).buildWebSubject();
        logger.debug("退出getSubject方法");
        return subject;
    }
}
