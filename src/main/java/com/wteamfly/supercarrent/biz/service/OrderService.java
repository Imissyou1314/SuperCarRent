/**
 * Copyright (c) 2007-2015 WteamFly.  All rights reserved. 网飞网络公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.supercarrent.biz.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wteamfly.supercarrent.entity.po.CarInfoPo;
import com.wteamfly.supercarrent.entity.po.OrdersPo;
import com.wteamfly.supercarrent.entity.po.UserPo;

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

    //服务端添加订单，是当给客户完成下单时调用的
    public void addOrdersByCustomer(OrdersPo orders ,CarInfoPo carinfo,UserPo currentUser){
    	//TODO 未完成
    	orders.setCarInfoId(carinfo.getCarInfoId());
    	SuperService service = SuperService.getInstance();
    	service.addEntity(orders, currentUser);
    	//设置订单的外键 车id时，同时设置OrderDetail的外键为Ordersid
    	
    	//通过车id查出类型为必备的OrderDetail
    }
    
}
