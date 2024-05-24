package com.deng.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deng.springbootinit.model.entity.BookInfo;
import com.deng.springbootinit.service.BookInfoService;
import com.deng.springbootinit.mapper.BookInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author a9090
* @description 针对表【book_info(小说信息)】的数据库操作Service实现
* @createDate 2024-05-24 15:47:28
*/
@Service
public class BookInfoServiceImpl extends ServiceImpl<BookInfoMapper, BookInfo>
    implements BookInfoService{

}




