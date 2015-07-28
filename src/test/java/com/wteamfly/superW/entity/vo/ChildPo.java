/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.superW.entity.vo;

import java.sql.Timestamp;

import com.wteamfly.supercarrent.entity.po.SuperPersistentObject;

/**
 * 值对象超类测试子类.
 * 
 * @author 侯骏雄
 * @since 3.0.0
 */
public class ChildPo extends SuperPersistentObject {

    /**
     * 测试用成员变量.
     */
    private String testfield;

    /**
     * 测试日期成员变量.
     */
    private Timestamp time;

    /**
     * 测试布尔型成员变量.
     */
    private Boolean bool;

    /**
     * @return 获取的testfield
     */
    public final String getTestfield() {
        return testfield;
    }

    /**
     * 设置testfield的方法.
     * 
     * @param testfield
     *            赋值给testfield的值
     */
    public final void setTestfield(final String testfield) {
        this.testfield = testfield;
    }

    /**
     * @return 获取的time
     */
    public final Timestamp getTime() {
        return time;
    }

    /**
     * 设置time的方法.
     * 
     * @param time
     *            赋值给time的值
     */
    public final void setTime(final Timestamp time) {
        this.time = time;
    }

    /**
     * @return 获取的bool
     */
    public final Boolean getBool() {
        return bool;
    }

    /**
     * 设置bool的方法.
     * 
     * @param bool
     *            赋值给bool的值
     */
    public final void setBool(final Boolean bool) {
        this.bool = bool;
    }

}
