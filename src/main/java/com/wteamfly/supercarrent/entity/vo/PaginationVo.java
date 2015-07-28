/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.supercarrent.entity.vo;

import com.wteamfly.supercarrent.helper.ValidateType;

/**
 * 分页信息值对象类.
 * 
 * @author 侯骏雄
 * @since 3.0.0
 */
public class PaginationVo extends SuperValueObject {
    /**
     * 单页条目数.
     */
    @ValidateType("DIGIT")
    private String size;

    /**
     * 当前页码.
     */
    @ValidateType("DIGIT")
    private String indexPageNum;

    /**
     * 总条目数.
     */
    @ValidateType("DIGIT")
    private String totalCount;

    /**
     * @return 获取的size
     */
    public final String getSize() {
        return size;
    }

    /**
     * 设置size的方法.
     * 
     * @param size
     *            赋值给size的值
     */
    public final void setSize(final String size) {
        this.size = size;
    }

    /**
     * @return 获取的indexPageNum
     */
    public final String getIndexPageNum() {
        return indexPageNum;
    }

    /**
     * 设置indexPageNum的方法.
     * 
     * @param indexPageNum
     *            赋值给indexPageNum的值
     */
    public final void setIndexPageNum(final String indexPageNum) {
        this.indexPageNum = indexPageNum;
    }

    /**
     * @return 获取的totalCount
     */
    public final String getTotalCount() {
        return totalCount;
    }

    /**
     * 设置totalCount的方法.
     * 
     * @param totalCount
     *            赋值给totalCount的值
     */
    public final void setTotalCount(final String totalCount) {
        this.totalCount = totalCount;
    }
}
