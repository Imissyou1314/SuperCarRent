/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.supercarrent.entity.po;

import java.util.List;

/**
 * 分页信息类.
 * 
 * @author 侯骏雄
 * @since 3.0.0
 */
public class PaginationPo extends SuperPersistentObject {

    /**
     * 单页条目数.
     */
    private Integer size;

    /**
     * 当前页码.
     */
    private Integer indexPageNum;

    /**
     * 总条目数.
     */
    private Long totalCount;

    /**
     * 当前页条目.
     */
    private List<? extends SuperPersistentObject> list;

    /**
     * @return 获取的size
     */
    public final Integer getSize() {
        return size;
    }

    /**
     * 设置size的方法.
     * 
     * @param size
     *            赋值给size的值
     */
    public final void setSize(final Integer size) {
        this.size = size;
    }

    /**
     * @return 获取的indexPageNum
     */
    public final Integer getIndexPageNum() {
        return indexPageNum;
    }

    /**
     * 设置indexPageNum的方法.
     * 
     * @param indexPageNum
     *            赋值给indexPageNum的值
     */
    public final void setIndexPageNum(final Integer indexPageNum) {
        this.indexPageNum = indexPageNum;
    }

    /**
     * @return 获取的totalCount
     */
    public final Long getTotalCount() {
        return totalCount;
    }

    /**
     * 设置totalCount的方法.
     * 
     * @param totalCount
     *            赋值给totalCount的值
     */
    public final void setTotalCount(final Long totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * @return 获取的list
     */
    public final List<? extends SuperPersistentObject> getList() {
        return list;
    }

    /**
     * 设置list的方法.
     * 
     * @param list
     *            赋值给list的值
     */
    public final void setList(final List<? extends SuperPersistentObject> list) {
        this.list = list;
    }
}
