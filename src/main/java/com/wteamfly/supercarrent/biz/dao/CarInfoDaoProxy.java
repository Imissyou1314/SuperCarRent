/**
 * Copyright (c) 2007-2015 WteamFly.  All rights reserved. 网飞网络公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.supercarrent.biz.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.wteamfly.supercarrent.entity.po.CarInfoPo;
import com.wteamfly.supercarrent.entity.po.PaginationPo;
import com.wteamfly.supercarrent.helper.ConstantVar;
import com.wteamfly.supercarrent.helper.HibernateHelper;

/**
 * 汽车管理DaoProxy.
 * 
 * @author 侯骏雄
 * @since 3.0.0
 */
public final class CarInfoDaoProxy {
	/**
	 * log4j实例对象.
	 */
	private static Logger logger = LogManager.getLogger(CarInfoDaoProxy.class);

	/**
	 * 单例对象.
	 */
	private static CarInfoDaoProxy instance = new CarInfoDaoProxy();

	/**
	 * 单例模式的私有构造方法.
	 */
	private CarInfoDaoProxy() {
	}

	/**
	 * 获取单例.
	 * 
	 * @return 单例
	 */
	public static CarInfoDaoProxy getInstance() {
		return instance;
	}

	/**
	 * 根据价格从小到大排序
	 * 
	 * @param paginationPo
	 *            分页实体.
	 * @return result 车所有信息排序后的列表
	 */
	@SuppressWarnings("unchecked")
	public final PaginationPo orderCarsByMoney(PaginationPo paginationPo) {
		logger.debug("orderCarsByMoney进入方法");
		PaginationPo result = null;
		// 业务逻辑开始
		HibernateHelper.getSessionFactory().getCurrentSession()
				.beginTransaction();
		Criteria fcriteria = HibernateHelper.getSessionFactory()
				.getCurrentSession().createCriteria(CarInfoPo.class);
		fcriteria.add(Restrictions.eq("carState", ConstantVar.STATEWITE));
		// 根据价格从小到大排序
		fcriteria.addOrder(Order.asc("carPrice"));
		paginationPo.setList(fcriteria.list());
		result = paginationPo;
		// 业务结束
		HibernateHelper.getSessionFactory().getCurrentSession()
				.getTransaction().commit();
		logger.debug("退出方法");
		return result;
	}

	/**
	 * 
	 * @param paginationPo
	 *            分页实体.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PaginationPo queryALLCarList(PaginationPo paginationPo) {
		logger.debug("进入方法");
		PaginationPo result = null;
		String hql = "from CarInfoPo";
		// 业务逻辑开始
		HibernateHelper.getSessionFactory().getCurrentSession()
				.beginTransaction();
		List<CarInfoPo> list = HibernateHelper.getSessionFactory()
				.getCurrentSession().createQuery(hql).list();
		paginationPo.setList(list);
		result = paginationPo;
		// 业务结束
		HibernateHelper.getSessionFactory().getCurrentSession()
				.getTransaction().commit();
		logger.debug("退出方法");
		return result;
	}
}
