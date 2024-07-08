package com.deng.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deng.springbootinit.common.ErrorCode;
import com.deng.springbootinit.common.PageRequest;
import com.deng.springbootinit.exception.ThrowUtils;
import com.deng.springbootinit.mapper.BookContentMapper;
import com.deng.springbootinit.mapper.BookInfoMapper;
import com.deng.springbootinit.model.dto.chapter.ChapterUpdateReqDto;
import com.deng.springbootinit.model.entity.AuthorInfo;
import com.deng.springbootinit.model.entity.BookChapter;
import com.deng.springbootinit.model.entity.BookContent;
import com.deng.springbootinit.model.entity.BookInfo;
import com.deng.springbootinit.service.AuthorInfoService;
import com.deng.springbootinit.service.BookChapterService;
import com.deng.springbootinit.mapper.BookChapterMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Resource
    private BookChapterMapper bookChapterMapper;

    @Resource
    private BookContentMapper bookContentMapper;

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
        log.info("作者分页查询pageList" + page.getRecords());
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateBookChapter(Long chapterId, ChapterUpdateReqDto chapterUpdateReqDto, HttpServletRequest request) {
        log.info("前端传的值" + chapterUpdateReqDto.toString());
        //校验作品是否属于当前作家
        BookChapter bookChapter = bookChapterMapper.selectById(chapterId);
        BookInfo bookInfo = bookInfoMapper.selectById(bookChapter.getBookId());
        AuthorInfo currentAuthor = authorInfoService.getCurrentAuthor(request);
        ThrowUtils.throwIf(Objects.isNull(bookInfo)
                ,ErrorCode.PARAMS_ERROR,"该书籍不存在或异常");
        ThrowUtils.throwIf(!Objects.equals(bookInfo.getAuthorId(),currentAuthor.getId())
                ,ErrorCode.NO_AUTH_ERROR,"非该书籍作者，请确认");
        //保存到小说章节表
        BookChapter newBookChapter = new BookChapter();
        //忽略章节内容
        BeanUtils.copyProperties(chapterUpdateReqDto,newBookChapter,chapterUpdateReqDto.getChapterContent());
        newBookChapter.setWordCount(chapterUpdateReqDto.getChapterContent().length());
        //查看语句
        log.info("bookChapterMapper更新");
        bookChapterMapper.updateById(newBookChapter);
        //更新章节内容
        BookContent bookContent = new BookContent();
        bookContent.setContent(chapterUpdateReqDto.getChapterContent());
        QueryWrapper<BookContent> bookContentQueryWrapper = new QueryWrapper<>();
        bookContentQueryWrapper.eq("chapterId", chapterId);
        bookContentMapper.update(bookContent, bookContentQueryWrapper);
        //更新小说信息
        BookInfo newBookInfo = new BookInfo();
        newBookInfo.setId(bookChapter.getId());
        //获取当前字数
        newBookInfo.setWordCount(
                bookInfo.getWordCount() - bookChapter.getWordCount() + chapterUpdateReqDto.getChapterContent().length()
        );
        if(Objects.equals(bookInfo.getLastChapterId(),chapterId)){
            newBookInfo.setLastChapterName(chapterUpdateReqDto.getChapterName());
        }
        log.info("bookInfoMapper更新");
        //此处不会更新update的值
        bookInfoMapper.updateById(newBookInfo);
        return true;
    }
}




