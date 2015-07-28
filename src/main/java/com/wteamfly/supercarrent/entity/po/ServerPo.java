/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.supercarrent.entity.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

/**
 * 服务持久层类.
 * 
 * @author 宋文光
 * @since 3.0.0
 */
@Entity
@Table(name = "t_server")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//添加下面两个有效率问题
@DynamicInsert
@DynamicUpdate
public class ServerPo extends SuperPersistentObject {

    /**
     * 服务类型编号.
     */
    private Long serverId;

    /**
     * 服务名称.
     */
    private String serverName;
    
    /**
     * 服务描述.
     */
    private String serverdescribe;
    
    /**
     * 服务费用.
     */
    private Integer servercost;
    
	/**
	 * 服务类型.
	 */
	private String serverType;
    

    /**
     * 创建时间.
     */
    private Date createTime;

    /**
     * 创建者编号.
     */
    private Long creatorId;

    /**
     * 修改时间.
     */
    private Date editeTime;

    /**
     * 修改者编号.
     */
    private Long editorId;

    /**
     * 是否删除,软删除.
     */
    private Boolean isDelete;

    /**
     * 版本.
     */
    private Long version;

    /**
     * @return 获取的permissionId
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ServerId")
    public final Long getServerId() {
        return serverId;
    }

    /**
     * 设置permissionId的方法.
     * 
     * @param permissionId
     *            赋值给permissionId的值
     */
    public final void setServerId(final Long serverId) {
        this.serverId = serverId;
    }

    /**
     * @return 获取的permissionName
     */
    @Type(type = "string")
    @Column(name = "ServerName")
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

    @Type(type = "string")
    @Column(name = "ServerDescribe")
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

	 @Type(type = "int")
	    @Column(name = "ServerCost")
	public Integer getServercost() {
		return servercost;
	}

	 /**
	     * 设置servercost的方法.
	     * 
	     * @param servercost
	     *            赋值给servercost的值
	     */
	public void setServercost(Integer servercost) {
		this.servercost = servercost;
	}
    
    /**
     * @return 获取的createTime
     */
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "CreateTime")
    public final Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置createTime的方法.
     * 
     * @param createTime
     *            赋值给createTime的值
     */
    public final void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return 获取的creatorId
     */
    @Type(type = "long")
    @Column(name = "CreatorId")
    public final Long getCreatorId() {
        return creatorId;
    }

    /**
     * 设置creatorId的方法.
     * 
     * @param creatorId
     *            赋值给creatorId的值
     */
    public final void setCreatorId(final Long creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * @return 获取的editeTime
     */
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "EditeTime")
    public final Date getEditeTime() {
        return editeTime;
    }

    /**
     * 设置editeTime的方法.
     * 
     * @param editeTime
     *            赋值给editeTime的值
     */
    public final void setEditeTime(final Date editeTime) {
        this.editeTime = editeTime;
    }

    /**
     * @return 获取的editorId
     */
    @Type(type = "long")
    @Column(name = "EditorId")
    public final Long getEditorId() {
        return editorId;
    }

    /**
     * 设置editorId的方法.
     * 
     * @param editorId
     *            赋值给editorId的值
     */
    public final void setEditorId(final Long editorId) {
        this.editorId = editorId;
    }

    /**
     * @return 获取的isDelete
     */
    @Type(type = "boolean")
    @Column(name = "IsDelete")
    public final Boolean getIsDelete() {
        return isDelete;
    }

    /**
     * 设置isDelete的方法.
     * 
     * @param isDelete
     *            赋值给isDelete的值
     */
    public final void setIsDelete(final Boolean isDelete) {
        this.isDelete = isDelete;
    }


    /**
     * @return 获取的version
     */
    @Version
    @Type(type = "long")
    @Column(name = "Version")
    public final Long getVersion() {
        return version;
    }

    /**
     * 设置version的方法.
     * 
     * @param version
     *            赋值给version的值
     */
    public final void setVersion(final Long version) {
        this.version = version;
    }

	/**
	 * @return 获取的serverType
	 */
	@Type(type = "string")
	@Column(name = "serverType")
	public String getServerType() {
		return serverType;
	}

	/**
	 * 设置serverType的方法.
	 * 
	 * @param serverType
	 *            赋值给serverType的值
	 */
	public void setServerType(String serverType) {
		this.serverType = serverType;
	}
	

}
