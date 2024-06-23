package com.deng.springbootinit.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Builder;
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
    @TableField(value = "workDirection")
    private Integer workDirection;

    /**
     * 类别ID
     */
    @TableField(value = "categoryId")
    private Long categoryId;

    /**
     * 类别名
     */
    @TableField(value = "categoryName")
    private String categoryName;

    /**
     * 小说封面地址
     */
    @TableField(value = "picUrl")
    private String picUrl;

    /**
     * 小说名
     */
    @TableField(value = "bookName")
    private String bookName;

    /**
     * 作家id
     */
    @TableField(value = "authorId")
    private Long authorId;

    /**
     * 作家名
     */
    @TableField(value = "authorName")
    private String authorName;

    /**
     * 书籍描述
     */
    @TableField(value = "bookDesc")
    private String bookDesc;

    /**
     * 评分;总分:10 ，真实评分 = score/10
     * 初始值为 11，代表未开始评分
     */
    @TableField(value = "score")
    private Integer score = 11;

    /**
     * 书籍状态;0-连载中 1-已完结
     */
    @TableField(value = "bookStatus")
    private Integer bookStatus;

    /**
     * 点击量
     */
    @TableField(value = "visitCount")
    private Long visitCount;

    /**
     * 总字数
     */
    @TableField(value = "wordCount")
    private Integer wordCount;

    /**
     * 评论数
     */
    @TableField(value = "commentCount")
    private Integer commentCount;

    /**
     * 最新章节ID
     */
    @TableField(value = "lastChapterId")
    private Long lastChapterId;

    /**
     * 最新章节名
     */
    @TableField(value = "lastChapterName")
    private String lastChapterName;

    /**
     * 最新章节更新时间
     */
    @TableField(value = "lastChapterUpdateTime")
    private LocalDateTime lastChapterUpdateTime;

    /**
     * 是否收费;1-收费 0-免费
     */
    @TableField(value = "isVip")
    private Integer isVip;

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
            && (this.getWorkDirection() == null ? other.getWorkDirection() == null : this.getWorkDirection().equals(other.getWorkDirection()))
            && (this.getCategoryId() == null ? other.getCategoryId() == null : this.getCategoryId().equals(other.getCategoryId()))
            && (this.getCategoryName() == null ? other.getCategoryName() == null : this.getCategoryName().equals(other.getCategoryName()))
            && (this.getPicUrl() == null ? other.getPicUrl() == null : this.getPicUrl().equals(other.getPicUrl()))
            && (this.getBookName() == null ? other.getBookName() == null : this.getBookName().equals(other.getBookName()))
            && (this.getAuthorId() == null ? other.getAuthorId() == null : this.getAuthorId().equals(other.getAuthorId()))
            && (this.getAuthorName() == null ? other.getAuthorName() == null : this.getAuthorName().equals(other.getAuthorName()))
            && (this.getBookDesc() == null ? other.getBookDesc() == null : this.getBookDesc().equals(other.getBookDesc()))
            && (this.getScore() == null ? other.getScore() == null : this.getScore().equals(other.getScore()))
            && (this.getBookStatus() == null ? other.getBookStatus() == null : this.getBookStatus().equals(other.getBookStatus()))
            && (this.getVisitCount() == null ? other.getVisitCount() == null : this.getVisitCount().equals(other.getVisitCount()))
            && (this.getWordCount() == null ? other.getWordCount() == null : this.getWordCount().equals(other.getWordCount()))
            && (this.getCommentCount() == null ? other.getCommentCount() == null : this.getCommentCount().equals(other.getCommentCount()))
            && (this.getLastChapterId() == null ? other.getLastChapterId() == null : this.getLastChapterId().equals(other.getLastChapterId()))
            && (this.getLastChapterName() == null ? other.getLastChapterName() == null : this.getLastChapterName().equals(other.getLastChapterName()))
            && (this.getLastChapterUpdateTime() == null ? other.getLastChapterUpdateTime() == null : this.getLastChapterUpdateTime().equals(other.getLastChapterUpdateTime()))
            && (this.getIsVip() == null ? other.getIsVip() == null : this.getIsVip().equals(other.getIsVip()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getWorkDirection() == null) ? 0 : getWorkDirection().hashCode());
        result = prime * result + ((getCategoryId() == null) ? 0 : getCategoryId().hashCode());
        result = prime * result + ((getCategoryName() == null) ? 0 : getCategoryName().hashCode());
        result = prime * result + ((getPicUrl() == null) ? 0 : getPicUrl().hashCode());
        result = prime * result + ((getBookName() == null) ? 0 : getBookName().hashCode());
        result = prime * result + ((getAuthorId() == null) ? 0 : getAuthorId().hashCode());
        result = prime * result + ((getAuthorName() == null) ? 0 : getAuthorName().hashCode());
        result = prime * result + ((getBookDesc() == null) ? 0 : getBookDesc().hashCode());
        result = prime * result + ((getScore() == null) ? 0 : getScore().hashCode());
        result = prime * result + ((getBookStatus() == null) ? 0 : getBookStatus().hashCode());
        result = prime * result + ((getVisitCount() == null) ? 0 : getVisitCount().hashCode());
        result = prime * result + ((getWordCount() == null) ? 0 : getWordCount().hashCode());
        result = prime * result + ((getCommentCount() == null) ? 0 : getCommentCount().hashCode());
        result = prime * result + ((getLastChapterId() == null) ? 0 : getLastChapterId().hashCode());
        result = prime * result + ((getLastChapterName() == null) ? 0 : getLastChapterName().hashCode());
        result = prime * result + ((getLastChapterUpdateTime() == null) ? 0 : getLastChapterUpdateTime().hashCode());
        result = prime * result + ((getIsVip() == null) ? 0 : getIsVip().hashCode());
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
        sb.append(", workDirection=").append(workDirection);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", categoryName=").append(categoryName);
        sb.append(", picUrl=").append(picUrl);
        sb.append(", bookName=").append(bookName);
        sb.append(", authorId=").append(authorId);
        sb.append(", authorName=").append(authorName);
        sb.append(", bookDesc=").append(bookDesc);
        sb.append(", score=").append(score);
        sb.append(", bookStatus=").append(bookStatus);
        sb.append(", visitCount=").append(visitCount);
        sb.append(", wordCount=").append(wordCount);
        sb.append(", commentCount=").append(commentCount);
        sb.append(", lastChapterId=").append(lastChapterId);
        sb.append(", lastChapterName=").append(lastChapterName);
        sb.append(", lastChapterUpdateTime=").append(lastChapterUpdateTime);
        sb.append(", isVip=").append(isVip);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}