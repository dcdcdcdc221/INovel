package com.deng.springbootinit.service;

import com.deng.springbootinit.model.dto.home.book.HomeBookRespDto;
import com.deng.springbootinit.model.entity.HomeBook;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author a9090
* @description 针对表【home_book(小说推荐)】的数据库操作Service
* @createDate 2024-05-24 15:47:28
*/
public interface HomeBookService extends IService<HomeBook> {

    List<HomeBookRespDto> listHomeBooks();
}
