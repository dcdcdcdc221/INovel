package com.deng.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deng.springbootinit.model.dto.home.book.HomeBookRespDto;
import com.deng.springbootinit.model.entity.HomeBook;
import com.deng.springbootinit.service.HomeBookService;
import com.deng.springbootinit.mapper.HomeBookMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author a9090
* @description 针对表【home_book(小说推荐)】的数据库操作Service实现
* @createDate 2024-05-24 15:47:28
*/
@Service
public class HomeBookServiceImpl extends ServiceImpl<HomeBookMapper, HomeBook>
    implements HomeBookService{

    @Override
    public List<HomeBookRespDto> listHomeBooks() {
        return null;
    }
}




