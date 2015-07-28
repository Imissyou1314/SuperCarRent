/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.superW.menu.biz.dao;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.dbunit.dataset.DefaultDataSet;
import org.dbunit.dataset.DefaultTable;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wteamfly.superW.testhelper.TestDBHelper;
import com.wteamfly.supercarrent.entity.po.RolePo;
import com.wteamfly.supercarrent.entity.po.UserPo;
import com.wteamfly.supercarrent.menu.biz.dao.MenuDaoProxy;
import com.wteamfly.supercarrent.menu.entity.po.MenuItemPo;

/**
 * 菜单DaoProxy测试类.
 * 
 * @since
 *
 */
public class MenuDaoProxyTest {

    /**
     * 测试启动前方法.
     * 
     * @throws java.lang.Exception
     *             普通异常.
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        // 获取原数据库内容
        IDataSet backupDataset = TestDBHelper.backupDataBase();
        // 构建测试用的数据
        // 指定测试用到的表
        DefaultTable userTable = new DefaultTable("t_user", backupDataset
                .getTable("t_user").getTableMetaData().getColumns());
        DefaultTable roleTable = new DefaultTable("t_role", backupDataset
                .getTable("t_role").getTableMetaData().getColumns());
        DefaultTable menuItemTable = new DefaultTable("t_menuitem",
                backupDataset.getTable("t_menuitem").getTableMetaData()
                        .getColumns());
        DefaultTable rolemenuitemmapTable = new DefaultTable(
                "t_rolemenuitemmap", backupDataset
                        .getTable("t_rolemenuitemmap").getTableMetaData()
                        .getColumns());

        // 写入数据
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .parse("2012-01-12 23:30:20");
        Object[] userData1 = { Long.valueOf("1"), "user1",
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", date, Long.valueOf("1"),
                null, null, Byte.valueOf("0"), Byte.valueOf("0"),
                Long.valueOf("0") };
        userTable.addRow(userData1);

        Object[] roleData1 = { Long.valueOf("1"), "访客", date,
                Long.valueOf("1"), null, null, Byte.valueOf("0"),
                Byte.valueOf("0"), Long.valueOf("0") };
        roleTable.addRow(roleData1);
        Object[] roleData2 = { Long.valueOf("2"), "超级管理员", date,
                Long.valueOf("1"), null, null, Byte.valueOf("0"),
                Byte.valueOf("0"), Long.valueOf("0") };
        roleTable.addRow(roleData2);

        Object[] roleData4 = { Long.valueOf("4"), "role4", date,
                Long.valueOf("1"), null, null, Byte.valueOf("0"),
                Byte.valueOf("0"), Long.valueOf("0") };
        roleTable.addRow(roleData4);
        // testEditRole使用
        Object[] roleData5 = { Long.valueOf("5"), "role5", date,
                Long.valueOf("1"), null, null, Byte.valueOf("0"),
                Byte.valueOf("0"), Long.valueOf("0") };
        roleTable.addRow(roleData5);
        // testPageRole使用
        Object[] roleData6 = { Long.valueOf("6"), "page", date,
                Long.valueOf("1"), null, null, Byte.valueOf("0"),
                Byte.valueOf("0"), Long.valueOf("0") };
        roleTable.addRow(roleData6);

        Object[] menuItemData1 = { 1, "MenuName", "menuItemName1", "icon1", 1,
                "sfds", "sdf", "sdf", 0, "2015-06-15 15:15:27", "1",
                "2015-06-15 15:15:27", null, '0', '0', 0 };
        menuItemTable.addRow(menuItemData1);
        Object[] menuItemData2 = { 2, "MenuName", "menuItemName2", "icon1", 1,
                "sfds", "sdf", "sdf", 0, "2015-06-15 15:15:27", "1",
                "2015-06-15 15:15:27", null, '0', '0', 0 };
        menuItemTable.addRow(menuItemData2);
        Object[] menuItemData3 = { Long.valueOf("3"), "MenuName",
                "menuItemName3", "icon1", 1, "sfds", "sdf", "sdf", 0,
                "2015-06-15 15:15:27", "1", "2015-06-15 15:15:27", null, '0',
                '0', 0 };
        menuItemTable.addRow(menuItemData3);
        Object[] menuItemData4 = { Long.valueOf("4"), "MenuName",
                "menuItemName4", "icon1", 1, "sfds", "sdf", "sdf", 0,
                "2015-06-15 15:15:27", "1", "2015-06-15 15:15:27", null, '0',
                '0', 0 };
        menuItemTable.addRow(menuItemData4);

        Object[] rolemenuitemmapData1 = { 1, 1, 1, "2015-06-15 15:16:40", 1,
                "2015-06-15 15:15:27", null, 0, 0, null };
        rolemenuitemmapTable.addRow(rolemenuitemmapData1);
        Object[] rolemenuitemmapData2 = { 2, 2, 1, "2015-06-15 15:16:40", 1,
                "2015-06-15 15:15:27", null, 0, 0, null };
        rolemenuitemmapTable.addRow(rolemenuitemmapData2);
        Object[] rolemenuitemmapData3 = { Long.valueOf("3"), 1, 2,
                "2015-06-15 15:16:40", 1, "2015-06-15 15:15:27", null, 0, 0,
                null };
        rolemenuitemmapTable.addRow(rolemenuitemmapData3);
        Object[] rolemenuitemmapData4 = { Long.valueOf("4"), 2,
                Long.valueOf("3"), "2015-06-15 15:16:40", 1,
                "2015-06-15 15:15:27", null, 0, 0, null };
        rolemenuitemmapTable.addRow(rolemenuitemmapData4);
        Object[] rolemenuitemmapData5 = { Long.valueOf("5"), 2,
                Long.valueOf("4"), "2015-06-15 15:16:40", 1,
                "2015-06-15 15:15:27", null, 0, 0, null };
        rolemenuitemmapTable.addRow(rolemenuitemmapData5);

        // 组合表
        DefaultTable[] tables = { userTable, roleTable, menuItemTable,
                rolemenuitemmapTable };
        // 创建测试数据库的引用以准备写入数据库中
        IDataSet tempDataSet = new DefaultDataSet(tables);
        // 写入数据库
        DatabaseOperation.CLEAN_INSERT.execute(TestDBHelper.getConn(),
                tempDataSet);
    }

    /**
     * 测试结束方法.
     * 
     * @throws java.lang.Exception
     *             普通异常.
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        TestDBHelper.restoreDataBase();
    }

    /**
     * 获取用户菜单项.
     * 
     * @throws ParseException
     *             转换异常.
     */
    @Test
    public final void testGetUserMenuItem() throws ParseException {
        UserPo uPo = new UserPo();
        uPo.setUserId(Long.valueOf("1"));
        uPo.setLoginName("user1");
        uPo.setPassword("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .parse("2012-01-12 23:30:20");
        uPo.setCreateTime(date);
        uPo.setCreatorId(Long.valueOf("1"));
        uPo.setIsDelete(false);
        uPo.setIsLockUp(false);
        uPo.setVersion(Long.valueOf("0"));
        MenuItemPo menuItemPo = new MenuItemPo();
        menuItemPo.setMenuName("MenuName");
        MenuDaoProxy menuDaoProxy = MenuDaoProxy.getInstance();
        List<MenuItemPo> list = menuDaoProxy.getUserMenuItem(uPo, menuItemPo);
        boolean rs = list.size() == Long.valueOf("3");
        assertTrue("获取失败", rs);
    }
    /**
     * 获取角色菜单项.
     * 
     * @throws ParseException
     *             转换异常.
     */
    @Test
    public final void testGetRoleMenuItem() throws ParseException {
        RolePo rolePo = new RolePo();
        rolePo.setRoleId(2L);
        MenuDaoProxy menuDaoProxy = MenuDaoProxy.getInstance();
        List<MenuItemPo> list = menuDaoProxy.getRoleMenuItem(rolePo);
        boolean rs = list.size() == Long.valueOf("3");
        assertTrue("获取失败", rs);
    }
    
}
