package com.deng.springbootinit.model.dto.chapter;



import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class ChapterAddReqDto implements Serializable {
    /**
     * 小说ID
     */
    @NotNull
    private String bookId;

    /**
     * 章节名
     */
    @NotBlank
    private String chapterName;

    /**
     * 章节内容
     */
    @NotBlank
    @Length(min=50)
    private String chapterContent;

    /**
     * 是否收费;1-收费 0-免费
     */
    @NotNull
    private Integer isVip;
}
