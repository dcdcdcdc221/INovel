package com.deng.springbootinit.controller.front;

import com.deng.springbootinit.common.BaseResponse;
import com.deng.springbootinit.model.dto.home.book.HomeBookRespDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 前台门户-首页模块 API 控制器
 */
@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/books")
    public BaseResponse<List<HomeBookRespDto>> listHomeBooks(){
        return null;
    }
}
