/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.supercarrent.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.wteamfly.supercarrent.biz.service.ResultMessage;

/**
 * 将ResultMessage封装到json拦截器.
 * 
 * @author 侯骏雄
 * @since 3.0.0
 */
public class ResultMessageToJsonInterceptor extends AbstractInterceptor {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * log4j实例对象.
     */
    private static Logger logger = LogManager
            .getLogger(ResultMessageToJsonInterceptor.class);

    /**
     * 拦截action回调进行ResultMessage封装为json处理.
     * 
     * @param invocation
     *            action作用域
     * @throws Exception
     *             普通异常
     * @return 拦截结果
     */
    @Override
    public final String intercept(final ActionInvocation invocation)
            throws Exception {
        logger.debug("进入ResultMessage封装为json拦截器");
        invocation.invoke();
        ActionSupport action = (ActionSupport) invocation.getAction();
        Class<? extends Object> actionClass = action.getClass();
        Object resultMessage = null;
        for (Method tempMethod : actionClass.getDeclaredMethods()) {
            if (ResultMessage.class
                    .isAssignableFrom(tempMethod.getReturnType())) {
                resultMessage = tempMethod.invoke(action);
                break;
            }
        }
        if (resultMessage != null) {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/html;charset=UTF8");
            PrintWriter out = null;
            try {
                out = response.getWriter();
                out.println(((ResultMessage) resultMessage).toJson());
            } catch (IOException e) {
                logger.error("Exception:", e);
                e.printStackTrace();
            }
            out.flush();
            out.close();
        }
        logger.debug("退出ResultMessage封装为json拦截器");
        return null;
    }
}
