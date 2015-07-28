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
import com.wteamfly.supercarrent.entity.po.PermissionPo;
import com.wteamfly.supercarrent.entity.po.UserPo;
import com.wteamfly.supercarrent.entity.vo.PaginationVo;
import com.wteamfly.supercarrent.entity.vo.PermissionVo;
import com.wteamfly.supercarrent.entity.vo.UserVo;

/**
 * 行为管理Action类.
 * 
 * @author 侯骏雄
 * @since 3.0.0
 */
public class PermissionManageAction extends ActionSupport {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * log4j实例对象.
     */
    private static Logger logger = LogManager
            .getLogger(PermissionManageAction.class);

    /**
     * 行为实体.
     */
    private PermissionVo permission;

    /**
     * 当前用户.
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
     * @return 获取的permission
     */
    public final PermissionVo getPermission() {
        return permission;
    }

    /**
     * 设置permission的方法.
     * 
     * @param permission
     *            赋值给permission的值
     */
    public final void setPermission(final PermissionVo permission) {
        this.permission = permission;
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
     * 添加行为.
     */
    public final void addPermission() {
        logger.debug("进入addPermission方法");
        final PermissionPo permissionPo = permission.voToPo(PermissionPo.class);
        final UserPo currentUserPo = currentUser.voToPo(UserPo.class);
        final SuperService service = SuperService.getInstance();
        resultMessage = service.addEntity(permissionPo, currentUserPo);
        resultMessage.setUserToken(true);
        logger.debug("退出addPermission方法");
    }

    /**
     * 删除行为.
     */
    public final void deletePermission() {
        logger.debug("进入deletePermission方法");
        final PermissionPo permissionPo = permission.voToPo(PermissionPo.class);
        final UserPo currentUserPo = currentUser.voToPo(UserPo.class);
        final SuperService service = SuperService.getInstance();
        resultMessage = service.logicDeleteEntity(permissionPo, currentUserPo);
        resultMessage.setUserToken(true);
        logger.debug("退出deletePermission方法");
    }

    /**
     * 编辑行为.
     */
    public final void editPermission() {
        logger.debug("进入editPermission方法");
        final PermissionPo permissionPo = permission.voToPo(PermissionPo.class);
        final UserPo currentUserPo = currentUser.voToPo(UserPo.class);
        final SuperService service = SuperService.getInstance();
        resultMessage = service.editEntity(permissionPo, currentUserPo);
        resultMessage.setUserToken(true);
        logger.debug("退出editPermission方法");
    }

    /**
     * 查询行为分页列表.
     */
    public final void pagePermission() {
        logger.debug("进入pagePermission方法");
        PaginationPo paginationPo = page.voToPo(PaginationPo.class);
        PermissionPo permissionPo = null;
        if (permission != null) {
            permissionPo = permission.voToPo(PermissionPo.class);
        } else {
            permissionPo = new PermissionPo();
        }
        permissionPo.setIsDelete(false);
        SuperService service = SuperService.getInstance();
        resultMessage = service.pageEntity(paginationPo, permissionPo);
        resultMessage.setUserToken(true);
        logger.debug("退出pagePermission方法");
    }
}
