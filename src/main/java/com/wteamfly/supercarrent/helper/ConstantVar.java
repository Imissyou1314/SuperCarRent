/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.supercarrent.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * http访问操作工具类.
 * 
 * @since 3.0.0
 */
public final class ConstantVar {
    /**
     * log4j实例对象.
     */
    private static Logger logger = LogManager.getLogger(ConstantVar.class
            .getName());

    /**
     * OderDetail中设定订单的类型为Car.
     */
    public final static String CARTYPE = "car";
    
    /**
     * OrderDetail中设定订单的类型为Order;
     */
    public final static String ORDERSTYPE = "order";
    
    /**
     * OrderDetail中设定订单的类型为tmpOrder;
     */
    public final static String TMPORDERSTYPE = "tmporder";
    
    /**
     * 汽车状态等待租出.
     */
    public final static Integer STATEWITE= 0 ;
    
    /**
     * 汽车状态真在使用.
     */
    public final static Integer STATEUSE = 1 ;
    
    /**
     *订单状态，下单成功.
     */
    public final static Integer READYSTATE = 1;
    
}
