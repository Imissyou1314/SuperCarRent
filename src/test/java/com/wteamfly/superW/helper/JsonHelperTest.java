/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.superW.helper;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wteamfly.supercarrent.entity.vo.RoleVo;
import com.wteamfly.supercarrent.entity.vo.UserVo;
import com.wteamfly.supercarrent.helper.JsonHelper;

/**
 * json操作工具类测试.
 * 
 * @author 侯骏雄
 * @since 3.0.0
 */
public class JsonHelperTest {

    /**
     * 测试启动前方法.
     * 
     * @throws Exception
     *             普通异常
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * 测试启动后方法.
     * 
     * @throws Exception
     *             普通异常
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * 将json字符串转换为指定javabean的列表. 1.传入用户登录信息数组的json判断转换是否正确 2.json空指针调用
     * 3.beanClass空指针调用
     */
    @Test
    public final void testJsonToBeanList() {
        String json = "[{\"userId\":\"1\",\"loginName\":\"aaa\",\"password\":\"bbb\"}]";
        List<UserVo> list = JsonHelper.jsonToBeanList(json, UserVo.class);
        boolean result = false;
        for (UserVo utVo : list) {
            result = utVo.getUserId().equals("1")
                    && utVo.getLoginName().equals("aaa")
                    && utVo.getPassword().equals("bbb");
            assertTrue("传入用户登录信息的json判断转换是否正确时失败", result);
        }
    }

    /**
     * 将json字符串转换为指定javabean测试. 1.传入用户登录信息的json判断转换是否正确 2.json空指针调用
     * 3.beanClass空指针调用 4.json格式不正确调用
     */
    @Test
    public final void testJsonToBean() {
        String json = "{\"userId\":\"1\",\"loginName\":\"aaa\",\"password\":\"bbb\"}";
        UserVo utVo = JsonHelper.jsonToBean(json, UserVo.class);
        boolean result = utVo.getUserId().equals("1")
                && utVo.getLoginName().equals("aaa")
                && utVo.getPassword().equals("bbb");
        assertTrue("传入用户登录信息的json判断转换是否正确时失败", result);
    }

    /**
     * 将object转换为json字符串测试. 1.转换SmsCodeVo.
     */
    @Test
    public final void testToJson() {
        RoleVo vo = new RoleVo();
        vo.setRoleId("1");
        vo.setRoleName("角色1");

        String json = JsonHelper.toJson(vo);
        boolean result = json.equals("{\"roleId\":\"1\",\"roleName\":\"角色1\"}");
        assertTrue("转换SmsCodeVo时失败", result);
    }
}
