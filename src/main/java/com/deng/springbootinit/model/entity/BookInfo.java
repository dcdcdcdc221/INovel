package com.deng.springbootinit.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 小说信息
 * @TableName book_info
 */
@TableName(value ="book_info")
@Data
public class BookInfo implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 作品方向;0-男频 1-女频
     */
    @TableField(value = "work_direction")
    private Integer work_direction;

    /**
     * 类别ID
     */
    @TableField(value = "category_id")
    private Long category_id;

    /**
     * 类别名
     */
    @TableField(value = "category_name")
    private String category_name;

    /**
     * 小说封面地址
     */
    @TableField(value = "pic_url")
    private String pic_url;

    /**
     * 小说名
     */
    @TableField(value = "book_name")
    private String book_name;

    /**
     * 作家id
     */
    @TableField(value = "author_id")
    private Long author_id;

    /**
     * 作家名
     */
    @TableField(value = "author_name")
    private String author_name;

    /**
     * 书籍描述
     */
    @TableField(value = "book_desc")
    private String book_desc;

    /**
     * 评分;总分:10 ，真实评分 = score/10
     */
    @TableField(value = "score")
    private Integer score;

    /**
     * 书籍状态;0-连载中 1-已完结
     */
    @TableField(value = "book_status")
    private Integer book_status;

    /**
     * 点击量
     */
    @TableField(value = "visit_count")
    private Long visit_count;

    /**
     * 总字数
     */
    @TableField(value = "word_count")
    private Integer word_count;

    /**
     * 评论数
     */
    @TableField(value = "comment_count")
    private Integer comment_count;

    /**
     * 最新章节ID
     */
    @TableField(value = "last_chapter_id")
    private Long last_chapter_id;

    /**
     * 最新章节名
     */
    @TableField(value = "last_chapter_name")
    private String last_chapter_name;

    /**
     * 最新章节更新时间
     */
    @TableField(value = "last_chapter_update_time")
    private LocalDateTime last_chapter_update_time;

    /**
     * 是否收费;1-收费 0-免费
     */
    @TableField(value = "is_vip")
    private Integer is_vip;

    /**
     * 创建时间
     */
    @TableField(value = "createTime")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "updateTime")
    private LocalDateTime updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        BookInfo other = (BookInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getWork_direction() == null ? other.getWork_direction() == null : this.getWork_direction().equals(other.getWork_direction()))
            && (this.getCategory_id() == null ? other.getCategory_id() == null : this.getCategory_id().equals(other.getCategory_id()))
            && (this.getCategory_name() == null ? other.getCategory_name() == null : this.getCategory_name().equals(other.getCategory_name()))
            && (this.getPic_url() == null ? other.getPic_url() == null : this.getPic_url().equals(other.getPic_url()))
            && (this.getBook_name() == null ? other.getBook_name() == null : this.getBook_name().equals(other.getBook_name()))
            && (this.getAuthor_id() == null ? other.getAuthor_id() == null : this.getAuthor_id().equals(other.getAuthor_id()))
            && (this.getAuthor_name() == null ? other.getAuthor_name() == null : this.getAuthor_name().equals(other.getAuthor_name()))
            && (this.getBook_desc() == null ? other.getBook_desc() == null : this.getBook_desc().equals(other.getBook_desc()))
            && (this.getScore() == null ? other.getScore() == null : this.getScore().equals(other.getScore()))
            && (this.getBook_status() == null ? other.getBook_status() == null : this.getBook_status().equals(other.getBook_status()))
            && (this.getVisit_count() == null ? other.getVisit_count() == null : this.getVisit_count().equals(other.getVisit_count()))
            && (this.getWord_count() == null ? other.getWord_count() == null : this.getWord_count().equals(other.getWord_count()))
            && (this.getComment_count() == null ? other.getComment_count() == null : this.getComment_count().equals(other.getComment_count()))
            && (this.getLast_chapter_id() == null ? other.getLast_chapter_id() == null : this.getLast_chapter_id().equals(other.getLast_chapter_id()))
            && (this.getLast_chapter_name() == null ? other.getLast_chapter_name() == null : this.getLast_chapter_name().equals(other.getLast_chapter_name()))
            && (this.getLast_chapter_update_time() == null ? other.getLast_chapter_update_time() == null : this.getLast_chapter_update_time().equals(other.getLast_chapter_update_time()))
            && (this.getIs_vip() == null ? other.getIs_vip() == null : this.getIs_vip().equals(other.getIs_vip()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getWork_direction() == null) ? 0 : getWork_direction().hashCode());
        result = prime * result + ((getCategory_id() == null) ? 0 : getCategory_id().hashCode());
        result = prime * result + ((getCategory_name() == null) ? 0 : getCategory_name().hashCode());
        result = prime * result + ((getPic_url() == null) ? 0 : getPic_url().hashCode());
        result = prime * result + ((getBook_name() == null) ? 0 : getBook_name().hashCode());
        result = prime * result + ((getAuthor_id() == null) ? 0 : getAuthor_id().hashCode());
        result = prime * result + ((getAuthor_name() == null) ? 0 : getAuthor_name().hashCode());
        result = prime * result + ((getBook_desc() == null) ? 0 : getBook_desc().hashCode());
        result = prime * result + ((getScore() == null) ? 0 : getScore().hashCode());
        result = prime * result + ((getBook_status() == null) ? 0 : getBook_status().hashCode());
        result = prime * result + ((getVisit_count() == null) ? 0 : getVisit_count().hashCode());
        result = prime * result + ((getWord_count() == null) ? 0 : getWord_count().hashCode());
        result = prime * result + ((getComment_count() == null) ? 0 : getComment_count().hashCode());
        result = prime * result + ((getLast_chapter_id() == null) ? 0 : getLast_chapter_id().hashCode());
        result = prime * result + ((getLast_chapter_name() == null) ? 0 : getLast_chapter_name().hashCode());
        result = prime * result + ((getLast_chapter_update_time() == null) ? 0 : getLast_chapter_update_time().hashCode());
        result = prime * result + ((getIs_vip() == null) ? 0 : getIs_vip().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", work_direction=").append(work_direction);
        sb.append(", category_id=").append(category_id);
        sb.append(", category_name=").append(category_name);
        sb.append(", pic_url=").append(pic_url);
        sb.append(", book_name=").append(book_name);
        sb.append(", author_id=").append(author_id);
        sb.append(", author_name=").append(author_name);
        sb.append(", book_desc=").append(book_desc);
        sb.append(", score=").append(score);
        sb.append(", book_status=").append(book_status);
        sb.append(", visit_count=").append(visit_count);
        sb.append(", word_count=").append(word_count);
        sb.append(", comment_count=").append(comment_count);
        sb.append(", last_chapter_id=").append(last_chapter_id);
        sb.append(", last_chapter_name=").append(last_chapter_name);
        sb.append(", last_chapter_update_time=").append(last_chapter_update_time);
        sb.append(", is_vip=").append(is_vip);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}