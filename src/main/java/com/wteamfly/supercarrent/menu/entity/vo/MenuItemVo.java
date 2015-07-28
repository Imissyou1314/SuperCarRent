/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.supercarrent.menu.entity.vo;

import com.wteamfly.supercarrent.entity.vo.SuperValueObject;
import com.wteamfly.supercarrent.helper.ValidateType;

/**
 * 菜单项数据层类.
 * 
 * @author 侯骏雄
 * @since 3.1.0
 */
public class MenuItemVo extends SuperValueObject {

    /**
     * 菜单项编号.
     */
    private String menuItemId;

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
    private String menuItemOrder;

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
    private String menuItemParentId;

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
     * 是否删除.
     */
    @ValidateType("LETTER")
    private String isDelete;

    /**
     * 是否冻结.
     */
    @ValidateType("LETTER")
    private String isLockUp;

    /**
     * 版本.
     */
    @ValidateType("DIGIT")
    private String version;

    /**
     * @return 获取的menuItemId
     */
    public final String getMenuItemId() {
        return menuItemId;
    }

    /**
     * 设置menuItemId的方法.
     * 
     * @param menuItemId
     *            赋值给menuItemId的值
     */
    public final void setMenuItemId(final String menuItemId) {
        this.menuItemId = menuItemId;
    }

    /**
     * @return 获取的menuName
     */
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
    public final String getMenuItemOrder() {
        return menuItemOrder;
    }

    /**
     * 设置menuItemOrder的方法.
     * 
     * @param menuItemOrder
     *            赋值给menuItemOrder的值
     */
    public final void setMenuItemOrder(final String menuItemOrder) {
        this.menuItemOrder = menuItemOrder;
    }

    /**
     * @return 获取的menuItemUrl
     */
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
    public final String getMenuItemParentId() {
        return menuItemParentId;
    }

    /**
     * 设置menuItemParentId的方法.
     * 
     * @param menuItemParentId
     *            赋值给menuItemParentId的值
     */
    public final void setMenuItemParentId(final String menuItemParentId) {
        this.menuItemParentId = menuItemParentId;
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
     * @return 获取的isLockUp
     */
    public final String getIsLockUp() {
        return isLockUp;
    }

    /**
     * 设置isLockUp的方法.
     * 
     * @param isLockUp
     *            赋值给isLockUp的值
     */
    public final void setIsLockUp(final String isLockUp) {
        this.isLockUp = isLockUp;
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
