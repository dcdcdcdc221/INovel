package com.deng.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deng.springbootinit.model.entity.BookContent;
import com.deng.springbootinit.service.BookContentService;
import com.deng.springbootinit.mapper.BookContentMapper;
import org.springframework.stereotype.Service;

/**
* @author a9090
* @description 针对表【book_content(小说内容)】的数据库操作Service实现
* @createDate 2024-06-21 10:48:22
*/
@Service
public class BookContentServiceImpl extends ServiceImpl<BookContentMapper, BookContent>
    implements BookContentService{

}




