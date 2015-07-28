/**
 * Copyright (c) 2007-2015 WteamFly.  All rights reserved. 网飞网络公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.supercarrent.biz.action;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.wteamfly.supercarrent.biz.service.ResultMessage;
import com.wteamfly.supercarrent.biz.service.SuperService;
import com.wteamfly.supercarrent.entity.po.OrdersPo;
import com.wteamfly.supercarrent.entity.po.PaginationPo;
import com.wteamfly.supercarrent.entity.po.UserPo;
import com.wteamfly.supercarrent.entity.vo.OrdersVo;
import com.wteamfly.supercarrent.entity.vo.PaginationVo;
import com.wteamfly.supercarrent.entity.vo.UserVo;

/**
 *订单管理Action类.
 * 
 * @author 黄祥谦
 * @since 3.0.0
 */
public class OrdersManageAction extends ActionSupport implements SessionAware{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * log4j实例对象.
     */
    private static Logger logger = LogManager.getLogger(OrdersManageAction.class);

    /**
     * 角色实体.
     */
    private OrdersVo orders;
    
    /**
     * 
     */
    private Map<String,Object> session;

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
    public final OrdersVo getOrders() {
        return orders;
    }

    /**
     * 设置carInfo的方法.
     * 
     * @param carInfo
     *            赋值给carInfo的值
     */
    public final void setOrders(final OrdersVo orders) {
        this.orders = orders;
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

    /**'
     * 设置临时时间.
     */
    public final void saveTmpTime(){
    	logger.debug("进入saveTmpTime方法");
    	session.put("orders", orders);
    	resultMessage.setResultInfo("cheng gong bao cun time");
    	resultMessage.setUserToken(true);
        logger.debug("退出saveTmpTime方法");
    	
    }
    
    /**
     * 添加订单.
     */
    public final void addOrders() {
        logger.debug("进入addOrders方法");
        OrdersVo tmporder = (OrdersVo) session.get("orders");
        if(tmporder.getBackCarTime() == null | tmporder.getGetCarTime() == null){
        	return ;
        }
        
        final OrdersPo ordersPo = orders.voToPo(OrdersPo.class);
        final UserPo currentUserPo = currentUser.voToPo(UserPo.class);
        final SuperService service = SuperService.getInstance();
        resultMessage = service.addEntity(ordersPo, currentUserPo);
        resultMessage.setUserToken(true);
        session.remove("getCarTime");
        session.remove("backCarTime");
        logger.debug("退出addOrders方法");
    }

    /**
     * 删除订单.
     */
    public final void deleteOrders() {
        logger.debug("进入deleteOrders方法");
        final OrdersPo rolePo = orders.voToPo(OrdersPo.class);
        final UserPo currentUserPo = currentUser.voToPo(UserPo.class);
        final SuperService service = SuperService.getInstance();
        resultMessage = service.logicDeleteEntity(rolePo, currentUserPo);
        resultMessage.setUserToken(true);
        logger.debug("退出deleteOrders方法");
    }

    /**
     * 编辑订单.
     */
    public final void editOrders() {
        logger.debug("进入editOrders方法");
        final OrdersPo ordersPo = orders.voToPo(OrdersPo.class);
        final UserPo currentUserPo = currentUser.voToPo(UserPo.class);
        final SuperService service = SuperService.getInstance();
        resultMessage = service.editEntity(ordersPo, currentUserPo);
        resultMessage.setUserToken(true);
        logger.debug("退出editOrders方法");
    }

    /**
     * 查询订单分页列表.
     */
    public final void pageOrders() {
        logger.debug("进入pageOrders方法");
        final PaginationPo paginationPo = page.voToPo(PaginationPo.class);
        OrdersPo ordersPo = null;
        if (orders != null) {
            ordersPo = orders.voToPo(OrdersPo.class);
        } else {
            ordersPo = new OrdersPo();
        }

        final SuperService service = SuperService.getInstance();
        resultMessage = service.pageEntity(paginationPo, ordersPo);
        resultMessage.setUserToken(true);
        logger.debug("退出pageOrders方法");
    }
    
    /**
     * 查询订单列表.
     */
    public final void getAllOrders() {
        logger.debug("进入getAllOrders方法");
        OrdersPo ordersPo = null;
        if (orders != null) {
            ordersPo = orders.voToPo(OrdersPo.class);
        } else {
            ordersPo = new OrdersPo();
        }
        final SuperService service = SuperService.getInstance();
        resultMessage = service.queryList(ordersPo);
        resultMessage.setUserToken(true);
        logger.debug("退出getAllOrders方法");
    }
   
    //wei le fang bian session zhu ru
	public void setSession(Map<String, Object> session) {
		
		this.session = session;
		
	}
}
