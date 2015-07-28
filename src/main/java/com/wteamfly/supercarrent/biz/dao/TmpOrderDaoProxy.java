/**
 * Copyright (c) 2007-2015 WteamFly.  All rights reserved. 网飞网络公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.supercarrent.biz.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.wteamfly.supercarrent.entity.po.OrderDetailPo;
import com.wteamfly.supercarrent.entity.po.TmpOrdersPo;
import com.wteamfly.supercarrent.helper.HibernateHelper;

/**
 * 临时订单DaoProxy.
 * 
 * @author 黄祥谦
 * @since 3.0.0
 */
public final class TmpOrderDaoProxy {
    /**
     * log4j实例对象.
     */
    private static Logger logger = LogManager
            .getLogger(TmpOrderDaoProxy.class);

    /**
     * 单例对象.
     */
    private static TmpOrderDaoProxy instance = new TmpOrderDaoProxy();

    /**
     * 单例模式的私有构造方法.
     */
    private TmpOrderDaoProxy() {
    }

    /**
     * 获取单例.
     * 
     * @return 单例
     */
    public static TmpOrderDaoProxy getInstance() {
        return instance;
    }

    /**
     * 判断角色是否有该行为.
     * 
     * @param pPoForCheck
     *            用于判断的行为
     * @param rPoForCheck
     *            用于判断的角色
     * @return List<BookLevelPo> 获取业务列表.
     */
    public List<OrderDetailPo> getOrderDetailByTmpOId(final TmpOrdersPo pPoForCheck) {
        logger.debug("进入getOrderDetailByTmpOId方法");
        boolean result = false;
        // 业务逻辑开始
        HibernateHelper.getSessionFactory().getCurrentSession()
                .beginTransaction();
        Criteria pCriteria = HibernateHelper.getSessionFactory()
                .getCurrentSession().createCriteria(OrderDetailPo.class);
        pCriteria.add(Restrictions.eq("tmpordersId",
                pPoForCheck.getTmpOrdersId()));
        List<OrderDetailPo> ordersDetailList  = pCriteria.list();

        // 业务逻辑结束
        HibernateHelper.getSessionFactory().getCurrentSession()
                .getTransaction().commit();
        logger.debug("退出hasPermissionByRoleId方法");
        return ordersDetailList;
    }
}
