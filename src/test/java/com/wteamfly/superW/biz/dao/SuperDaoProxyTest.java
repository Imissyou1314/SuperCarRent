/**
 * Copyright (c) 2007-2015 WteamFly.  All rights reserved. 网飞网络公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.superW.biz.dao;

import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;
import java.sql.SQLException;
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
import com.wteamfly.supercarrent.biz.dao.SuperDaoProxy;
import com.wteamfly.supercarrent.entity.po.PaginationPo;
import com.wteamfly.supercarrent.entity.po.RolePo;
import com.wteamfly.supercarrent.entity.po.UserPo;

/**
 * 超级DaoProxy测试类.
 * 
 * @author 侯骏雄
 * @since 3.0.0
 */
public class SuperDaoProxyTest {

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
        DefaultTable roleTable = new DefaultTable("t_role", backupDataset
                .getTable("t_role").getTableMetaData().getColumns());
        DefaultTable userTable = new DefaultTable("t_user", backupDataset
                .getTable("t_user").getTableMetaData().getColumns());
        DefaultTable permissionTable = new DefaultTable("t_permission",
                backupDataset.getTable("t_permission").getTableMetaData()
                        .getColumns());
        DefaultTable userRoleTable = new DefaultTable("t_userrolemap",
                backupDataset.getTable("t_userrolemap").getTableMetaData()
                        .getColumns());
        DefaultTable rolePermissionTable = new DefaultTable(
                "t_rolepermissionmap", backupDataset
                        .getTable("t_rolepermissionmap").getTableMetaData()
                        .getColumns());

        // 写入数据
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .parse("2012-01-12 23:30:20");
        Object[] userData1 = { Long.valueOf("1"), "user1",
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", date, Long.valueOf("1"),
                null, null, Byte.valueOf("0"), Byte.valueOf("0"),
                Long.valueOf("0") };
        userTable.addRow(userData1);
        // testAddEntity使用
        // Object[] roleData1 = { Long.valueOf("1"), "role1", date,
        // Long.valueOf("0"), null, null, Byte.valueOf("0"),
        // Byte.valueOf("0"), Long.valueOf("0") };
        // roleTable.addRow(roleData1);
        // testDeleteEntity使用
        Object[] roleData2 = { Long.valueOf("2"), "role2", date,
                Long.valueOf("0"), null, null, Byte.valueOf("0"),
                Byte.valueOf("0"), Long.valueOf("0") };
        roleTable.addRow(roleData2);
        // testLogicDeleteEntity使用
        Object[] roleData3 = { Long.valueOf("3"), "role3", date,
                Long.valueOf("0"), null, null, Byte.valueOf("0"),
                Byte.valueOf("0"), Long.valueOf("0") };
        roleTable.addRow(roleData3);
        // testLockUpEntity使用
        Object[] roleData4 = { Long.valueOf("4"), "role4", date,
                Long.valueOf("0"), null, null, Byte.valueOf("0"),
                Byte.valueOf("0"), Long.valueOf("0") };
        roleTable.addRow(roleData4);
        // testEditEntity使用
        Object[] roleData5 = { Long.valueOf("5"), "role5", date,
                Long.valueOf("0"), null, null, Byte.valueOf("0"),
                Byte.valueOf("0"), Long.valueOf("0") };
        roleTable.addRow(roleData5);
        // testQueryList使用
        Object[] roleData6 = { Long.valueOf("6"), "role6", date,
                Long.valueOf("0"), null, null, Byte.valueOf("0"),
                Byte.valueOf("0"), Long.valueOf("0") };
        roleTable.addRow(roleData6);
        // testQueryCount使用
        Object[] roleData7 = { Long.valueOf("7"), "role7", date,
                Long.valueOf("0"), null, null, Byte.valueOf("0"),
                Byte.valueOf("0"), Long.valueOf("0") };
        roleTable.addRow(roleData7);
        // testPageEntity使用
        Object[] roleData8 = { Long.valueOf("8"), "page", date,
                Long.valueOf("0"), null, null, Byte.valueOf("0"),
                Byte.valueOf("0"), Long.valueOf("0") };
        roleTable.addRow(roleData8);
        // testPageEntity使用
        Object[] roleData9 = { Long.valueOf("9"), "page", date,
                Long.valueOf("0"), null, null, Byte.valueOf("0"),
                Byte.valueOf("0"), Long.valueOf("0") };
        roleTable.addRow(roleData9);
        // testPageEntity使用
        Object[] roleData10 = { Long.valueOf("999999"), "page", date,
                Long.valueOf("0"), null, null, Byte.valueOf("0"),
                Byte.valueOf("0"), Long.valueOf("0") };
        roleTable.addRow(roleData10);

        // 组合表
        DefaultTable[] tables = { roleTable, userTable, permissionTable,
                userRoleTable, rolePermissionTable };
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
     * addEntity测试方法.
     * 
     * @throws Exception
     *             通用异常.
     */
    @Test
    public final void testAddEntity() throws Exception {
        RolePo rolePo = new RolePo();
        rolePo.setRoleName("role1");
        rolePo.setCreatorId(Long.valueOf("1"));
        UserPo uPo = new UserPo();
        uPo.setUserId(Long.valueOf("1"));
        SuperDaoProxy daoProxy = SuperDaoProxy.getInstance();
        daoProxy.addEntity(rolePo, uPo);
        String sql = "select count(*) size from t_role "
                + "where RoleName='role1';";
        ResultSet resultSet = TestDBHelper.executeQuery(sql);
        boolean result = false;
        if (resultSet.next()) {
            long size = resultSet.getLong("size");
            result = size == 1;
        }
        assertTrue("保存成功时失败", result);
    }

    /**
     * deleteEntity测试方法.
     * 
     * @throws Exception
     *             通用异常.
     */
    @Test
    public final void testDeleteEntity() throws Exception {
        SuperDaoProxy daoProxy = SuperDaoProxy.getInstance();
        RolePo po = new RolePo();
        po.setRoleId(Long.valueOf("999999"));
        daoProxy.deleteEntity(po);

        String sql = "select count(*) size from t_role where RoleId = 999999;";
        ResultSet resultSet = TestDBHelper.executeQuery(sql);
        boolean result = false;
        if (resultSet.next()) {
            long size = resultSet.getLong("size");
            result = size == 0;
        }
        assertTrue("删除成功时失败", result);
    }

    /**
     * logicDeleteEntity测试方法.
     * 
     * @throws Exception
     *             通用异常.
     */
    @Test
    public final void testLogicDeleteEntity() throws Exception {
        SuperDaoProxy daoProxy = SuperDaoProxy.getInstance();
        RolePo po = new RolePo();
        po.setRoleId(Long.valueOf("3"));
        UserPo uPo = new UserPo();
        uPo.setUserId(Long.valueOf("1"));
        daoProxy.logicDeleteEntity(po, uPo);

        String sql = "select count(*) size from t_role where RoleId = 3 and isDelete = true;";
        ResultSet resultSet = TestDBHelper.executeQuery(sql);
        boolean result = false;
        if (resultSet.next()) {
            long size = resultSet.getLong("size");
            result = size == 1;
        }
        assertTrue("逻辑删除成功时失败", result);
    }

    /**
     * lockUpEntity测试方法.
     * 
     * @throws Exception
     *             通用异常.
     */
    @Test
    public final void testLockUpEntity() throws Exception {
        SuperDaoProxy daoProxy = SuperDaoProxy.getInstance();
        RolePo po = new RolePo();
        po.setRoleId(Long.valueOf("4"));
        UserPo uPo = new UserPo();
        uPo.setUserId(Long.valueOf("1"));
        daoProxy.lockUpEntity(po, uPo);

        String sql = "select count(*) size from t_role where RoleId = 4 and isLockUp = true;";
        ResultSet resultSet = TestDBHelper.executeQuery(sql);
        boolean result = false;
        if (resultSet.next()) {
            long size = resultSet.getLong("size");
            result = size == 1;
        }
        assertTrue("冻结成功时失败", result);
    }

    /**
     * editEntity测试方法.
     * 
     * @throws Exception
     *             通用异常.
     */
    @Test
    public final void testEditEntity() throws Exception {
        SuperDaoProxy daoProxy = SuperDaoProxy.getInstance();
        RolePo rPo = new RolePo();
        rPo.setRoleId(Long.valueOf("5"));
        rPo.setRoleName("role55");
        UserPo uPo = new UserPo();
        uPo.setUserId(Long.valueOf("1"));
        daoProxy.editEntity(rPo, uPo);

        String sql = "select count(*) size from t_role "
                + " where RoleName = 'role55' and RoleId = 5;";
        ResultSet resultSet = TestDBHelper.executeQuery(sql);
        boolean result = false;
        if (resultSet.next()) {
            long size = resultSet.getLong("size");
            result = size == 1;
        }
        assertTrue("修改成功时失败", result);
    }

    /**
     * queryList测试方法.
     * 
     * @throws SQLException
     *             sql异常.
     */
    @Test
    public final void testQueryList() throws SQLException {
        SuperDaoProxy daoProxy = SuperDaoProxy.getInstance();
        RolePo rPo = new RolePo();
        rPo.setRoleName("role6");
        @SuppressWarnings("unchecked")
        List<RolePo> list = (List<RolePo>) daoProxy.queryList(rPo);

        String sql = "select roleId from t_role "
                + " where RoleName = 'role6';";
        ResultSet resultSet = TestDBHelper.executeQuery(sql);
        boolean result = false;
        int size = 0;
        while (resultSet.next()) {
            for (RolePo po : list) {
                if (po.getRoleId().equals(resultSet.getLong("roleId"))) {
                    size++;
                    break;
                }
            }
        }
        result = size == list.size();
        assertTrue("查询成功时失败", result);
    }

    /**
     * queryCount测试方法.
     * 
     * @throws SQLException
     *             sql异常.
     */
    @Test
    public final void testQueryCount() throws SQLException {
        SuperDaoProxy daoProxy = SuperDaoProxy.getInstance();
        RolePo rPo = new RolePo();
        rPo.setRoleName("role7");
        Long size = daoProxy.queryCount(rPo);

        boolean result = size == Long.valueOf("1");
        assertTrue("查询成功时失败", result);
    }

    /**
     * pageEntity测试方法.
     * 
     * @throws SQLException
     *             sql异常.
     */
    @Test
    public final void testPageEntity() throws SQLException {
        SuperDaoProxy daoProxy = SuperDaoProxy.getInstance();
        RolePo rPo = new RolePo();
        rPo.setRoleName("page");
        PaginationPo page = new PaginationPo();
        page.setIndexPageNum(1);
        page.setSize(1);
        page = daoProxy.pageEntity(page, rPo);
        boolean result = ((RolePo) page.getList().get(0)).getRoleId() == Long
                .valueOf("8");
        assertTrue("查询分页成功时失败", result);

        page.setIndexPageNum(2);
        page.setSize(1);
        page = daoProxy.pageEntity(page, rPo);
        result = ((RolePo) page.getList().get(0)).getRoleId() == Long
                .valueOf("9");
        assertTrue("查询分页成功时失败", result);
    }
}
