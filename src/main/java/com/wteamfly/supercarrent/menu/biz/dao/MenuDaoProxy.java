/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.supercarrent.menu.biz.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wteamfly.supercarrent.entity.po.RolePo;
import com.wteamfly.supercarrent.entity.po.UserPo;
import com.wteamfly.supercarrent.helper.HibernateHelper;
import com.wteamfly.supercarrent.menu.entity.po.MenuItemPo;

/**
 * 菜单管理daoProxy层.
 * @author 殷梓淞.
 * @since 3.1.0
 */
public final class MenuDaoProxy {

    /**
     * log4j实例对象.
     */
    private static Logger logger = LogManager.getLogger(MenuDaoProxy.class);

    /**
     * 单例对象.
     */
    private static MenuDaoProxy instance = new MenuDaoProxy();

    /**
     * 单例模式的私有构造方法.
     */
    private MenuDaoProxy() {

    }

    /**
     * 获取单例.
     * 
     * @return 单例
     */
    public static MenuDaoProxy getInstance() {
        return instance;
    }

    /**
     * 获取用户权限菜单项.
     * 
     * @param userPo
     *            用户实体.
     * @param menuItemPo
     *            菜单实体.
     * @return List<MenuItemPo> 菜单实体列表.
     */
    public List<MenuItemPo> getUserMenuItem(final UserPo userPo,
            final MenuItemPo menuItemPo) {
        logger.debug("进入方法getUserMenuItem");
        // 业务逻辑开始
        HibernateHelper.getSessionFactory().getCurrentSession()
                .beginTransaction();

        String sql = " select * from t_menuitem "
                + " where IsDelete = 0 and MenuItemId in (select MenuItemId from t_rolemenuitemmap "
                + " where IsDelete = 0 and RoleId in(select RoleId from t_userrolemap "
                + " where IsDelete = 0 and UserId = " + userPo.getUserId()
                + " )) and MenuName = '" + menuItemPo.getMenuName()
                + " ' order by MenuItemOrder asc";
        @SuppressWarnings("unchecked")
        List<MenuItemPo> list = HibernateHelper.getSessionFactory()
                .getCurrentSession().createSQLQuery(sql)
                .addEntity(MenuItemPo.class).list();
        HibernateHelper.getSessionFactory().getCurrentSession()
                .getTransaction().commit();
        logger.debug("退出方法getUserMenuItem");
        return list;
    }

    /**
     * 获取角色权限菜单项.
     * 
     * @param rolePo
     *            角色实体.
     * @return List<MenuItemPo> 菜单实体列表.
     */
    public List<MenuItemPo> getRoleMenuItem(final RolePo rolePo) {
        logger.debug("进入方法getRoleMenuItem");
        // 业务逻辑开始
        HibernateHelper.getSessionFactory().getCurrentSession()
                .beginTransaction();

        String sql = " select * from t_menuitem where IsDelete = 0 and menuitemid in "
                + " (select MenuItemId from t_rolemenuitemmap where IsDelete = 0 and RoleId = '"
                + rolePo.getRoleId() + "')";
        @SuppressWarnings("unchecked")
        List<MenuItemPo> list = HibernateHelper.getSessionFactory()
                .getCurrentSession().createSQLQuery(sql)
                .addEntity(MenuItemPo.class).list();
        HibernateHelper.getSessionFactory().getCurrentSession()
                .getTransaction().commit();
        logger.debug("退出方法getRoleMenuItem");
        return list;
    }
}
