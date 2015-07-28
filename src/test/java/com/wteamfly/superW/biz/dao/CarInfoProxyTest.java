/**
 * Copyright (c) 2007-2015 WteamFly.  All rights reserved. 网飞网络公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.superW.biz.dao;

import java.util.Date;

import org.dbunit.dataset.DefaultDataSet;
import org.dbunit.dataset.DefaultTable;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wteamfly.superW.testhelper.TestDBHelper;
import com.wteamfly.supercarrent.biz.dao.SuperDaoProxy;
import com.wteamfly.supercarrent.entity.po.CarInfoPo;
import com.wteamfly.supercarrent.entity.po.UserPo;

/**
 * 车辆管理DaoProxy测试类.
 * 
 * @author 侯骏雄
 * @since 3.0.0
 */
public class CarInfoProxyTest {
    /**
     * 测试启动前方法.
     * 
     * @throws java.lang.Exception
     *             普通异常.
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        // 获取原数据库内容
        @SuppressWarnings("unused")
		IDataSet backupDataset = TestDBHelper.backupDataBase();
        // 构建测试用的数据
        // 指定测试用到的表

        // 写入数据

        // 组合表
        DefaultTable[] tables = {};
        // 创建测试数据库的引用以准备写入数据库中
        IDataSet tempDataSet = new DefaultDataSet(tables);
        // 写入数据库
        DatabaseOperation.CLEAN_INSERT.execute(TestDBHelper.getConn(),
                tempDataSet);
    }

    /**
     * 测试结束方法.
     * 
     * @throws java.lang.Exception
     *             普通异常.
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        TestDBHelper.restoreDataBase();
    }


    /**
     * hasPermissionByRoleId测试方法.
     * @throws Exception 
     * 
     */
    @Test
    public final void testAdd() throws Exception {
//    	CarInfoDaoProxy daoProxy = CarInfoDaoProxy.getInstance();
    	SuperDaoProxy daoProxy = SuperDaoProxy.getInstance();
        CarInfoPo cPo = new CarInfoPo();
        cPo.setCarBrand("极品飞车");
        cPo.setCarInfoId(1L);
        cPo.setCarState(1);
        cPo.setCarType("极品飞车");
        cPo.setCreateTime(new Date());
//        cPo.setCreatorId(1L);
        cPo.setIsDelete(false);
        cPo.setVersion(0L);
        UserPo currentUser = new UserPo();
        currentUser.setUserId(1L);
        daoProxy.addEntity(cPo, currentUser);
    }
}
