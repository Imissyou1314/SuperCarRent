/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.supercarrent.menu.entity.po;

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

import com.wteamfly.supercarrent.entity.po.SuperPersistentObject;

/**
 * 角色菜单项映射持久层类.
 * 
 * @author 侯骏雄
 * @since 3.1.0
 */
@Entity
@Table(name = "t_rolemenuitemmap")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DynamicInsert
@DynamicUpdate
public class RoleMenuItemMapPo extends SuperPersistentObject {

    /**
     * 角色菜单项映射编号.
     */
    private Long roleMenuItemMapId;

    /**
     * 角色编号.
     */
    private Long roleId;

    /**
     * 菜单项编号.
     */
    private Long menuItemId;

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
     * 是否删除.
     */
    private Boolean isDelete;

    /**
     * 是否冻结.
     */
    private Boolean isLockUp;

    /**
     * 版本.
     */
    private Long version;

    /**
     * @return 获取的roleMenuItemMapId
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RoleMenuItemMapId")
    public final Long getRoleMenuItemMapId() {
        return roleMenuItemMapId;
    }

    /**
     * 设置roleMenuItemMapId的方法.
     * 
     * @param roleMenuItemMapId
     *            赋值给roleMenuItemMapId的值
     */
    public final void setRoleMenuItemMapId(final Long roleMenuItemMapId) {
        this.roleMenuItemMapId = roleMenuItemMapId;
    }

    /**
     * @return 获取的menuItemId
     */
    @Type(type = "long")
    @Column(name = "menuItemId")
    public final Long getMenuItemId() {
        return menuItemId;
    }

    /**
     * 设置menuItemId的方法.
     * 
     * @param menuItemId
     *            赋值给menuItemId的值
     */
    public final void setMenuItemId(final Long menuItemId) {
        this.menuItemId = menuItemId;
    }

    /**
     * @return 获取的roleId
     */
    @Type(type = "long")
    @Column(name = "RoleId")
    public final Long getRoleId() {
        return roleId;
    }

    /**
     * 设置roleId的方法.
     * 
     * @param roleId
     *            赋值给roleId的值
     */
    public final void setRoleId(final Long roleId) {
        this.roleId = roleId;
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
     * @return 获取的isLockUp
     */
    @Type(type = "boolean")
    @Column(name = "IsLockUp")
    public final Boolean getIsLockUp() {
        return isLockUp;
    }

    /**
     * 设置isLockUp的方法.
     * 
     * @param isLockUp
     *            赋值给isLockUp的值
     */
    public final void setIsLockUp(final Boolean isLockUp) {
        this.isLockUp = isLockUp;
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

}
