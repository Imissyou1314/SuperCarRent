/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.superW.helper;

import static org.junit.Assert.assertTrue;

import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wteamfly.supercarrent.helper.HibernateHelper;

/**
 * http访问操作工具类测试.
 * 
 * @author 侯骏雄
 * @since 3.0.0
 */
public class HibernateHelperTest {

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
     * 测试创建Session工厂.
     */
    @Test
    public final void testBuildSessionFactory() {
        SessionFactory sf = HibernateHelper.getSessionFactory();
        boolean result = sf.isClosed();
        assertTrue("测试创建Session工厂失败", !result);
    }

}
