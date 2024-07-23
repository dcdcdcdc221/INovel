package com.deng.springbootinit.job.cycle;

import cn.hutool.core.collection.CollUtil;
import com.deng.springbootinit.mapper.BookInfoMapper;
import com.deng.springbootinit.model.dto.book.BookEsDTO;
import com.deng.springbootinit.model.entity.BookInfo;
import com.deng.springbootinit.esdao.BookEsDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 书籍增量更新,定时任务
 */
//TODO 后期考虑使用mq发送
//TODO 后续考虑开启作者查询功能
@Component
@Slf4j
public class IncSyncBookInfoToEs {
    @Resource
    private BookEsDao bookEsDao;

    @Resource
    private BookInfoMapper bookInfoMapper;

    @Scheduled(fixedRate = 60 * 1000)
    public void run() {
        // 查询近 5 分钟内的数据
        Date fiveMinutesAgoDate = new Date(new Date().getTime() - 5 * 60 * 1000L);
        List<BookInfo> bookInfoList = bookInfoMapper.listBookInfo(fiveMinutesAgoDate);
        if (CollUtil.isEmpty(bookInfoList)) {
            log.info("no inc post");
            return;
        }
        List<BookEsDTO> bookEsDTOList = bookInfoList.stream()
                .map(BookEsDTO::objToDto)
                .collect(Collectors.toList());
        final int pageSize = 500;
        int total = bookEsDTOList.size();
        log.info("IncSyncPostToEs start, total {}", total);
        for (int i = 0; i < total; i += pageSize) {
            int end = Math.min(i + pageSize, total);
            log.info("sync from {} to {}", i, end);
            bookEsDao.saveAll(bookEsDTOList.subList(i, end));
        }
        log.info("IncSyncPostToEs end, total {}", total);
    }
}
