package com.deng.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deng.springbootinit.common.ErrorCode;
import com.deng.springbootinit.exception.BusinessException;
import com.deng.springbootinit.mapper.UserInfoMapper;
import com.deng.springbootinit.model.dto.author.AuthorRegisterRequest;
import com.deng.springbootinit.model.entity.AuthorInfo;
import com.deng.springbootinit.model.entity.UserInfo;
import com.deng.springbootinit.service.AuthorInfoService;
import com.deng.springbootinit.mapper.AuthorInfoMapper;
import com.deng.springbootinit.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author a9090
* @description 针对表【author_info(作者信息)】的数据库操作Service实现
* @createDate 2024-05-23 16:33:55
*/
@Service
public class AuthorInfoServiceImpl extends ServiceImpl<AuthorInfoMapper, AuthorInfo>
    implements AuthorInfoService{
    @Resource
    private AuthorInfoMapper authorInfoMapper;


    @Override
    public long register(String userAccount,AuthorRegisterRequest authorRegisterRequest) {
        //查询是否注册过
        if(isRegister(userAccount)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"你已经是作者了");
        }
        //插入
        AuthorInfo authorInfo = new AuthorInfo();
        authorInfo.setInviteCode(authorRegisterRequest.getInviteCode());
        authorInfo.setPenName(authorRegisterRequest.getPenName());
        authorInfo.setPhone(authorRegisterRequest.getPhone());
        authorInfo.setWorkDirection(authorRegisterRequest.getWorkDirection());
        authorInfo.setUserAccount(userAccount);
        return authorInfoMapper.insert(authorInfo);
    }

    /**
     * 是否注册
     * @param userAccount
     * @return
     */
    @Override
    public boolean isRegister(String userAccount){
        QueryWrapper<AuthorInfo> queryWrapper = new QueryWrapper<>();
        QueryWrapper<AuthorInfo> eq = queryWrapper.eq("userAccount", userAccount);
        return authorInfoMapper.exists(eq);
    }
}



