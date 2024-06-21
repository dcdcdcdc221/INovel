package com.deng.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deng.springbootinit.model.entity.BookChapter;
import com.deng.springbootinit.service.BookChapterService;
import com.deng.springbootinit.mapper.BookChapterMapper;
import org.springframework.stereotype.Service;

/**
* @author a9090
* @description 针对表【book_chapter(小说章节)】的数据库操作Service实现
* @createDate 2024-06-21 09:45:11
*/
@Service
public class BookChapterServiceImpl extends ServiceImpl<BookChapterMapper, BookChapter>
    implements BookChapterService{

}




