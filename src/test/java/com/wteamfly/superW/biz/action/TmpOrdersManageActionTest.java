/**
 * Copyright (c) 2007-2015 WteamFly.  All rights reserved. 网飞网络公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.superW.biz.action;

import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.wteamfly.supercarrent.biz.action.CarManageAction;

/**
 * 车辆管理Acton测试类.
 * 
 * @author 黄祥谦
 * @since 3.0.0
 */
public class TmpOrdersManageActionTest extends
        BaseActionTest<CarManageAction> {

    /**
     * testAddTmpOrders测试方法.
     * 
     * @throws Exception
     *             普通异常.
     */
    @Test
    public final void testAddTmpOrders() throws Exception {
        loginTestSystem();
//        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//        .parse("2012-01-12 23:30:20");
        request.setParameter("tmporders",
                "{\"usedTime\":\"2\""
                + ",\"getCarTime\":\"2012-01-12 23:30:20\""
                + ",\"backCarTime\":\"2012-01-12 23:30:20\""
                + ",\"totalCost\":\"120000\""
                + ",\"userId\":\"1\""
                + ",\"carInfoId\":\"1\""
                + ",\"carVersion\":\"1\""
                + ",\"carBrand\":\"宝马\""
                + ",\"carInsurance\":\"40\""
                + ",\"carGear\":\"3000\""
                + ",\"carPrice\":\"120000\""
                + ",\"carInsurance\":\"1200\""
                + ",\"carGear\":\"1.4自动挡\""
                + ",\"carCarriage\":\"三厢\""
                + ",\"carState\":\"0\""
                + ",\"isDelete\":\"false\""
                + ",\"version\":\"0\""
                + "}");
        String resultMessage = executeAction("/SuperW/addTmpOrders.action");
        boolean rs = -1 != resultMessage
                .indexOf("{\"userToken\":true,\"serviceResult\":true,\"resultInfo\":\"添加成功\"}");
        assertTrue("返回服務信息錯誤失敗", rs);
    }

    /**
     * testDeleteCar测试方法.
     *
     * @throws Exception
     *             普通异常.
     */
    @Test
    public final void testDeleteOrders() throws Exception {
    	//登录测试系统
    	loginTestSystem();
//    	private Long tmpordersId;
        request.setParameter("tmporders", "{\"tmpordersId\":\"1\"}");
//        request.setParameter("currentUser", "{\"userId\":\"1\"}");
        String resultMessage = executeAction("/SuperW/deleteTmpOrders.action");
        boolean rs = -1 != resultMessage
                .indexOf("{\"userToken\":true,\"serviceResult\":true,\"resultInfo\":\"成功逻辑删除\"}");
        assertTrue("返回服務信息錯誤失敗", rs);
    }

    /**
     * testEditCar测试方法.
     * 
     * @throws Exception
     *             普通异常.
     */
    @Test
    public final void testEditOrders() throws Exception {
    	loginTestSystem();
    	 request.setParameter("tmporders",
                 "{\"usedTime\":\"2\""
                 + ",\"getCarTime\":\"2012-01-12 23:30:20\""
                 + ",\"backCarTime\":\"2012-01-12 23:30:20\""
                 + ",\"totalCost\":\"120000\""   //id是必须的
                 + ",\"tmpordersId\":\"2\""
                 + ",\"userId\":\"1\""
                 + ",\"carInfoId\":\"1\""
                 + ",\"carVersion\":\"1\""
                 + ",\"carBrand\":\"宝马\""
                 + ",\"carInsurance\":\"40\""
                 + ",\"carGear\":\"3000\""
                 + ",\"carPrice\":\"120000\""
                 + ",\"carInsurance\":\"1200\""
                 + ",\"carGear\":\"1.4自动挡\""
                 + ",\"carCarriage\":\"三厢\""
                 + ",\"carVersion\":\"x86\""
                 + ",\"isDelete\":\"false\""
                 + "}");
        String resultMessage = executeAction("/SuperW/editTmpOrders.action");
        boolean rs = -1 != resultMessage
                .indexOf("{\"userToken\":true,\"serviceResult\":true,\"resultInfo\":\"修改成功\"}");
        assertTrue("返回服務信息錯誤失敗", rs);
    }
    /**
     * testGetAllCars测试方法.
     *
     * @throws Exception
     *             普通异常.
     */
    @Test
    public final void testGetAllTmpOrders() throws Exception {
    	loginTestSystem();
//    	request.setParameter("carInfo",
//    			"{\"carBrand\":\"宝马\""
//    					+ "}");
    	request.setParameter("page", "{\"size\":\"1\", \"indexPageNum\":\"1\"}");//分页函数必备参数
    	String resultMessage = executeAction("/SuperW/getAllTmpOrders.action");
    	boolean rs = -1 != resultMessage
    			.indexOf("{\"userToken\":true,\"serviceResult\":true,\"resultInfo\":\"查询实体成功\"}");
    	assertTrue("返回服務信息錯誤失敗", rs);
    }

    /**
     * testPageCar测试方法.
     * 
     * @throws Exception
     *             普通异常.
     */
    @Test
    public final void testPageTmpOrders() throws Exception {
    	loginTestSystem();
        request.setParameter("page", "{\"size\":\"1\", \"indexPageNum\":\"1\"}");
        String resultMessage = executeAction("/SuperW/pageCar.action");
        boolean rs = -1 != resultMessage.indexOf("\"resultInfo\":\"分页成功\"");
        assertTrue("返回服務信息錯誤失敗", rs);

        request.setParameter("permission", "{\"permissionName\":\"page\"}");
        request.setParameter("page", "{\"size\":\"1\", \"indexPageNum\":\"1\"}");
        resultMessage = executeAction("/SuperW/pageTmpOrders.action");
        rs = -1 != resultMessage.indexOf("\"resultInfo\":\"分页成功\"");
        assertTrue("返回服務信息錯誤失敗", rs);
    }
}
