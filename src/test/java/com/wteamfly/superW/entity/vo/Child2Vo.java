/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.superW.entity.vo;

import com.wteamfly.supercarrent.entity.vo.SuperValueObject;

/**
 * 值对象超类测试子类用于继承.
 * 
 * @author 殷梓淞
 * @since v2.0.0
 */
public class Child2Vo extends SuperValueObject {
    /**
     * 测试用成员变量.
     */
    private String testfield2;

    /**
     * @return 获取的testfield2
     */
    public final String getTestfield2() {
        return testfield2;
    }

    /**
     * 设置testfield2的方法.
     * @param testfield2 赋值给testfield2的值
     */
    public final void setTestfield2(final String testfield2) {
        this.testfield2 = testfield2;
    }
    
}
