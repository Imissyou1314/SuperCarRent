/**
 * Copyright (c) 2007-2015 WteamFly.  All rights reserved. 网飞网络公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.supercarrent.biz.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import com.wteamfly.supercarrent.biz.dao.SuperDaoProxy;
import com.wteamfly.supercarrent.entity.po.UserPo;
import com.wteamfly.supercarrent.helper.ShiroHelper;

/**
 * 系统Service类.
 *
 * @author 侯骏雄
 * @since 3.0.0
 */
public final class SystemService {
    /**
     * log4j实例对象.
     */
    private static Logger logger = LogManager.getLogger(SystemService.class);

    /**
     * 单例对象.
     */
    private static SystemService instance = new SystemService();

    /**
     * 单例模式的私有构造方法.
     */
    private SystemService() {
    }

    /**
     * 获取单例.
     * 
     * @return 单例
     */
    public static SystemService getInstance() {
        return instance;
    }

    /**
     * 账号登录操作.
     * 
     * @param uPoForLogin
     *            账号的相关信息
     * @param request
     *            请求信息
     * @param response
     *            请求回调信息
     * @return 服务结果信息
     */
    public ResultMessage login(final UserPo uPoForLogin,
            final ServletRequest request, final ServletResponse response) {
        logger.debug("进入login方法");
        ResultMessage rs = new ResultMessage();
        try {
            Subject currentUser = ShiroHelper.getSubject(request, response);
            UsernamePasswordToken token = new UsernamePasswordToken(
                    uPoForLogin.getLoginName(), uPoForLogin.getPassword());
            token.setRememberMe(true);
            currentUser.login(token);
            SuperDaoProxy daoProxy = SuperDaoProxy.getInstance();
            UserPo lPo = (UserPo) daoProxy.queryList(uPoForLogin).get(0);
            currentUser.getSession().setAttribute("user", lPo);
            rs.setServiceResult(true);
            rs.setResultInfo("登录成功");
        } catch (IncorrectCredentialsException se) {
            if (uPoForLogin.getLoginName() == null && uPoForLogin.getPassword() != null) {
                logger.error("登录名为空");
                logger.error("Exception:", se);
                se.printStackTrace();
            } else if (uPoForLogin.getLoginName() == null && uPoForLogin.getPassword() == null) {
                logger.error("uPoForLogin内容为空");
                logger.error("Exception:", se);
                se.printStackTrace();
            } else {
                logger.error("密码错误,password:" + uPoForLogin.getPassword(), se);
                rs.setServiceResult(false);
                rs.setResultInfo("密码错误");
            }
        } catch (AuthenticationException se) {
            if (uPoForLogin.getPassword() == null) {
                logger.error("Password为空");
                logger.error("Exception:", se);
                se.printStackTrace();
            } else {
                logger.error("用户名不存在,loginName:" + uPoForLogin.getLoginName(), se);
                rs.setServiceResult(false);
                rs.setResultInfo("用户名不存在");
            }
        } catch (Exception e) {
            if (uPoForLogin == null) {
                logger.error("uPoForLogin空指针异常");
            }
            logger.error("Exception:", e);
            e.printStackTrace();
        }
        logger.debug("退出login方法");
        return rs;
    }
    
    /**
     * 判断是否有用户信息.
     * 
     * @param currentUser
     *            当前用户
     * @return true:代表用户没指定信息
     */
    public ResultMessage isNotMessage(final UserPo currentUserPo) {
    	logger.debug("进入isNotMessage方法");
    	ResultMessage rs = new ResultMessage();
    	SuperDaoProxy daoproxy = SuperDaoProxy
                .getInstance();
        @SuppressWarnings("unchecked")
		List<UserPo> list = (List<UserPo>) daoproxy.queryList(currentUserPo);
        Iterator<UserPo> it = list.iterator();
        UserPo user = it.next();
        Boolean result = new Boolean(false);
        if (user.getCardNumber() == null || user.getCardType() == null 
        		 || user.getEmail() == null || user.getRealName() == null) {
        	result = true;
        } 
        Map<String, Object> parm = new HashMap<String, Object>();
        parm.put("isNotMessage", result.toString());
        rs.setResultParm(parm);
        rs.setResultInfo("分页成功");
        rs.setServiceResult(true);
    	logger.debug("退出isNotMessage方法");
		return rs;
    }

    /**
     * 用户登出操作.
     * 
     * @param request
     *            请求信息
     * @param response
     *            请求回调信息
     * @return 服务结果信息
     */
    public ResultMessage logout(final ServletRequest request,
            final ServletResponse response) {
        logger.debug("进入logout方法");
        ResultMessage rs = new ResultMessage();
        Subject currentUser = ShiroHelper.getSubject(request, response);
        currentUser.logout();
        rs.setServiceResult(true);
        logger.debug("退出logout方法");
        return rs;
    }

    /**
     * 判断是否已登录.
     * 
     * @param request
     *            请求信息
     * @param response
     *            请求回调信息
     * @return 服务结果信息
     */
    public ResultMessage isLogin(final ServletRequest request,
            final ServletResponse response) {
        logger.debug("进入isLogin方法");
        ResultMessage rs = new ResultMessage();
        Subject currentUser = ShiroHelper.getSubject(request, response);
        Boolean result = currentUser.isAuthenticated();
        Map<String, Object> parm = new HashMap<String, Object>();
        parm.put("isLogin", result.toString());
        rs.setResultParm(parm);
        rs.setServiceResult(true);
        logger.debug("退出isLogin方法");
        return rs;
    }
}
