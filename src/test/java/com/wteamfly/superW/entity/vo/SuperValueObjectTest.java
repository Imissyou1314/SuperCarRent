/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.superW.entity.vo;

import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wteamfly.supercarrent.entity.vo.RoleVo;

/**
 * 值对象超类测试.
 * 
 * @author 侯骏雄
 * @since 3.0.0
 */
public class SuperValueObjectTest {

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
     * 将值对象转换为持久对象测试. 1.全空 2.全满 3.空指针
     * 
     */
    @Test
    public final void testVoToPo() {
        ChildVo vo = new ChildVo();
        ChildPo po = vo.voToPo(ChildPo.class);
        boolean result = po == null;
        assertTrue("全空时不正确", result);

        vo.setTestfield("aaa");
        vo.setTime("2015-11-11 11:11:11");
        vo.setBool("false");
        po = (ChildPo) vo.voToPo(ChildPo.class);
        result = false;
        try {
            result = po.getTestfield().equals("aaa")
                    && po.getTime().equals(
                            new Timestamp(new SimpleDateFormat(
                                    "yyyy-MM-dd HH:mm:ss").parse(
                                    String.valueOf("2015-11-11 11:11:11"))
                                    .getTime())) && po.getBool().equals(false);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertTrue("全满时不正确", result);
    }

    /**
     * 持久层对象转换为值对象测试. 1.全空 2.全满 3.空指针
     * 
     */
    @Test
    public final void testPoToVo() {
        ChildVo vo = new ChildVo();
        ChildPo po = new ChildPo();
        vo.poToVo(po);
        boolean result = vo.getTestfield() == null && vo.getTime() == null;
        assertTrue("全空时不正确", result);

        vo = new ChildVo();
        po = new ChildPo();
        po.setTestfield("aaa");
        try {
            po.setTime(new Timestamp(
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(
                            String.valueOf("2015-11-11 11:11:11")).getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        po.setBool(false);
        vo.poToVo(po);
        result = vo.getTestfield().equals("aaa")
                && vo.getTime().equals("2015-11-11 11:11:11")
                && vo.getBool().equals("false");
        assertTrue("全满时不正确", result);
    }

    /**
     * json字符串转换为持久层对象测试. 1.全空 2.全满 3.空指针
     * 
     */
    @Test
    public final void testJsonToVo() {
        String json = "{\"testfield\":\"\", \"time\":\"\"}";
        ChildVo vo = new ChildVo();
        vo.jsonToVo(json);
        boolean result = vo.getTestfield() == null && vo.getTime() == null;
        assertTrue("全空时不正确", result);

        json = "{\"testfield\":\"a\", \"time\":\"2015-11-11 11:11:11\"}";
        vo = new ChildVo();
        vo.jsonToVo(json);
        result = vo.getTestfield().equals("a")
                && vo.getTime().equals("2015-11-11 11:11:11");
        assertTrue("全满时不正确", result);
    }

    /**
     * 验证vo中的值测试. 1.正确 2.错误
     * 
     */
    @Test
    public final void testValidata() {
        RoleVo vo = new RoleVo();
        vo.setRoleId("1");
        vo.setRoleName("角色1");

        boolean result = vo.validata();
        assertTrue("正确时不正确", result);

        vo.setRoleId("r");
        result = vo.validata();
        assertTrue("错误时不正确", !result);
    }
}
