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
import com.wteamfly.supercarrent.entity.po.ServerPo;
import com.wteamfly.supercarrent.entity.po.UserPo;
import com.wteamfly.supercarrent.entity.vo.PaginationVo;
import com.wteamfly.supercarrent.entity.vo.ServerVo;
import com.wteamfly.supercarrent.entity.vo.UserVo;

/**
 * 车辆管理Action类.
 * 
 * @author 黄祥谦
 * @since 3.0.0
 */
public class ServerManageAction extends ActionSupport {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * log4j实例对象.
     */
    private static Logger logger = LogManager.getLogger(ServerManageAction.class);

    /**
     * 角色实体.
     */
    private ServerVo server;

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
     * @return 获取的carInfo
     */
    public final ServerVo getServer() {
        return server;
    }

    /**
     * 设置carInfo的方法.
     * 
     * @param carInfo
     *            赋值给carInfo的值
     */
    public final void setServer(final ServerVo server) {
        this.server = server;
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
    public final void addServer() {
        logger.debug("进入addServer方法");
        final ServerPo serverPo = server.voToPo(ServerPo.class);
        final UserPo currentUserPo = currentUser.voToPo(UserPo.class);
        final SuperService service = SuperService.getInstance();
        resultMessage = service.addEntity(serverPo, currentUserPo);
        resultMessage.setUserToken(true);
        logger.debug("退出addServer方法");
    }

    /**
     * 删除角色.
     */
    public final void deleteServer() {
        logger.debug("进入deleteServer方法");
        final ServerPo rolePo = server.voToPo(ServerPo.class);
        final UserPo currentUserPo = currentUser.voToPo(UserPo.class);
        final SuperService service = SuperService.getInstance();
        resultMessage = service.logicDeleteEntity(rolePo, currentUserPo);
        resultMessage.setUserToken(true);
        logger.debug("退出deleteServer方法");
    }

    /**
     * 编辑角色.
     */
    public final void editServer() {
        logger.debug("进入editServer方法");
        final ServerPo serverPo = server.voToPo(ServerPo.class);
        final UserPo currentUserPo = currentUser.voToPo(UserPo.class);
        final SuperService service = SuperService.getInstance();
        resultMessage = service.editEntity(serverPo, currentUserPo);
        resultMessage.setUserToken(true);
        logger.debug("退出editServer方法");
    }

    /**
     * 查询角色分页列表.
     */
    public final void pageServer() {
        logger.debug("进入pageServer方法");
        final PaginationPo paginationPo = page.voToPo(PaginationPo.class);
        ServerPo serverPo = null;
        if (server != null) {
            serverPo = server.voToPo(ServerPo.class);
        } else {
            serverPo = new ServerPo();
        }

        final SuperService service = SuperService.getInstance();
        resultMessage = service.pageEntity(paginationPo, serverPo);
        resultMessage.setUserToken(true);
        logger.debug("退出pageServer方法");
    }
    
    /**
     * 查询角色列表.
     */
    public final void getAllServers() {
        logger.debug("进入getAllServers方法");
        ServerPo serverPo = null;
        if (server != null) {
            serverPo = server.voToPo(ServerPo.class);
        } else {
            serverPo = new ServerPo();
        }
        final SuperService service = SuperService.getInstance();
        resultMessage = service.queryList(serverPo);
        resultMessage.setUserToken(true);
        logger.debug("退出getAllServers方法");
    }
}
