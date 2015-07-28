/**
 * Copyright (c) 2007-2015 WteamFly.  All rights reserved. 网飞网络公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.superW.biz.action;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.wteamfly.supercarrent.biz.action.CarManageAction;

/**
 * 车辆管理Acton测试类.
 * 
 * @author 黄祥谦
 * @since 3.0.0
 */
public class CarManageActionTest extends
        BaseActionTest<CarManageAction> {

    /**
     * testAddCar测试方法.
     * 
     * @throws Exception
     *             普通异常.
     */
    @Test
    public final void testAddCar() throws Exception {
        loginTestSystem();
        request.setParameter("carInfo",
                "{\"carType\":\"三厢\""
                + ",\"carBrand\":\"宝马\""
                + ",\"carVersion\":\"x86\""
                + ",\"carPrice\":\"120000\""
                + ",\"carInsurance\":\"1200\""
                + ",\"carGear\":\"1.4自动挡\""
                + ",\"carCarriage\":\"三厢\""
                + ",\"carState\":\"0\""
                + "}");
//        request.setParameter("currentUser", "{\"userId\":\"1\"}");
//        System.out.println(request.getSession().getAttribute("currentUser"));
        String resultMessage = executeAction("/SuperW/addCar.action");
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
    public final void testDeleteCar() throws Exception {
    	//登录测试系统
    	loginTestSystem();
        request.setParameter("carInfo", "{\"carInfoId\":\"4\"}");
//        request.setParameter("currentUser", "{\"userId\":\"1\"}");
        String resultMessage = executeAction("/SuperW/deleteCar.action");
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
    public final void testEditCar() throws Exception {
    	loginTestSystem();
    	 request.setParameter("carInfo",
                 "{\"carType\":\"三厢\""
                 + ",\"carInfoId\":\"1\""    //必备参数id
                 + ",\"carBrand\":\"宝马\""
                 + ",\"carVersion\":\"x86\""
                 + ",\"carPrice\":\"120000\""
                 + ",\"carInsurance\":\"1200\""
                 + ",\"carGear\":\"1.4自动挡1\""
                 + ",\"carCarriage\":\"三厢\""
                 + ",\"carState\":\"0\""
                 + "}");
        String resultMessage = executeAction("/SuperW/editCar.action");
        boolean rs = -1 != resultMessage
                .indexOf("{\"userToken\":true,\"serviceResult\":true,\"resultInfo\":\"修改成功\"}");
        assertTrue("返回服務信息錯誤失敗", rs);
    }
    /**
     * testOrderCarsByMoney测试方法.
     * 
     * @throws Exception
     *             普通异常.
     */
    @Test
    public final void testOrderCarsByMoney() throws Exception {
    	loginTestSystem();
//    	request.setParameter("carInfo",
//    			"{\"carPrice\":\"120000\""
//    					+ "}");
    	 request.setParameter("page", "{\"size\":\"1\", \"indexPageNum\":\"1\"}");//分页函数必备参数
    	String resultMessage = executeAction("/SuperW/orderCarsByMoney.action");
    	boolean rs = -1 != resultMessage
    			.indexOf("{\"userToken\":true,\"serviceResult\":true,\"resultInfo\":\"修改成功\"}");
    	assertTrue("返回服務信息錯誤失敗", rs);
    }
    /**
     * testGetCarsByBrand测试方法.
     *
     * @throws Exception
     *             普通异常.
     */
    @Test
    public final void testGetCarsByBrand() throws Exception {
    	loginTestSystem();
    	request.setParameter("carInfo",
    			"{\"carBrand\":\"宝马\""
    					+ "}");
    	request.setParameter("page", "{\"size\":\"1\", \"indexPageNum\":\"1\"}");//分页函数必备参数
    	String resultMessage = executeAction("/SuperW/getCarsByBrand.action");
    	boolean rs = -1 != resultMessage
    			.indexOf("{\"userToken\":true,\"serviceResult\":true,\"resultInfo\":\"修改成功\"}");
    	assertTrue("返回服務信息錯誤失敗", rs);
    }
    /**
     * testGetCarsByType测试方法.
     *
     * @throws Exception
     *             普通异常.
     */
    @Test
    public final void testGetCarsByType() throws Exception {
    	loginTestSystem();
    	request.setParameter("carInfo",
    			"{\"carType\":\"三厢\""
    					+ "}");
    	request.setParameter("page", "{\"size\":\"1\", \"indexPageNum\":\"1\"}");//分页函数必备参数
    	String resultMessage = executeAction("/SuperW/getCarsByType.action");
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
    public final void testGetAllCars() throws Exception {
    	loginTestSystem();
//    	request.setParameter("carInfo",
//    			"{\"carBrand\":\"宝马\""
//    					+ "}");
    	request.setParameter("page", "{\"size\":\"1\", \"indexPageNum\":\"1\"}");//分页函数必备参数
    	String resultMessage = executeAction("/SuperW/getAllCars.action");
    	boolean rs = -1 != resultMessage
    			.indexOf("{\"userToken\":true,\"serviceResult\":true,\"resultInfo\":\"修改成功\"}");
    	assertTrue("返回服務信息錯誤失敗", rs);
    }

    /**
     * testPageCar测试方法.
     * 
     * @throws Exception
     *             普通异常.
     */
    @Test
    public final void testPageCar() throws Exception {
    	loginTestSystem();
        request.setParameter("page", "{\"size\":\"1\", \"indexPageNum\":\"1\"}");
        String resultMessage = executeAction("/SuperW/pageCar.action");
        boolean rs = -1 != resultMessage.indexOf("\"resultInfo\":\"分页成功\"");
        assertTrue("返回服務信息錯誤失敗", rs);

        request.setParameter("permission", "{\"permissionName\":\"page\"}");
        request.setParameter("page", "{\"size\":\"1\", \"indexPageNum\":\"1\"}");
        resultMessage = executeAction("/SuperW/pageCar.action");
        rs = -1 != resultMessage.indexOf("\"resultInfo\":\"分页成功\"");
        assertTrue("返回服務信息錯誤失敗", rs);
    }
    
    /**
	     * testGetCarOrderDetails测试方法.
	     *
	     * @throws Exception
	     *             普通异常.
	     */
	    @Test
	    public final void testGetOrderDetailByCarId() throws Exception {
	    	//登录测试系统
	    	loginTestSystem();
	    	request.setParameter("carInfo", "{\"carInfoId\":\"1\"}");
	//        request.setParameter("currentUser", "{\"userId\":\"1\"}");
	    	String resultMessage = executeAction("/SuperW/getOrderDetailByCarId.action");
	    	boolean rs = -1 != resultMessage
	    			.indexOf("{\"userToken\":true,\"serviceResult\":true,\"resultInfo\":\"查询实体成功\"}");
	    	assertTrue("返回服務信息錯誤失敗", rs);
	    }

	/**
     * testGetAllOrderDetailByCarId测试方法.
     *
     * @throws Exception
     *             普通异常.
     */
    @Test
    public final void testGetAllOrderDetailByCarId() throws Exception {
    	//登录测试系统
    	loginTestSystem();
    	request.setParameter("carInfo", "{\"carInfoId\":\"1\"}");
//        request.setParameter("currentUser", "{\"userId\":\"1\"}");
    	String resultMessage = executeAction("/SuperW/getAllOrderDetailByCarId.action");
    	boolean rs = -1 != resultMessage
    			.indexOf("{\"userToken\":true,\"serviceResult\":true,\"resultInfo\":\"查询实体成功\",\"resultParm\":{\"entityList\":[]}}");
    	assertTrue("返回服務信息錯誤失敗", rs);
    }
}
