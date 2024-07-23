package com.deng.springbootinit.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deng.springbootinit.common.BaseResponse;
import com.deng.springbootinit.model.entity.BookInfo;
import com.deng.springbootinit.model.vo.BookInfoVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 前台-搜索API
 */
@RestController
@RequestMapping("/search")
public class SearchController {

    @PostMapping("/page/vo")
    public BaseResponse<Page<BookInfoVO>>
}
