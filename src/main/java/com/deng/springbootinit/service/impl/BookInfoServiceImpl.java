package com.deng.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deng.springbootinit.common.BaseResponse;
import com.deng.springbootinit.common.ErrorCode;
import com.deng.springbootinit.common.PageRequest;
import com.deng.springbootinit.exception.BusinessException;
import com.deng.springbootinit.exception.ThrowUtils;
import com.deng.springbootinit.mapper.AuthorInfoMapper;
import com.deng.springbootinit.mapper.BookChapterMapper;
import com.deng.springbootinit.mapper.BookContentMapper;
import com.deng.springbootinit.mapper.BookInfoMapper;
import com.deng.springbootinit.model.dto.chapter.ChapterAddReqDto;
import com.deng.springbootinit.model.dto.chapter.ChapterContentRespDto;
import com.deng.springbootinit.model.dto.home.book.BookAddReqDto;
import com.deng.springbootinit.model.entity.*;
import com.deng.springbootinit.service.AuthorInfoService;
import com.deng.springbootinit.service.BookChapterService;
import com.deng.springbootinit.service.BookInfoService;
import com.deng.springbootinit.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.Objects;

import static com.deng.springbootinit.constant.UserConstant.USER_LOGIN_STATE;

/**
* @author a9090
* @description 针对表【book_info(小说信息)】的数据库操作Service实现
* @createDate 2024-05-24 16:14:47
*/
@Service
@Slf4j
public class BookInfoServiceImpl extends ServiceImpl<BookInfoMapper, BookInfo>
    implements BookInfoService{
    @Resource
    private BookInfoMapper bookInfoMapper;

    @Resource
    private AuthorInfoService authorInfoService;


    @Resource
    private BookChapterMapper bookChapterMapper;


    @Resource
    private BookContentMapper bookContentMapper;


    /**
     * 存储小说
     * @param bookAddReqDto
     * @param request
     * @return
     */
    @Override
    public Boolean saveBook(BookAddReqDto bookAddReqDto, HttpServletRequest request) {
        //校验是否异常
        if(null == bookInfoMapper && null == request){
            return null;
        }
        //校验用户是否为作家
        if(authorInfoService.getCurrentAuthor(request) == null){
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR,"你不是作家没有权限发布书籍");
        }
        //校验小说名是否已经存在
        QueryWrapper<BookInfo> queryWrapper = new QueryWrapper<>();
        QueryWrapper<BookInfo> bookName = queryWrapper.eq("bookName", bookAddReqDto.getBookName());
        if(bookInfoMapper.selectCount(bookName) > 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"已经存在该书籍了");
        }
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
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"保存失败");
        }
        //返回小说id
        return true;
    }


    /**
     * 分页查询
     * @param pageReqDto
     * @param request
     * @return
     */
    @Override
    public Page<BookInfo> listAuthorBooks(PageRequest pageReqDto,
                                          HttpServletRequest request) {
        AuthorInfo currentAuthor = authorInfoService.getCurrentAuthor(request);
        if(null == currentAuthor){
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR,"请登录");
        }
        int current = pageReqDto.getCurrent();
        int pageSize = pageReqDto.getPageSize();
        QueryWrapper<BookInfo> queryWrapper = new QueryWrapper<>();
        QueryWrapper<BookInfo> authorId = queryWrapper.eq("authorId", currentAuthor.getId());
        Page<BookInfo> page = this.page(new Page<>(current, pageSize), authorId);
        return page;
    }

    /**
     * 小说章节信息保存
     * TODO 发mq
     * 存储两个表，小说章节，小说章节内容表
     * @param chapterAddReqDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveBookChapter(ChapterAddReqDto chapterAddReqDto,HttpServletRequest request) {
        //校验作品是否属于当前作家
        System.out.println("我们的值"+chapterAddReqDto);
        System.out.println("是否登录"+chapterAddReqDto.getBookId());
        BookInfo bookInfo = bookInfoMapper.selectById(chapterAddReqDto.getBookId());
//        long bookId = Long.parseLong(chapterAddReqDto.getBookId());
        Long bookId = chapterAddReqDto.getBookId();
        AuthorInfo currentAuthor = authorInfoService.getCurrentAuthor(request);
        if(!Objects.equals(bookInfo.getAuthorId(),currentAuthor.getId())) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR, "未登录");
        }
//        UserInfo attribute = (UserInfo) request.getSession().getAttribute(USER_LOGIN_STATE);
        Long id = authorInfoService.getCurrentAuthor(request).getId();
        System.out.println("bookinfo"+bookInfo);
        if(!Objects.equals(bookInfo.getAuthorId(),id)){
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR,"未登录或不是当前书籍作者");
        }
        //获取最新章节
        int chapterNum = 0;
        QueryWrapper<BookChapter> queryWrapper = new QueryWrapper<>();
        //限制条数为1条
        queryWrapper.eq("bookId",bookId)
                .orderByDesc("chapterNum")
                .last("limit 1");
        BookChapter bookChapter = bookChapterMapper.selectOne(queryWrapper);
        if(Objects.nonNull(bookChapter)){
            //最新章节加1（最新章节）
            chapterNum = bookChapter.getChapterNum() + 1;
            log.info("Book chapter(添加前的章节)" + bookChapter.getChapterNum());
            log.info("chapterNum(添加后的章节)" + chapterNum);
        }
        //保存到小说章节表
        BookChapter newBookChapter = new BookChapter();
        BeanUtils.copyProperties(chapterAddReqDto,newBookChapter);
        newBookChapter.setBookId(bookId);
        newBookChapter.setChapterNum(chapterNum);
        newBookChapter.setWordCount(chapterAddReqDto.getChapterContent().length());
        newBookChapter.setBookId(bookId);
        bookChapterMapper.insert(newBookChapter);
        //保存到小说内容表
        BookContent bookContent = new BookContent();
        bookContent.setChapterId(newBookChapter.getId());
        bookContent.setContent(chapterAddReqDto.getChapterContent());
        bookContentMapper.insert(bookContent);
        //更新小说最新章节和字数信息
        BookInfo newBookInfo = new BookInfo();
        newBookInfo.setId(bookId);
        newBookInfo.setLastChapterId(newBookChapter.getId());
        newBookInfo.setLastChapterName(newBookChapter.getChapterName());
        //更新字数
        newBookInfo.setWordCount(bookInfo.getWordCount() + newBookChapter.getWordCount());
        bookInfoMapper.updateById(newBookInfo);
        //TODO 清除小说信息缓存+rocketmq发给es消费
        return true;
        }

    /**
     * 小说章节信息查询
     * @param chapterId
     * @param request
     * @return
     */
    @Override
    public ChapterContentRespDto getBookChapter(Long chapterId,HttpServletRequest request) {
        //获取当前作者
        AuthorInfo currentAuthor = authorInfoService.getCurrentAuthor(request);
        //当前章节是否存在
        //判断当前书籍是否属于当前作者
        BookChapter bookChapter = bookChapterMapper.selectById(chapterId);
        ThrowUtils.throwIf(Objects.isNull(bookChapter),ErrorCode.FORBIDDEN_ERROR,"当前章节或书籍不存在");
        Long bookId = bookChapter.getBookId();
        BookInfo bookInfo = bookInfoMapper.selectById(bookId);
        ThrowUtils.throwIf(Objects.isNull(bookInfo),
                ErrorCode.NO_AUTH_ERROR,"当前书籍异常，请联系管理员");
        log.info("获取的AuthorId" + bookInfo.getAuthorId());
        log.info("当前的AuthorId" + currentAuthor.getId());
        ThrowUtils.throwIf(!Objects.equals(bookInfo.getAuthorId(), currentAuthor.getId()),
                ErrorCode.NO_AUTH_ERROR,"非当前书籍作者，请确认");
        //查询章节信息
        //查询章节内容
        QueryWrapper<BookContent> queryWrapper = new QueryWrapper<>();
        QueryWrapper<BookContent> eq = queryWrapper.eq("chapterId", chapterId);
        BookContent bookContent = bookContentMapper.selectOne(eq);
        return ChapterContentRespDto.builder().chapterContent(bookContent.getContent())
                .isVip(bookChapter.getIsVip())
                .chapterName(bookChapter.getChapterName())
                .chapterNum(bookChapter.getChapterNum())
                .build();
    }
}



