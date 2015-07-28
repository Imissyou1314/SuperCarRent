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
import com.wteamfly.supercarrent.biz.service.TmpOrderService;
import com.wteamfly.supercarrent.entity.po.OrderDetailPo;
import com.wteamfly.supercarrent.entity.po.PaginationPo;
import com.wteamfly.supercarrent.entity.po.TmpOrdersPo;
import com.wteamfly.supercarrent.entity.po.UserPo;
import com.wteamfly.supercarrent.entity.vo.OrderDetailVo;
import com.wteamfly.supercarrent.entity.vo.PaginationVo;
import com.wteamfly.supercarrent.entity.vo.TmpOrdersVo;
import com.wteamfly.supercarrent.entity.vo.UserVo;
import com.wteamfly.supercarrent.helper.ConstantVar;

/**
 * 临时订单管理Action类.
 * 
 * @author 黄祥谦
 * @since 3.0.0
 */
public class TmpOrdersManageAction extends ActionSupport {
	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	/**
	 * log4j实例对象.
	 */
	private static Logger logger = LogManager
			.getLogger(TmpOrdersManageAction.class);

	/**
	 * 角色实体.
	 */
	private TmpOrdersVo tmporders;

	public TmpOrdersVo getTmporders() {
		return tmporders;
	}

	public void setTmporders(TmpOrdersVo tmporders) {
		this.tmporders = tmporders;
	}

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
	 * 添加临时订单,当前用户已经登录，.
	 */
	public final void addTmpOrders() {
		logger.debug("进入addTmpOrders方法");

		final TmpOrdersPo tmpTmpOrdersPo = tmporders.voToPo(TmpOrdersPo.class);
		final UserPo currentUserPo = currentUser.voToPo(UserPo.class);
		final SuperService service = SuperService.getInstance();
		resultMessage = service.addEntity(tmpTmpOrdersPo, currentUserPo);
		resultMessage.setUserToken(true);

		logger.debug("退出addTmpOrders方法");
	}

	/**
	 * 删除临时订单.
	 */
	public final void deleteTmpOrders() {
		logger.debug("进入deleteOrders方法");
		final TmpOrdersPo rolePo = tmporders.voToPo(TmpOrdersPo.class);
		final UserPo currentUserPo = currentUser.voToPo(UserPo.class);
		final SuperService service = SuperService.getInstance();
		resultMessage = service.logicDeleteEntity(rolePo, currentUserPo);
		resultMessage.setUserToken(true);
		logger.debug("退出deleteOrders方法");
	}

	/**
	 * 编辑临时订单.
	 */
	public final void editTmpOrders() {
		logger.debug("进入editOrders方法");
		final TmpOrdersPo TmpOrdersPo = tmporders.voToPo(TmpOrdersPo.class);
		final UserPo currentUserPo = currentUser.voToPo(UserPo.class);
		final SuperService service = SuperService.getInstance();
		resultMessage = service.editEntity(TmpOrdersPo, currentUserPo);
		resultMessage.setUserToken(true);
		logger.debug("退出editOrders方法");
	}

	/**
	 * 查询角色分页列表.
	 */
	public final void pageTmpOrders() {
		logger.debug("进入pageOrders方法");
		final PaginationPo paginationPo = page.voToPo(PaginationPo.class);
		TmpOrdersPo tmpTmpOrdersPo = null;
		if (tmporders != null) {
			tmpTmpOrdersPo = tmporders.voToPo(TmpOrdersPo.class);
		} else {
			tmpTmpOrdersPo = new TmpOrdersPo();
		}

		final SuperService service = SuperService.getInstance();
		resultMessage = service.pageEntity(paginationPo, tmpTmpOrdersPo);
		resultMessage.setUserToken(true);
		logger.debug("退出pageOrders方法");
	}

	/**
	 * 查询订单列表.
	 */
	public final void getAllTmpOrders() {
		logger.debug("进入getAllOrders方法");
		TmpOrdersPo TmpOrdersPo = null;
		if (tmporders != null) {
			TmpOrdersPo = tmporders.voToPo(TmpOrdersPo.class);
		} else {
			TmpOrdersPo = new TmpOrdersPo();
		}
		final SuperService service = SuperService.getInstance();
		resultMessage = service.queryList(TmpOrdersPo);
		resultMessage.setUserToken(true);
		logger.debug("退出getAllOrders方法");
	}

	/**
	 * 通过临时订单id获得订单明细
	 */
	public final void getOrderDetailByTmpOId() {
		logger.debug("进入getOrderDetailByTmpOId方法");
		OrderDetailVo odVo = new OrderDetailVo();
		//设置查询条件
		odVo.setForeignId(tmporders.getTmpOrdersId());
		OrderDetailPo odPo = odVo.voToPo(OrderDetailPo.class);
		odPo.setForeignIdType(ConstantVar.TMPORDERSTYPE);
		
		SuperService service = SuperService.getInstance();
		resultMessage = service.queryList(odPo);
		resultMessage.setUserToken(true);
		logger.debug("退出getOrderDetailByTmpOId方法");
	}

	public final void addOrders() {
		logger.debug("进入addOrders方法");
		TmpOrdersPo tmpOrdersPo = null;

		tmpOrdersPo = tmporders.voToPo(TmpOrdersPo.class);
		final UserPo currentUserPo = currentUser.voToPo(UserPo.class);

		TmpOrderService service = TmpOrderService.getInstance();
		resultMessage = service.addOrders(tmpOrdersPo, currentUserPo);
		resultMessage.setUserToken(true);
		logger.debug("退出addOrders方法");

	}

}
