package com.deng.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deng.springbootinit.common.ErrorCode;
import com.deng.springbootinit.exception.BusinessException;
import com.deng.springbootinit.mapper.BookInfoMapper;
import com.deng.springbootinit.model.dto.home.book.BookAddReqDto;
import com.deng.springbootinit.model.entity.AuthorInfo;
import com.deng.springbootinit.model.entity.BookInfo;
import com.deng.springbootinit.service.AuthorInfoService;
import com.deng.springbootinit.service.BookInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
* @author a9090
* @description 针对表【book_info(小说信息)】的数据库操作Service实现
* @createDate 2024-05-24 16:14:47
*/
@Service
public class BookInfoServiceImpl extends ServiceImpl<BookInfoMapper, BookInfo>
    implements BookInfoService{
    @Resource
    BookInfoMapper bookInfoMapper;

    @Resource
    AuthorInfoService authorInfoService;

    /**
     * 存储小说
     * @param bookAddReqDto
     * @param request
     * @return
     */
    @Override
    public Long saveBook(BookAddReqDto bookAddReqDto, HttpServletRequest request) {
        //校验是否异常
        if(null == bookInfoMapper && null == request){
            return null;
        }
        //校验小说名是否已经存在
        QueryWrapper<BookInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("bookName", bookAddReqDto.getBookName());
        //设置其他信息
        BookInfo bookInfo = new BookInfo();
        BeanUtils.copyProperties(bookAddReqDto, bookInfo);
        AuthorInfo currentAuthor = authorInfoService.getCurrentAuthor(request);
        //设置作家信息
        bookInfo.setAuthorId(currentAuthor.getId());
        bookInfo.setAuthorName(currentAuthor.getPenName());
        //保存小说信息
        int insert = bookInfoMapper.insert(bookInfo);
        if(insert != 1){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //返回小说id
        return bookInfo.getId();
    }
}




