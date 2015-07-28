/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.supercarrent.interceptor;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.wteamfly.supercarrent.biz.dao.PermissionDaoProxy;
import com.wteamfly.supercarrent.biz.service.ResultMessage;
import com.wteamfly.supercarrent.entity.po.PermissionPo;
import com.wteamfly.supercarrent.entity.po.RolePo;
import com.wteamfly.supercarrent.entity.po.UserPo;
import com.wteamfly.supercarrent.entity.vo.UserVo;
import com.wteamfly.supercarrent.helper.ShiroHelper;

/**
 * shiro身份验证拦截器.
 * 
 * @author 侯骏雄
 * @since 3.0.0
 */
public class ShiroInterceptor extends AbstractInterceptor {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * log4j实例对象.
     */
    private static Logger logger = LogManager.getLogger(ShiroInterceptor.class);

    /**
     * 访客的角色id.
     */
    private static final long VISITOR_ROLEID = 1L;

    /**
     * 拦截action进行身份验证处理.
     * 
     * @param invocation
     *            action作用域
     * @throws Exception
     *             普通异常
     * @return 拦截结果
     */
    @SuppressWarnings("unused")
	@Override
    public final String intercept(final ActionInvocation invocation)
            throws Exception {
        logger.debug("进入拦截action进行身份验证处理");
        String permissionName = invocation.getProxy().getMethod();
        PermissionDaoProxy permissionDaoProxy = PermissionDaoProxy
                .getInstance();
        PermissionPo pPoForVisitor = new PermissionPo();
        pPoForVisitor.setPermissionName(permissionName);
        RolePo rPoForVisitor = new RolePo();
        rPoForVisitor.setRoleId(VISITOR_ROLEID);
        PermissionDaoProxy dao = PermissionDaoProxy.getInstance();
        boolean result = dao.hasPermissionByRoleId(pPoForVisitor, rPoForVisitor);

        // 当result为true时，该action对应的行为具有访客权限不需要登录进入else中
        
        if (!result) {
            Subject currentUser = ShiroHelper.getSubject(
                    ServletActionContext.getRequest(),
                    ServletActionContext.getResponse());
            // 当currentUser.isAuthenticated()为true时说明当前用户已经登录
            if (currentUser.isAuthenticated()) {
                UserPo uPo = (UserPo) currentUser.getSession().getAttribute(
                        "user");
                boolean permissionResult = permissionDaoProxy
                        .hasPermissionByUserId(pPoForVisitor, uPo);
                
              //TODO 测试是将权限改成完全通过，到时候在改回来if(permissionResult)测试方便
                if (true) {
                    UserVo uVo = new UserVo();
                    uVo.poToVo(uPo);
                    invocation.getInvocationContext().getParameters()
                            .put("currentUser", uVo);
                    invocation.invoke();
                } else {
                    ResultMessage rs = new ResultMessage();
                    rs.setResultInfo("没有该操作权限");
                    rs.setServiceResult(false);
                    rs.setUserToken(false);
                    ActionSupport action = (ActionSupport) invocation
                            .getAction();
                    Class<? extends Object> actionClass = action.getClass();
                    for (Method tempMethod : actionClass.getDeclaredMethods()) {
                        if (Arrays.asList(tempMethod.getParameterTypes())
                                .contains(ResultMessage.class)) {
                            tempMethod.invoke(action, rs);
                            break;
                        }
                    }
                }
            } else {
                ResultMessage rs = new ResultMessage();
                if (currentUser.isRemembered()) {
                    rs.setResultInfo("没有该操作权限");
                } else {
                    rs.setResultInfo("请重新登录");
                }
                rs.setServiceResult(false);
                rs.setUserToken(false);
                ActionSupport action = (ActionSupport) invocation.getAction();
                Class<? extends Object> actionClass = action.getClass();
                for (Method tempMethod : actionClass.getDeclaredMethods()) {
                    if (Arrays.asList(tempMethod.getParameterTypes()).contains(
                            ResultMessage.class)) {
                        tempMethod.invoke(action, rs);
                        break;
                    }
                }
            }
        } else {
            invocation.invoke();
        }
        logger.debug("退出拦截action进行身份验证处理");
        return null;
    }
}
