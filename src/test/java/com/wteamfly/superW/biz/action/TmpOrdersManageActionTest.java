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
import org.junit.Test;

import com.wteamfly.superW.testhelper.TestDBHelper;
import com.wteamfly.supercarrent.biz.action.PermissionManageAction;
import com.wteamfly.supercarrent.entity.po.UserPo;
import com.wteamfly.supercarrent.helper.ShiroHelper;

/**
 * 临时订单管理Action类.
 * 
 * @author 黄祥谦
 * @since 3.0.0
 */
public class TmpOrdersManageActionTest extends
        StrutsJUnit4TestCase<PermissionManageAction> {

    /**
     * 测试启动前方法.
     * 
     * @throws java.lang.Exception
     *             普通异常.
     */
//    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        // 获取原数据库内容
        IDataSet backupDataset = TestDBHelper.backupDataBase();
        // 构建测试用的数据
        // 指定测试用到的表
        DefaultTable userTable = new DefaultTable("t_user", backupDataset
                .getTable("t_user").getTableMetaData().getColumns());
        DefaultTable permissionTable = new DefaultTable("t_permission",
                backupDataset.getTable("t_permission").getTableMetaData()
                        .getColumns());
        DefaultTable rolepermissionTable = new DefaultTable(
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

        Object[] addPermission = { Long.valueOf("1"), "addPermission", date,
                Long.valueOf("1"), null, null, Byte.valueOf("0"),
                Byte.valueOf("0"), Long.valueOf("0") };
        permissionTable.addRow(addPermission);
        Object[] deletePermission = { Long.valueOf("2"), "deletePermission",
                date, Long.valueOf("1"), null, null, Byte.valueOf("0"),
                Byte.valueOf("0"), Long.valueOf("0") };
        permissionTable.addRow(deletePermission);
        Object[] editPermission = { Long.valueOf("3"), "editPermission", date,
                Long.valueOf("1"), null, null, Byte.valueOf("0"),
                Byte.valueOf("0"), Long.valueOf("0") };
        permissionTable.addRow(editPermission);
        Object[] pagePermission = { Long.valueOf("4"), "pagePermission", date,
                Long.valueOf("1"), null, null, Byte.valueOf("0"),
                Byte.valueOf("0"), Long.valueOf("0") };
        permissionTable.addRow(pagePermission);

        Object[] rolePermissionMap1 = { Long.valueOf("1"), Long.valueOf("2"),
                Long.valueOf("1"), date, Long.valueOf("1"), null, null,
                Byte.valueOf("0"), Byte.valueOf("0"), Long.valueOf("0") };
        rolepermissionTable.addRow(rolePermissionMap1);
        Object[] rolePermissionMap2 = { Long.valueOf("2"), Long.valueOf("2"),
                Long.valueOf("2"), date, Long.valueOf("1"), null, null,
                Byte.valueOf("0"), Byte.valueOf("0"), Long.valueOf("0") };
        rolepermissionTable.addRow(rolePermissionMap2);
        Object[] rolePermissionMap3 = { Long.valueOf("3"), Long.valueOf("2"),
                Long.valueOf("3"), date, Long.valueOf("1"), null, null,
                Byte.valueOf("0"), Byte.valueOf("0"), Long.valueOf("0") };
        rolepermissionTable.addRow(rolePermissionMap3);
        Object[] rolePermissionMap4 = { Long.valueOf("4"), Long.valueOf("2"),
                Long.valueOf("4"), date, Long.valueOf("1"), null, null,
                Byte.valueOf("0"), Byte.valueOf("0"), Long.valueOf("0") };
        rolepermissionTable.addRow(rolePermissionMap4);

        // testAddPermission用
         Object[] permission5 = { Long.valueOf("5"), "permission5", date,
         Long.valueOf("1"), null, null, Byte.valueOf("0"),
         Byte.valueOf("0"), Long.valueOf("0") };
         permissionTable.addRow(permission5);
//         testDeletePermission用
        Object[] permission6 = { Long.valueOf("6"), "permission6", date,
                Long.valueOf("1"), null, null, Byte.valueOf("0"),
                Byte.valueOf("0"), Long.valueOf("0") };
        permissionTable.addRow(permission6);
        // testEditPermission用
        Object[] permission7 = { Long.valueOf("7"), "permission7", date,
                Long.valueOf("1"), null, null, Byte.valueOf("0"),
                Byte.valueOf("0"), Long.valueOf("0") };
        permissionTable.addRow(permission7);
        // testPagePermission用
        Object[] permission8 = { Long.valueOf("8"), "page", date,
                Long.valueOf("1"), null, null, Byte.valueOf("0"),
                Byte.valueOf("0"), Long.valueOf("0") };
        permissionTable.addRow(permission8);

        // 组合表
        DefaultTable[] tables = { permissionTable, userTable,
                rolepermissionTable };
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
//    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        TestDBHelper.restoreDataBase();
    }

    /**
     * addTmpOrders测试方法.
     * 
     * @throws Exception
     *             普通异常.
     */
    @Test
    public final void testAddTmpOrders() throws Exception {
        Subject currentUser = ShiroHelper.getSubject(this.request,
                this.response);
        UsernamePasswordToken token = new UsernamePasswordToken("user1",
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        token.setRememberMe(true);
        UserPo uPo = null ;
        try {
            currentUser.login(token);//登录系统
            uPo = new UserPo();
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
        
        request.setParameter("permission",
                "{\"permissionName\":\"permission5\"}");
        String resultMessage = executeAction("/SuperW/addPermission.action");
        boolean rs = -1 != resultMessage
                .indexOf("{\"userToken\":true,\"serviceResult\":true,\"resultInfo\":\"添加成功\"}");
        assertTrue("返回服務信息錯誤失敗", rs);
    }

    /**
     * deletePermission测试方法.
     *
     * @throws Exception
     *             普通异常.
     */
    @Test
    public final void testDeletePermission() throws Exception {
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

        request.setParameter("permission", "{\"permissionId\":\"6\"}");
//        request.setParameter("currentUser", "{\"userId\":\"1\"}");
        String resultMessage = executeAction("/SuperW/deletePermission.action");
        boolean rs = -1 != resultMessage
                .indexOf("{\"userToken\":true,\"serviceResult\":true,\"resultInfo\":\"成功逻辑删除\"}");
        assertTrue("返回服務信息錯誤失敗", rs);
    }

    /**
     * editPermission测试方法.
     * 
     * @throws Exception
     *             普通异常.
     */
    @Test
    public final void testEditPermission() throws Exception {
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

        request.setParameter("permission",
                "{\"permissionId\":\"7\",\"permissionName\":\"permission77\"}");
        String resultMessage = executeAction("/SuperW/editPermission.action");
        boolean rs = -1 != resultMessage
                .indexOf("{\"userToken\":true,\"serviceResult\":true,\"resultInfo\":\"修改成功\"}");
        assertTrue("返回服務信息錯誤失敗", rs);
    }

    /**
     * pagePermission测试方法.
     * 
     * @throws Exception
     *             普通异常.
     */
    @Test
    public final void testPagePermission() throws Exception {
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
        String resultMessage = executeAction("/SuperW/pagePermission.action");
        boolean rs = -1 != resultMessage.indexOf("\"resultInfo\":\"分页成功\"");
        assertTrue("返回服務信息錯誤失敗", rs);

        request.setParameter("permission", "{\"permissionName\":\"page\"}");
        request.setParameter("page", "{\"size\":\"1\", \"indexPageNum\":\"1\"}");
        resultMessage = executeAction("/SuperW/pagePermission.action");
        rs = -1 != resultMessage.indexOf("\"resultInfo\":\"分页成功\"");
        assertTrue("返回服務信息錯誤失敗", rs);
    }
}
