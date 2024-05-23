package com.deng.springbootinit.controller.author;

import com.deng.springbootinit.common.BaseResponse;
import com.deng.springbootinit.common.ResultUtils;
import com.deng.springbootinit.model.dto.author.AuthorRegisterRequest;
import com.deng.springbootinit.service.AuthorInfoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController("/author")
public class AuthorController {
    @Resource
    private AuthorInfoService authorInfoService;

    /**
     * 用户注册
     * @param authorRegisterRequest
     * @return
     */
    @PostMapping("/register")
    public BaseResponse<Long> register (@Valid @RequestBody AuthorRegisterRequest authorRegisterRequest){
        long result = authorInfoService.register(authorRegisterRequest);
        return ResultUtils.success(result);
    }
}
