/**
 * Copyright (c) 2007-2015 WteamFly.  All rights reserved. 网飞网络公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.supercarrent.biz.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wteamfly.supercarrent.biz.dao.SuperDaoProxy;
import com.wteamfly.supercarrent.entity.po.CarInfoPo;
import com.wteamfly.supercarrent.entity.po.OrderDetailPo;
import com.wteamfly.supercarrent.entity.po.OrdersPo;
import com.wteamfly.supercarrent.entity.po.PaginationPo;
import com.wteamfly.supercarrent.entity.po.SuperPersistentObject;
import com.wteamfly.supercarrent.entity.po.UserPo;
import com.wteamfly.supercarrent.entity.vo.OrderDetailVo;
import com.wteamfly.supercarrent.entity.vo.OrdersVo;
import com.wteamfly.supercarrent.entity.vo.UserVo;
import com.wteamfly.supercarrent.helper.ConstantVar;

/**
 * 订单Service类.
 *
 * @author 黄祥谦
 * @since 3.0.0
 */
public final class OrderService {
	/**
	 * log4j实例对象.
	 */
	private static Logger logger = LogManager.getLogger(OrderService.class);

	/**
	 * 单例对象.
	 */
	private static OrderService instance = new OrderService();

	/**
	 * 单例模式的私有构造方法.
	 */
	private OrderService() {
	}

	/**
	 * 获取单例.
	 * 
	 * @return 单例
	 */
	public static OrderService getInstance() {
		return instance;
	}

	// 服务端添加订单，是当给客户完成下单时调用的
	public void addOrdersByCustomer(OrdersPo orders, CarInfoPo carinfo,
			UserPo currentUser) {
		// TODO 未完成
		orders.setCarInfoId(carinfo.getCarInfoId());
		SuperService service = SuperService.getInstance();
		service.addEntity(orders, currentUser);
		// 设置订单的外键 车id时，同时设置OrderDetail的外键为Ordersid

		// 通过车id查出类型为必备的OrderDetail
	}

	/**
	 * 根据用户id获取用户的订单.
	 * 
	 * @param currentUser
	 *            当前用户.
	 * @param paginationPo
	 *            分页实体.
	 * @return ResultMessage 订单实体.
	 */
	@SuppressWarnings("unchecked")
	public ResultMessage finalOrderByUserId(final UserVo currentUser,
			final PaginationPo paginationPo) {
		logger.debug("进入finalOrderByUserId方法");
		ResultMessage rs = new ResultMessage();
		UserPo userPo = currentUser.voToPo(UserPo.class);
		OrdersPo orderPo = new OrdersPo();
		orderPo.setUserId(userPo.getUserId());

		try {
			SuperDaoProxy daoproxy = SuperDaoProxy.getInstance();
			PaginationPo pageResult = daoproxy
					.pageEntity(paginationPo, orderPo);

			List<OrdersPo> listPos = (List<OrdersPo>) pageResult.getList();
			List<OrdersVo> listVos = new ArrayList<OrdersVo>();
			OrdersVo vo = null;
			for (OrdersPo ordersPo : listPos) {
				vo = new OrdersVo();
				vo.poToVo(ordersPo);
				listVos.add(vo);
			}

			Map<String, Object> parm = new HashMap<String, Object>();
			parm.put("pageList", listVos);
			parm.put("totalCount", pageResult.getTotalCount());

			rs.setResultParm(parm);
			rs.setResultInfo("分页成功");
			rs.setServiceResult(true);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		logger.debug("退出finalOrderByUserId方法");
		return rs;
	}

	/**
	 * 根据订单状态查找订单.
	 * 
	 * @param paginationPo
	 *            分页实体.
	 * @return ResultMessage 订单集合.
	 */
	@SuppressWarnings("unchecked")
	public ResultMessage finalOrdersByState(final PaginationPo paginationPo) {
		logger.debug("进入finalOrdersByState方法");
		ResultMessage rs = new ResultMessage();
		try {
			SuperDaoProxy daoproxy = SuperDaoProxy.getInstance();
			OrdersPo orderPo = new OrdersPo();
			// 设置查找订单状态.
			orderPo.setOrderState(ConstantVar.READYSTATE);

			PaginationPo pageResult = daoproxy
					.pageEntity(paginationPo, orderPo);

			List<OrdersPo> listPos = (List<OrdersPo>) pageResult.getList();
			List<OrdersVo> listVos = new ArrayList<OrdersVo>();
			OrdersVo vo = null;
			for (OrdersPo ordersPo : listPos) {
				vo = new OrdersVo();
				vo.poToVo(ordersPo);
				listVos.add(vo);
			}

			Map<String, Object> parm = new HashMap<String, Object>();
			parm.put("pageList", listVos);
			parm.put("totalCount", pageResult.getTotalCount());

			rs.setResultParm(parm);
			rs.setResultInfo("分页成功");
			rs.setServiceResult(true);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		logger.debug("退出finalOrdersByState方法");
		return rs;
	}

	/**
	 * 根据Ordersid获取对应的OrderDetail.
	 * 
	 * @param ordersPo
	 *            订单实体.
	 * @return rs 订单详细的一条记录.
	 */
	@SuppressWarnings("null")
	public ResultMessage findOrderDetailByOrderId(OrdersPo ordersPo) {
		logger.debug("进入findOrderDetailByOrderId方法");
		ResultMessage rs = new ResultMessage();
		SuperDaoProxy dao = SuperDaoProxy.getInstance();
		Long id = ordersPo.getCarInfoId();
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
		logger.debug("退出findOrderDetailByOrderId方法");
		return rs;
	}

}
