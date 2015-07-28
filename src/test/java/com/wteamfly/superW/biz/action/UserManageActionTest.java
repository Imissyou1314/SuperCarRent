/**
 * Copyright (c) 2007-2015 WteamFly.  All rights reserved. 网飞网络公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.superW.biz.action;

import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.StrutsJUnit4TestCase;
import org.dbunit.dataset.DefaultDataSet;
import org.dbunit.dataset.DefaultTable;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wteamfly.superW.testhelper.TestDBHelper;
import com.wteamfly.supercarrent.biz.action.UserManageAction;
import com.wteamfly.supercarrent.entity.po.UserPo;
import com.wteamfly.supercarrent.helper.ShiroHelper;

/**
 * 用户管理Action测试类.
 * 
 * @author 侯骏雄
 * @since 3.0.0
 */
public class UserManageActionTest extends
        StrutsJUnit4TestCase<UserManageAction> {

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
        // 写入数据
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .parse("2012-01-12 23:30:20");
        Object[] userData1 = { Long.valueOf("1"), "user1",
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", date, Long.valueOf("1"),
                null, null, Byte.valueOf("0"), Byte.valueOf("0"),
                Long.valueOf("0") };
        userTable.addRow(userData1);

        // testAddUser使用
        // Object[] userData2 = { Long.valueOf("2"), "user2",
        // "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB", date, Long.valueOf("1"),
        // null, null, Byte.valueOf("0"), Byte.valueOf("0"),
        // Long.valueOf("0") };
        // userTable.addRow(userData2);
        // testDeleteUser使用
        Object[] userData3 = { Long.valueOf("3"), "user3",
                "CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC", date, Long.valueOf("1"),
                null, null, Byte.valueOf("0"), Byte.valueOf("0"),
                Long.valueOf("0") };
        userTable.addRow(userData3);
        // testEditUser使用
        Object[] userData4 = { Long.valueOf("4"), "user4",
                "DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD", date, Long.valueOf("1"),
                null, null, Byte.valueOf("0"), Byte.valueOf("0"),
                Long.valueOf("0") };
        userTable.addRow(userData4);
        // testPageUser使用
        Object[] userData5 = { Long.valueOf("5"), "page",
                "EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE", date, Long.valueOf("1"),
                null, null, Byte.valueOf("0"), Byte.valueOf("0"),
                Long.valueOf("0") };
        userTable.addRow(userData5);

        // 组合表
        DefaultTable[] tables = { userTable };
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
     * addUser测试方法.
     * 
     * @throws Exception
     *             普通异常.
     */
    @Test
    public final void testAddUser() throws Exception {
        Subject currentUser = ShiroHelper.getSubject(this.request,
                this.response);
        UsernamePasswordToken token = new UsernamePasswordToken("user1",
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        token.setRememberMe(true);
        try {
            currentUser.login(token);
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
            currentUser.getSession().setAttribute("user", uPo);
        } catch (Exception se) {
            se.printStackTrace();
        }

        request.setParameter("user",
                "{\"roleId\":\"1\",\"loginName\":\"user2\",\"password\":"
                        + "\"BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB\"}");
        String resultMessage = executeAction("/SuperW/addUser.action");
        boolean rs = -1 != resultMessage
                .indexOf("{\"userToken\":true,\"serviceResult\":true,\"resultInfo\":\"添加成功\"}");
        assertTrue("返回服務信息錯誤失敗", rs);
    }

    /**
     * deleteUser测试方法.
     * 
     * @throws Exception
     *             普通异常.
     */
    @Test
    public final void testDeleteUser() throws Exception {
        Subject currentUser = ShiroHelper.getSubject(this.request,
                this.response);
        UsernamePasswordToken token = new UsernamePasswordToken("user1",
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        token.setRememberMe(true);
        try {
            currentUser.login(token);
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
            currentUser.getSession().setAttribute("user", uPo);
        } catch (Exception se) {
            se.printStackTrace();
        }

        request.setParameter("user", "{\"userId\":\"3\"}");
        String resultMessage = executeAction("/SuperW/deleteUser.action");
        boolean rs = -1 != resultMessage
                .indexOf("{\"userToken\":true,\"serviceResult\":true,\"resultInfo\":\"成功逻辑删除\"}");
        assertTrue("返回服務信息錯誤失敗", rs);
    }

    /**
     * editUser测试方法.
     * 
     * @throws Exception
     *             普通异常.
     */
    @Test
    public final void testEditUser() throws Exception {
        Subject currentUser = ShiroHelper.getSubject(this.request,
                this.response);
        UsernamePasswordToken token = new UsernamePasswordToken("user1",
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        token.setRememberMe(true);
        try {
            currentUser.login(token);
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
            currentUser.getSession().setAttribute("user", uPo);
        } catch (Exception se) {
            se.printStackTrace();
        }

        request.setParameter("user",
                "{\"userId\":\"4\",\"loginName\":\"user44\"}");
        String resultMessage = executeAction("/SuperW/editUser.action");
        boolean rs = -1 != resultMessage
                .indexOf("{\"userToken\":true,\"serviceResult\":true,\"resultInfo\":\"修改成功\"}");
        assertTrue("返回服務信息錯誤失敗", rs);
    }

    /**
     * pageUser测试方法.
     * 
     * @throws Exception
     *             普通异常.
     */
    @Test
    public final void testPageUser() throws Exception {
        Subject currentUser = ShiroHelper.getSubject(this.request,
                this.response);
        UsernamePasswordToken token = new UsernamePasswordToken("user1",
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        token.setRememberMe(true);
        try {
            currentUser.login(token);
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
            currentUser.getSession().setAttribute("user", uPo);
        } catch (Exception se) {
            se.printStackTrace();
        }

        request.setParameter("page", "{\"size\":\"1\", \"indexPageNum\":\"1\"}");
        String resultMessage = executeAction("/SuperW/pageUser.action");
        boolean rs = -1 != resultMessage.indexOf("\"resultInfo\":\"分页成功\"");
        assertTrue("返回服務信息錯誤失敗", rs);

        request.setParameter("user", "{\"userName\":\"page\"}");
        request.setParameter("page", "{\"size\":\"1\", \"indexPageNum\":\"1\"}");
        resultMessage = executeAction("/SuperW/pageUser.action");
        rs = -1 != resultMessage.indexOf("\"resultInfo\":\"分页成功\"");
        assertTrue("返回服務信息錯誤失敗", rs);
    }
}
