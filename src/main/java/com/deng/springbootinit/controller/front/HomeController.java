package com.deng.springbootinit.controller.front;

import com.deng.springbootinit.common.BaseResponse;
import com.deng.springbootinit.common.ErrorCode;
import com.deng.springbootinit.common.ResultUtils;
import com.deng.springbootinit.model.dto.book.BookChapterListResponse;
import com.deng.springbootinit.model.dto.book.BookContentQueryResponse;
import com.deng.springbootinit.model.dto.home.book.HomeBookRespDto;
import com.deng.springbootinit.model.entity.UserInfo;
import com.deng.springbootinit.service.BookChapterService;
import com.deng.springbootinit.service.BookContentService;
import com.deng.springbootinit.service.HomeBookService;
import com.deng.springbootinit.service.UserService;
import com.deng.springbootinit.service.impl.BookContentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.checkerframework.checker.index.qual.SameLen;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 前台门户-首页模块 API 控制器
 */
@RestController
@RequestMapping("/home")
@Slf4j
public class HomeController {

    @Resource
    private HomeBookService homeBookService;

    @Resource
    private BookChapterService bookChapterService;

    @Resource
    private BookContentService bookContentService;



    @GetMapping("/books")
    public BaseResponse<List<HomeBookRespDto>> listHomeBooks(){
        return ResultUtils.success(homeBookService.listHomeBooks());
    }

    /**
     * 阅读当前查询书籍
     * @param bookId
     * @param request
     * @return
     */
    @GetMapping("/books/{bookId}")
    public BaseResponse<BookContentQueryResponse> getBookContent(@PathVariable("bookId") Long bookId,
                                                                 HttpServletRequest request){
        return ResultUtils.success(bookContentService.getBookContent(bookId,request));
    }

    @GetMapping("/chapter/{bookId}")
    public BaseResponse<List<BookChapterListResponse>> getBookChapterList(@PathVariable("bookId") Long bookId){
        return ResultUtils.success(bookChapterService.getBookChapterList(bookId));
    }
}
