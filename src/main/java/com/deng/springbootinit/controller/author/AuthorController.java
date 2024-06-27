package com.deng.springbootinit.controller.author;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deng.springbootinit.common.BaseResponse;
import com.deng.springbootinit.common.PageRequest;
import com.deng.springbootinit.common.ResultUtils;
import com.deng.springbootinit.model.dto.author.AuthorRegisterRequest;
import com.deng.springbootinit.model.dto.chapter.ChapterAddReqDto;
import com.deng.springbootinit.model.dto.home.book.BookAddReqDto;
import com.deng.springbootinit.model.entity.BookInfo;
import com.deng.springbootinit.model.entity.UserInfo;
import com.deng.springbootinit.service.AuthorInfoService;
import com.deng.springbootinit.service.BookInfoService;
import com.deng.springbootinit.service.UserService;
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
    @GetMapping("/book/list")
    public BaseResponse<Page<BookInfo>> listBooks(@ModelAttribute  PageRequest pageReqDto, HttpServletRequest request){
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
     * @param bookId
     * @param dto
     * @return
     */
    @ApiOperation("小说信息保存")
    @PostMapping("/book/chapter/{bookId}")
    public BaseResponse<Boolean> publishBookChapter(@PathVariable Long bookId,
                                                    @Valid @RequestBody ChapterAddReqDto dto,
                                                    HttpServletRequest request){
        dto.setBookId(bookId);
        return ResultUtils.success(bookInfoService.saveBookChapter(dto,request));
    }
}
