/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.supercarrent.helper;

/**
 * 正则表达式类型枚举.
 * 
 * @since 3.0.0
 */
public enum PatternType {
    /**
     * 纯数字.
     */
    DIGIT,
    /**
     * 手机号码.
     */
    MOBILENUM,
    /**
     * 手机验证码.
     */
    SMSCODE,
    /**
     * MD532位.
     */
    MD5_32,
    /**
     * 新浪微博授权码.
     */
    SINAAUTHCODE,
    /**
     * url.
     */
    URL,
    /**
     * json.
     */
    JSON,
    /**
     * 标准时间格式.
     */
    DATETIME,
    /**
     * 纯字母.
     */
    LETTER,
    /**
     * 纯汉字.
     */
    CHINESE_CHAR
}
