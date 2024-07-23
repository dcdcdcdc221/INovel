package com.deng.springbootinit.esdao;

import com.deng.springbootinit.model.dto.book.BookEsDTO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 搜索服务类
 */
public interface BookEsDao extends ElasticsearchRepository<BookEsDTO,Long> {
}
