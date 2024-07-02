package com.deng.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deng.springbootinit.common.ErrorCode;
import com.deng.springbootinit.common.PageRequest;
import com.deng.springbootinit.exception.ThrowUtils;
import com.deng.springbootinit.mapper.BookInfoMapper;
import com.deng.springbootinit.model.entity.AuthorInfo;
import com.deng.springbootinit.model.entity.BookChapter;
import com.deng.springbootinit.model.entity.BookInfo;
import com.deng.springbootinit.service.AuthorInfoService;
import com.deng.springbootinit.service.BookChapterService;
import com.deng.springbootinit.mapper.BookChapterMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
* @author a9090
* @description 针对表【book_chapter(小说章节)】的数据库操作Service实现
* @createDate 2024-06-21 09:45:11
*/
@Slf4j
@Service
public class BookChapterServiceImpl extends ServiceImpl<BookChapterMapper, BookChapter>
    implements BookChapterService{

    @Resource
    private AuthorInfoService authorInfoService;

    @Resource
    private BookInfoMapper bookInfoMapper;

    @Override
    public Page<BookChapter> listBookChapters(String bookId, PageRequest pageRequest, HttpServletRequest request) {
        //获取当前作者
        AuthorInfo currentAuthor = authorInfoService.getCurrentAuthor(request);
        ThrowUtils.throwIf(Objects.isNull(currentAuthor),
                ErrorCode.NO_AUTH_ERROR,"不是当前作者或者未登录");
        //id转换为long类型
        long bookIdWithLong = Long.parseLong(bookId);
        //获取当前书籍
        BookInfo bookInfo = bookInfoMapper.selectById(bookIdWithLong);
        //校验书籍是否属于当前作者
        Long authorId = bookInfo.getAuthorId();
        ThrowUtils.throwIf(Objects.equals(bookIdWithLong,authorId),
                ErrorCode.NO_AUTH_ERROR,"请确认是否为该书籍作者");
        //分页查询章节
        int current = pageRequest.getCurrent();
        int pageSize = pageRequest.getPageSize();
        QueryWrapper<BookChapter> queryWrapper = new QueryWrapper<>();
        //TODO 添加多种查询筛选
        QueryWrapper<BookChapter> bookId1 = queryWrapper.eq("bookId", bookIdWithLong)
                .orderByDesc("chapterNum");
        log.info("章节查询执行");
        Page<BookChapter> page = this.page(new Page<>(current, pageSize), bookId1);
        return page;
    }
}




