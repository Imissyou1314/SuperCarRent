/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.superW.entity.po;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wteamfly.superW.entity.vo.Child2Po;
import com.wteamfly.superW.entity.vo.ChildPo;

/**
 * 持久对象超类测试.
 * 
 * @author 侯骏雄
 * @since 3.0.0
 */
public class SuperPersistentObjectTest {

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
     * 返回此实体的哈希码测试.
     * 
     */
    @Test
    public final void testHashCode() {
        ChildPo po1 = new ChildPo();
        po1.setTestfield("a");
        ChildPo po2 = new ChildPo();
        po2.setTestfield("b");
        boolean result = po1.hashCode() != po2.hashCode();
        assertTrue("不相等实体时失败", result);

        po1 = new ChildPo();
        po1.setTestfield("910114");
        po2 = new ChildPo();
        po2.setTestfield("910114");
        result = po1.hashCode() == po2.hashCode();
        assertTrue("相等实体时失败", result);
    }

    /**
     * 将此实体与指定的对象比较. 1.实体类型不同 2.实体类型相同但成员变量不同 3.实体相同 4.anObject为空
     * 
     */
    @Test
    public final void testEquals() {
        ChildPo po1 = new ChildPo();
        Child2Po po2 = new Child2Po();
        boolean result = po1.equals(po2);
        assertTrue("实体类型不同时失败", !result);

        po1 = new ChildPo();
        po1.setTestfield("a");
        ChildPo po3 = new ChildPo();
        po3.setTestfield("b");
        result = po1.equals(po3);
        assertTrue("实体类型相同但成员变量不同时失败", !result);

        po1 = new ChildPo();
        po1.setTestfield("a");
        po3 = new ChildPo();
        po3.setTestfield("a");
        result = po1.equals(po3);
        assertTrue("实体相同时失败", result);
    }
}
