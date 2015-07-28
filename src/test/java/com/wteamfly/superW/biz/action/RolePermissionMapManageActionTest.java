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
 * 角色行为映射管理Action测试类.
 * 
 * @author 侯骏雄
 * @since 3.0.0
 */
public class RolePermissionMapManageActionTest extends
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
        // 指定测试用到的表
        DefaultTable rolePermissionMapTable = new DefaultTable(
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

        Object[] rolePermissionMapData1 = { Long.valueOf("1"),
                Long.valueOf("2"), Long.valueOf("20"), date, Long.valueOf("1"),
                null, null, Byte.valueOf("0"), Byte.valueOf("0"),
                Long.valueOf("0") };
        rolePermissionMapTable.addRow(rolePermissionMapData1);
        Object[] rolePermissionMapData2 = { Long.valueOf("2"),
                Long.valueOf("2"), Long.valueOf("21"), date, Long.valueOf("1"),
                null, null, Byte.valueOf("0"), Byte.valueOf("0"),
                Long.valueOf("0") };
        rolePermissionMapTable.addRow(rolePermissionMapData2);
        Object[] rolePermissionMapData3 = { Long.valueOf("3"),
                Long.valueOf("2"), Long.valueOf("22"), date, Long.valueOf("1"),
                null, null, Byte.valueOf("0"), Byte.valueOf("0"),
                Long.valueOf("0") };
        rolePermissionMapTable.addRow(rolePermissionMapData3);
        Object[] rolePermissionMapData4 = { Long.valueOf("4"),
                Long.valueOf("2"), Long.valueOf("23"), date, Long.valueOf("1"),
                null, null, Byte.valueOf("0"), Byte.valueOf("0"),
                Long.valueOf("0") };
        rolePermissionMapTable.addRow(rolePermissionMapData4);
        

        // testAddRolePermissionMap使用
        // Object[] rolePermissionMapData5 = { Long.valueOf("5"),
        // Long.valueOf("2"),
        // Long.valueOf("1"), date, Long.valueOf("1"), null, null,
        // Byte.valueOf("0"), Byte.valueOf("0"), Long.valueOf("0") };
        // rolePermissionMapTable.addRow(rolePermissionMapData5);
        // testDeleteRolePermissionMap使用
        Object[] rolePermissionMapData6 = { Long.valueOf("6"),
                Long.valueOf("2"), Long.valueOf("1"), date, Long.valueOf("1"),
                null, null, Byte.valueOf("0"), Byte.valueOf("0"),
                Long.valueOf("0") };
        rolePermissionMapTable.addRow(rolePermissionMapData6);
        // testEditRolePermissionMap使用
        Object[] rolePermissionMapData7 = { Long.valueOf("7"),
                Long.valueOf("2"), Long.valueOf("1"), date, Long.valueOf("1"),
                null, null, Byte.valueOf("0"), Byte.valueOf("0"),
                Long.valueOf("0") };
        rolePermissionMapTable.addRow(rolePermissionMapData7);
        // testPageRolePermissionMap使用
        Object[] rolePermissionMapData8 = { Long.valueOf("8"),
                Long.valueOf("2"), Long.valueOf("1"), date, Long.valueOf("1"),
                null, null, Byte.valueOf("0"), Byte.valueOf("0"),
                Long.valueOf("0") };
        rolePermissionMapTable.addRow(rolePermissionMapData8);
        
        // testPageNotHasRolePermission使用
        Object[] rolePermissionMapData9 = { Long.valueOf("9"),
                Long.valueOf("2"), Long.valueOf("35"), date, Long.valueOf("1"),
                null, null, Byte.valueOf("0"), Byte.valueOf("0"),
                Long.valueOf("0") };
        rolePermissionMapTable.addRow(rolePermissionMapData9);

        // 组合表
        DefaultTable[] tables = { userTable, rolePermissionMapTable };
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
     * addRolePermissionMap测试方法.
     * 
     * @throws Exception
     *             普通异常.
     */
    @Test
    public final void testAddRolePermissionMap() throws Exception {
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

        request.setParameter("rolePermissionMap",
                "{\"roleId\":\"2\",\"permissionId\":\"1\"}");
        String resultMessage = executeAction("/SuperW/addRolePermissionMap.action");
        boolean rs = -1 != resultMessage
                .indexOf("{\"userToken\":true,\"serviceResult\":true,\"resultInfo\":\"添加成功\"}");
        assertTrue("返回服務信息錯誤失敗", rs);
    }

    /**
     * deleteRolePermissionMap测试方法.
     * 
     * @throws Exception
     *             普通异常.
     */
    @Test
    public final void testDeleteRolePermissionMap() throws Exception {
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

        request.setParameter("rolePermissionMap",
                "{\"rolePermissionMapId\":\"6\"}");
        String resultMessage = executeAction("/SuperW/deleteRolePermissionMap.action");
        boolean rs = -1 != resultMessage
                .indexOf("{\"userToken\":true,\"serviceResult\":true,\"resultInfo\":\"成功删除\"}");
        assertTrue("返回服務信息錯誤失敗", rs);
    }

    /**
     * editRolePermissionMap测试方法.
     * 
     * @throws Exception
     *             普通异常.
     */
    @Test
    public final void testEditRolePermissionMap() throws Exception {
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

        request.setParameter("rolePermissionMap",
                "{\"rolePermissionMapId\":\"7\",\"roleId\":\"1\"}");
        String resultMessage = executeAction("/SuperW/editRolePermissionMap.action");
        boolean rs = -1 != resultMessage
                .indexOf("{\"userToken\":true,\"serviceResult\":true,\"resultInfo\":\"修改成功\"}");
        assertTrue("返回服務信息錯誤失敗", rs);
    }

    /**
     * pageRolePermissionMap测试方法.
     * 
     * @throws Exception
     *             普通异常.
     */
    @Test
    public final void testPageRolePermissionMap() throws Exception {
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
        String resultMessage = executeAction("/SuperW/pageRolePermissionMap.action");
        boolean rs = -1 != resultMessage.indexOf("\"resultInfo\":\"分页成功\"");
        assertTrue("返回服務信息錯誤失敗", rs);

        request.setParameter("rolePermissionMap", "{\"roleId\":\"1\"}");
        request.setParameter("page", "{\"size\":\"1\", \"indexPageNum\":\"1\"}");
        resultMessage = executeAction("/SuperW/pageRolePermissionMap.action");
        rs = -1 != resultMessage.indexOf("\"resultInfo\":\"分页成功\"");
        assertTrue("返回服務信息錯誤失敗", rs);
    }
    
    /**
     * pageNotHasRolePermission测试方法.
     * 
     * @throws Exception
     *             普通异常.
     */
    @Test
    public final void testPageNotHasRolePermission() throws Exception {
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
        String resultMessage = executeAction("/SuperW/pageNotHasRolePermission.action");
        boolean rs = -1 != resultMessage.indexOf("\"resultInfo\":\"分页成功\"");
        assertTrue("返回服務信息錯誤失敗", rs);

        request.setParameter("rolePermissionMap", "{\"roleId\":\"1\"}");
        request.setParameter("page", "{\"size\":\"1\", \"indexPageNum\":\"1\"}");
        resultMessage = executeAction("/SuperW/pageNotHasRolePermission.action");
        rs = -1 != resultMessage.indexOf("\"resultInfo\":\"分页成功\"");
        assertTrue("返回服務信息錯誤失敗", rs);
    }
    
    
}
