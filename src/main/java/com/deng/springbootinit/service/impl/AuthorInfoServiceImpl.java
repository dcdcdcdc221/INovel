package com.deng.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deng.springbootinit.common.ErrorCode;
import com.deng.springbootinit.exception.BusinessException;
import com.deng.springbootinit.model.dto.author.AuthorRegisterRequest;
import com.deng.springbootinit.model.entity.AuthorInfo;
import com.deng.springbootinit.model.entity.UserInfo;
import com.deng.springbootinit.service.AuthorInfoService;
import com.deng.springbootinit.mapper.AuthorInfoMapper;
import org.apache.commons.lang3.StringUtils;
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
    public long register(AuthorRegisterRequest authorRegisterRequest) {
        QueryWrapper<AuthorInfo> queryWrapper = new QueryWrapper<>();
        QueryWrapper<AuthorInfo> eq = queryWrapper.eq("userAccount",
                authorRegisterRequest.getUserAccount());
        Long count = authorInfoMapper.selectCount(eq);
        if(count > 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"已注册为作者");
        }
        AuthorInfo authorInfo = new AuthorInfo();
        authorInfo.setInviteCode(authorRegisterRequest.getInviteCode());
        authorInfo.setPenName(authorRegisterRequest.getPenName());
        authorInfo.setPhone(authorRegisterRequest.getPhone());
        authorInfo.setWorkDirection(authorRegisterRequest.getWorkDirection());
        authorInfo.setUserAccount(authorInfo.getUserAccount());
        int insert = authorInfoMapper.insert(authorInfo);
        if(insert < 1){
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"创建失败");
        }
        return  authorInfo.getId();
    }
}




