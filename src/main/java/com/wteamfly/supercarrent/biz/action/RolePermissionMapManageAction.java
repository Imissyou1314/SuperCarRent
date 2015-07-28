/**
 * Copyright (c) 2007-2015 WteamFly.  All rights reserved. 网飞网络公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.supercarrent.biz.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import com.wteamfly.supercarrent.biz.service.ResultMessage;
import com.wteamfly.supercarrent.biz.service.RolePermissionMapManageServcie;
import com.wteamfly.supercarrent.biz.service.SuperService;
import com.wteamfly.supercarrent.entity.po.PaginationPo;
import com.wteamfly.supercarrent.entity.po.RolePermissionMapPo;
import com.wteamfly.supercarrent.entity.po.UserPo;
import com.wteamfly.supercarrent.entity.vo.PaginationVo;
import com.wteamfly.supercarrent.entity.vo.RolePermissionMapVo;
import com.wteamfly.supercarrent.entity.vo.UserVo;

/**
 * 角色行为映射管理Action类.
 * 
 * @author 侯骏雄
 * @since 3.0.0
 */
public class RolePermissionMapManageAction extends ActionSupport {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * log4j实例对象.
     */
    private static Logger logger = LogManager
            .getLogger(RolePermissionMapManageAction.class);

    /**
     * 角色行为映射实体.
     */
    private RolePermissionMapVo rolePermissionMap;

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
     * @return 获取的rolePermissionMap
     */
    public final RolePermissionMapVo getRolePermissionMap() {
        return rolePermissionMap;
    }

    /**
     * 设置rolePermissionMap的方法.
     * 
     * @param rolePermissionMap
     *            赋值给rolePermissionMap的值
     */
    public final void setRolePermissionMap(
            final RolePermissionMapVo rolePermissionMap) {
        this.rolePermissionMap = rolePermissionMap;
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
     * 添加角色行为映射.
     */
    public final void addRolePermissionMap() {
        logger.debug("进入addRolePermissionMap方法");
        final RolePermissionMapPo rolePermissionMapPo = rolePermissionMap
                .voToPo(RolePermissionMapPo.class);
        final UserPo currentUserPo = currentUser.voToPo(UserPo.class);
        final SuperService service = SuperService.getInstance();
        resultMessage = service.addEntity(rolePermissionMapPo, currentUserPo);
        resultMessage.setUserToken(true);
        logger.debug("退出addRolePermissionMap方法");
    }

    /**
     * 删除角色行为映射.
     */
    public final void deleteRolePermissionMap() {
        logger.debug("进入deleteRolePermissionMap方法");
        final RolePermissionMapPo rolePermissionMapPo = rolePermissionMap
                .voToPo(RolePermissionMapPo.class);
        final SuperService service = SuperService.getInstance();
        resultMessage = service.deleteEntity(rolePermissionMapPo);
        resultMessage.setUserToken(true);
        logger.debug("退出deleteRolePermissionMap方法");
    }

    /**
     * 编辑角色行为映射.
     */
    public final void editRolePermissionMap() {
        logger.debug("进入editRolePermissionMap方法");
        final RolePermissionMapPo rolePermissionMapPo = rolePermissionMap
                .voToPo(RolePermissionMapPo.class);
        final UserPo currentUserPo = currentUser.voToPo(UserPo.class);
        final SuperService service = SuperService.getInstance();
        resultMessage = service.editEntity(rolePermissionMapPo, currentUserPo);
        resultMessage.setUserToken(true);
        logger.debug("退出editRolePermissionMap方法");
    }

    /**
     * 查询角色行为映射分页列表.
     */
    public final void pageRolePermissionMap() {
        logger.debug("进入pageRolePermissionMap方法");
        final PaginationPo paginationPo = page.voToPo(PaginationPo.class);
        RolePermissionMapPo rolePermissionMapPo = null;
        if (rolePermissionMap != null) {
            rolePermissionMapPo = rolePermissionMap
                    .voToPo(RolePermissionMapPo.class);
        } else {
            rolePermissionMapPo = new RolePermissionMapPo();
        }

        final RolePermissionMapManageServcie service = RolePermissionMapManageServcie.getInstance();
        resultMessage = service.pageRolePermission(paginationPo, rolePermissionMapPo);
        resultMessage.setUserToken(true);
        logger.debug("退出pageRolePermissionMap方法");
    }

    /**
     * 查询角色未拥有行为列表.
     */
    public final void pageNotHasRolePermission() {
        logger.debug("进入pageNotHasRolePermission方法");
        final PaginationPo paginationPo = page.voToPo(PaginationPo.class);
        RolePermissionMapPo rolePermissionMapPo = null;
        if (rolePermissionMap != null) {
            rolePermissionMapPo = rolePermissionMap
                    .voToPo(RolePermissionMapPo.class);
        } else {
            rolePermissionMapPo = new RolePermissionMapPo();
        }
        final RolePermissionMapManageServcie service = RolePermissionMapManageServcie
                .getInstance();
        resultMessage = service.pageNotHasRolePermission(paginationPo,
                rolePermissionMapPo);
        resultMessage.setUserToken(true);
        logger.debug("退出pageNotHasRolePermission方法");
    }
}
