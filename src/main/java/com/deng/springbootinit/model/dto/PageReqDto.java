package com.deng.springbootinit.model.dto;

import lombok.Data;

/**
 * 分页请求数据格式封装
 */
@Data
public class PageReqDto<T> {
    /**
     * 请求页码，默认第 1 页
     */
    private int pageNum = 1;

    /**
     * 每页大小，默认每页 10 条
     */
    private int pageSize = 10;

    /**
     * 是否查询所有，默认不查所有 为true时 无效
     */
    private boolean fetchAll = false;
}
