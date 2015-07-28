/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.superW.helper;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wteamfly.supercarrent.helper.HttpHelper;

/**
 * http访问操作工具类测试.
 * 
 * @author 侯骏雄
 * @since 3.0.0
 */
public class HttpHelperTest {

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
     * 测试http协议get方法请求. 1.通过访问www.baidu.com来回去该页面html代码.
     * 然后判断该代码是否包含"<html>"标签来判断访问是否成功. 2.传url空指针抛出异常提示. 3.url格式错误抛出异常提示.
     */
    @Test
    public final void testRequestByGet() {
        String url = "http://www.baidu.com";
        String response = HttpHelper.requestByGet(url);
        boolean result = response.indexOf("baidu") != -1;
        assertTrue("通过get方法访问百度首页失败", result);
    }

}
