package com.deng.springbootinit.model.dto.chapter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 返回参数
 * 此处有一个问题，当使用builder注解的时候，默认生成全参构造方法
 * 所以需要加NoArgsConstructor生成无参，但 builder 强依赖全参，所以需加上 AllArgsConstructor
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    /**
     * 章节数
     */
    private Integer chapterNum;
}
