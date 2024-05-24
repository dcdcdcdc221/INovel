package com.deng.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deng.springbootinit.common.BaseResponse;
import com.deng.springbootinit.common.ErrorCode;
import com.deng.springbootinit.exception.BusinessException;
import com.deng.springbootinit.mapper.UserInfoMapper;
import com.deng.springbootinit.model.dto.author.AuthorRegisterRequest;
import com.deng.springbootinit.model.entity.AuthorInfo;
import com.deng.springbootinit.model.entity.UserInfo;
import com.deng.springbootinit.model.vo.AuthVO;
import com.deng.springbootinit.service.AuthorInfoService;
import com.deng.springbootinit.mapper.AuthorInfoMapper;
import com.deng.springbootinit.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author a9090
* @description 针对表【author_info(作者信息)】的数据库操作Service实现
* @createDate 2024-05-23 16:33:55
*/
@RequestMapping("/author")
@Service
public class AuthorInfoServiceImpl extends ServiceImpl<AuthorInfoMapper, AuthorInfo>
    implements AuthorInfoService{
    @Resource
    private AuthorInfoMapper authorInfoMapper;

    @Resource
    private UserService userService;


    /**
     * 注册为作家
     * @param userAccount
     * @param authorRegisterRequest
     * @return
     */
    @Override
    public long register(String userAccount,AuthorRegisterRequest authorRegisterRequest) {
        //查询是否注册过
        if(isRegister(userAccount)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"你已经是作者了");
        }
        if(authorRegisterRequest == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请输入必要信息");
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


    /**
     * 获取当前作者
     * @param request
     * @return
     */
    @Override
    public AuthorInfo getCurrentAuthor(HttpServletRequest request) {
        UserInfo loginUser = userService.getLoginUser(request);
        if(null == loginUser){
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR,"请先登录");
        }
        String userAccount = loginUser.getUserAccount();
        QueryWrapper<AuthorInfo> queryWrapper = new QueryWrapper<>();
        QueryWrapper<AuthorInfo> eq = queryWrapper.eq("userAccount", userAccount);
        return authorInfoMapper.selectOne(eq);
    }
}



