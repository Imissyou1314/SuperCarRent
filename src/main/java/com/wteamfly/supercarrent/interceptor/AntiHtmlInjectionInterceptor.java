/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.supercarrent.interceptor;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 防止html注入拦截器.
 * 
 * @author 侯骏雄
 * @since 3.0.0
 */
public class AntiHtmlInjectionInterceptor extends AbstractInterceptor {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * log4j实例对象.
     */
    private static Logger logger = LogManager
            .getLogger(AntiHtmlInjectionInterceptor.class);

    /**
     * 拦截action进行防html注入处理.
     * 
     * @param invocation
     *            action作用域
     * @throws Exception
     *             普通异常
     * @return 拦截结果
     */
    @SuppressWarnings("unchecked")
    @Override
    public final String intercept(final ActionInvocation invocation)
            throws Exception {
        logger.debug("进入防html注入拦截器");
        // 获取参数
        ActionContext ctx = invocation.getInvocationContext();
        @SuppressWarnings("rawtypes")
        Map parm = ctx.getParameters();
        Object tempObj = null;
        String turnStr = null;
        for (Object key : parm.keySet()) {
            tempObj = parm.get(key);
            if (tempObj.getClass().isAssignableFrom(String.class)) {
                turnStr = ((String) tempObj).replaceAll("<", "&lt;");
                turnStr = turnStr.replaceAll(">", "&rt;");
                parm.put(key, turnStr);
            }
        }
        invocation.invoke();
        logger.debug("退出防html注入拦截器");
        return null;
    }
}
