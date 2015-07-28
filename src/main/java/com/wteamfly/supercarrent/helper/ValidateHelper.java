/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.supercarrent.helper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 数据验证操作工具类.
 * 
 * @since 3.0.0
 */
public final class ValidateHelper {
    /**
     * log4j实例对象.
     */
    private static Logger logger = LogManager.getLogger(ValidateHelper.class
            .getName());
    /**
     * 字符串"get"的长度.
     */
    private static final int LENGTH_OF_STRING_GET = 3;
    /**
     * 纯数字正则表达式.
     */
    private static Pattern digitPattern = Pattern.compile("[\\d]*");
    /**
     * 手机号码正则表达式.
     */
    private static Pattern mobileNumPattern = Pattern.compile("1+\\d{10}");
    /**
     * 短信验证码正则表达式.
     */
    private static Pattern smsCodePattern = Pattern.compile("[\\w\\d]{6}");
    /**
     * md5 32位正则表达式.
     */
    private static Pattern md532Pattern = Pattern.compile("[\\w\\d]{32}");
    /**
     * 纯字母正则表达式.
     */
    private static Pattern letterPattern = Pattern.compile("[A-Za-z]*");
    /**
     * 新浪微博授权码正则表达式.
     */
    private static Pattern sinaAuthCodePattern = Pattern
            .compile("[\\w\\d\\.]{32}");
    /**
     * url正则表达式.
     */
    private static Pattern urlPattern = Pattern
            .compile("(http|https):\\/\\/[\\w\\-_]+(\\.[\\w\\-_]+)"
                    + "+([\\w\\u4e00-\\u9fa5\\-\\.,@!\\[\\]?^=%&amp;:/~\\+#]*"
                    + "[\\w\\u4e00-\\u9fa5\\-\\@!\\[\\]?^=%&amp;/~\\+#])?");
    /**
     * json正则表达式.
     */
    private static Pattern jsonPattern = Pattern.compile("\\{"
            + "(\"(.*)\":(.*))?((,\"(.*)\":(.*))*)" + "\\}");
    /**
     * 时间格式正则表达式.
     */
    private static Pattern dateTimePattern = Pattern
            .compile("[\\d]{4}-[\\d]{2}-[\\d]{2}\\s"
                    + "[\\d]{2}:[\\d]{2}:[\\d]{2}");
    /**
     * 纯中文正则表达式.
     */
    private static Pattern chineseCharPattern = Pattern
            .compile("[\\u4e00-\\u9fa5]*");
    /**
     * 手机号码长度.
     */
    private static final int MOBILENUMBER_LENGTH = 11;
    /**
     * 短信验证码的长度.
     */
    private static final int SMSCODE_LENGTH = 6;
    /**
     * md5 32位的长度.
     */
    private static final int MD532_LENGTH = 32;
    /**
     * "yyyy-MM-dd HH:mm:ss"时间格式的字符串长度.
     */
    private static final int STANDARD_DATE_FORMATE_LENGTH = 19;

    /**
     * 数据验证操作工具类的私有构造方法，其作用是为了防止用户显式生成工具类的实例对象.
     * 
     */
    private ValidateHelper() {
    }

    /**
     * 根据type验证data的格式是否正确.
     * 
     * @param type
     *            所要验证的类型
     * @param data
     *            所要验证的数据
     * @return 验证结果
     */
    public static boolean vaildate(final PatternType type, final String data) {
        logger.debug("进入vaildata方法");
        boolean result = false;
        switch (type) {
        case DIGIT:
            result = digitPattern.matcher(data).matches();
            break;
        case MOBILENUM:
            if (data.length() == MOBILENUMBER_LENGTH) {
                result = mobileNumPattern.matcher(data).matches();
            }
            break;
        case SMSCODE:
            if (data.length() == SMSCODE_LENGTH) {
                result = smsCodePattern.matcher(data).matches();
            }
            break;
        case MD5_32:
            if (data.length() == MD532_LENGTH) {
                result = md532Pattern.matcher(data).matches();
            }
            break;
        case SINAAUTHCODE:
            if (data.length() == MD532_LENGTH) {
                result = sinaAuthCodePattern.matcher(data).matches();
            }
            break;
        case URL:
            result = urlPattern.matcher(data).matches();
            break;
        case JSON:
            result = jsonPattern.matcher(data.replace(" ", "")).matches();
            break;
        case DATETIME:
            if (data.length() == STANDARD_DATE_FORMATE_LENGTH) {
                result = dateTimePattern.matcher(data).matches();
            }
            break;
        case LETTER:
            result = letterPattern.matcher(data).matches();
            break;
        case CHINESE_CHAR:
            result = chineseCharPattern.matcher(data).matches();
            break;
        default:
            break;
        }
        logger.debug("退出vaildata方法");
        return result;
    }

    /**
     * 验证实体是否正确.
     * 
     * @param bean
     *            所要验证的实体
     * @param <T>
     *            所要验证的实体类型
     * @return 验证结果
     */
    public static <T> boolean vaildateBean(final T bean) {
        logger.debug("进入vaildateBean方法");
        boolean result = true;
        try {
            Class<?> beanClass = bean.getClass();
            String methodName = null;
            String fieldName = null;
            Object methodResult = null;
            Field field = null;
            ValidateType annotation = null;
            String validateType = null;
            for (Method m : beanClass.getDeclaredMethods()) {
                methodName = m.getName();
                if ("get".equals(methodName.substring(0, LENGTH_OF_STRING_GET))) {
                    methodResult = m.invoke(bean);
                    if (methodResult != null && !"".equals(methodResult)
                            && !"null".equals(methodResult)) {
                        fieldName = methodName.substring(LENGTH_OF_STRING_GET,
                                methodName.length());
                        char[] fieldNameArr = fieldName.toCharArray();
                        fieldNameArr[0] = Character
                                .toLowerCase(fieldNameArr[0]);
                        fieldName = new String(fieldNameArr);
                        field = beanClass.getDeclaredField(fieldName);
                        annotation = field.getAnnotation(ValidateType.class);
                        if (annotation != null) {
                            validateType = annotation.value();
                            if (!vaildate(PatternType.valueOf(validateType),
                                    String.valueOf(methodResult))) {
                                result = false;
                                break;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Exception:", e);
            e.printStackTrace();
        }
        logger.debug("退出vaildateBean方法");
        return result;
    }
}
