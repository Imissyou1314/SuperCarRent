/**
 * Copyright (c) 2007-2015 WteamFly.  All rights reserved. 网飞网络公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.supercarrent.biz.service;

import static com.wteamfly.supercarrent.helper.ConstantVar.CARTYPE;
import static com.wteamfly.supercarrent.helper.ConstantVar.ORDERSTYPE;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wteamfly.supercarrent.entity.po.CarInfoPo;
import com.wteamfly.supercarrent.entity.po.OrderDetailPo;
import com.wteamfly.supercarrent.entity.po.OrdersPo;
import com.wteamfly.supercarrent.entity.po.UserPo;

/**
 * 订单详情Service类.
 *
 * @author 黄祥谦	
 * @since 3.0.0
 */
public final class OrderDetailService {
    /**
     * log4j实例对象.
     */
    private static Logger logger = LogManager.getLogger(OrderDetailService.class);

    /**
     * 单例对象.
     */
    private static OrderDetailService instance = new OrderDetailService();

    /**
     * 单例模式的私有构造方法.
     */
    private OrderDetailService() {
    }

    /**
     * 获取单例.
     * 
     * @return 单例
     */
    public static OrderDetailService getInstance() {
        return instance;
    }

    //服务端添加订单详情，是当给车辆添加服务时调用的
    public void addOrderDetailByAccount(OrderDetailPo orderdetail ,CarInfoPo carinfo,UserPo currentUser){
    	//TODO 未完成
    	orderdetail.setForeignId(carinfo.getCarInfoId());
    	orderdetail.setForeignIdType(CARTYPE);
    	SuperService service = SuperService.getInstance();
    	service.addEntity(orderdetail, currentUser);
    	
    }
    
    //客户端添加订单详情，是下订单的情况下调用的
    public void addOrderDetailByCustomer(OrderDetailPo orderdetail ,OrdersPo orders,UserPo currentUser){
    	//TODO 未完成
    	orderdetail.setForeignId(orders.getOrdersId());
    	orderdetail.setForeignIdType(ORDERSTYPE);
    	SuperService service = SuperService.getInstance();
    	service.addEntity(orderdetail, currentUser);
    	
    }
    
}
