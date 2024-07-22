package com.deng.springbootinit.model.dto.book;

import com.deng.springbootinit.model.dto.post.PostEsDTO;
import com.deng.springbootinit.model.entity.BookInfo;
import com.deng.springbootinit.model.entity.Post;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
//开启此注释开启es功能
@Document(indexName = "book")
public class BookEsDTO implements Serializable {
    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    private Long id;

    private Long categoryId;

    private String categoryName;

    private String picUrl;

    private String bookName;

    private Long authorId;

    private String authorName;

    private String bookDesc;

    private Integer bookStatus;

    private Long visitCount;

    private Integer wordCount;

    private Integer commentCount;

    private Long firstChapterId;

    private Long lastChapterId;

    private String lastChapterName;

    /**
     * 不被分词,不创建索引，不被分词，日期格式
     */
    @Field(index = false,store = true,type = FieldType.Date,format = {},pattern = DATE_TIME_PATTERN)
    private LocalDateTime updateTime;


    /**
     * 对象转包装类
     * @param bookInfo
     * @return
     */
    public static BookEsDTO objToDto(BookInfo bookInfo) {
        if(bookInfo == null){
            return null;
        }
        BookEsDTO bookEsDTO = new BookEsDTO();
        BeanUtils.copyProperties(bookInfo,bookEsDTO);
        return bookEsDTO;
    }
}
