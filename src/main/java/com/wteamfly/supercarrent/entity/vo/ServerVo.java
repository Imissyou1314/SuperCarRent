/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.supercarrent.entity.vo;

import com.wteamfly.supercarrent.helper.ValidateType;

/**
 * 服务数据值对象类.
 * 
 * @author 宋文光
 * @since 1.0.0
 */
public class ServerVo extends SuperValueObject {

    /**
     * 服务类型编号.
     */
    @ValidateType("DIGIT")
    private String serverId;

    /**
     * 服务名称.
     */
    @ValidateType("LETTER")
    private String serverName;
    
    /**
     * 服务描述.
     */
    @ValidateType("LETTER")
    private String serverdescribe;
    
    /**
     * 服务费用.
     */
    @ValidateType("DIGIT")
    private String servercost;
    

    /**
     * 创建时间.
     */
    @ValidateType("DATETIME")
    private String createTime;

    /**
     * 创建者编号.
     */
    @ValidateType("DIGIT")
    private String creatorId;

    /**
     * 修改时间.
     */
    @ValidateType("DATETIME")
    private String editeTime;

    /**
     * 修改者编号.
     */
    @ValidateType("DIGIT")
    private String editorId;

    /**
     * 是否删除,软删除.
     */
    @ValidateType("LETTER")
    private String isDelete;

    /**
     * 版本.
     */
    @ValidateType("LETTER")
    private String version;

    /**
     * @return 获取的permissionId
     */
    public final String getServerId() {
        return serverId;
    }

    /**
     * 设置permissionId的方法.
     * 
     * @param permissionId
     *            赋值给permissionId的值
     */
    public final void setServerId(final String serverId) {
        this.serverId = serverId;
    }

    /**
     * @return 获取的permissionName
     */
    public final String getServerName() {
        return serverName;
    }

    /**
     * 设置permissionName的方法.
     * 
     * @param permissionName
     *            赋值给permissionName的值
     */
    public final void setServerName(final String serverName) {
        this.serverName = serverName;
    }

	public String getServerdescribe() {
		return serverdescribe;
	}

    /**
     * 设置serverdescribe的方法.
     * 
     * @param serverdescribe
     *            赋值给serverdescribe的值
     */
	public void setServerdescribe(String serverdescribe) {
		this.serverdescribe = serverdescribe;
	}

	public String getServercost() {
		return servercost;
	}

	 /**
	     * 设置servercost的方法.
	     * 
	     * @param servercost
	     *            赋值给servercost的值
	     */
	public void setServercost(String servercost) {
		this.servercost = servercost;
	}
    
    /**
     * @return 获取的createTime
     */
    public final String getCreateTime() {
        return createTime;
    }

    /**
     * 设置createTime的方法.
     * 
     * @param createTime
     *            赋值给createTime的值
     */
    public final void setCreateTime(final String createTime) {
        this.createTime = createTime;
    }

    /**
     * @return 获取的creatorId
     */
    public final String getCreatorId() {
        return creatorId;
    }

    /**
     * 设置creatorId的方法.
     * 
     * @param creatorId
     *            赋值给creatorId的值
     */
    public final void setCreatorId(final String creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * @return 获取的editeTime
     */
    public final String getEditeTime() {
        return editeTime;
    }

    /**
     * 设置editeTime的方法.
     * 
     * @param editeTime
     *            赋值给editeTime的值
     */
    public final void setEditeTime(final String editeTime) {
        this.editeTime = editeTime;
    }

    /**
     * @return 获取的editorId
     */
    public final String getEditorId() {
        return editorId;
    }

    /**
     * 设置editorId的方法.
     * 
     * @param editorId
     *            赋值给editorId的值
     */
    public final void setEditorId(final String editorId) {
        this.editorId = editorId;
    }

    /**
     * @return 获取的isDelete
     */
    public final String getIsDelete() {
        return isDelete;
    }

    /**
     * 设置isDelete的方法.
     * 
     * @param isDelete
     *            赋值给isDelete的值
     */
    public final void setIsDelete(final String isDelete) {
        this.isDelete = isDelete;
    }


    /**
     * @return 获取的version
     */
    public final String getVersion() {
        return version;
    }

    /**
     * 设置version的方法.
     * 
     * @param version
     *            赋值给version的值
     */
    public final void setVersion(final String version) {
        this.version = version;
    }

  

}
