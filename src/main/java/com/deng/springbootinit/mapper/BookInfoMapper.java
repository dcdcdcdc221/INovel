package com.deng.springbootinit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deng.springbootinit.model.entity.BookInfo;

import java.util.Date;
import java.util.List;

/**
* @author a9090
* @description 针对表【book_info(小说信息)】的数据库操作Mapper
* @createDate 2024-05-24 15:47:28
* @Entity com.deng.springbootinit.model.entity.BookInfo
*/
public interface BookInfoMapper extends BaseMapper<BookInfo> {
    List<BookInfo> listBookInfo(Date minUpdateTime);

}




