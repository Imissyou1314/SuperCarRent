/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.supercarrent.entity.vo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.collections.list.UnmodifiableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wteamfly.supercarrent.entity.po.SuperPersistentObject;
import com.wteamfly.supercarrent.helper.JsonHelper;
import com.wteamfly.supercarrent.helper.ValidateHelper;

/**
 * 值对象超类,该类提供了一些值对象共用的方法.
 * 
 * @author 侯骏雄
 * @since 3.0.0
 */
public class SuperValueObject extends SuperPersistentObject {
    /**
     * log4j实例对象.
     */
    private static Logger logger = LogManager.getLogger(SuperValueObject.class
            .getName());

    /**
     * 返回转换后的参数.
     * 
     * @param obj
     *            成员变量
     * @param castClass
     *            转换类型
     * @return 指定类型参数
     */
    private static Object castVoParam(final Object obj, final Class<?> castClass) {
        logger.debug("进入castVoParam方法");
        Object castParm = null;
        try {
            if (castClass.isAssignableFrom(Timestamp.class)) {
                Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                        .parse(String.valueOf(obj));
                castParm = new Timestamp(date.getTime());
            } else if (castClass.isAssignableFrom(String.class)) {
                castParm = obj;
            } else {
                castParm = castClass.getMethod("valueOf", String.class).invoke(
                        castClass, obj);
            }
        } catch (Exception e) {
            logger.error("Exception:", e);
            e.printStackTrace();
        }
        logger.debug("退出castVoParam方法");
        return castParm;
    }

    /**
     * 返回转换后的参数.
     * 
     * @param obj
     *            成员变量
     * @return string类型参数
     */
    private static Object castPoParam(final Object obj) {
        logger.debug("进入castPoParam方法");
        Object castParm = null;
        try {
            Class<?> objClass = obj.getClass();
            if (objClass.isAssignableFrom(Timestamp.class)) {
                castParm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                        .format(obj);
            } else if (objClass.isAssignableFrom(String.class)) {
                castParm = obj;
            } else {
                castParm = String.valueOf(obj);
            }
        } catch (Exception e) {
            logger.error("Exception:", e);
            e.printStackTrace();
        }
        logger.debug("退出castPoParam方法");
        return castParm;
    }

    /**
     * 将值对象转换为持久对象.
     * 
     * @param poClass
     *            持久类对象
     * @param <T>
     *            持久类对象类型
     * @return 转换后的持久对象.
     */
    public final <T> T voToPo(final Class<T> poClass) {
        logger.debug("进入voToPo方法");
        T poObj = null;
        try {
            BeanMap voMap = new BeanMap(this);
            UnmodifiableList voValues = (UnmodifiableList) voMap.values();
            @SuppressWarnings({ "unchecked", "rawtypes" })
            List<?> tempList = new ArrayList(voValues);
            boolean hasNull = true;
            do {
                hasNull = tempList.remove(null);
            } while (hasNull);

            // 默认有一个代表本对象类型的class成员变量
            if (tempList.size() > 1) {
                poObj = poClass.newInstance();
                BeanMap poMap = new BeanMap(poObj);
                Object value = null;
                String keyName = null;
                for (Object key : voMap.keySet()) {
                    value = voMap.get(key);
                    keyName = key.toString();
                    if (value != null
                            && !value.getClass().isAssignableFrom(Class.class)
                            && !"".equals(value)) {
                        poMap.getWriteMethod(keyName).invoke(poObj,
                                castVoParam(value, poMap.getType(keyName)));
                    }
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        logger.debug("退出voToPo方法");
        return poObj;
    }

    /**
     * 持久层对象转换为值对象.
     * 
     * @param po
     *            数据持久层对象.
     * @param <T>
     *            数据持久层对象类型.
     */
    public final <T> void poToVo(final T po) {
        logger.debug("进入poToVo方法");
        try {
            BeanMap voMap = new BeanMap(this);
            BeanMap poMap = new BeanMap(po);
            Object value = null;
            String keyName = null;
            for (Object key : poMap.keySet()) {
                value = poMap.get(key);
                keyName = key.toString();
                if (value != null
                        && !value.getClass().isAssignableFrom(Class.class)
                        && !"".equals(value)) {
                    voMap.getWriteMethod(keyName).invoke(this,
                            castPoParam(value));
                }
            }
        } catch (Exception e) {
            logger.error("Exception:", e);
            e.printStackTrace();
        }
        logger.debug("退出poToVo方法");
    }

    /**
     * json字符串转换为持久层对象.
     * 
     * @param json
     *            json字符串.
     */
    public final void jsonToVo(final String json) {
        logger.debug("进入jsonToVo方法");
        try {
            Class<?> beanClass = this.getClass();

            Object bean = JsonHelper.jsonToBean(json, beanClass);
            
            BeanMap voMap = new BeanMap(this);
            BeanMap beanMap = new BeanMap(bean);
            Object value = null;
            String keyName = null;
            for (Object key : beanMap.keySet()) {
                value = beanMap.get(key);
                keyName = key.toString();
                if (value != null
                        && !value.getClass().isAssignableFrom(Class.class)
                        && !"".equals(value)) {
                    voMap.getWriteMethod(keyName).invoke(this,
                            castPoParam(value));
                }
            }
        } catch (Exception e) {
            logger.error("Exception:", e);
            e.printStackTrace();
        }
        logger.debug("退出jsonToVo方法");
    }

    /**
     * 验证vo中的值.
     * 
     * @return true 全部正确 false 存在错误值
     */
    public final boolean validata() {
        logger.debug("进入validata方法");
        boolean result = ValidateHelper.vaildateBean(this);
        logger.debug("退出validata方法");
        return result;
    }
}
