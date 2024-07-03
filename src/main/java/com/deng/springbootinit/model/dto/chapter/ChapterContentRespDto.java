package com.deng.springbootinit.model.dto.chapter;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
public class ChapterContentRespDto implements Serializable {

    /**
     * 章节标题
     */
    private String chapterName;

    /**
     * 章节内容
     */
    private String chapterContent;

    /**
     * 是否收费;1-收费 0-免费
     */
    private Integer isVip;

}
