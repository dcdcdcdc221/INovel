package com.deng.springbootinit.service;

import com.deng.springbootinit.model.dto.book.BookContentQueryResponse;
import com.deng.springbootinit.model.entity.BookContent;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
* @author a9090
* @description 针对表【book_content(小说内容)】的数据库操作Service
* @createDate 2024-06-21 10:48:22
*/
public interface BookContentService extends IService<BookContent> {

    BookContentQueryResponse getBookContent(Long bookId, HttpServletRequest request);
}
