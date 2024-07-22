package com.deng.springbootinit.job.once;

import cn.hutool.core.collection.CollUtil;
import com.deng.springbootinit.model.dto.book.BookEsDTO;
import com.deng.springbootinit.model.entity.BookInfo;
import com.deng.springbootinit.service.BookInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class FullSyncBookInfoToEs implements CommandLineRunner {

    @Resource
    private BookInfoService bookInfoService;

    @Resource
    private BookEsDTO bookEsDTO;
    @Override
    public void run(String... args) throws Exception {
        List<BookInfo> list = bookInfoService.list();
        if(CollUtil.isEmpty(list)) {
            return;
        }
        List<BookEsDTO> bookEsDTOList = list.stream().map(BookEsDTO::objToDto).collect(Collectors.toList());
        final int pageSize = 500;
        int total = bookEsDTOList.size();
        log.info("FullSyncBookInfoToEs start, total {}", total);
    }
}
