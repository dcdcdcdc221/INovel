package com.deng.springbootinit.job.once;

import cn.hutool.core.collection.CollUtil;
import com.deng.springbootinit.model.dto.book.BookEsDTO;
import com.deng.springbootinit.model.entity.BookInfo;
import com.deng.springbootinit.service.BookInfoService;
import com.deng.springbootinit.esdao.BookEsDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 全量更新书籍
 * 运行程序时初始化
 */
@Component
@Slf4j
public class FullSyncBookInfoToEs implements CommandLineRunner {

    @Resource
    private BookInfoService bookInfoService;

    @Resource
    private BookEsDao bookEsDao;
    @Override
    public void run(String... args) throws Exception {
        List<BookInfo> list = bookInfoService.list();
        log.info("FullSyncBookInfoToEs first get list {}", list.toString());
        if(CollUtil.isEmpty(list)) {
            return;
        }
        List<BookEsDTO> bookEsDTOList = list.stream().map(BookEsDTO::objToDto).collect(Collectors.toList());
        final int pageSize = 500;
        int total = bookEsDTOList.size();
        log.info("FullSyncBookInfoToEs start, total {}", total);
        for (int i = 0; i < total; i += pageSize) {
            int end = Math.min(i + pageSize, total);
            log.info("sync from {} to {}", i, end);
            bookEsDao.saveAll(bookEsDTOList.subList(i, end));
        }
        log.info("FullSyncBookInfoToEs end, total {}", total);
    }
}
