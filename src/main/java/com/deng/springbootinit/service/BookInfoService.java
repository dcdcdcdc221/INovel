package com.deng.springbootinit.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deng.springbootinit.common.BaseResponse;
import com.deng.springbootinit.common.PageRequest;
import com.deng.springbootinit.model.dto.book.BookQueryRequest;
import com.deng.springbootinit.model.dto.chapter.ChapterAddReqDto;
import com.deng.springbootinit.model.dto.chapter.ChapterContentRespDto;
import com.deng.springbootinit.model.dto.home.book.BookAddReqDto;
import com.deng.springbootinit.model.entity.BookChapter;
import com.deng.springbootinit.model.entity.BookInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deng.springbootinit.model.vo.BookInfoVO;

import javax.servlet.http.HttpServletRequest;

/**
* @author a9090
* @description 针对表【book_info(小说信息)】的数据库操作Service
* @createDate 2024-05-24 16:14:47
*/
public interface BookInfoService extends IService<BookInfo> {
    /**
     * 存储小说
     * @param bookAddReqDto
     * @param request
     * @return
     */
    Boolean saveBook(BookAddReqDto bookAddReqDto, HttpServletRequest request);


    /**
     * 列表查询小说
     * @param pageReqDto
     * @return
     */
    Page<BookInfo> listAuthorBooks(PageRequest pageReqDto, HttpServletRequest request);


    /**
     * 小说章节信息保存
     * @param chapterAddReqDto
     * @return
     */
    Boolean saveBookChapter(ChapterAddReqDto chapterAddReqDto,HttpServletRequest request);

    /**
     * 小说章节信息查询
     * @param chapterId
     * @return
     */
    ChapterContentRespDto getBookChapter(Long chapterId,HttpServletRequest request);

    /**
     * 从ES查询
     * @param bookQueryRequest
     * @return
     */
    Page<BookInfo> searchFromEs(BookQueryRequest bookQueryRequest);

    Page<BookInfoVO> getPostVOPage(Page<BookInfo> bookInfoPage, HttpServletRequest request);
}
