package com.deng.springbootinit.model.dto.book;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class BookContentQueryResponse implements Serializable {
    private String content;

    private String chapterName;

    private Integer isVip;

    private Integer wordCount;
}
