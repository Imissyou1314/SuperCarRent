/**
 * Copyright (c) 2007-2015 WteamFly.  All rights reserved. 网飞网络公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.supercarrent.biz.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.wteamfly.supercarrent.biz.service.ResultMessage;
import com.wteamfly.supercarrent.biz.service.SystemService;
import com.wteamfly.supercarrent.entity.po.UserPo;
import com.wteamfly.supercarrent.entity.vo.UserVo;

/**
 * 系统Action类.
 *
 * @author 侯骏雄
 * @since 3.0.0
 */
public class SystemAction extends ActionSupport {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * log4j实例对象.
     */
    private static Logger logger = LogManager.getLogger(SystemAction.class);

    /**
     * 用户登录账户.
     */
    private UserVo user;

    /**
     * 当前用户.
     */
    private UserVo currentUser;

    /**
     * 结果信息.
     */
    private ResultMessage resultMessage;
	

    /**
     * @return 获取的currentUser
     */
    public final UserVo getCurrentUser() {
        return currentUser;
    }

    /**
     * 设置currentUser的方法.
     * 
     * @param currentUser
     *            赋值给currentUser的值
     */
    public final void setCurrentUser(final UserVo currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * @return 获取的user
     */
    public final UserVo getUser() {
        return user;
    }

    /**
     * 设置user的方法.
     * 
     * @param user
     *            赋值给user的值
     */
    public final void setUser(final UserVo user) {
        this.user = user;
    }

    /**
     * @return 获取的resultMessage
     */
    public final ResultMessage getResultMessage() {
        return resultMessage;
    }

    /**
     * 设置resultMessage的方法.
     * 
     * @param resultMessage
     *            赋值给resultMessage的值
     */
    public final void setResultMessage(final ResultMessage resultMessage) {
        this.resultMessage = resultMessage;
    }

    /**
     * 用户登录.
     */
    public final void login() {
        logger.debug("进入login方法");
        SystemService service = SystemService.getInstance();
        resultMessage = service.login(user.voToPo(UserPo.class),
                ServletActionContext.getRequest(),
                ServletActionContext.getResponse());
        logger.debug("退出login方法");
    }

    /**
     * 用户注销.
     */
    public final void logout() {
        logger.debug("进入logout方法");
        SystemService service = SystemService.getInstance();
        resultMessage = service.logout(ServletActionContext.getRequest(),
                ServletActionContext.getResponse());
        logger.debug("退出logout方法");
    }
    
    /**
     * 判断是否已登录.
     */
    public final void isLogin() {
        logger.debug("进入isLogin方法");
        SystemService service = SystemService.getInstance();
        resultMessage = service.isLogin(ServletActionContext.getRequest(),
                ServletActionContext.getResponse());
        logger.debug("退出isLogin方法");
    }
    
    /**
     * 判断用户是否填写信息.
     */
    public final void isNotMessage() {
    	logger.debug("进入isNotMessage方法");
    	ResultMessage resultMessage = new ResultMessage();
    	final UserPo currentUserPo = currentUser.voToPo(UserPo.class);
    	final SystemService service = SystemService.getInstance();
    	resultMessage = service.isNotMessage(currentUserPo);
    	resultMessage.setUserToken(true);
    	logger.debug("退出isNotMessage方法");
    }
    
    
    
    
    
}
