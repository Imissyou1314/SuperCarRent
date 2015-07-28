/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.supercarrent.biz.dao;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.wteamfly.supercarrent.entity.po.PaginationPo;
import com.wteamfly.supercarrent.entity.po.PermissionPo;
import com.wteamfly.supercarrent.entity.po.RolePermissionMapPo;
import com.wteamfly.supercarrent.helper.HibernateHelper;

/**
 * 角色行为映射管理Daoproxy类.
 * 
 * @author 殷梓淞
 * @since 3.0.0
 */
public final class RolePermissionMapDaoproxy {
    /**
     * log4j实例对象.
     */
    private static Logger logger = LogManager
            .getLogger(RolePermissionMapDaoproxy.class);

    /**
     * 单例对象.
     */
    private static RolePermissionMapDaoproxy instance = new RolePermissionMapDaoproxy();

    /**
     * 单例模式的私有构造方法.
     */
    private RolePermissionMapDaoproxy() {
    }

    /**
     * 获取单例.
     * 
     * @return 单例
     */
    public static RolePermissionMapDaoproxy getInstance() {
        return instance;
    }

    /**
     * 查询角色未拥有行为列表.
     * 
     * @param pageInfo
     *            分页实体.
     * @param rolePermissionMapPo
     *            角色行为关系.
     * @return 行为列表.
     * @throws Exception 普通异常.
     */
    @SuppressWarnings("unchecked")
    public PaginationPo pageNotHasRolePermission(
            final PaginationPo pageInfo,
            final RolePermissionMapPo rolePermissionMapPo) throws Exception {
        logger.debug("进入方法pageNotHasRolePermission");
        PaginationPo result = null;
            
            // 业务逻辑开始
            HibernateHelper.getSessionFactory().getCurrentSession()
                    .beginTransaction();
            Criteria criteria = HibernateHelper.getSessionFactory()
                    .getCurrentSession().createCriteria(PermissionPo.class);
            criteria.add(Restrictions.eq("isLockUp", false));
            criteria.add(Restrictions.eq("isDelete", false));
            criteria.add(Restrictions.sqlRestriction(" permissionId not in  "
                    + " (select permissionId from t_rolepermissionmap where RoleId ='"
                    + rolePermissionMapPo.getRoleId() + "')"));
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
    
    /**
     * 查询角色拥有行为列表.
     * 
     * @param pageInfo
     *            分页实体.
     * @param rolePermissionMapPo
     *            角色行为关系.
     * @return 行为列表.
     * @throws Exception 普通异常.
     */
    @SuppressWarnings("unchecked")
    public PaginationPo pageRolePermission(
            final PaginationPo pageInfo,
            final RolePermissionMapPo rolePermissionMapPo) throws Exception {
        logger.debug("进入方法pageNotHasRolePermission");
        PaginationPo result = null;
            
            // 业务逻辑开始
            HibernateHelper.getSessionFactory().getCurrentSession()
                    .beginTransaction();
            Criteria criteria = HibernateHelper.getSessionFactory()
                    .getCurrentSession().createCriteria(PermissionPo.class);
            criteria.add(Restrictions.eq("isLockUp", false));
            criteria.add(Restrictions.eq("isDelete", false));
            criteria.add(Restrictions.sqlRestriction(" permissionId in "
                    + " (select permissionId from t_rolepermissionmap where RoleId ='"
                    + rolePermissionMapPo.getRoleId() + "')"));
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
}
