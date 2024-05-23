package com.deng.springbootinit.model.dto.author;

import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@Data
public class AuthorRegisterRequest implements Serializable {
    private static final long serialVersionUID = 3191241716373120793L;


    /**
     * 邀请码
     */
    private String inviteCode;

    /**
     * 笔名
     */
    @NotBlank(message = "笔名不能为空！")
    private String penName;

    /**
     * 手机号码
     */
    @NotBlank(message = "手机号不能为空！")
    @Pattern(regexp = "^1[3|4|5|6|7|8|9][0-9]{9}$", message = "手机号格式不正确！")
    private String phone;


    /**
     * 作品方向;0-男频 1-女频
     */
    @NotNull(message = "作品方向不能为空！")
    @Min(0)
    @Max(1)
    private Integer workDirection;


}
