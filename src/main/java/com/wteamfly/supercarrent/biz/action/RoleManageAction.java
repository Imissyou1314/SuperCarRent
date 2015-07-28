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
import com.wteamfly.supercarrent.entity.po.RolePo;
import com.wteamfly.supercarrent.entity.po.UserPo;
import com.wteamfly.supercarrent.entity.vo.PaginationVo;
import com.wteamfly.supercarrent.entity.vo.RoleVo;
import com.wteamfly.supercarrent.entity.vo.UserVo;

/**
 * 角色管理Action类.
 * 
 * @author 侯骏雄
 * @since 3.0.0
 */
public class RoleManageAction extends ActionSupport {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * log4j实例对象.
     */
    private static Logger logger = LogManager.getLogger(RoleManageAction.class);

    /**
     * 角色实体.
     */
    private RoleVo role;

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
     * 添加角色.
     */
    public final void addRole() {
        logger.debug("进入addRole方法");
        final RolePo rolePo = role.voToPo(RolePo.class);
        final UserPo currentUserPo = currentUser.voToPo(UserPo.class);
        final SuperService service = SuperService.getInstance();
        resultMessage = service.addEntity(rolePo, currentUserPo);
        resultMessage.setUserToken(true);
        logger.debug("退出addRole方法");
    }

    /**
     * 删除角色.
     */
    public final void deleteRole() {
        logger.debug("进入deleteRole方法");
        final RolePo rolePo = role.voToPo(RolePo.class);
        final UserPo currentUserPo = currentUser.voToPo(UserPo.class);
        final SuperService service = SuperService.getInstance();
        resultMessage = service.logicDeleteEntity(rolePo, currentUserPo);
        resultMessage.setUserToken(true);
        logger.debug("退出deleteRole方法");
    }

    /**
     * 编辑角色.
     */
    public final void editRole() {
        logger.debug("进入editRole方法");
        final RolePo rolePo = role.voToPo(RolePo.class);
        final UserPo currentUserPo = currentUser.voToPo(UserPo.class);
        final SuperService service = SuperService.getInstance();
        resultMessage = service.editEntity(rolePo, currentUserPo);
        resultMessage.setUserToken(true);
        logger.debug("退出editRole方法");
    }

    /**
     * 查询角色分页列表.
     */
    public final void pageRole() {
        logger.debug("进入pageRole方法");
        final PaginationPo paginationPo = page.voToPo(PaginationPo.class);
        RolePo rolePo = null;
        if (role != null) {
            rolePo = role.voToPo(RolePo.class);
        } else {
            rolePo = new RolePo();
        }

        final SuperService service = SuperService.getInstance();
        resultMessage = service.pageEntity(paginationPo, rolePo);
        resultMessage.setUserToken(true);
        logger.debug("退出pageRole方法");
    }
    
    /**
     * 查询角色列表.
     */
    public final void getAllRoles() {
        logger.debug("进入getAllRoles方法");
        RolePo rolePo = null;
        if (role != null) {
            rolePo = role.voToPo(RolePo.class);
        } else {
            rolePo = new RolePo();
        }
        final SuperService service = SuperService.getInstance();
        resultMessage = service.queryList(rolePo);
        resultMessage.setUserToken(true);
        logger.debug("退出pageRole方法");
    }
}
