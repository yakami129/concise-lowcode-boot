package com.github.yakami.library.infrastructure.common.dto;

import java.io.Serializable;

/**
 * @author kaka
 * @Date 2020-09-21
 */
public class PageReqDTO implements Serializable {

    private static final long serialVersionUID = -8416772694741488451L;

    /**
     * 当前页码
     */
    private int pageNum = 1;
    /**
     * 每页数量
     */
    private int pageSize = 10;



    public int getPageNum() {
        return pageNum == 0 ? 1 : pageNum;
    }

    public PageReqDTO setPageNum(int pageNum) {
        this.pageNum = pageNum;
        return this;
    }

    public int getPageSize() {
        return pageSize == 0 ? 10 : pageSize;
    }

    public PageReqDTO setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }
}
