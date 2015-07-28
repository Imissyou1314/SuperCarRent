/**
 * Copyright (c) 2007-2015 WteamFly.  All rights reserved. 网飞网络公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.supercarrent.biz.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import com.wteamfly.supercarrent.biz.service.ResultMessage;
import com.wteamfly.supercarrent.biz.service.SuperService;
import com.wteamfly.supercarrent.entity.po.PaginationPo;
import com.wteamfly.supercarrent.entity.po.UserPo;
import com.wteamfly.supercarrent.entity.vo.PaginationVo;
import com.wteamfly.supercarrent.entity.vo.UserVo;

/**
 * 用户管理Action类.
 * 
 * @author 侯骏雄
 * @since 3.0.0
 */
public class UserManageAction extends ActionSupport {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * log4j实例对象.
     */
    private static Logger logger = LogManager.getLogger(UserManageAction.class);

    /**
     * 用户实体.
     */
    private UserVo user;

    /**
     * 当前用户实体.
     */
    private UserVo currentUser;

    /**
     * 结果信息.
     */
    private ResultMessage resultMessage;

    /**
     * 分页信息.
     */
    private PaginationVo page;

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
     * @return 获取的page
     */
    public final PaginationVo getPage() {
        return page;
    }

    /**
     * 设置page的方法.
     * 
     * @param page
     *            赋值给page的值
     */
    public final void setPage(final PaginationVo page) {
        this.page = page;
    }

    /**
     * 添加成员.
     */
    public final void addUser() {
        logger.debug("进入addUser方法");
        final UserPo userPo = user.voToPo(UserPo.class);
        final UserPo currentUserPo = currentUser.voToPo(UserPo.class);
        final SuperService service = SuperService.getInstance();
        resultMessage = service.addEntity(userPo, currentUserPo);
        resultMessage.setUserToken(true);
        logger.debug("退出addUser方法");
    }

    /**
     * 删除用户.
     */
    public final void deleteUser() {
        logger.debug("进入lockUpUser方法");
        final UserPo userPo = user.voToPo(UserPo.class);
        final UserPo currentUserPo = currentUser.voToPo(UserPo.class);
        final SuperService service = SuperService.getInstance();
        resultMessage = service.logicDeleteEntity(userPo, currentUserPo);
        resultMessage.setUserToken(true);
        logger.debug("退出lockUpUser方法");
    }

    /**
     * 编辑用户.
     */
    public final void editUser() {
        logger.debug("进入editUser方法");
        final UserPo userPo = user.voToPo(UserPo.class);
        final UserPo currentUserPo = currentUser.voToPo(UserPo.class);
        final SuperService service = SuperService.getInstance();
        resultMessage = service.editEntity(userPo, currentUserPo);
        resultMessage.setUserToken(true);
        logger.debug("退出editUser方法");
    }

    /**
     * 查询用户分页列表.
     */
    public final void pageUser() {
        logger.debug("进入pageUser方法");
        final PaginationPo paginationPo = page.voToPo(PaginationPo.class);
        UserPo userPo = null;
        if (user != null) {
            userPo = user.voToPo(UserPo.class);
        } else {
            userPo = new UserPo();
        }

        final SuperService service = SuperService.getInstance();
        resultMessage = service.pageEntity(paginationPo, userPo);
        resultMessage.setUserToken(true);
        logger.debug("退出pageUser方法");
    }
}
