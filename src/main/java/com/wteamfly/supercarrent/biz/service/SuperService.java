/**
 * Copyright (c) 2007-2015 WteamFly.  All rights reserved. 网飞网络公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.supercarrent.biz.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wteamfly.supercarrent.biz.dao.SuperDaoProxy;
import com.wteamfly.supercarrent.entity.po.PaginationPo;
import com.wteamfly.supercarrent.entity.po.SuperPersistentObject;
import com.wteamfly.supercarrent.entity.po.UserPo;

/**
 * 抽象Service类.
 * 
 * @author 侯骏雄
 * @since 3.0.0
 */
public final class SuperService {
    /**
     * log4j实例对象.
     */
    private static Logger logger = LogManager.getLogger(SuperService.class);

    /**
     * 单例对象.
     */
    private static SuperService instance = new SuperService();

    /**
     * 单例模式的私有构造方法.
     */
    private SuperService() {
    }

    /**
     * 获取单例.
     * 
     * @return 单例
     */
    public static SuperService getInstance() {
        return instance;
    }

    /**
     * 添加实体.
     * 
     * @param entityPo
     *            实体.
     * @param currentUser
     *            当前操作用户，数据库记录该用户进行添加.
     * @return ResultMessage 服务信息.
     */
    public ResultMessage addEntity(final SuperPersistentObject entityPo,
            final UserPo currentUser) {
        logger.debug("进入addEntity方法");
        ResultMessage rs = new ResultMessage();
        try {
            SuperDaoProxy daoProxy = SuperDaoProxy.getInstance();
            daoProxy.addEntity(entityPo, currentUser);
            rs.setResultInfo("添加成功");
            rs.setServiceResult(true);
        } catch (Exception e) {
            e.printStackTrace();
            rs.setResultInfo("添加失败");
            rs.setServiceResult(false);
        }
        logger.debug("退出addEntity方法");
        return rs;
    }

    /**
     * 删除实体.
     * 
     * @param entityPo
     *            实体.
     * @return ResultMessage 服务信息.
     */
    public ResultMessage deleteEntity(final SuperPersistentObject entityPo) {
        logger.debug("进入deleteEntity方法");
        ResultMessage rs = new ResultMessage();
        try {
            SuperDaoProxy daoProxy = SuperDaoProxy.getInstance();
            daoProxy.deleteEntity(entityPo);
            rs.setResultInfo("成功删除");
            rs.setServiceResult(true);
        } catch (Exception e) {
            e.printStackTrace();
            rs.setResultInfo("删除失败");
            rs.setServiceResult(false);
        }
        logger.debug("退出deleteEntity方法");
        return rs;
    }

//    public SuperPersistentObject getEntityById(SuperPersistentObject entityPo){
//    	logger.debug("进入getEntityById方法");
//        ResultMessage rs = new ResultMessage();
//        try {
//            SuperDaoProxy daoProxy = SuperDaoProxy.getInstance();
//            SuperPersistentObject object = daoProxy.getEntityById(entityPo);
//            rs.setResultInfo("成功获取对象");
//            HashMap<String, Object> resultParm = new HashMap<String,Object>();
//            resultParm.put(entityPo.getClass().getName(), object);
//            rs.setResultParm(resultParm);
//            rs.setServiceResult(true);
//        } catch (Exception e) {
//            e.printStackTrace();
//            rs.setResultInfo("获取对象失败");
//            rs.setServiceResult(false);
//        }
//        logger.debug("退出getEntityById方法");
//        return rs;
//    } 
    
    
    
    /**
     * 逻辑删除实体.
     * 
     * @param entityPo
     *            实体.
     * @param currentUser
     *            当前操作用户，数据库记录该用户进行逻辑删除.
     * @return ResultMessage 服务信息.
     */
    public ResultMessage logicDeleteEntity(
            final SuperPersistentObject entityPo, final UserPo currentUser) {
        logger.debug("进入logicDeleteEntity方法");
        ResultMessage rs = new ResultMessage();
        try {
            SuperDaoProxy daoProxy = SuperDaoProxy.getInstance();
            daoProxy.logicDeleteEntity(entityPo, currentUser);
            rs.setResultInfo("成功逻辑删除");
            rs.setServiceResult(true);
        } catch (Exception e) {
            e.printStackTrace();
            rs.setResultInfo("逻辑删除失败");
            rs.setServiceResult(false);
        }
        logger.debug("退出logicDeleteEntity方法");
        return rs;
    }

    /**
     * 冻结实体.
     * 
     * @param entityPo
     *            实体.
     * @param currentUser
     *            当前操作用户，数据库记录该用户进行冻结.
     * @return ResultMessage 服务信息.
     */
    public ResultMessage lockUpEntity(final SuperPersistentObject entityPo,
            final UserPo currentUser) {
        logger.debug("进入lockUpEntity方法");
        ResultMessage rs = new ResultMessage();
        try {
            SuperDaoProxy daoProxy = SuperDaoProxy.getInstance();
            daoProxy.lockUpEntity(entityPo, currentUser);
            rs.setResultInfo("成功冻结");
            rs.setServiceResult(true);
        } catch (Exception e) {
            e.printStackTrace();
            rs.setResultInfo("冻结失败");
            rs.setServiceResult(false);
        }
        logger.debug("退出lockUpEntity方法");
        return rs;
    }

    /**
     * 编辑实体.
     * 
     * @param entityPo
     *            实体.
     * @param currentUser
     *            当前操作用户，数据库记录该用户进行添加.
     * @return ResultMessage 服务信息.
     */
    public ResultMessage editEntity(final SuperPersistentObject entityPo,
            final UserPo currentUser) {
        logger.debug("进入editEntity方法");
        ResultMessage rs = new ResultMessage();
        try {
            SuperDaoProxy daoProxy = SuperDaoProxy.getInstance();
            daoProxy.editEntity(entityPo, currentUser);
            rs.setResultInfo("修改成功");
            rs.setServiceResult(true);
        } catch (Exception e) {
            e.printStackTrace();
            rs.setResultInfo("修改失败");
            rs.setServiceResult(false);
        }
        logger.debug("退出editEntity方法");
        return rs;
    }

    /**
     * 查询分页列表.
     * 
     * @param pageInfo
     *            分页信息.
     * @param entityPo
     *            分页实体查询信息.
     * @return ResultMessage 服务信息.
     */
    public ResultMessage pageEntity(final PaginationPo pageInfo,
            final SuperPersistentObject entityPo) {
        ResultMessage rs = new ResultMessage();
        SuperDaoProxy daoProxy = SuperDaoProxy.getInstance();
        PaginationPo pageResult = daoProxy.pageEntity(pageInfo, entityPo);
        
        Map<String, Object> parm = new HashMap<String, Object>();
        parm.put("pageList", pageResult.getList());
        parm.put("totalCount", pageResult.getTotalCount());
        rs.setResultParm(parm);
        rs.setResultInfo("分页成功");
        rs.setServiceResult(true);
        return rs;
    }
    
    /**
     * 查询实体列表.
     * 
     * @param entityPo
     *            分页实体查询信息.
     * @return ResultMessage 服务信息.
     */
    public ResultMessage queryList(
            final SuperPersistentObject entityPo) {
        ResultMessage rs = new ResultMessage();
        SuperDaoProxy daoProxy = SuperDaoProxy.getInstance();
        List<? extends SuperPersistentObject> entityList = daoProxy.queryList(entityPo);
        
        Map<String, Object> parm = new HashMap<String, Object>();
        parm.put("entityList", entityList);
        rs.setResultParm(parm);
        rs.setResultInfo("查询实体成功");
        rs.setServiceResult(true);
        return rs;
    }
}
