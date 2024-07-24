package com.deng.springbootinit.model.dto.book;

import com.deng.springbootinit.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * ES检索
 */
//TODO 先不考虑其他过滤，优先实现
@EqualsAndHashCode(callSuper = true)
@Data
public class BookQueryRequest extends PageRequest implements Serializable {
    private String searchText;
}
