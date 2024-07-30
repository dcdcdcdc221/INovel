package com.deng.springbootinit.model.dto.book;

import lombok.Data;

import java.io.Serializable;

/**
 * 目录查询
 */
@Data
public class BookChapterListResponse implements Serializable {
    /**
     * 章节id
     */
    private Long id;

    private Integer chapterNum;

    private String chapterName;

    private Integer isVip;
}
