/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.supercarrent.menu.biz.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wteamfly.supercarrent.biz.dao.SuperDaoProxy;
import com.wteamfly.supercarrent.biz.service.ResultMessage;
import com.wteamfly.supercarrent.entity.po.UserPo;
import com.wteamfly.supercarrent.menu.biz.dao.MenuDaoProxy;
import com.wteamfly.supercarrent.menu.entity.po.MenuItemPo;
import com.wteamfly.supercarrent.menu.entity.vo.MenuItemVo;

/**
 * 菜单管理Service.
 * 
 * @author 殷梓淞.
 * @since 3.1.0
 */
public final class MenuManageService {
    /**
     * log4j实例对象.
     */
    private static Logger logger = LogManager
            .getLogger(MenuManageService.class);

    /**
     * 单例对象.
     */
    private static MenuManageService instance = new MenuManageService();

    /**
     * 单例模式的私有构造方法.
     */
    private MenuManageService() {
    }

    /**
     * 获取单例.
     * 
     * @return 单例
     */
    public static MenuManageService getInstance() {
        return instance;
    }

    /**
     * 获取菜单.
     * 
     * @return ResultMessage 结果集合.
     */
    public ResultMessage getMenuItem() {
        logger.debug("进入getMenuItem方法");
        ResultMessage rs = new ResultMessage();
        try {
            MenuItemPo poForQuery = new MenuItemPo();
            poForQuery.setIsDelete(false);
            SuperDaoProxy daoProxy = SuperDaoProxy.getInstance();
            @SuppressWarnings("unchecked")
            List<MenuItemPo> listPo = (List<MenuItemPo>) daoProxy
                    .queryList(poForQuery);
            MenuItemVo menuItemVo;
            List<MenuItemVo> listVo = new ArrayList<MenuItemVo>();
            for (MenuItemPo menuItemPo : listPo) {
                menuItemVo = new MenuItemVo();
                menuItemVo.poToVo(menuItemPo);
                listVo.add(menuItemVo);
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("menuItemList", listVo);
            rs.setResultParm(map);
            rs.setResultInfo("查询菜单列表成功");
            rs.setServiceResult(true);
        } catch (Exception e) {
            logger.error(e);
        }

        logger.debug("退出getMenuItem方法");
        return rs;
    }

    /**
     * 获取用户菜单.
     * 
     * @param userPo
     *            用户实体.
     * @param menuItemPo
     *            菜单实体.
     * @return ResultMessage 结果集合.
     */
    public ResultMessage getUserMenuItem(final UserPo userPo,
            final MenuItemPo menuItemPo) {
        logger.debug("进入getRoleMenuItem方法");
        ResultMessage rs = new ResultMessage();
        try {
            MenuItemPo poForQuery = new MenuItemPo();
            poForQuery.setIsDelete(false);
            poForQuery.setMenuName(menuItemPo.getMenuName());
            MenuDaoProxy daoProxy = MenuDaoProxy.getInstance();
            List<MenuItemPo> listPo = daoProxy.getUserMenuItem(userPo,
                    poForQuery);
            MenuItemVo menuItemVo;
            List<MenuItemVo> listVo = new ArrayList<MenuItemVo>();
            for (MenuItemPo menuItemPo2 : listPo) {
                menuItemVo = new MenuItemVo();
                menuItemVo.poToVo(menuItemPo2);
                listVo.add(menuItemVo);
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("menuItemList", listVo);
            rs.setResultParm(map);
            rs.setResultInfo("查询菜单列表成功");
            rs.setServiceResult(true);
        } catch (Exception e) {
            logger.error(e);
            System.out.println(e);
        }

        logger.debug("退出getRoleMenuItem方法");
        return rs;
    }
}
