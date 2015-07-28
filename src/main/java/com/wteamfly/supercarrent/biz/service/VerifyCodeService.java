/**
 * Copyright (c) 2007-2015 WteamFly.  All rights reserved. 网飞网络公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.supercarrent.biz.service;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wteamfly.supercarrent.helper.VerifyCode;


/**
 * 生成验证码service类.
 * 
 * @author 宋文光
 * @since 3.0.0
 */
public class VerifyCodeService {
	/**
     * log4j实例对象.
     */
    private static Logger logger = LogManager.getLogger(SuperService.class);
    
    /**
     * 单例对象.
     */
    private static VerifyCodeService instance = new VerifyCodeService();
    
    /**
     * 单例模式的私有构造方法.
     */
    private VerifyCodeService() {
    }
    
    /**
     * 获取单例.
     * 
     * @return 单例
     */
    public static VerifyCodeService getInstance() {
        return instance;
    }
    
    /**
     * 生成验证码.
     * 
     * @return ResultMessage 验证码.
     */
    public ResultMessage getVerifyCode()  {
    	ResultMessage rs = new ResultMessage();
    	logger.debug("进入getVerifyCode方法");
    	try {
			VerifyCode verifycode = new VerifyCode();
			BufferedImage bi = verifycode.getImage();
			VerifyCode.output(bi, new FileOutputStream("f:/a.jpg"));
			String vf = verifycode.getText();
			rs.setResultInfo(vf);
			rs.setServiceResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setResultInfo("生成失败");
            rs.setServiceResult(false);
		}
    	logger.debug("退出getVerifyCode方法");
		return rs;
    }
    
    /**
     * 验证输入验证码.
     * 
     * @param verifyCode
     *            系统生成验证码.
     * @param uVerifyCode
     *            用户输入的验证码.
     * @return ResultMessage 服务信息.
     */
    public ResultMessage confirmVerifyCode(final String verifyCode 
    		, final String uVerifyCode) {
    	ResultMessage rs = new ResultMessage();
    	logger.debug("进入ConfirmVerifyCode方法");
    	rs.setServiceResult(false);
    	if (verifyCode == uVerifyCode) {
    		rs.setServiceResult(true);
    	} 
    	logger.debug("退出ConfirmVerifyCode方法");
		return rs;
    }
    
    
}
