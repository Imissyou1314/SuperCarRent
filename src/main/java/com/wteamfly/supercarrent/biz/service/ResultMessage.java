/**
 * Copyright (c) 2007-2015 WteamFly.  All rights reserved. 网飞网络公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.supercarrent.biz.service;

import java.util.Map;

import com.wteamfly.supercarrent.helper.JsonHelper;

/**
 * 服务返回信息类.
 * 
 * @author 侯骏雄
 * @since 3.0.0
 */
public class ResultMessage {
    /**
     * 用户令牌判断结果.
     */
    private Boolean userToken;

    /**
     * 新浪授权码判断结果.
     */
    private Boolean sinaAuthCode;

    /**
     * 服务类执行结果.
     */
    private Boolean serviceResult;

    /**
     * 返回结果信息.
     */
    private String resultInfo;

    /**
     * @return 获取的sinaAuthCode
     */
    public final Boolean getSinaAuthCode() {
        return sinaAuthCode;
    }

    /**
     * 设置sinaAuthCode的方法.
     * 
     * @param sinaAuthCode
     *            赋值给sinaAuthCode的值
     */
    public final void setSinaAuthCode(final Boolean sinaAuthCode) {
        this.sinaAuthCode = sinaAuthCode;
    }

    /**
     * 返回参数，又vo的json字符串组成.
     */
    private Map<String, Object> resultParm;

    /**
     * @return 获取的userToken
     */
    public final Boolean getUserToken() {
        return userToken;
    }

    /**
     * 设置userToken的方法.
     * 
     * @param userToken
     *            赋值给userToken的值
     */
    public final void setUserToken(final Boolean userToken) {
        this.userToken = userToken;
    }

    /**
     * @return 获取的serviceResult
     */
    public final Boolean getServiceResult() {
        return serviceResult;
    }

    /**
     * 设置serviceResult的方法.
     * 
     * @param serviceResult
     *            赋值给serviceResult的值
     */
    public final void setServiceResult(final Boolean serviceResult) {
        this.serviceResult = serviceResult;
    }

    /**
     * @return 获取的resultInfo
     */
    public final String getResultInfo() {
        return resultInfo;
    }

    /**
     * 设置resultInfo的方法.
     * 
     * @param resultInfo
     *            赋值给resultInfo的值
     */
    public final void setResultInfo(final String resultInfo) {
        this.resultInfo = resultInfo;
    }

    /**
     * @return 获取的resultParm
     */
    public final Map<String, Object> getResultParm() {
        return resultParm;
    }

    /**
     * 设置resultParm的方法.
     * 
     * @param resultParm
     *            赋值给resultParm的值
     */
    public final void setResultParm(final Map<String, Object> resultParm) {
        this.resultParm = resultParm;
    }

    /**
     * 拼接json字符串以返回.
     * 
     * @return 返回信息的json字符串
     */
    public final String toJson() {
        return JsonHelper.toJson(this);
    }
}
