/**
 * Copyright (c) 2007-2015 WteamFly.  All rights reserved. 网飞网络公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.superW.biz.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.StrutsJUnit4TestCase;

import com.wteamfly.supercarrent.entity.po.UserPo;
import com.wteamfly.supercarrent.helper.ShiroHelper;

/**
 * 测试Action基类.
 * 
 * @author 黄祥谦
 * @since 3.0.0
 */
public class BaseActionTest<T> extends
        StrutsJUnit4TestCase<T> {

    public void loginTestSystem(){
    	 Subject currentUser = ShiroHelper.getSubject(this.request,
                 this.response);
         UsernamePasswordToken token = new UsernamePasswordToken("易临风",
                 "123456");
         token.setRememberMe(true);
         UserPo uPo = null ;
         try {
             currentUser.login(token);
             uPo = new UserPo();
             uPo.setUserId(Long.valueOf("2"));
             uPo.setLoginName("易临风");
             uPo.setPassword("123456");
             Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                     .parse("2012-01-12 23:30:20");
             uPo.setCreateTime(date);
             uPo.setCreatorId(Long.valueOf("1"));
             uPo.setIsDelete(false);
             uPo.setIsLockUp(false);
             uPo.setVersion(Long.valueOf("0"));
             currentUser.getSession().setAttribute("user", uPo);
         } catch (Exception se) {
             se.printStackTrace();
         }
    }
}
