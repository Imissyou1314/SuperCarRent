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
import com.wteamfly.supercarrent.entity.po.RolePo;
import com.wteamfly.supercarrent.menu.biz.dao.MenuDaoProxy;
import com.wteamfly.supercarrent.menu.entity.po.MenuItemPo;
import com.wteamfly.supercarrent.menu.entity.po.RoleMenuItemMapPo;
import com.wteamfly.supercarrent.menu.entity.vo.MenuItemVo;
import com.wteamfly.supercarrent.menu.entity.vo.RoleMenuItemMapVo;

/**
 * 角色菜单关系管理.
 * 
 * @author 殷梓淞.
 * @since 3.1.0
 */
public final class RoleMenuItemMapService {
    /**
     * log4j实例对象.
     */
    private static Logger logger = LogManager
            .getLogger(RoleMenuItemMapService.class);

    /**
     * 单例对象.
     */
    private static RoleMenuItemMapService instance = new RoleMenuItemMapService();

    /**
     * 单例模式的私有构造方法.
     */
    private RoleMenuItemMapService() {
    }

    /**
     * 获取单例.
     * 
     * @return 单例
     */
    public static RoleMenuItemMapService getInstance() {
        return instance;
    }

    /**
     * 获取角色菜单.
     * 
     * @param rolePo
     *            角色实体.
     * @return ResultMessage 结果集合.
     */
    public ResultMessage getRoleMenuItem(final RolePo rolePo) {
        logger.debug("进入getRoleMenuItem方法");
        ResultMessage rs = new ResultMessage();
        try {
            MenuDaoProxy daoProxy = MenuDaoProxy.getInstance();
            List<MenuItemPo> listPo = daoProxy.getRoleMenuItem(rolePo);
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

    /**
     * 根据角色id获取角色菜单关系.
     * 
     * @param roleMenuItemMapPo
     *            角色菜单关系实体.
     * @return 结果集合.
     */
    public ResultMessage queryRoleMenuItemMap(
            final RoleMenuItemMapPo roleMenuItemMapPo) {
        logger.debug("进入queryRoleMenuItemMap方法");
        ResultMessage rs = new ResultMessage();
        try {
            RoleMenuItemMapPo poForQuery = new RoleMenuItemMapPo();
            poForQuery.setRoleId(roleMenuItemMapPo.getRoleId());

            SuperDaoProxy daoProxy = SuperDaoProxy.getInstance();
            @SuppressWarnings("unchecked")
            List<RoleMenuItemMapPo> listPos = (List<RoleMenuItemMapPo>) daoProxy
                    .queryList(poForQuery);
            List<RoleMenuItemMapVo> listVos = new ArrayList<RoleMenuItemMapVo>();
            RoleMenuItemMapVo vo;
            for (RoleMenuItemMapPo roleMenuItemMapPo2 : listPos) {
                vo = new RoleMenuItemMapVo();
                vo.poToVo(roleMenuItemMapPo2);
                listVos.add(vo);
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("roleMenuItemMapList", listVos);
            rs.setResultParm(map);
            rs.setResultInfo("查询角色菜单关系列表成功");
            rs.setServiceResult(true);
        } catch (Exception e) {
            logger.error(e);
            System.out.println(e);
        }
        logger.debug("退出queryRoleMenuItemMap方法");
        return rs;
    }
}
