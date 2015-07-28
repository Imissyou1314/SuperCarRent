/**
 * Copyright (c) 2007-2015 WteamFly.  All rights reserved. 网飞网络公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.superW.biz.action;

import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
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
import com.wteamfly.supercarrent.biz.action.RoleManageAction;
import com.wteamfly.supercarrent.entity.po.UserPo;
import com.wteamfly.supercarrent.helper.ShiroHelper;

/**
 * 角色管理Acton类.
 * 
 * @author 侯骏雄
 * @since 3.0.0
 */
public class RoleManageActionTest extends
        StrutsJUnit4TestCase<RoleManageAction> {

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

        // testaddRole使用
        // Object[] roleData3 = { Long.valueOf("3"), "role3", date,
        // Long.valueOf("1"), null, null, Byte.valueOf("0"),
        // Byte.valueOf("0"), Long.valueOf("0") };
        // roleTable.addRow(roleData3);
        // testDeleteRole使用
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

        // 组合表
        DefaultTable[] tables = { userTable, roleTable };
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
     * addRole测试方法.
     * 
     * @throws Exception
     *             普通异常.
     */
    @Test
    public final void testAddRole() throws Exception {
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

        request.setParameter("role", "{\"roleName\":\"role3\"}");
        String resultMessage = executeAction("/SuperW/addRole.action");
        boolean rs = -1 != resultMessage
                .indexOf("{\"userToken\":true,\"serviceResult\":true,\"resultInfo\":\"添加成功\"}");
        assertTrue("返回服務信息錯誤失敗", rs);
    }

    /**
     * deleteRole测试方法.
     * 
     * @throws Exception
     *             普通异常.
     */
    @Test
    public final void testDeleteRole() throws Exception {
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

        request.setParameter("role", "{\"roleId\":\"4\"}");
        String resultMessage = executeAction("/SuperW/deleteRole.action");
        boolean rs = -1 != resultMessage
                .indexOf("{\"userToken\":true,\"serviceResult\":true,\"resultInfo\":\"成功逻辑删除\"}");
        assertTrue("返回服務信息錯誤失敗", rs);
    }

    /**
     * editRole测试方法.
     * 
     * @throws Exception
     *             普通异常.
     */
    @Test
    public final void testEditRole() throws Exception {
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
            Timestamp timestamp = new Timestamp(date.getTime());
            uPo.setCreateTime(timestamp);
            uPo.setCreatorId(Long.valueOf("1"));
            uPo.setIsLockUp(false);
            currentUser.getSession().setAttribute("user", uPo);
        } catch (Exception se) {
            se.printStackTrace();
        }

        request.setParameter("role",
                "{\"roleId\":\"5\",\"roleName\":\"role55\"}");
        String resultMessage = executeAction("/SuperW/editRole.action");
        boolean rs = -1 != resultMessage
                .indexOf("{\"userToken\":true,\"serviceResult\":true,\"resultInfo\":\"修改成功\"}");
        assertTrue("返回服務信息錯誤失敗", rs);
    }

    /**
     * pageRole测试方法.
     * 
     * @throws Exception
     *             普通异常.
     */
    @Test
    public final void testPageRole() throws Exception {
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
        String resultMessage = executeAction("/SuperW/pageRole.action");
        boolean rs = -1 != resultMessage.indexOf("\"resultInfo\":\"分页成功\"");
        assertTrue("返回服務信息錯誤失敗", rs);

        request.setParameter("role", "{\"roleName\":\"page\"}");
        request.setParameter("page", "{\"size\":\"1\", \"indexPageNum\":\"1\"}");
        resultMessage = executeAction("/SuperW/pageRole.action");
        rs = -1 != resultMessage.indexOf("\"resultInfo\":\"分页成功\"");
        assertTrue("返回服務信息錯誤失敗", rs);
    }
    
    /**
     * pageRole测试方法.
     * 
     * @throws Exception
     *             普通异常.
     */
    @Test
    public final void testGetAllRoles() throws Exception {
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

        String resultMessage = executeAction("/SuperW/getAllRoles.action");
        boolean rs = -1 != resultMessage.indexOf("\"resultInfo\":\"查询实体成功\"");
        assertTrue("返回服務信息錯誤失敗", rs);

        request.setParameter("role", "{\"roleName\":\"page\"}");
        resultMessage = executeAction("/SuperW/getAllRoles.action");
        rs = -1 != resultMessage.indexOf("\"resultInfo\":\"查询实体成功\"");
        assertTrue("返回服務信息錯誤失敗", rs);
    }
}
