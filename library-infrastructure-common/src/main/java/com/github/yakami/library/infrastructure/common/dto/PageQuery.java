package com.github.yakami.library.infrastructure.common.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by alan on 2022/5/24.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PageQuery implements Serializable {

    private static final long serialVersionUID = 5892579562783361767L;

    /**
     * 当前页
     */
    private Integer pageNo;

    /**
     * 页面大小
     */
    private Integer pageSize;

}
