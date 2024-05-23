package com.deng.springbootinit.service;

import com.deng.springbootinit.model.dto.author.AuthorRegisterRequest;
import com.deng.springbootinit.model.entity.AuthorInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deng.springbootinit.model.entity.UserInfo;

/**
* @author a9090
* @description 针对表【author_info(作者信息)】的数据库操作Service
* @createDate 2024-05-23 16:33:55
*/
public interface AuthorInfoService extends IService<AuthorInfo> {
     long register(String userAccount,AuthorRegisterRequest authorRegisterRequest);

     boolean isRegister(String userAccount);

}
