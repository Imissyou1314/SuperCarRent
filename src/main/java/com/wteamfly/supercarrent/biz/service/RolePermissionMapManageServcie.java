/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.supercarrent.biz.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wteamfly.supercarrent.biz.dao.RolePermissionMapDaoproxy;
import com.wteamfly.supercarrent.biz.dao.SuperDaoProxy;
import com.wteamfly.supercarrent.entity.po.PaginationPo;
import com.wteamfly.supercarrent.entity.po.PermissionPo;
import com.wteamfly.supercarrent.entity.po.RolePermissionMapPo;
import com.wteamfly.supercarrent.entity.vo.PermissionVo;

/**
 * 角色行为映射管理Servcie类.
 * 
 * @author 殷梓淞
 * @since 3.0.0
 */
public final class RolePermissionMapManageServcie {
    /**
     * log4j实例对象.
     */
    private static Logger logger = LogManager
            .getLogger(RolePermissionMapManageServcie.class);

    /**
     * 单例对象.
     */
    private static RolePermissionMapManageServcie instance = new RolePermissionMapManageServcie();

    /**
     * 单例模式的私有构造方法.
     */
    private RolePermissionMapManageServcie() {
    }

    /**
     * 获取单例.
     * 
     * @return 单例
     */
    public static RolePermissionMapManageServcie getInstance() {
        return instance;
    }

    /**
     * 查询角色未拥有行为列表.
     * 
     * @param paginationPo
     *            分页实体.
     * @param rolePermissionMapPo
     *            角色行为关系.
     * @return 结果集.
     */
    public ResultMessage pageNotHasRolePermission(final PaginationPo paginationPo,
            final RolePermissionMapPo rolePermissionMapPo) {
        ResultMessage rs = new ResultMessage();
        try {
            RolePermissionMapDaoproxy daoproxy = RolePermissionMapDaoproxy
                    .getInstance();
            PaginationPo pageResult = daoproxy.pageNotHasRolePermission(
                    paginationPo, rolePermissionMapPo);

            @SuppressWarnings("unchecked")
            List<PermissionPo> listPos = (List<PermissionPo>) pageResult
                    .getList();
            List<PermissionVo> listVos = new ArrayList<PermissionVo>();
            PermissionVo vo = null;
            for (PermissionPo permissionPo : listPos) {
                vo = new PermissionVo();
                vo.poToVo(permissionPo);
                listVos.add(vo);
            }

            Map<String, Object> parm = new HashMap<String, Object>();
            parm.put("pageList", listVos);
            parm.put("totalCount", pageResult.getTotalCount());

            rs.setResultParm(parm);
            rs.setResultInfo("分页成功");
            rs.setServiceResult(true);
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 查询角色拥有行为列表.
     * 
     * @param paginationPo
     *            分页实体.
     * @param rolePermissionMapPo
     *            角色行为关系.
     * @return 结果集.
     */
    public ResultMessage pageRolePermission(final PaginationPo paginationPo,
            final RolePermissionMapPo rolePermissionMapPo) {
        ResultMessage rs = new ResultMessage();
        try {
            RolePermissionMapDaoproxy daoproxy = RolePermissionMapDaoproxy
                    .getInstance();
            PaginationPo pageResult = daoproxy.pageRolePermission(
                    paginationPo, rolePermissionMapPo);

            @SuppressWarnings("unchecked")
            List<PermissionPo> listPos = (List<PermissionPo>) pageResult
                    .getList();
            List<PermissionVo> listVos = new ArrayList<PermissionVo>();
            PermissionVo vo = null;
            for (PermissionPo permissionPo : listPos) {
                vo = new PermissionVo();
                vo.poToVo(permissionPo);
                listVos.add(vo);
            }

            //查询行为关系表
            SuperDaoProxy daoProxy2 = SuperDaoProxy.getInstance();
            PaginationPo pageResult2 = daoProxy2.pageEntity(paginationPo, rolePermissionMapPo);
            
            Map<String, Object> parm = new HashMap<String, Object>();
            parm.put("pageList", listVos);
            parm.put("pageMapList", pageResult2.getList());
            parm.put("totalCount", pageResult.getTotalCount());

            rs.setResultParm(parm);
            rs.setResultInfo("分页成功");
            rs.setServiceResult(true);
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }
        return rs;
    }

}
