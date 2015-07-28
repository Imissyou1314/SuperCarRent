/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.superW.entity.po;

/**
 * 数据库表持久对象（预留-生成实体用的实体类）.
 * 
 * @author 殷梓淞
 * @since 1.0.0
 */
public class TablePo {

    /**
     * 字段名称.
     */
    private String field;

    /**
     * 数据类型.
     */
    private String type;

    /**
     * 主外键.
     */
    private String key;

    /**
     * 注释.
     */
    private String comment;

    /**
     * 是否空.
     */
    private String isNull;

    /**
     * @return 获取的field
     */
    public final String getField() {
        return field;
    }

    /**
     * 设置field的方法.
     * 
     * @param field
     *            赋值给field的值
     */
    public final void setField(final String field) {
        this.field = field;
    }

    /**
     * @return 获取的type
     */
    public final String getType() {
        return type;
    }

    /**
     * 设置type的方法.
     * 
     * @param type
     *            赋值给type的值
     */
    public final void setType(final String type) {
        this.type = type;
    }

    /**
     * @return 获取的key
     */
    public final String getKey() {
        return key;
    }

    /**
     * 设置key的方法.
     * 
     * @param key
     *            赋值给key的值
     */
    public final void setKey(final String key) {
        this.key = key;
    }

    /**
     * @return 获取的comment
     */
    public final String getComment() {
        return comment;
    }

    /**
     * 设置comment的方法.
     * 
     * @param comment
     *            赋值给comment的值
     */
    public final void setComment(final String comment) {
        this.comment = comment;
    }

    /**
     * @return 获取的isNull
     */
    public final String getIsNull() {
        return isNull;
    }

    /**
     * 设置isNull的方法.
     * 
     * @param isNull
     *            赋值给isNull的值
     */
    public final void setIsNull(final String isNull) {
        this.isNull = isNull;
    }
}
