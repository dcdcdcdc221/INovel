package com.deng.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deng.springbootinit.common.ErrorCode;
import com.deng.springbootinit.exception.ThrowUtils;
import com.deng.springbootinit.mapper.BookChapterMapper;
import com.deng.springbootinit.model.dto.book.BookContentQueryResponse;
import com.deng.springbootinit.model.entity.BookChapter;
import com.deng.springbootinit.model.entity.BookContent;
import com.deng.springbootinit.model.entity.UserInfo;
import com.deng.springbootinit.service.BookContentService;
import com.deng.springbootinit.mapper.BookContentMapper;
import com.deng.springbootinit.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
* @author a9090
* @description 针对表【book_content(小说内容)】的数据库操作Service实现
* @createDate 2024-06-21 10:48:22
*/
@Service
@Slf4j
public class BookContentServiceImpl extends ServiceImpl<BookContentMapper, BookContent>
    implements BookContentService{

    @Resource
    private UserService userService;

    @Resource
    private BookChapterMapper bookChapterMapper;

    @Resource
    private BookContentMapper bookContentMapper;

    @Override
    public BookContentQueryResponse getBookContent(Long bookId, HttpServletRequest request) {
        //判断是否登录
        UserInfo loginUser = userService.getLoginUser(request);
        log.info("用户搜索阅读章节获取用户信息结果 {}", loginUser);
        ThrowUtils.throwIf(ObjectUtils.isEmpty(loginUser), ErrorCode.NO_AUTH_ERROR,"未登录");
        BookContentQueryResponse bookContent = bookContentMapper.getBookContent(bookId);
        ThrowUtils.throwIf(ObjectUtils.isEmpty(bookContent), ErrorCode.NOT_FOUND_ERROR,"该书籍暂无章节");
        //信息为空
        //TODO 新增isVIP
        //获取当前书籍的第一个章节信息
        return bookContentMapper.getBookContent(bookId);
    }
}




