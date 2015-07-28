/**
 * Copyright (c) 2007-2015 WteamFly.  All rights reserved. 网飞网络公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.superW.biz.dao;

import static org.junit.Assert.assertTrue;

import org.dbunit.dataset.DefaultDataSet;
import org.dbunit.dataset.DefaultTable;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wteamfly.superW.testhelper.TestDBHelper;
import com.wteamfly.supercarrent.biz.dao.PermissionDaoProxy;
import com.wteamfly.supercarrent.entity.po.PermissionPo;
import com.wteamfly.supercarrent.entity.po.RolePo;
import com.wteamfly.supercarrent.entity.po.UserPo;

/**
 * 行为权限管理DaoProxy测试类.
 * 
 * @author 侯骏雄
 * @since 3.0.0
 */
public class PermissionDaoProxyTest {
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

        // 写入数据

        // 组合表
        DefaultTable[] tables = {};
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
     * hasPermissionByUserId测试方法.
     * 
     */
    @Test
    public final void testHasPermissionByUserId() {
        PermissionDaoProxy daoProxy = PermissionDaoProxy.getInstance();
        PermissionPo pPo = new PermissionPo();
        pPo.setPermissionName("logout");

        UserPo uPo = new UserPo();
        uPo.setUserId(Long.valueOf("1"));
        boolean methodResult = daoProxy.hasPermissionByUserId(pPo, uPo);
        boolean result = methodResult;
        assertTrue("判断行为正确时失敗", result);
    }

    /**
     * hasPermissionByRoleId测试方法.
     * 
     */
    @Test
    public final void testHasPermissionByRoleId() {
        PermissionDaoProxy daoProxy = PermissionDaoProxy.getInstance();
        PermissionPo pPo = new PermissionPo();
        pPo.setPermissionName("login");
        RolePo rPo = new RolePo();
        rPo.setRoleId(Long.valueOf("1"));
        boolean methodResult = daoProxy.hasPermissionByRoleId(pPo, rPo);
        boolean result = methodResult;
        assertTrue("判断行为正确时失敗", result);
    }
}
