package com.deng.springbootinit.controller.author;

import com.deng.springbootinit.common.BaseResponse;
import com.deng.springbootinit.common.ErrorCode;
import com.deng.springbootinit.common.ResultUtils;
import com.deng.springbootinit.exception.BusinessException;
import com.deng.springbootinit.model.dto.author.AuthorRegisterRequest;
import com.deng.springbootinit.model.entity.UserInfo;
import com.deng.springbootinit.service.AuthorInfoService;
import com.deng.springbootinit.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController("/author")
public class AuthorController {
    @Resource
    private AuthorInfoService authorInfoService;

    @Resource
    private UserService userService;

    /**
     * 用户注册
     * @param request
     * @return
     */
    @PostMapping("/register")
    public BaseResponse<Long> register (HttpServletRequest request, @Valid @RequestBody AuthorRegisterRequest authorRegisterRequest){
        //获取当前用户
        UserInfo loginUser = userService.getLoginUser(request);
        Long id = loginUser.getId();
        String userAccount = loginUser.getUserAccount();
        long register = authorInfoService.register(userAccount,authorRegisterRequest);
        return ResultUtils.success(register);
    }
}
