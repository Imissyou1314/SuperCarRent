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
 * 菜单项持久层类.
 * 
 * @author 侯骏雄
 * @since 3.1.0
 */
@Entity
@Table(name = "t_menuitem")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DynamicInsert
@DynamicUpdate
public class MenuItemPo extends SuperPersistentObject {

    /**
     * 菜单项编号.
     */
    private Long menuItemId;

    /**
     * 菜单项所属菜单名.
     */
    private String menuName;

    /**
     * 菜单项标题.
     */
    private String menuItemName;

    /**
     * 菜单项图标.
     */
    private String menuItemIcon;

    /**
     * 菜单项排序.
     */
    private Long menuItemOrder;

    /**
     * 菜单项地址.
     */
    private String menuItemUrl;

    /**
     * 菜单项路由地址.
     */
    private String menuItemRouteUrl;

    /**
     * 菜单项控制器.
     */
    private String menuItemCtrl;

    /**
     * 菜单项父菜单项编号.
     */
    private Long menuItemParentId;

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
     * @return 获取的menuItemId
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MenuItemId")
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
     * @return 获取的menuName
     */
    @Type(type = "string")
    @Column(name = "MenuName")
    public final String getMenuName() {
        return menuName;
    }

    /**
     * 设置menuName的方法.
     * 
     * @param menuName
     *            赋值给menuName的值
     */
    public final void setMenuName(final String menuName) {
        this.menuName = menuName;
    }

    /**
     * @return 获取的menuItemName
     */
    @Type(type = "string")
    @Column(name = "MenuItemName")
    public final String getMenuItemName() {
        return menuItemName;
    }

    /**
     * 设置menuItemName的方法.
     * 
     * @param menuItemName
     *            赋值给menuItemName的值
     */
    public final void setMenuItemName(final String menuItemName) {
        this.menuItemName = menuItemName;
    }

    /**
     * @return 获取的menuItemIcon
     */
    @Type(type = "string")
    @Column(name = "MenuItemIcon")
    public final String getMenuItemIcon() {
        return menuItemIcon;
    }

    /**
     * 设置menuItemIcon的方法.
     * 
     * @param menuItemIcon
     *            赋值给menuItemIcon的值
     */
    public final void setMenuItemIcon(final String menuItemIcon) {
        this.menuItemIcon = menuItemIcon;
    }

    /**
     * @return 获取的menuItemOrder
     */
    @Type(type = "long")
    @Column(name = "MenuItemOrder")
    public final Long getMenuItemOrder() {
        return menuItemOrder;
    }

    /**
     * 设置menuItemOrder的方法.
     * 
     * @param menuItemOrder
     *            赋值给menuItemOrder的值
     */
    public final void setMenuItemOrder(final Long menuItemOrder) {
        this.menuItemOrder = menuItemOrder;
    }

    /**
     * @return 获取的menuItemUrl
     */
    @Type(type = "string")
    @Column(name = "MenuItemUrl")
    public final String getMenuItemUrl() {
        return menuItemUrl;
    }

    /**
     * 设置menuItemUrl的方法.
     * 
     * @param menuItemUrl
     *            赋值给menuItemUrl的值
     */
    public final void setMenuItemUrl(final String menuItemUrl) {
        this.menuItemUrl = menuItemUrl;
    }

    /**
     * @return 获取的menuItemRouteUrl
     */
    @Type(type = "string")
    @Column(name = "MenuItemRouteUrl")
    public final String getMenuItemRouteUrl() {
        return menuItemRouteUrl;
    }

    /**
     * 设置menuItemRouteUrl的方法.
     * 
     * @param menuItemRouteUrl
     *            赋值给menuItemRouteUrl的值
     */
    public final void setMenuItemRouteUrl(final String menuItemRouteUrl) {
        this.menuItemRouteUrl = menuItemRouteUrl;
    }

    /**
     * @return 获取的menuItemCtrl
     */
    @Type(type = "string")
    @Column(name = "MenuItemCtrl")
    public final String getMenuItemCtrl() {
        return menuItemCtrl;
    }

    /**
     * 设置menuItemCtrl的方法.
     * 
     * @param menuItemCtrl
     *            赋值给menuItemCtrl的值
     */
    public final void setMenuItemCtrl(final String menuItemCtrl) {
        this.menuItemCtrl = menuItemCtrl;
    }

    /**
     * @return 获取的menuItemParentId
     */
    @Type(type = "long")
    @Column(name = "MenuItemParentId")
    public final Long getMenuItemParentId() {
        return menuItemParentId;
    }

    /**
     * 设置menuItemParentId的方法.
     * 
     * @param menuItemParentId
     *            赋值给menuItemParentId的值
     */
    public final void setMenuItemParentId(final Long menuItemParentId) {
        this.menuItemParentId = menuItemParentId;
    }

    /**
     * @return 获取的createTime
     */
    @Temporal(value = TemporalType.TIMESTAMP )
    @Column(length=255,name = "CreateTime")
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
    @Column(length=255,name = "EditeTime")
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
