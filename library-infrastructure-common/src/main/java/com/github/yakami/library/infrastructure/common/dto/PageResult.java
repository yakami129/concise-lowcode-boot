package com.github.yakami.library.infrastructure.common.dto;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * Created by alan on 2022/5/24.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PageResult<E> implements Serializable {

    private static final long serialVersionUID = 1077216979845886649L;

    /**
     * 结果集
     */
    private List<E> rows;

    /**
     * 分页参数
     */
    private Page page;

    public PageResult() {
    }

    public PageResult(IPage<E> iPage) {
        this.rows = iPage.getRecords();
        this.page = new Page(iPage.getCurrent(), iPage.getSize(), iPage.getTotal());
    }

    public PageResult(Long pageNo, Long pageSize, Long totalRow, List<E> rows) {
        this.rows = rows;
        this.page = new Page(pageNo, pageSize, totalRow);
    }

    public PageResult(List<E> rows) {
        this.rows = rows;
        this.page = new Page(1L, rows.size() + 0L, rows.size() + 0L);
    }

    public class Page implements Serializable {

        private static final long serialVersionUID = -8483407730222509296L;

        /**
         * 当前页
         */
        private Long pageNo;

        /**
         * 页面大小
         */
        private Long pageSize;

        /**
         * 结果总条数
         */
        private Long totalRow;

        public Page(Long pageNo, Long pageSize, Long totalRow) {
            this.pageNo = pageNo;
            this.pageSize = pageSize;
            this.totalRow = totalRow;
        }

        public Long getPageNo() {
            return pageNo;
        }

        public void setPageNo(Long pageNo) {
            this.pageNo = pageNo;
        }

        public Long getPageSize() {
            return pageSize;
        }

        public void setPageSize(Long pageSize) {
            this.pageSize = pageSize;
        }

        public Long getTotalRow() {
            return totalRow;
        }

        public void setTotalRow(Long totalRow) {
            this.totalRow = totalRow;
        }
    }

}
