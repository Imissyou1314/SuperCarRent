/**
 * Copyright (c) 2007-2015 WteamFly.  All rights reserved. 网飞网络公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.supercarrent.biz.service;

import static com.wteamfly.supercarrent.helper.ConstantVar.READYSTATE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wteamfly.supercarrent.biz.dao.SuperDaoProxy;
import com.wteamfly.supercarrent.biz.dao.TmpOrderDaoProxy;
import com.wteamfly.supercarrent.entity.po.OrderDetailPo;
import com.wteamfly.supercarrent.entity.po.OrdersPo;
import com.wteamfly.supercarrent.entity.po.SuperPersistentObject;
import com.wteamfly.supercarrent.entity.po.TmpOrdersPo;
import com.wteamfly.supercarrent.entity.po.UserPo;
import com.wteamfly.supercarrent.entity.vo.OrderDetailVo;

/**
 * 订单Service类.
 *
 * @author 黄祥谦
 * @since 3.0.0
 */
public final class TmpOrderService {
	/**
	 * log4j实例对象.
	 */
	private static Logger logger = LogManager.getLogger(TmpOrderService.class);

	/**
	 * 单例对象.
	 */
	private static TmpOrderService instance = new TmpOrderService();

	/**
	 * 单例模式的私有构造方法.
	 */
	private TmpOrderService() {
	}

	/**
	 * 获取单例.
	 * 
	 * @return 单例
	 */
	public static TmpOrderService getInstance() {
		return instance;
	}

	@SuppressWarnings("unused")
	public ResultMessage getOrderDetailByTmpOId(TmpOrdersPo tmpOrdersPo) {

		logger.debug("进入getOrderDetailByTmpOId方法");
		ResultMessage rs = new ResultMessage();
		TmpOrderDaoProxy tmpOrderDao = TmpOrderDaoProxy.getInstance();
		List<OrderDetailPo> orderDetailByTmpOId = tmpOrderDao
				.getOrderDetailByTmpOId(tmpOrdersPo);

		HashMap<String, Object> resultParm = new HashMap<String, Object>();

		rs.setResultParm(resultParm);
		rs.setUserToken(true);
		logger.debug("退出getOrderDetailByTmpOId方法");
		return rs;
	}

	public ResultMessage addOrders(TmpOrdersPo tmpOrdersPo, UserPo currentUser) {
		ResultMessage rs = new ResultMessage();
		try {
			OrdersPo ordersPo = new OrdersPo();
			SuperDaoProxy dao = SuperDaoProxy.getInstance();
			List<? extends SuperPersistentObject> tmpList = dao
					.queryList(tmpOrdersPo);
			BeanUtils.copyProperties(ordersPo, tmpList.get(0));

			ordersPo.setOrderState(READYSTATE);
			dao.addEntity(ordersPo, currentUser);

		} catch (Exception e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rs.setUserToken(true);
		return rs;
	}

	/**
	 * 通过tmpOrdersId找到相应的OrderDetail.
	 * 
	 * @param tmpOrdersPo
	 * @param currentUserPo
	 * @return
	 */
	@SuppressWarnings("null")
	public ResultMessage findOrderDeetailByTmpOrdersId(TmpOrdersPo tmpOrdersPo) {
		ResultMessage rs = new ResultMessage();
		SuperDaoProxy dao = SuperDaoProxy.getInstance();
		Long id = tmpOrdersPo.getCarInfoId();
		OrderDetailVo orderDetailVo = null;
		OrderDetailPo orderDetailPo = null;
		if (id != 0) {
			orderDetailPo = new OrderDetailPo();
			orderDetailPo.setOrderDetailId(id);
		} else {
			rs.setResultParm(null);
			rs.setResultInfo("没有该记录");
			rs.setServiceResult(false);
		}
		List<? extends SuperPersistentObject> OrdersDatailPo = dao
				.queryList(orderDetailPo);
		OrderDetailPo Po = (OrderDetailPo) OrdersDatailPo.get(0);
		orderDetailVo.poToVo(Po);
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("OrderDatail", orderDetailVo);
		rs.setResultParm(parm);
		rs.setResultInfo("查询成功");
		rs.setServiceResult(true);
		return rs;
	}
}