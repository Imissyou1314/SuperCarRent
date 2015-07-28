/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.supercarrent.entity.vo;

import javax.persistence.Version;

import com.wteamfly.supercarrent.helper.ValidateType;

/**
 * 车辆持久层类.
 * 
 * @author 宋文光
 * @since 3.0.0
 */
public class CarInfoVo extends SuperValueObject{

	/**
	 * 车编号.
	 */
	@ValidateType("DIGIT")
	private String carInfoId;

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
	@ValidateType("DIGIT")
	private String carPrice;

	/**
	 * 基本险.
	 */
	@ValidateType("DIGIT")
	private String carInsurance;

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
	@ValidateType("DIGIT")
	private String carAccriage;

	/**
	 * 车状态.
	 */
	@ValidateType("DIGIT")
	private String carState;

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
	 * @return 获取的carInfoId
	 */
	public final String getCarInfoId() {
		return carInfoId;
	}

	/**
	 * 设置carInfoId的方法.
	 * 
	 * @param carInfoId
	 *            赋值给carInfoId的值
	 */
	public final void setCarInfoId(final String carInfoId) {
		this.carInfoId = carInfoId;
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
	@Version
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

	/**
	 * @return 获取的carType
	 */

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

	public final String getCarState() {
		return carState;
	}

	/**
	 * 设置carState的方法.
	 * 
	 * @param carState
	 *            赋值给carState的值
	 */
	public final void setCarState(final String carState) {
		this.carState = carState;
	}

	/**
	 * @return 获取的carVersion
	 */

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

	public final String getCarPrice() {
		return carPrice;
	}

	/**
	 * 设置carPrice的方法.
	 * 
	 * @param carPrice
	 *            赋值给carPrice的值
	 */
	public final void setCarPrice(final String carPrice) {
		this.carPrice = carPrice;
	}

	/**
	 * @return 获取的carInsurance
	 */

	public final String getCarInsurance() {
		return carInsurance;
	}

	/**
	 * 设置carInsurance的方法.
	 * 
	 * @param carInsurance
	 *            赋值给carInsurance的值
	 */
	public final void setCarInsurance(final String carInsurance) {
		this.carInsurance = carInsurance;
	}

	/**
	 * @return 获取的carGear
	 */

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

	public final String getCarAccriage() {
		return carAccriage;
	}

	/**
	 * 设置carAccriage的方法.
	 * 
	 * @param carAccriage
	 *            赋值给carAccriage的值
	 */
	public final void setCarAccriage(final String carAccriage) {
		this.carAccriage = carAccriage;
	}

}
