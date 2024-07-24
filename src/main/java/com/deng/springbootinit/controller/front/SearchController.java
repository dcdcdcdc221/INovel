package com.deng.springbootinit.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deng.springbootinit.common.BaseResponse;
import com.deng.springbootinit.common.ErrorCode;
import com.deng.springbootinit.common.ResultUtils;
import com.deng.springbootinit.exception.ThrowUtils;
import com.deng.springbootinit.model.dto.book.BookQueryRequest;
import com.deng.springbootinit.model.entity.BookInfo;
import com.deng.springbootinit.model.vo.BookInfoVO;
import com.deng.springbootinit.service.BookInfoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 前台-搜索API
 */
@RestController
@RequestMapping("/search")
public class SearchController {
    @Resource
    private BookInfoService bookInfoService;

    @PostMapping("/page/vo")
    public BaseResponse<Page<BookInfoVO>> searchPostVOByPage(@RequestBody BookQueryRequest bookQueryRequest,
                                                             HttpServletRequest request){
        long size = bookQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        Page<BookInfo> bookInfoPage = bookInfoService.searchFromEs(bookQueryRequest);
        return ResultUtils.success(bookInfoService.getPostVOPage(bookInfoPage,request));
    }
}
