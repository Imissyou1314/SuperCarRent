/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.superW.biz.service;

import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsJUnit4TestCase;
import org.dbunit.dataset.DefaultDataSet;
import org.dbunit.dataset.DefaultTable;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.opensymphony.xwork2.ActionContext;
import com.wteamfly.superW.testhelper.TestDBHelper;
import com.wteamfly.superW.testhelper.TestLogHelper;
import com.wteamfly.supercarrent.biz.action.SystemAction;
import com.wteamfly.supercarrent.biz.service.ResultMessage;
import com.wteamfly.supercarrent.biz.service.SystemService;
import com.wteamfly.supercarrent.entity.po.UserPo;
import com.wteamfly.supercarrent.helper.ShiroHelper;

/**
 * 系统Service测试类.
 * 
 * @author 侯骏雄
 * @since 3.0.0
 */
public class SystemServiceTest extends StrutsJUnit4TestCase<SystemAction> {

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
        // testLogin用
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
        // testIsLogin用
        Object[] userData3 = { Long.valueOf("3"), "user3",
                "CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC", date, Long.valueOf("1"),
                null, null, Byte.valueOf("0"), Byte.valueOf("0"),
                Long.valueOf("0") };
        userTable.addRow(userData3);

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
     * Test method for
     * {@link com.wteamfly.supercarrent.biz.service.SystemService#login()} .
     */
    @Test
    public final void testLogin() {
        Map<String, Object> map = new HashMap<String, Object>();
        ActionContext context = new ActionContext(map);
        ServletActionContext.setContext(context);

        SystemService service = SystemService.getInstance();
        service.login(null, this.request, this.response);
        Boolean rs = TestLogHelper.checklog("uPoForLogin空指针异常");
        assertTrue("uPoForLogin空指针异常", rs);

        TestLogHelper.clearLog();
        UserPo mUserPo = new UserPo();
        service.login(mUserPo, this.request, this.response);
        rs = TestLogHelper.checklog("uPoForLogin内容为空");
        assertTrue("uPoForLogin内容为空正确失败", rs);

        TestLogHelper.clearLog();
        mUserPo = new UserPo();
        mUserPo.setLoginName("a");
        service.login(mUserPo, this.request, this.response);
        rs = TestLogHelper.checklog("Password为空");
        assertTrue("Password为空正确失败", rs);

        TestLogHelper.clearLog();
        mUserPo = new UserPo();
        mUserPo.setLoginName(null);
        mUserPo.setPassword("a");
        service.login(mUserPo, this.request, this.response);
        rs = TestLogHelper.checklog("登录名为空");
        assertTrue("登录名为空正确失败", rs);

        TestLogHelper.clearLog();
        mUserPo = new UserPo();
        mUserPo.setLoginName("a");
        mUserPo.setPassword("b");
        ResultMessage rMessage = service.login(mUserPo, this.request,
                this.response);
        rs = !rMessage.getServiceResult();
        assertTrue("用户名不存在正确失败", rs);
        rs = rMessage.getResultInfo().equals("用户名不存在");
        assertTrue("用户名不存在正确失败", rs);

        TestLogHelper.clearLog();
        mUserPo = new UserPo();
        mUserPo.setLoginName("user1");
        mUserPo.setPassword("b");
        rMessage = service.login(mUserPo, this.request, this.response);
        rs = !rMessage.getServiceResult();
        assertTrue("密码错误正确失败", rs);
        rs = rMessage.getResultInfo().equals("密码错误");
        assertTrue("密码错误正确失败", rs);

        TestLogHelper.clearLog();
        mUserPo = new UserPo();
        mUserPo.setLoginName("user1");
        mUserPo.setPassword("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        rMessage = service.login(mUserPo, this.request, this.response);
        rs = rMessage.getServiceResult();
        assertTrue("登录正确失败", rs);
        rs = rMessage.getResultInfo().equals("登录成功");
        assertTrue("登录正确失败", rs);
    }

    /**
     * logout测试方法.
     */
    @Test
    public final void testLogout() {
        Subject currentUser = ShiroHelper.getSubject(this.request,
                this.response);
        UsernamePasswordToken token = new UsernamePasswordToken("user2",
                "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
        token.setRememberMe(true);
        try {
            currentUser.login(token);
        } catch (Exception se) {
            se.printStackTrace();
        }

        SystemService service = SystemService.getInstance();
        ResultMessage rMessage = service.logout(this.request, this.response);
        Boolean rs = rMessage.getServiceResult();
        assertTrue("注销失败", rs);
        currentUser = ShiroHelper.getSubject(this.request, this.response);
        rs = currentUser.isRemembered();
        assertTrue("注销失败", !rs);
        rs = currentUser.isAuthenticated();
        assertTrue("注销失败", !rs);
    }

    /**
     * isLogin测试方法.
     */
    @Test
    public final void testIsLogin() {
        SystemService service = SystemService.getInstance();
        ResultMessage rMessage = service.isLogin(this.request, this.response);
        boolean rs = rMessage.getServiceResult();
        assertTrue("判断是否已登录失败", rs);
        rs = rMessage.getResultParm().get("isLogin").equals("false");
        assertTrue("判断是否已登录失败", rs);

        Subject currentUser = ShiroHelper.getSubject(this.request,
                this.response);
        UsernamePasswordToken token = new UsernamePasswordToken("user3",
                "CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC");
        token.setRememberMe(true);
        try {
            currentUser.login(token);
        } catch (Exception se) {
            se.printStackTrace();
        }
        rMessage = service.isLogin(this.request, this.response);
        rs = rMessage.getServiceResult();
        assertTrue("判断是否已登录失败", rs);
        rs = rMessage.getResultParm().get("isLogin").equals("true");
        assertTrue("判断是否已登录失败", rs);
    }
}
