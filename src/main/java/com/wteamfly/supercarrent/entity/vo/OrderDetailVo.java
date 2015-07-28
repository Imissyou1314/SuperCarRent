/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.supercarrent.entity.vo;

import javax.persistence.Version;

import com.wteamfly.supercarrent.helper.ValidateType;

/**
 * 订单明细持久层类.
 * 
 * @author 宋文光
 * @since 3.0.0
 */
// 添加下面两个有效率问题
public final class OrderDetailVo extends SuperValueObject {

	/**
	 * 订单明细编号.
	 */
	@ValidateType("DIGIT")
	private String orderDetailId;

	/**
	 * 外键id，有可能是车辆类型的外键，有可能是订单类型的外键.
	 */
	// TODO 为完善，为向数据库添加该字段
	@ValidateType("DIGIT")
	private String foreignId;

	/**
	 * 外键类型，有可能是车辆类型的外键，有可能是订单类型的外键.
	 */
	private String foreignIdType;
	/**
	 * 该订单详情是否必须.
	 */
	@ValidateType("LETTER")
	private String necessary;

	/**
	 * 服务类型编号.
	 */
	@ValidateType("DIGIT")
	private String serverId;

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
	@ValidateType("DIGIT")
	private String servercost;

	/**
	 * 服务数量.
	 */
	@ValidateType("DIGIT")
	private String servernum;

	/**
	 * 服务类型.
	 */
	private String serverType;

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
	@ValidateType("DIGIT")
	private String version;

	/**
	 * @return 获取的permissionId.
	 */
	
	public final String getOrderDetailId() {
		return orderDetailId;
	}

	/**
	 * 设置permissionId的方法.
	 * 
	 * @param permissionId
	 *            赋值给permissionId的值.
	 */
	public final void setOrderDetailId(final String orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	/**
	 * @return 获取的createTime.
	 */

	public final String getCreateTime() {
		return createTime;
	}

	/**
	 * 设置createTime的方法.
	 * 
	 * @param createTime
	 *            赋值给createTime的值.
	 */
	public final void setCreateTime(final String createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return 获取的creatorId.
	 */

	public final String getCreatorId() {
		return creatorId;
	}

	/**
	 * 设置creatorId的方法.
	 * 
	 * @param creatorId
	 *            赋值给creatorId的值.
	 */
	public final void setCreatorId(final String creatorId) {
		this.creatorId = creatorId;
	}

	/**
	 * @return 获取的editeTime.
	 */

	public final String getEditeTime() {
		return editeTime;
	}

	/**
	 * 设置editeTime的方法.
	 * 
	 * @param editeTime
	 *            赋值给editeTime的值.
	 */
	public final void setEditeTime(final String editeTime) {
		this.editeTime = editeTime;
	}

	/**
	 * @return 获取的editorId.
	 */

	public final String getEditorId() {
		return editorId;
	}

	/**
	 * 设置editorId的方法.
	 * 
	 * @param editorId
	 *            赋值给editorId的值.
	 */
	public final void setEditorId(final String editorId) {
		this.editorId = editorId;
	}

	/**
	 * @return 获取的isDelete.
	 */

	public final String getIsDelete() {
		return isDelete;
	}

	/**
	 * 设置isDelete的方法.
	 * 
	 * @param isDelete
	 *            赋值给isDelete的值.
	 */
	public final void setIsDelete(final String isDelete) {
		this.isDelete = isDelete;
	}

	/**
	 * @return 获取的version.
	 */
	@Version
	public final String getVersion() {
		return version;
	}

	/**
	 * 设置version的方法.
	 * 
	 * @param version
	 *            赋值给version的值.
	 */
	public final void setVersion(final String version) {
		this.version = version;
	}

	/**
	 * @return 获取的serverId.
	 */

	public final String getServerId() {
		return serverId;
	}

	/**
	 * 设置serverId的方法.
	 * 
	 * @param serverId
	 *            赋值给serverId的值.
	 */
	public final void setServerId(final String serverId) {
		this.serverId = serverId;
	}

	/**
	 * @return 获取的serverName.
	 */

	public final String getServerName() {
		return serverName;
	}

	/**
	 * 设置serverName的方法.
	 * 
	 * @param permissionName
	 *            赋值给permissionName的值.
	 */
	public final void setServerName(final String serverName) {
		this.serverName = serverName;
	}

	/**
	 * @return 获取的serverdescribe.
	 */

	public final String getServerdescribe() {
		return serverdescribe;
	}

	/**
	 * 设置serverdescribe的方法.
	 * 
	 * @param serverdescribe
	 *            赋值给serverdescribe的值.
	 */
	public final void setServerdescribe(final String serverdescribe) {
		this.serverdescribe = serverdescribe;
	}

	/**
	 * @return 获取的servercost.
	 */

	public final String getServercost() {
		return servercost;
	}

	/**
	 * 设置servercost的方法.
	 * 
	 * @param servercost
	 *            赋值给servercost的值.
	 */
	public final void setServercost(final String servercost) {
		this.servercost = servercost;
	}

	/**
	 * @return 获取的servernum.
	 */

	public final String getServernum() {
		return servernum;
	}

	/**
	 * 设置servernum的方法.
	 * 
	 * @param servercost
	 *            赋值给servernum的值.
	 */
	public final void setServernum(final String servernum) {
		this.servernum = servernum;
	}

	/**
	 * @return 获取的necessary.
	 */

	public final String isNecessary() {
		return necessary;
	}

	/**
	 * 设置necessary的方法.
	 * 
	 * @param necessary
	 *            赋值给necessary的值.
	 */
	public final void setNecessary(final String necessary) {
		this.necessary = necessary;
	}

	/**
	 * @return 获取的foreignId.
	 */

	public final String getForeignId() {
		return foreignId;
	}

	/**
	 * 设置foreignId的方法.
	 * 
	 * @param foreignId
	 *            赋值给foreignId的值.
	 */
	public final void setForeignId(final String foreignId) {
		this.foreignId = foreignId;
	}

	/**
	 * @return 获取的foreignIdType.
	 */

	public final String getForeignIdType() {
		return foreignIdType;
	}

	/**
	 * 设置foreignIdType的方法.
	 * 
	 * @param foreignIdType
	 *            赋值给foreignIdType的值.
	 */
	public final void setForeignIdType(final String foreignIdType) {
		this.foreignIdType = foreignIdType;
	}

	/**
	 * @return 获取的serverType.
	 */

	public final String getServerType() {
		return serverType;
	}

	/**
	 * 设置serverType的方法.
	 * 
	 * @param serverType
	 *            赋值给serverType的值.
	 */
	public final void setServerType(final String serverType) {
		this.serverType = serverType;
	}

}
