package com.deng.springbootinit.model.dto.home.book;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class BookAddReqDto {

    /**
     * 作品方向;0-男频 1-女频
     */
    @NotNull
    private Integer workDirection;


    /**
     * 类别名
     */
    @NotBlank
    //TODO 新增类别表限制自定义类别名
    private String categoryName;

    /**
     * 小说封面地址
     */
    //TODO OOS对象存储
    @NotBlank
    private String picUrl;

    /**
     * 小说名
     */
    @NotBlank
    private String bookName;

    /**
     * 书籍描述
     */
    @NotBlank
    private String bookDesc;


    /**
     * 是否收费;1-收费 0-免费
     */
    @NotNull
    private Integer isVip;
}