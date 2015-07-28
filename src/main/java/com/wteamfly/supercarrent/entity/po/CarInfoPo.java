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
 * 车辆持久层类.
 * 
 * @author 宋文光
 * @since 3.0.0
 */
@Entity
@Table(name = "t_carinfo")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
// 添加下面两个有效率问题
@DynamicInsert
@DynamicUpdate
public class CarInfoPo extends SuperPersistentObject {

	/**
	 * 车编号.
	 */
	private Long carInfoId;

	/**
	 * 车类型.
	 */
	private String carType;

	/**
	 * 车品牌.
	 */
	private String carBrand;

	/**
	 * 车型号.
	 */
	private String carVersion;

	/**
	 * 日均价.
	 */
	private Long carPrice;

	/**
	 * 基本险.
	 */
	private Long carInsurance;

	/**
	 * 车档位.
	 */
	private String carGear;

	/**
	 * 车厢类型.
	 */
	private String carCarriage;

	/**
	 * 预授权.
	 */
	private Long carAccriage;

	/**
	 * 车状态.
	 */
	private Integer carState;

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
	 * @return 获取的carInfoId
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CarInfoId")
	public final Long getCarInfoId() {
		return carInfoId;
	}

	/**
	 * 设置carInfoId的方法.
	 * 
	 * @param carInfoId
	 *            赋值给carInfoId的值
	 */
	public final void setCarInfoId(final Long carInfoId) {
		this.carInfoId = carInfoId;
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
	 * @return 获取的carType
	 */
	@Type(type = "string")
	@Column(name = "CarType")
	public final String getCarType() {
		return carType;
	}

	/**
	 * 设置carType的方法.
	 * 
	 * @param carType
	 *            赋值给carType的值
	 */
	public final void setCarType(final String carType) {
		this.carType = carType;
	}

	/**
	 * @return 获取的carBrand
	 */
	@Type(type = "string")
	@Column(name = "CarBrand")
	public final String getCarBrand() {
		return carBrand;
	}

	/**
	 * 设置carBrand的方法.
	 * 
	 * @param carbrand
	 *            赋值给carbrand的值
	 */
	public final void setCarBrand(final String carBrand) {
		this.carBrand = carBrand;
	}

	/**
	 * @return 获取的carState
	 */
	@Type(type = "int")
	@Column(name = "CarState")
	public final Integer getCarState() {
		return carState;
	}

	/**
	 * 设置carState的方法.
	 * 
	 * @param carState
	 *            赋值给carState的值
	 */
	public final void setCarState(final Integer carState) {
		this.carState = carState;
	}

	/**
	 * @return 获取的carVersion
	 */
	@Type(type = "string")
	@Column(name = "CarVersion")
	public final String getCarVersion() {
		return carVersion;
	}

	/**
	 * 设置carVersion的方法.
	 * 
	 * @param carVersion
	 *            赋值给carVersion的值
	 */
	public final void setCarVersion(final String carVersion) {
		this.carVersion = carVersion;
	}

	/**
	 * @return 获取的carPrice
	 */
	@Type(type = "long")
	@Column(name = "CarPrice")
	public final Long getCarPrice() {
		return carPrice;
	}

	/**
	 * 设置carPrice的方法.
	 * 
	 * @param carPrice
	 *            赋值给carPrice的值
	 */
	public final void setCarPrice(final Long carPrice) {
		this.carPrice = carPrice;
	}

	/**
	 * @return 获取的carInsurance
	 */
	@Type(type = "long")
	@Column(name = "CarInsurance")
	public final Long getCarInsurance() {
		return carInsurance;
	}

	/**
	 * 设置carInsurance的方法.
	 * 
	 * @param carInsurance
	 *            赋值给carInsurance的值
	 */
	public final void setCarInsurance(final Long carInsurance) {
		this.carInsurance = carInsurance;
	}

	/**
	 * @return 获取的carGear
	 */
	@Type(type = "string")
	@Column(name = "CarGear")
	public final String getCarGear() {
		return carGear;
	}

	/**
	 * 设置carGear的方法.
	 * 
	 * @param carGear
	 *            赋值给carGear的值
	 */
	public final void setCarGear(final String carGear) {
		this.carGear = carGear;
	}

	/**
	 * @return 获取的carCarriage
	 */
	@Type(type = "string")
	@Column(name = "CarCarriage")
	public final String getCarCarriage() {
		return carCarriage;
	}

	/**
	 * 设置carCarriage的方法.
	 * 
	 * @param carCarriage
	 *            赋值给carCarriage的值
	 */
	public final void setCarCarriage(final String carCarriage) {
		this.carCarriage = carCarriage;
	}

	/**
	 * @return 获取的carAccriage
	 */
	@Type(type = "long")
	@Column(name = "CarAccriage")
	public final Long getCarAccriage() {
		return carAccriage;
	}

	/**
	 * 设置carAccriage的方法.
	 * 
	 * @param carAccriage
	 *            赋值给carAccriage的值
	 */
	public final void setCarAccriage(final Long carAccriage) {
		this.carAccriage = carAccriage;
	}

}
