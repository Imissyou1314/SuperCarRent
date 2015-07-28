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
import com.wteamfly.supercarrent.entity.po.OrderDetailPo;
import com.wteamfly.supercarrent.entity.po.PaginationPo;
import com.wteamfly.supercarrent.entity.po.UserPo;
import com.wteamfly.supercarrent.entity.vo.OrderDetailVo;
import com.wteamfly.supercarrent.entity.vo.PaginationVo;
import com.wteamfly.supercarrent.entity.vo.UserVo;

/**
 * 车辆管理Action类.
 * 
 * @author 黄祥谦
 * @since 3.0.0
 */
public class OrderDetailManageAction extends ActionSupport {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * log4j实例对象.
     */
    private static Logger logger = LogManager.getLogger(OrderDetailManageAction.class);

    /**
     * 角色实体.
     */
    private OrderDetailVo orderDetail;

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
    public final OrderDetailVo getOrderDetail() {
        return orderDetail;
    }

    /**
     * 设置orderDetail的方法.
     * 
     * @param orderDetail
     *            赋值给orderDetail的值
     */
    public final void setOrderDetail(final OrderDetailVo orderDetail) {
        this.orderDetail = orderDetail;
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
    public final void addOrderDetail() {
        logger.debug("进入addOrderDetail方法");
        final OrderDetailPo orderDetailPo = orderDetail.voToPo(OrderDetailPo.class);
        final UserPo currentUserPo = currentUser.voToPo(UserPo.class);
        final SuperService service = SuperService.getInstance();
        resultMessage = service.addEntity(orderDetailPo, currentUserPo);
        resultMessage.setUserToken(true);
        logger.debug("退出addOrderDetail方法");
    }

    /**
     * 删除角色.
     */
    public final void deleteOrderDetail() {
        logger.debug("进入deleteOrderDetail方法");
        final OrderDetailPo orderDetailPo = orderDetail.voToPo(OrderDetailPo.class);
        final UserPo currentUserPo = currentUser.voToPo(UserPo.class);
        final SuperService service = SuperService.getInstance();
        resultMessage = service.logicDeleteEntity(orderDetailPo, currentUserPo);
        resultMessage.setUserToken(true);
        logger.debug("退出deleteOrderDetail方法");
    }

    /**
     * 编辑角色.
     */
    public final void editOrderDetail() {
        logger.debug("进入editOrderDetail方法");
        final OrderDetailPo orderDetailPo = orderDetail.voToPo(OrderDetailPo.class);
        final UserPo currentUserPo = currentUser.voToPo(UserPo.class);
        final SuperService service = SuperService.getInstance();
        resultMessage = service.editEntity(orderDetailPo, currentUserPo);
        resultMessage.setUserToken(true);
        logger.debug("退出editOrderDetail方法");
    }

    /**
     * 查询角色分页列表.
     */
    public final void pageOrderDetail() {
        logger.debug("进入pageOrderDetail方法");
        final PaginationPo paginationPo = page.voToPo(PaginationPo.class);
        OrderDetailPo orderDetailPo = null;
        if (orderDetail != null) {
            orderDetailPo = orderDetail.voToPo(OrderDetailPo.class);
        } else {
            orderDetailPo = new OrderDetailPo();
        }

        final SuperService service = SuperService.getInstance();
        resultMessage = service.pageEntity(paginationPo, orderDetailPo);
        resultMessage.setUserToken(true);
        logger.debug("退出pageOrderDetail方法");
    }
    
    /**
     * 查询角色列表.
     */
    public final void getAllOrderDetail() {
        logger.debug("进入getAllOrderDetail方法");
        OrderDetailPo orderDetailPo = null;
        if (orderDetail != null) {
            orderDetailPo = orderDetail.voToPo(OrderDetailPo.class);
        } else {
            orderDetailPo = new OrderDetailPo();
        }
        final SuperService service = SuperService.getInstance();
        resultMessage = service.queryList(orderDetailPo);
        resultMessage.setUserToken(true);
        logger.debug("退出getAllOrderDetail方法");
    }
}
