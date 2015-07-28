/**
 * Copyright (c) 2007-2015 WteamFly.  All rights reserved. 网飞网络公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.superW.biz.action;

import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.dbunit.dataset.DefaultDataSet;
import org.dbunit.dataset.DefaultTable;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wteamfly.superW.testhelper.TestDBHelper;
import com.wteamfly.superW.testhelper.TestLogHelper;
import com.wteamfly.supercarrent.biz.action.SystemAction;

/**
 * 系统Action测试类.
 * 
 * @author 侯骏雄
 * @since 3.0.0
 */
public class SystemActionTest extends BaseActionTest<SystemAction> {

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
        DefaultTable userRoleTable = new DefaultTable("t_userrolemap",
                backupDataset.getTable("t_userrolemap").getTableMetaData()
                        .getColumns());

        // 写入数据
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .parse("2012-01-12 23:30:20");
        // testLogin使用
        Object[] userData1 = { Long.valueOf("1"), "user1",
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", date, Long.valueOf("1"),
                null, null, Byte.valueOf("0"), Byte.valueOf("0"),
                Long.valueOf("0") };
        userTable.addRow(userData1);
        // testLogout用
        Object[] userData2 = { Long.valueOf("2"), "user2",
                "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB", date, Long.valueOf("1"),
                null, null, Byte.valueOf("0"), Byte.valueOf("0"),
                Long.valueOf("0") };
        userTable.addRow(userData2);

        // testLogin使用
        Object[] userRoleMapData1 = { Long.valueOf("1"), Long.valueOf("1"),
                Long.valueOf("2"), date, Long.valueOf("1"), null, null,
                Byte.valueOf("0"), Byte.valueOf("0"), Long.valueOf("0") };
        userRoleTable.addRow(userRoleMapData1);
        // testLogout用
        Object[] userRoleMapData2 = { Long.valueOf("2"), Long.valueOf("2"),
                Long.valueOf("2"), date, Long.valueOf("1"), null, null,
                Byte.valueOf("0"), Byte.valueOf("0"), Long.valueOf("0") };
        userRoleTable.addRow(userRoleMapData2);

        // 组合表
        DefaultTable[] tables = { userTable, userRoleTable };
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
     * Test method for 测试登陆方法.
     * {@link com.wteamfly.supercarrent.biz.action.SystemAction#login()}.
     * 
     * @throws Exception
     *             普通异常.
     */
    @Test
    public final void testLogin() throws Exception {
        TestLogHelper.clearLog();
        request.setParameter("user", "{\"loginName\":\"user1\","
                + "\"password\":\"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"}");
        String resultMessage = executeAction("/SuperW/login.action");
        boolean result = -1 != resultMessage.indexOf("\"serviceResult\":true");
        assertTrue("成功登录时失败", result);
    }

    /**
     * logout测试方法.
     * 
     * @throws Exception
     *             普通异常.
     */
    @Test
    public final void testLogout() throws Exception {
    	loginTestSystem();
        String resultMessage = executeAction("/SuperW/logout.action");
        boolean result = -1 != resultMessage
                .indexOf("{\"serviceResult\":true}");
        assertTrue("成功注销时失败", result);
    }

    /**
     * isLogin测试方法.
     * 
     * @throws Exception
     *             普通异常.
     */
    @Test
    public final void testIsLogin() throws Exception {
        String resultMessage = executeAction("/SuperW/isLogin.action");
        boolean result = -1 != resultMessage.indexOf("{\"serviceResult\":true");
        assertTrue("判断是否已登录时失败", result);
    }
}
