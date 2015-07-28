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
import com.wteamfly.supercarrent.entity.po.PaginationPo;
import com.wteamfly.supercarrent.entity.po.UserPo;
import com.wteamfly.supercarrent.entity.vo.PaginationVo;
import com.wteamfly.supercarrent.entity.vo.UserVo;
import com.wteamfly.supercarrent.menu.biz.service.MenuManageService;
import com.wteamfly.supercarrent.menu.entity.po.MenuItemPo;
import com.wteamfly.supercarrent.menu.entity.vo.MenuItemVo;
import com.wteamfly.supercarrent.menu.entity.vo.RoleMenuItemMapVo;

/**
 * 菜单管理Action层.
 * 
 * @author 殷梓淞.
 * @since 3.1.0
 */
public class MenuManageAction extends ActionSupport {

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
     * 添加菜单.
     */
    public final void addMenuItem() {
        logger.debug("进入方法addMenuItem");
        final MenuItemPo menuItemPo = menuItem.voToPo(MenuItemPo.class);
        final UserPo currentUserPo = currentUser.voToPo(UserPo.class);
        SuperService service = SuperService.getInstance();
        resultMessage = service.addEntity(menuItemPo, currentUserPo);
        resultMessage.setUserToken(true);
        logger.debug("退出方法addMenuItem");
    }

    /**
     * 删除菜单.
     */
    public final void deleteMenuItem() {
        logger.debug("进入deleteMenuItem方法");
        final MenuItemPo menuItemPo = menuItem.voToPo(MenuItemPo.class);
        final UserPo currentUserPo = currentUser.voToPo(UserPo.class);
        final SuperService service = SuperService.getInstance();
        resultMessage = service.logicDeleteEntity(menuItemPo, currentUserPo);
        resultMessage.setUserToken(true);
        logger.debug("退出deleteMenuItem方法");
    }

    /**
     * 编辑菜单.
     */
    public final void editMenuItem() {
        logger.debug("进入editMenuItem方法");
        final MenuItemPo menuItemPo = menuItem.voToPo(MenuItemPo.class);
        final UserPo currentUserPo = currentUser.voToPo(UserPo.class);
        final SuperService service = SuperService.getInstance();
        resultMessage = service.editEntity(menuItemPo, currentUserPo);
        resultMessage.setUserToken(true);
        logger.debug("退出editMenuItem方法");
    }



    /**
     * 获取当前用户菜单.
     */
    public final void getCurrentUserMenuItem() {
        logger.debug("进入getMenuItem方法");
        final UserPo currentUserPo = currentUser.voToPo(UserPo.class);
        final MenuItemPo menuItemPo = menuItem.voToPo(MenuItemPo.class);
        MenuManageService service = MenuManageService.getInstance();
        resultMessage = service.getUserMenuItem(currentUserPo, menuItemPo);
        resultMessage.setUserToken(true);
        logger.debug("退出getMenuItem方法");
    }

    /**
     * 获取所有菜单.
     */
    public final void getMenuItem() {
        logger.debug("进入getMenuItem方法");
        MenuManageService service = MenuManageService.getInstance();
        resultMessage = service.getMenuItem();
        resultMessage.setUserToken(true);
        logger.debug("退出getMenuItem方法");

    }
    
    /**
     * 查询菜单分页列表.
     */
    public final void getPageMenuItem() {
        logger.debug("进入pagePermission方法");
        PaginationPo paginationPo = page.voToPo(PaginationPo.class);
        MenuItemPo menuItemPo = null;
        if (menuItem != null) {
            menuItemPo = menuItem.voToPo(MenuItemPo.class);
        } else {
            menuItemPo = new MenuItemPo();
        }
        menuItemPo.setIsDelete(false);
        SuperService service = SuperService.getInstance();
        resultMessage = service.pageEntity(paginationPo, menuItemPo);
        resultMessage.setUserToken(true);
        logger.debug("退出pagePermission方法");
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
     * @return 获取的page
     */
    public final PaginationVo getPage() {
        return page;
    }

    /**
     * 设置page的方法.
     * @param page 赋值给page的值
     */
    public final void setPage(final PaginationVo page) {
        this.page = page;
    }
}
