/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.supercarrent.helper;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * http访问操作工具类.
 * 
 * @since 3.0.0
 */
public final class HttpHelper {
    /**
     * log4j实例对象.
     */
    private static Logger logger = LogManager.getLogger(HttpHelper.class
            .getName());

    /**
     * http客户端.
     */
    private static CloseableHttpClient client = HttpClientBuilder.create()
            .build();

    /**
     * http访问操作工具类的私有构造方法，其作用是为了防止用户显式生成工具类的实例对象.
     * 
     */
    private HttpHelper() {
    }

    /**
     * 使用get方式来访问传入的url，并返回服务器反馈到的信息.
     * 
     * @param url
     *            需要访问的url
     * @return 服务器反馈信息
     */
    public static String requestByGet(final String url) {
        logger.debug("进入requestByGet方法");
        String response = null;
        try {
            HttpGet httpget = new HttpGet(url);
            response = client.execute(httpget, new BasicResponseHandler());
        } catch (Exception e) {
            boolean result = ValidateHelper.vaildate(PatternType.URL, url);
            if (!result) {
                logger.error("url格式不正确,url=" + url + "，注意勿出现中文全角标点符号");
            }
            logger.error("Exception:", e);
            e.printStackTrace();
        }
        logger.debug("退出requestByGet方法");
        return response;
    }
}
