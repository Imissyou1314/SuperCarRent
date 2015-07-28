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

import com.wteamfly.supercarrent.entity.vo.RoleVo;
import com.wteamfly.supercarrent.helper.PatternType;
import com.wteamfly.supercarrent.helper.ValidateHelper;

/**
 * 数据验证操作工具类测试.
 * 
 * @author 侯骏雄
 * @since 3.0.0
 */
public class ValidateHelperTest {

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
     * 根据type验证data的格式是否正确测试. 1.正确的手机号码 2.正确的短信验证码 3.正确的md532位 4.正确的新浪微博授权码
     * 5.正确的纯数字 6.正确的Url 7.正确的Json 8.正确的时间 9.正确的纯字母 10.正确的纯汉字 10.数据为空 11.类型为空
     */
    @Test
    public final void testVaildata() {
        String data = "12345678901";
        boolean result = ValidateHelper.vaildate(PatternType.MOBILENUM, data);
        assertTrue("正确的手机号码失败", result);

        data = "12df34";
        result = ValidateHelper.vaildate(PatternType.SMSCODE, data);
        assertTrue("正确的短信验证码失败", result);

        data = "AAAAAAAAAAZZZZZZZZZZXXXXXXXXXXRR";
        result = ValidateHelper.vaildate(PatternType.MD5_32, data);
        assertTrue("正确的md532位失败", result);

        data = "2.00AAAAAAZZZZZZZZZZXXXXXXXXXXRR";
        result = ValidateHelper.vaildate(PatternType.SINAAUTHCODE, data);
        assertTrue("正确的新浪微博授权码失败", result);

        data = "12345";
        result = ValidateHelper.vaildate(PatternType.DIGIT, data);
        assertTrue("正确的纯数字", result);

        data = "http://www.baidu.com";
        result = ValidateHelper.vaildate(PatternType.URL, data);
        assertTrue("正确的Url", result);

        data = "{\"a\":\"b\",\"1\":\"2\"}";
        result = ValidateHelper.vaildate(PatternType.JSON, data);
        assertTrue("正确的Json", result);

        data = "2012-01-12 23:30:20";
        result = ValidateHelper.vaildate(PatternType.DATETIME, data);
        assertTrue("正确的时间", result);

        data = "asdf";
        result = ValidateHelper.vaildate(PatternType.LETTER, data);
        assertTrue("正确的纯字母", result);

        data = "吃货";
        result = ValidateHelper.vaildate(PatternType.CHINESE_CHAR, data);
        assertTrue("正确的纯汉字", result);
    }

    /**
     * 验证实体是否正确测试. 1.验证实体错误 2.bean为空
     */
    @Test
    public final void testVaildateBean() {
        RoleVo vo = new RoleVo();
        vo.setRoleId("ed");
        vo.setRoleName("角色1");

        boolean result = ValidateHelper.vaildateBean(vo);
        assertTrue("验证实体错误时失败", !result);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        System.setErr(printStream);
        ValidateHelper.vaildateBean(null);
        String resultMessage = byteArrayOutputStream.toString();
        result = -1 != resultMessage.indexOf("NullPointerException");
        assertTrue("数据为空时失败", result);
    }

}
