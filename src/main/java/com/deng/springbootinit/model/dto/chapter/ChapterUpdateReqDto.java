package com.deng.springbootinit.model.dto.chapter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChapterUpdateReqDto {
    /**
     * 章节标题
     */
    @NotBlank
    private String chapterName;

    /**
     * 章节内容
     */
    @NotBlank
    @Length(min = 50)
    private String chapterContent;

    /**
     * 是否收费;1-收费 0-免费
     */
    @NotNull
    private Integer isVip;

    private static final long serialVersionUID = 1L;
}
