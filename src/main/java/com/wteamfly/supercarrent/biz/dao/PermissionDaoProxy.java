/**
 * Copyright (c) 2007-2015 WteamFly.  All rights reserved. 网飞网络公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.supercarrent.biz.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.wteamfly.supercarrent.entity.po.PermissionPo;
import com.wteamfly.supercarrent.entity.po.RolePermissionMapPo;
import com.wteamfly.supercarrent.entity.po.RolePo;
import com.wteamfly.supercarrent.entity.po.UserPo;
import com.wteamfly.supercarrent.entity.po.UserRoleMapPo;
import com.wteamfly.supercarrent.helper.HibernateHelper;

/**
 * 行为权限DaoProxy.
 * 
 * @author 侯骏雄
 * @since 3.0.0
 */
public final class PermissionDaoProxy {
    /**
     * log4j实例对象.
     */
    private static Logger logger = LogManager
            .getLogger(PermissionDaoProxy.class);

    /**
     * 单例对象.
     */
    private static PermissionDaoProxy instance = new PermissionDaoProxy();

    /**
     * 单例模式的私有构造方法.
     */
    private PermissionDaoProxy() {
    }

    /**
     * 获取单例.
     * 
     * @return 单例
     */
    public static PermissionDaoProxy getInstance() {
        return instance;
    }

    /**
     * 判断用户是否有该行为.
     * 
     * @param pPoForCheck
     *            用于判断的行为
     * @param uPoForCheck
     *            用于判断的用户
     * @return List<BookLevelPo> 获取业务列表.
     */
    @SuppressWarnings("unchecked")
    public boolean hasPermissionByUserId(final PermissionPo pPoForCheck,
            final UserPo uPoForCheck) {
        logger.debug("进入hasPermissionByUserId方法");
        boolean result = false;
        // 业务逻辑开始
        HibernateHelper.getSessionFactory().getCurrentSession()
                .beginTransaction();

        Criteria urmCriteria = HibernateHelper.getSessionFactory()
                .getCurrentSession().createCriteria(UserRoleMapPo.class);
        urmCriteria.add(Restrictions.eq("userId", uPoForCheck.getUserId()));
        List<UserRoleMapPo> urmList = urmCriteria.list();

        Criteria rgmCriteria = null;
        List<RolePermissionMapPo> rgmList = null;
        PermissionPo pPo = null;
        for (UserRoleMapPo urm : urmList) {
            rgmCriteria = HibernateHelper.getSessionFactory()
                    .getCurrentSession()
                    .createCriteria(RolePermissionMapPo.class);
            rgmCriteria.add(Restrictions.eq("roleId", urm.getRoleId()));
            rgmList = rgmCriteria.list();
            for (RolePermissionMapPo rgm : rgmList) {
                pPo = (PermissionPo) HibernateHelper.getSessionFactory()
                        .getCurrentSession()
                        .get(PermissionPo.class, rgm.getPermissionId());
                if (pPo.getPermissionName().equals(
                        pPoForCheck.getPermissionName())) {
                    result = true;
                    break;
                }
            }
            if (result) {
                break;
            }
        }

        // 业务逻辑结束
        HibernateHelper.getSessionFactory().getCurrentSession()
                .getTransaction().commit();
        logger.debug("退出hasPermissionByUserId方法");
        return result;
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
    public boolean hasPermissionByRoleId(final PermissionPo pPoForCheck,
            final RolePo rPoForCheck) {
        logger.debug("进入hasPermissionByRoleId方法");
        boolean result = false;
        // 业务逻辑开始
        HibernateHelper.getSessionFactory().getCurrentSession()
                .beginTransaction();
        Criteria pCriteria = HibernateHelper.getSessionFactory()
                .getCurrentSession().createCriteria(PermissionPo.class);
        pCriteria.add(Restrictions.eq("permissionName",
                pPoForCheck.getPermissionName()));
        PermissionPo pPo = (PermissionPo) pCriteria.uniqueResult();

        Criteria rgmCriteria = HibernateHelper.getSessionFactory()
                .getCurrentSession().createCriteria(RolePermissionMapPo.class);
        rgmCriteria.add(Restrictions.eq("permissionId", pPo.getPermissionId()));
        rgmCriteria.add(Restrictions.eq("roleId", rPoForCheck.getRoleId()));
        Long size = (Long) rgmCriteria.setProjection(Projections.rowCount())
                .uniqueResult();

        if (size > 0) {
            result = true;
        }

        // 业务逻辑结束
        HibernateHelper.getSessionFactory().getCurrentSession()
                .getTransaction().commit();
        logger.debug("退出hasPermissionByRoleId方法");
        return result;
    }
}
