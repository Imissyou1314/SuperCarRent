package com.wteamfly.supercarrent.biz.action;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wteamfly.supercarrent.biz.service.ResultMessage;
import com.wteamfly.supercarrent.biz.service.VerifyCodeService;


/**
 * 验证码Action类.
 * 
 * @author 宋文光
 * @since 3.0.0
 */
public class VerfyCodeAction {
    /**
     * log4j实例对象.
     */
    private static Logger logger = LogManager.getLogger(OrdersManageAction.class);
    
    /**
     * session.
     */
    private Map<String,Object> session;

    /**
     * 结果信息.
     */
    private ResultMessage resultMessage;
    
    /**
     * 用户输入的验证码.
     */
    private String uVerifyCode;
    
    /**
     * @return 获取的uVerifyCode
     */
    public String getuVerifyCode() {
		return uVerifyCode;
	}

    /**
     * 设置uVerifyCode的方法.
     * 
     * @param uVerifyCode
     *            赋值给uVerifyCode的值
     */
	public void setuVerifyCode(String uVerifyCode) {
		this.uVerifyCode = uVerifyCode;
	}

	/**
     * @return 获取的resultMessage
     */
    public final ResultMessage getResultMessage() {
        return resultMessage;
    }

    /**
     * 设置resultMessage的方法.
     * 
     * @param resultMessage
     *            赋值给resultMessage的值
     */
    public final void setResultMessage(final ResultMessage resultMessage) {
        this.resultMessage = resultMessage;
    }
    
    /**
     * 设置session的方法.
     * 
     * @param session
     *            赋值给session的值
     */
    public void setSession(Map<String, Object> session) {
		this.session = session;
	}
    
    /**
     * 获取验证码.
     */
    public final void getVerifyCode() {
    	logger.debug("进入action层getVerifyCode方法");
    	final VerifyCodeService service = VerifyCodeService.getInstance();
    	resultMessage = service.getVerifyCode();
    	session.put("verifyCode", resultMessage.getResultInfo());
    	resultMessage.setUserToken(true);
    	logger.debug("退出action层getVerifyCode方法");
    }
    
    
    /**
     * 验证输入验证码.
     */
    public final void confirmVerifyCode() {
    	logger.debug("进入action层confirmVerifyCode方法");
    	final VerifyCodeService service = VerifyCodeService.getInstance();
    	final String vf = (String) session.get("verifyCode");
    	resultMessage = service.confirmVerifyCode(vf , uVerifyCode);
    	resultMessage.setUserToken(true);
    	logger.debug("退出action层confirmVerifyCode方法");
    }
    
    
    

}
