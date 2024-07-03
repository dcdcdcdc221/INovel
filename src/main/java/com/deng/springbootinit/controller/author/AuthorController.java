package com.deng.springbootinit.controller.author;

import co.elastic.clients.elasticsearch.nodes.Http;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deng.springbootinit.common.BaseResponse;
import com.deng.springbootinit.common.PageRequest;
import com.deng.springbootinit.common.ResultUtils;
import com.deng.springbootinit.model.dto.author.AuthorRegisterRequest;
import com.deng.springbootinit.model.dto.chapter.ChapterAddReqDto;
import com.deng.springbootinit.model.dto.chapter.ChapterContentRespDto;
import com.deng.springbootinit.model.dto.chapter.ChapterUpdateReqDto;
import com.deng.springbootinit.model.dto.home.book.BookAddReqDto;
import com.deng.springbootinit.model.entity.BookChapter;
import com.deng.springbootinit.model.entity.BookInfo;
import com.deng.springbootinit.model.entity.UserInfo;
import com.deng.springbootinit.service.AuthorInfoService;
import com.deng.springbootinit.service.BookChapterService;
import com.deng.springbootinit.service.BookInfoService;
import com.deng.springbootinit.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Resource
    private AuthorInfoService authorInfoService;

    @Resource
    private UserService userService;

    @Resource
    private BookInfoService bookInfoService;

    @Resource
    private BookChapterService bookChapterService;

    @Resource
    private ObjectMapper objectMapper;

    /**
     * 用户注册
     * @param request
     * @return
     */
    @PostMapping("/register")
    public BaseResponse<Long> register (HttpServletRequest request,
                                        @Valid @RequestBody AuthorRegisterRequest authorRegisterRequest){
        //获取当前用户
        UserInfo loginUser = userService.getLoginUser(request);
        Long id = loginUser.getId();
        String userAccount = loginUser.getUserAccount();
        long register = authorInfoService.register(userAccount,authorRegisterRequest);
        return ResultUtils.success(register);
    }


    /**
     * 小说发布
     * @param bookAddReqDto
     * @param request
     * @return
     */
    @PostMapping("/book/published")
    public BaseResponse<Boolean> publishBook(@Valid @RequestBody BookAddReqDto bookAddReqDto,HttpServletRequest request){
        return ResultUtils.success(bookInfoService.saveBook(bookAddReqDto, request));
    }

//    @PostMapping("/book/chapter/{bookId}")
//    public BaseResponse<Boolean> publishBookChapter(@PathVariable("bookId") String bookId,
//                                                    )
    /**
     * 小说分页查询
     * @param pageReqDto

     * @return
     */
    @ResponseBody
    @GetMapping("/book/list")
    public BaseResponse<Page<BookInfo>> listBooks(@ModelAttribute  PageRequest pageReqDto, HttpServletRequest request) throws JsonProcessingException {
        System.out.println("输出的结果" + objectMapper.writeValueAsString(bookInfoService.listAuthorBooks(pageReqDto,request)));
        return ResultUtils.success(bookInfoService.listAuthorBooks(pageReqDto,request));
    }


    /**
     * 判断是否已成为作家
     * @param request
     * @return
     */
    @GetMapping("/book/isRegister")
    public BaseResponse<Boolean> isRegister(HttpServletRequest request){
        UserInfo loginUser = userService.getLoginUser(request);
        String userAccount = loginUser.getUserAccount();
        return ResultUtils.success(authorInfoService.isRegister(userAccount));
    }

    /**
     * 小说章节发布
     * @param
     * @param dto
     * @return
     */
    @ApiOperation("小说信息保存")
    @PostMapping("/book/chapter")
    public BaseResponse<Boolean> publishBookChapter(
                                                    @Valid @RequestBody ChapterAddReqDto dto,
                                                    HttpServletRequest request){
        return ResultUtils.success(bookInfoService.saveBookChapter(dto,request));
    }

    /**
     * 小说章节查询接口
     * @param chapterId
     * @param request
     * @return
     */
    @ApiOperation("小说章节查询")
    @GetMapping("/book/chapter/{chapterId}")
    public BaseResponse<ChapterContentRespDto> getBookChapter(
            @PathVariable("chapterId") Long chapterId, HttpServletRequest request) {
        return ResultUtils.success(bookInfoService.getBookChapter(chapterId,request));
    }

    @ApiOperation("小说章节更新")
    @PutMapping("/book/chapter/{chapterId}")
    public BaseResponse<Boolean> updateBookChapter(@PathVariable("chapterId") Long chapterId,
                                                   ChapterUpdateReqDto chapterUpdateReqDto,
                                                   HttpServletRequest request){
        return ResultUtils.success(bookChapterService.updateBookChapter(chapterId, chapterUpdateReqDto,request));
    }


    /**
     * 小说章节发布列表查询
     * @param bookId
     * @param pageRequest
     * @param request
     * @return
     */
    @ApiOperation("小说章节发布列表查询接口")
    @GetMapping("/book/chapters/{bookId}")
    public BaseResponse<Page<BookChapter>> listBookChapter(@PathVariable String bookId,
                                                           @ModelAttribute PageRequest pageRequest,
                                                           HttpServletRequest request
    ){
        return ResultUtils.success(bookChapterService.listBookChapters(bookId,pageRequest,request));
    }
}
