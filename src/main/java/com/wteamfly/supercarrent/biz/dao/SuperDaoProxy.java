/**
 * Copyright (c) 2007-2015 WteamFly.  All rights reserved. 网飞网络公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.supercarrent.biz.dao;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.Id;

import org.apache.commons.beanutils.BeanMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.wteamfly.supercarrent.entity.po.PaginationPo;
import com.wteamfly.supercarrent.entity.po.SuperPersistentObject;
import com.wteamfly.supercarrent.entity.po.UserPo;
import com.wteamfly.supercarrent.helper.HibernateHelper;

/**
 * 超级DaoProxy类.
 * 
 * @author 侯骏雄
 * @since 3.0.0
 */
public final class SuperDaoProxy {
    /**
     * log4j实例对象.
     */
    private static Logger logger = LogManager.getLogger(SuperDaoProxy.class);

    /**
     * 单例对象.
     */
    private static SuperDaoProxy instance = new SuperDaoProxy();

    /**
     * 单例模式的私有构造方法.
     */
    private SuperDaoProxy() {
    }

    /**
     * 获取单例.
     * 
     * @return 单例
     */
    public static SuperDaoProxy getInstance() {
        return instance;
    }

    /**
     * 添加实体.
     * 
     * @param entityPo
     *            实体.
     * @param currentUser
     *            当前操作用户，数据库记录该用户进行添加.
     * @throws Exception
     *             通用异常.
     */
    public void addEntity(final SuperPersistentObject entityPo,
            final UserPo currentUser) throws Exception {
        logger.debug("进入addEntity方法");
        try {
            // 业务逻辑开始
            HibernateHelper.getSessionFactory().getCurrentSession()
                    .beginTransaction();
            entityPo.getClass().getMethod("setCreatorId", Long.class)
                    .invoke(entityPo, currentUser.getUserId());
            HibernateHelper.getSessionFactory().getCurrentSession()
                    .save(entityPo);
            // 业务逻辑结束
            HibernateHelper.getSessionFactory().getCurrentSession()
                    .getTransaction().commit();
        } catch (Exception ex) {
            HibernateHelper.getSessionFactory().getCurrentSession()
                    .getTransaction().rollback();
            logger.error(ex);
            ex.printStackTrace();
            throw new Exception(ex);
        }
        logger.debug("退出addEntity方法");
    }

    /**
     * 删除实体.
     * 
     * @param entityPo
     *            实体.
     * @throws Exception
     *             通用异常.
     */
    public void deleteEntity(final SuperPersistentObject entityPo)
            throws Exception {
        logger.debug("进入deleteEntity方法");
        try {
            // 业务逻辑开始
            HibernateHelper.getSessionFactory().getCurrentSession()
                    .beginTransaction();

            Map<String, Object> notNullParam = null;
            BeanMap beanMap = new BeanMap(entityPo);
            notNullParam = new HashMap<String, Object>();
            @SuppressWarnings("unchecked")
            Iterator<Object> keyIterator = beanMap.keySet().iterator();
            String propertyName = null;
            while (keyIterator.hasNext()) {
                propertyName = (String) keyIterator.next();
                if (!propertyName.equals("class")
                        && beanMap.get(propertyName) != null) {
                    notNullParam.put(propertyName, beanMap.get(propertyName));
                }
            }

            Criteria criteria = HibernateHelper.getSessionFactory()
                    .getCurrentSession().createCriteria(entityPo.getClass());
            for (String key : notNullParam.keySet()) {
                criteria.add(Restrictions.eq(key, notNullParam.get(key)));
            }

            List<?> result = criteria.list();

            for (int i = 0; i < result.size(); i++) {
                HibernateHelper.getSessionFactory().getCurrentSession()
                        .delete(result.get(i));
            }

            // 业务逻辑结束
            HibernateHelper.getSessionFactory().getCurrentSession()
                    .getTransaction().commit();
        } catch (Exception ex) {
            HibernateHelper.getSessionFactory().getCurrentSession()
                    .getTransaction().rollback();
            logger.error(ex);
            ex.printStackTrace();
            throw new Exception(ex);
        }
        logger.debug("退出deleteEntity方法");
    }

    /**
     * 逻辑删除实体.
     * 
     * @param entityPo
     *            实体.
     * @param currentUser
     *            当前操作用户，数据库记录该用户进行逻辑删除.
     * @throws Exception
     *             通用异常.
     */
    public void logicDeleteEntity(final SuperPersistentObject entityPo,
            final UserPo currentUser) throws Exception {
        logger.debug("进入logicDeleteEntity方法");
        try {
            // 业务逻辑开始
            HibernateHelper.getSessionFactory().getCurrentSession()
                    .beginTransaction();

            Class<?> entityClass = entityPo.getClass();

            Map<String, Object> notNullParam = null;
            BeanMap beanMap = new BeanMap(entityPo);
            notNullParam = new HashMap<String, Object>();
            @SuppressWarnings("unchecked")
            Iterator<Object> keyIterator = beanMap.keySet().iterator();
            String propertyName = null;
            while (keyIterator.hasNext()) {
                propertyName = (String) keyIterator.next();
                if (!propertyName.equals("class")
                        && beanMap.get(propertyName) != null) {
                    notNullParam.put(propertyName, beanMap.get(propertyName));
                }
            }

            Criteria criteria = HibernateHelper.getSessionFactory()
                    .getCurrentSession().createCriteria(entityClass);
            for (String key : notNullParam.keySet()) {
                criteria.add(Restrictions.eq(key, notNullParam.get(key)));
            }

            List<?> result = criteria.list();

            Object temp = null;
            for (int i = 0; i < result.size(); i++) {
                temp = result.get(i);
                entityClass.getMethod("setIsDelete", Boolean.class).invoke(
                        temp, true);
                entityClass.getMethod("setEditorId", Long.class).invoke(temp,
                        currentUser.getUserId());
                HibernateHelper.getSessionFactory().getCurrentSession()
                        .update(temp);
            }

            // 业务逻辑结束
            HibernateHelper.getSessionFactory().getCurrentSession()
                    .getTransaction().commit();
        } catch (Exception ex) {
            HibernateHelper.getSessionFactory().getCurrentSession()
                    .getTransaction().rollback();
            logger.error(ex);
            ex.printStackTrace();
            throw new Exception(ex);
        }
        logger.debug("退出logicDeleteEntity方法");
    }

    /**
     * 冻结实体.
     * 
     * @param entityPo
     *            实体.
     * @param currentUser
     *            当前操作用户，数据库记录该用户进行冻结.
     * @throws Exception
     *             通用异常.
     */
    public void lockUpEntity(final SuperPersistentObject entityPo,
            final UserPo currentUser) throws Exception {
        logger.debug("进入lockUpEntity方法");
        try {
            // 业务逻辑开始
            HibernateHelper.getSessionFactory().getCurrentSession()
                    .beginTransaction();

            Class<?> entityClass = entityPo.getClass();

            Map<String, Object> notNullParam = null;
            BeanMap beanMap = new BeanMap(entityPo);
            notNullParam = new HashMap<String, Object>();
            @SuppressWarnings("unchecked")
            Iterator<Object> keyIterator = beanMap.keySet().iterator();
            String propertyName = null;
            while (keyIterator.hasNext()) {
                propertyName = (String) keyIterator.next();
                if (!propertyName.equals("class")
                        && beanMap.get(propertyName) != null) {
                    notNullParam.put(propertyName, beanMap.get(propertyName));
                }
            }

            Criteria criteria = HibernateHelper.getSessionFactory()
                    .getCurrentSession().createCriteria(entityClass);
            for (String key : notNullParam.keySet()) {
                criteria.add(Restrictions.eq(key, notNullParam.get(key)));
            }

            List<?> result = criteria.list();

            Object temp = null;
            for (int i = 0; i < result.size(); i++) {
                temp = result.get(i);
                entityClass.getMethod("setIsLockUp", Boolean.class).invoke(
                        temp, true);
                entityClass.getMethod("setEditorId", Long.class).invoke(temp,
                        currentUser.getUserId());
                HibernateHelper.getSessionFactory().getCurrentSession()
                        .update(temp);
            }

            // 业务逻辑结束
            HibernateHelper.getSessionFactory().getCurrentSession()
                    .getTransaction().commit();
        } catch (Exception ex) {
            HibernateHelper.getSessionFactory().getCurrentSession()
                    .getTransaction().rollback();
            logger.error(ex);
            ex.printStackTrace();
            throw new Exception(ex);
        }
        logger.debug("退出lockUpEntity方法");
    }

    /**
     * 编辑实体.
     * 
     * @param entityPo
     *            实体.
     * @param currentUser
     *            当前操作用户，数据库记录该用户进行编辑.
     * @throws Exception
     *             通用异常.
     */
    public void editEntity(final SuperPersistentObject entityPo,
            final UserPo currentUser) throws Exception {
        logger.debug("进入editEntity方法");
        try {
            // 业务逻辑开始
            HibernateHelper.getSessionFactory().getCurrentSession()
                    .beginTransaction();

            Class<? extends SuperPersistentObject> entityClass = entityPo
                    .getClass();
            
            Long entityId = null;
            for (Method m: entityClass.getMethods()) {
                if (m.isAnnotationPresent(Id.class)) {
                    entityId = (Long) m.invoke(entityPo);
                }
            }

            Object rEntityPo = HibernateHelper.getSessionFactory()
                    .getCurrentSession().get(entityClass, entityId);

            Map<String, Object> notNullParam = null;
            BeanMap beanMap = new BeanMap(entityPo);
            notNullParam = new HashMap<String, Object>();
            @SuppressWarnings("unchecked")
            Iterator<Object> keyIterator = beanMap.keySet().iterator();
            String propertyName = null;
            while (keyIterator.hasNext()) {
                propertyName = (String) keyIterator.next();
                if (!propertyName.equals("class")
                        && beanMap.get(propertyName) != null) {
                    notNullParam.put(propertyName, beanMap.get(propertyName));
                }
            }

            BeanMap rBeanMap = new BeanMap(rEntityPo);
            for (String key : notNullParam.keySet()) {
                rBeanMap.put(key, notNullParam.get(key));
            }

            rEntityPo.getClass().getMethod("setEditorId", Long.class)
                    .invoke(rEntityPo, currentUser.getUserId());

            HibernateHelper.getSessionFactory().getCurrentSession()
                    .update(rEntityPo);

            // 业务逻辑结束
            HibernateHelper.getSessionFactory().getCurrentSession()
                    .getTransaction().commit();
        } catch (Exception ex) {
            HibernateHelper.getSessionFactory().getCurrentSession()
                    .getTransaction().rollback();
            logger.error(ex);
            ex.printStackTrace();
            throw new Exception(ex);
        }
        logger.debug("退出editEntity方法");
    }

    /**
     * 查询实体列表.
     * 
     * @param entityPo
     *            实体.
     * @return 查询结果.
     */
    @SuppressWarnings("unchecked")
    public List<? extends SuperPersistentObject> queryList(
            final SuperPersistentObject entityPo) {
        logger.debug("进入queryList方法");
        List<? extends SuperPersistentObject> result = null;

        // 业务逻辑开始
        HibernateHelper.getSessionFactory().getCurrentSession()
                .beginTransaction();

        Class<?> entityClass = entityPo.getClass();

        Map<String, Object> notNullParam = null;
        BeanMap beanMap = new BeanMap(entityPo);
        notNullParam = new HashMap<String, Object>();
        Iterator<Object> keyIterator = beanMap.keySet().iterator();
        String propertyName = null;
        while (keyIterator.hasNext()) {
            propertyName = (String) keyIterator.next();
            if (!propertyName.equals("class")
                    && beanMap.get(propertyName) != null) {
                notNullParam.put(propertyName, beanMap.get(propertyName));
            }
        }

        Criteria criteria = HibernateHelper.getSessionFactory()
                .getCurrentSession().createCriteria(entityClass);
        for (String key : notNullParam.keySet()) {
            criteria.add(Restrictions.eq(key, notNullParam.get(key)));
        }

        result = criteria.list();

        // 业务逻辑结束
        HibernateHelper.getSessionFactory().getCurrentSession()
                .getTransaction().commit();
        logger.debug("退出queryList方法");
        return result;
    }

    /**
     * 查询条目数列表.
     * 
     * @param entityPo
     *            实体.
     * @return 查询结果条目数.
     */
    public Long queryCount(final SuperPersistentObject entityPo) {
        logger.debug("进入queryCount方法");
        Long result = null;

        // 业务逻辑开始
        HibernateHelper.getSessionFactory().getCurrentSession()
                .beginTransaction();

        Class<?> entityClass = entityPo.getClass();

        Map<String, Object> notNullParam = null;
        BeanMap beanMap = new BeanMap(entityPo);
        notNullParam = new HashMap<String, Object>();
        @SuppressWarnings("unchecked")
        Iterator<Object> keyIterator = beanMap.keySet().iterator();
        String propertyName = null;
        while (keyIterator.hasNext()) {
            propertyName = (String) keyIterator.next();
            if (!propertyName.equals("class")
                    && beanMap.get(propertyName) != null) {
                notNullParam.put(propertyName, beanMap.get(propertyName));
            }
        }

        Criteria criteria = HibernateHelper.getSessionFactory()
                .getCurrentSession().createCriteria(entityClass);
        for (String key : notNullParam.keySet()) {
            criteria.add(Restrictions.eq(key, notNullParam.get(key)));
        }
        result = (Long) criteria.setProjection(Projections.rowCount())
                .uniqueResult();

        // 业务逻辑结束
        HibernateHelper.getSessionFactory().getCurrentSession()
                .getTransaction().commit();
        logger.debug("退出queryCount方法");
        return result;
    }

    /**
     * 查询分页列表.
     * 
     * @param pageInfo
     *            分页信息.
     * @param entityPo
     *            分页实体查询信息.
     * @return 分页信息.
     */
    @SuppressWarnings("unchecked")
    public PaginationPo pageEntity(final PaginationPo pageInfo,
            final SuperPersistentObject entityPo) {
        logger.debug("进入pageEntity方法");
        PaginationPo result = null;

        // 业务逻辑开始
        HibernateHelper.getSessionFactory().getCurrentSession()
                .beginTransaction();

        Class<?> entityClass = entityPo.getClass();

        Map<String, Object> notNullParam = null;
        BeanMap beanMap = new BeanMap(entityPo);
        notNullParam = new HashMap<String, Object>();
        Iterator<Object> keyIterator = beanMap.keySet().iterator();
        String propertyName = null;
        while (keyIterator.hasNext()) {
            propertyName = (String) keyIterator.next();
            if (!propertyName.equals("class")
                    && beanMap.get(propertyName) != null) {
                notNullParam.put(propertyName, beanMap.get(propertyName));
            }
        }

        Criteria criteria = HibernateHelper.getSessionFactory()
                .getCurrentSession().createCriteria(entityClass);
        for (String key : notNullParam.keySet()) {
            criteria.add(Restrictions.eq(key, notNullParam.get(key)));
        }
        pageInfo.setTotalCount((Long) criteria.setProjection(
                Projections.rowCount()).uniqueResult());
        criteria.setProjection(null);
        criteria.setFirstResult((pageInfo.getIndexPageNum() - 1)
                * pageInfo.getSize());
        criteria.setMaxResults(pageInfo.getSize());
        pageInfo.setList(criteria.list());
        result = pageInfo;

        // 业务逻辑结束
        HibernateHelper.getSessionFactory().getCurrentSession()
                .getTransaction().commit();
        logger.debug("退出pageEntity方法");
        return result;
    }

//	public SuperPersistentObject getEntityById(SuperPersistentObject entityPo) {
//		  logger.debug("进入getEntityById方法");
//	        // 业务逻辑开始
//	        HibernateHelper.getSessionFactory().getCurrentSession()
//	                .beginTransaction();
//	        Class<?> entityClass = entityPo.getClass();
//
//	          HibernateHelper.getSessionFactory()
//	                .getCurrentSession().get(entityClass, );
//
//	        // 业务逻辑结束
//	        HibernateHelper.getSessionFactory().getCurrentSession()
//	                .getTransaction().commit();
//	        logger.debug("退出getEntityById方法");
//	        return result;
//	}
}
