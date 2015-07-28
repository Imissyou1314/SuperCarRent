/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.supercarrent.entity.po;

import org.apache.commons.beanutils.BeanMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 持久对象超类,实体的共用方法.
 * 
 * @author 侯骏雄
 * @since 3.0.0
 */
public class SuperPersistentObject {
    /**
     * log4j实例对象.
     */
    private static Logger logger = LogManager
            .getLogger(SuperPersistentObject.class);
    
    /**
     * 2的5次方.
     */
    private static final int TWO_TO_THE_POWER_OF_FIVE = 31;

    /**
     * 返回此实体的哈希码。实体对象的哈希码根据以下公式计算. s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1]
     * 使用 int 算法，这里 s[i] 是实体的第 i个成员变量的哈希吗，n是成员变量总数，^ 表示求幂。（空实体的哈希值为 0。）
     * 
     * @return 此对象的哈希码值.
     */
    @Override
    public final int hashCode() {
        logger.debug("进入hashCode方法");
        int h = 0;
        BeanMap beanMap = new BeanMap(this);
        for (Object value: beanMap.values()) {
            if (value != null) {
                h = TWO_TO_THE_POWER_OF_FIVE * h + value.hashCode();
            }
        }
        logger.debug("进入hashCode方法");
        return h;
    }

    /**
     * 将此实体与指定的对象比较. 当且仅当该参数不为 null,并且是与此对象成员变量相同的实体对象时,结果才为 true.
     * 
     * @param other
     *            与此实体进行比较的对象。
     * 
     * @return true 给定对象表示的实体与本实体相等 false 给定对象表示的实体与本实体不相等
     */
    @Override
    public final boolean equals(final Object other) {
        logger.debug("进入equals方法");
        boolean result = true;
        result = this.getClass().isInstance(other);
        
        if (result) {
            BeanMap beanMap = new BeanMap(this);
            BeanMap otherBeanMap = new BeanMap(other);
            Object val = null;
            Object otherVal = null;
            for (Object key: beanMap.keySet()) {
                val = beanMap.get(key);
                otherVal = otherBeanMap.get(key);

                if (val != null && otherVal != null) {
                    result = beanMap.get(key).equals(otherBeanMap.get(key));
                } else if (val == null && otherVal == null) {
                    result = true;
                } else {
                    result = false;
                }
                if (!result) {
                    break;
                }
            }
        }
        logger.debug("进入equals方法");
        return result;
    }
}
