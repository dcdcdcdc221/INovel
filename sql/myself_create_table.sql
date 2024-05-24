-- 用户表
create table if not exists user_info
(
    id           bigint auto_increment comment 'id' primary key,
    userAccount  varchar(256)                           not null comment '账号',
    userPassword varchar(512)                           not null comment '密码',
    unionId      varchar(256)                           null comment '微信开放平台id',
    mpOpenId     varchar(256)                           null comment '公众号openId',
    userName     varchar(256)                           null comment '用户昵称',
    userAvatar   varchar(1024)                          null comment '用户头像',
    userProfile  varchar(512)                           null comment '用户简介',
    userRole     varchar(256) default 'userInfo'            not null comment '用户角色：userInfo/admin/ban',
    userSex      tinyint(3) unsigned default null       null comment '用户性别;0-男 1-女',
    createTime   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint      default 0                 not null comment '是否删除',
    index idx_unionId (unionId),
    UNIQUE KEY `pk_id` (`id`)
    ) comment '用户信息' collate = utf8mb4_unicode_ci;
drop table author_info;
-- 作者信息表
CREATE TABLE if not exists author_info (
id bigint(20)                            unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
userAccount                              varchar(256) not null comment '账号',
inviteCode                               varchar(20) NOT NULL COMMENT '邀请码',
penName                                  varchar(20) NOT NULL COMMENT '笔名',
phone                                    varchar(20) DEFAULT NULL COMMENT '手机号码',
chatAccount                              varchar(50) DEFAULT NULL COMMENT 'QQ或微信账号',
email                                    varchar(50) DEFAULT NULL COMMENT '电子邮箱',
workDirection tinyint(3)                 unsigned DEFAULT NULL COMMENT '作品方向;0-男频 1-女频',
status tinyint(3)                        unsigned NOT NULL DEFAULT '0' COMMENT '0：正常;1-封禁',
createTime datetime                      DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
updateTime datetime                      DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
PRIMARY KEY (id),
UNIQUE KEY pk_id (id)
) ENGINE=InnoDB  COLLATE=utf8mb4_unicode_ci COMMENT='作者信息';

-- 小说推荐表
DROP TABLE IF EXISTS home_book;
CREATE TABLE home_book (
id bigint(20)                           unsigned NOT NULL AUTO_INCREMENT,
type tinyint(3)                         unsigned NOT NULL COMMENT '推荐类型;0-轮播图 1-顶部栏 2-本周强推 3-热门推荐 4-精品推荐',
sort tinyint(3)                         unsigned NOT NULL COMMENT '推荐排序',
bookId bigint(20)                       unsigned NOT NULL COMMENT '推荐小说ID',
createTime datetime                     default CURRENT_TIMESTAMP COMMENT '创建时间',
updateTime datetime                     default CURRENT_TIMESTAMP COMMENT '更新时间',
PRIMARY KEY (id),
UNIQUE KEY pk_id (id)
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='小说推荐';