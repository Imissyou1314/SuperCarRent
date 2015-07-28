/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.supercarrent.menu.biz.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import com.wteamfly.supercarrent.biz.service.ResultMessage;
import com.wteamfly.supercarrent.biz.service.SuperService;
import com.wteamfly.supercarrent.entity.po.RolePo;
import com.wteamfly.supercarrent.entity.po.UserPo;
import com.wteamfly.supercarrent.entity.vo.PaginationVo;
import com.wteamfly.supercarrent.entity.vo.RoleVo;
import com.wteamfly.supercarrent.entity.vo.UserVo;
import com.wteamfly.supercarrent.menu.biz.service.RoleMenuItemMapService;
import com.wteamfly.supercarrent.menu.entity.po.RoleMenuItemMapPo;
import com.wteamfly.supercarrent.menu.entity.vo.MenuItemVo;
import com.wteamfly.supercarrent.menu.entity.vo.RoleMenuItemMapVo;

/**
 * 角色菜单管理Action层.
 * 
 * @author 殷梓淞.
 * @since 3.1.0
 */
public class RoleMenuItemMapAction extends ActionSupport {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * log4j实例对象.
     */
    private static Logger logger = LogManager.getLogger(MenuManageAction.class);

    /**
     * 当前用户.
     */
    private UserVo currentUser;

    /**
     * 当前用户.
     */
    private RoleVo role;
    /**
     * 菜单实体.
     */
    private MenuItemVo menuItem;

    /**
     * 角色菜单实体.
     */
    private RoleMenuItemMapVo roleMenuItemMap;

    /**
     * 结果集.
     */
    private ResultMessage resultMessage;

    /**
     * 分页信息.
     */
    private PaginationVo page;

    /**
     * 添加角色菜单关系.
     */
    public final void addRoleMenuItemMap() {
        logger.debug("进入方法addRoleMenuItemMap");
        final RoleMenuItemMapPo roleMenuItemMapPo = roleMenuItemMap
                .voToPo(RoleMenuItemMapPo.class);
        final UserPo currentUserPo = currentUser.voToPo(UserPo.class);
        SuperService service = SuperService.getInstance();
        resultMessage = service.addEntity(roleMenuItemMapPo, currentUserPo);
        resultMessage.setUserToken(true);
        logger.debug("退出方法addRoleMenuItemMap");
    }

    /**
     * 获取角色菜单.
     */
    public final void getRoleMenuItem() {
        logger.debug("进入getRoleMenuItem方法");
        RoleMenuItemMapService service = RoleMenuItemMapService.getInstance();
        resultMessage = service.getRoleMenuItem(role.voToPo(RolePo.class));
        resultMessage.setUserToken(true);
        logger.debug("退出getRoleMenuItem方法");
    }

    /**
     * 删除角色菜单关系.
     */
    public final void deleteRoleMenuItemMap() {
        logger.debug("进入方法deleteRoleMenuItemMap");
        final RoleMenuItemMapPo roleMenuItemMapPo = roleMenuItemMap
                .voToPo(RoleMenuItemMapPo.class);
        SuperService service = SuperService.getInstance();
        resultMessage = service.deleteEntity(roleMenuItemMapPo);
        resultMessage.setUserToken(true);
        logger.debug("退出方法deleteRoleMenuItemMap");
    }

    /**
     * 根据角色id获取角色菜单关系.
     */
    public final void queryRoleMenuItemMap() {
        logger.debug("进入getRoleMenuItem方法");
        final RoleMenuItemMapPo roleMenuItemMapPo = roleMenuItemMap
                .voToPo(RoleMenuItemMapPo.class);
        RoleMenuItemMapService service = RoleMenuItemMapService.getInstance();
        resultMessage = service.queryRoleMenuItemMap(roleMenuItemMapPo);
        resultMessage.setUserToken(true);
        logger.debug("退出getRoleMenuItem方法");
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
     * @return 获取的menuItem
     */
    public final MenuItemVo getMenuItem() {
        return menuItem;
    }

    /**
     * 设置menuItem的方法.
     * 
     * @param menuItem
     *            赋值给menuItem的值
     */
    public final void setMenuItem(final MenuItemVo menuItem) {
        this.menuItem = menuItem;
    }

    /**
     * @return 获取的roleMenuItemMap
     */
    public final RoleMenuItemMapVo getRoleMenuItemMap() {
        return roleMenuItemMap;
    }

    /**
     * 设置roleMenuItemMap的方法.
     * 
     * @param roleMenuItemMap
     *            赋值给roleMenuItemMap的值
     */
    public final void setRoleMenuItemMap(final RoleMenuItemMapVo roleMenuItemMap) {
        this.roleMenuItemMap = roleMenuItemMap;
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
     * @return 获取的role
     */
    public final RoleVo getRole() {
        return role;
    }

    /**
     * 设置role的方法.
     * 
     * @param role
     *            赋值给role的值
     */
    public final void setRole(final RoleVo role) {
        this.role = role;
    }
}
