package com.deng.springbootinit.service;

import com.deng.springbootinit.model.entity.UserInfo;
import javax.annotation.Resource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 帖子点赞服务测试
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@SpringBootTest
class PostThumbServiceTest {

    @Resource
    private PostThumbService postThumbService;

    private static final UserInfo LOGIN_USER_INFO = new UserInfo();

    @BeforeAll
    static void setUp() {
        LOGIN_USER_INFO.setId(1L);
    }

    @Test
    void doPostThumb() {
        int i = postThumbService.doPostThumb(1L, LOGIN_USER_INFO);
        Assertions.assertTrue(i >= 0);
    }
}
