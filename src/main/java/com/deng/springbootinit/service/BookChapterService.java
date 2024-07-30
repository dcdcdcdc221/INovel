package com.deng.springbootinit.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deng.springbootinit.common.PageRequest;
import com.deng.springbootinit.model.dto.book.BookChapterListResponse;
import com.deng.springbootinit.model.dto.chapter.ChapterUpdateReqDto;
import com.deng.springbootinit.model.entity.BookChapter;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author a9090
* @description 针对表【book_chapter(小说章节)】的数据库操作Service
* @createDate 2024-06-21 09:45:11
*/
public interface BookChapterService extends IService<BookChapter> {
    /**
     * 查询小说发布章节列表(作者用)
     * @param bookId
     * @param pageRequest
     * @param request
     * @return
     */
    Page<BookChapter> listBookChapters(String bookId, PageRequest pageRequest, HttpServletRequest request);

    /**
     * 更新小说章节内容
     * @param chapterId
     * @param chapterUpdateReqDto
     * @param request
     * @return
     */
    Boolean updateBookChapter(Long chapterId, ChapterUpdateReqDto chapterUpdateReqDto, HttpServletRequest request);

    /**
     * 获取小说章节信息(用户用)
     * @param bookId
     * @return
     */
    List<BookChapterListResponse> getBookChapterList(Long bookId);
}
