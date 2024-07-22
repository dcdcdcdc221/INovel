package com.deng.springbootinit.service;

import com.deng.springbootinit.model.dto.book.BookEsDTO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 搜索服务类
 */
public interface SearchService extends ElasticsearchRepository<BookEsDTO,Long> {
}
