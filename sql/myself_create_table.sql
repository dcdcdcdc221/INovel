-- 用户表
create table if not exists user_info
(
    id
    bigint
    auto_increment
    comment
    'id'
    primary
    key,
    userAccount
    varchar
(
    256
) not null comment '账号',
    userPassword varchar
(
    512
) not null comment '密码',
    unionId varchar
(
    256
) null comment '微信开放平台id',
    mpOpenId varchar
(
    256
) null comment '公众号openId',
    userName varchar
(
    256
) null comment '用户昵称',
    userAvatar varchar
(
    1024
) null comment '用户头像',
    userProfile varchar
(
    512
) null comment '用户简介',
    userRole varchar
(
    256
) default 'userInfo' not null comment '用户角色：userInfo/admin/ban',
    userSex tinyint
(
    3
) unsigned default null null comment '用户性别;0-男 1-女',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete tinyint default 0 not null comment '是否删除',
    index idx_unionId
(
    unionId
),
    UNIQUE KEY `pk_id`
(
    `id`
)
    ) comment '用户信息' collate = utf8mb4_unicode_ci;
drop table author_info;
-- 作者信息表
CREATE TABLE if not exists author_info
(
    id bigint
(
    20
) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    userAccount varchar
(
    256
) not null comment '账号',
    inviteCode varchar
(
    20
) NOT NULL COMMENT '邀请码',
    penName varchar
(
    20
) NOT NULL COMMENT '笔名',
    phone varchar
(
    20
) DEFAULT NULL COMMENT '手机号码',
    chatAccount varchar
(
    50
) DEFAULT NULL COMMENT 'QQ或微信账号',
    email varchar
(
    50
) DEFAULT NULL COMMENT '电子邮箱',
    workDirection tinyint
(
    3
) unsigned DEFAULT NULL COMMENT '作品方向;0-男频 1-女频',
    status tinyint
(
    3
) unsigned NOT NULL DEFAULT '0' COMMENT '0：正常;1-封禁',
    createTime datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updateTime datetime DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY
(
    id
),
    UNIQUE KEY pk_id
(
    id
)
    ) ENGINE = InnoDB
    COLLATE = utf8mb4_unicode_ci COMMENT ='作者信息';

-- 小说推荐表
DROP TABLE IF EXISTS home_book;
CREATE TABLE home_book
(
    id         bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    type       tinyint(3) unsigned NOT NULL COMMENT '推荐类型;0-轮播图 1-顶部栏 2-本周强推 3-热门推荐 4-精品推荐',
    sort       tinyint(3) unsigned NOT NULL COMMENT '推荐排序',
    bookId     bigint(20) unsigned NOT NULL COMMENT '推荐小说ID',
    createTime datetime default CURRENT_TIMESTAMP not null COMMENT '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY pk_id (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 96
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='小说推荐';

DROP TABLE IF EXISTS book_info;
CREATE TABLE book_info
(
    id                    bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    workDirection         tinyint(3) unsigned DEFAULT NULL COMMENT '作品方向;0-男频 1-女频',
    categoryId            bigint(20) unsigned DEFAULT NULL COMMENT '类别ID',
    categoryName          varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '类别名',
    picUrl                varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci                          NOT NULL COMMENT '小说封面地址',
    bookName              varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci                           NOT NULL COMMENT '小说名',
    authorId              bigint(20) unsigned NOT NULL COMMENT '作家id',
    authorName            varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci                           NOT NULL COMMENT '作家名',
    bookDesc              varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci                         NOT NULL COMMENT '书籍描述',
    score                 tinyint(3) unsigned NOT NULL COMMENT '评分;总分:10 ，真实评分 = score/10',
    bookStatus            tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '书籍状态;0-连载中 1-已完结',
    visitCount            bigint(20) unsigned NOT NULL DEFAULT '103' COMMENT '点击量',
    wordCount             int(10) unsigned NOT NULL DEFAULT '0' COMMENT '总字数',
    commentCount          int(10) unsigned NOT NULL DEFAULT '0' COMMENT '评论数',
    lastChapterId         bigint(20) unsigned DEFAULT NULL COMMENT '最新章节ID',
    lastChapterName       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '最新章节名',
    lastChapterUpdateTime datetime                                                     DEFAULT NULL COMMENT '最新章节更新时间',
    isVip                 tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否收费;1-收费 0-免费',
    createTime            datetime                                                     default CURRENT_TIMESTAMP not null COMMENT '创建时间',
    updateTime            datetime                                                     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id) USING BTREE,
    UNIQUE KEY uk_bookName_authorName (bookName, authorName) USING BTREE,
    UNIQUE KEY pk_id (id) USING BTREE,
    KEY                   idx_createTime (createTime) USING BTREE,
    KEY                   idx_lastChapterUpdateTime (lastChapterUpdateTime) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1431630596354977793
  DEFAULT CHARSET = utf8mb4 COMMENT ='小说信息';


-- 小说章节表
DROP TABLE IF EXISTS `book_chapter`;
CREATE TABLE if not exists `book_chapter`
(
    `id` bigint
(
    20
) unsigned NOT NULL AUTO_INCREMENT,
    `bookId` bigint
(
    20
) unsigned NOT NULL COMMENT '小说ID',
    `chapterNum` smallint
(
    5
) unsigned NOT NULL COMMENT '章节号',
    `chapterName` varchar
(
    100
) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '章节名',
    `wordCount` int
(
    10
) unsigned NOT NULL COMMENT '章节字数',
    `isVip` tinyint
(
    3
) unsigned NOT NULL DEFAULT '0' COMMENT '是否收费;1-收费 0-免费',
    `createTime` datetime DEFAULT NULL,
    `updateTime` datetime DEFAULT NULL,
    PRIMARY KEY
(
    `id`
) USING BTREE,
    UNIQUE KEY `uk_bookId_chapterNum`
(
    `bookId`,
    `chapterNum`
)
  USING BTREE,
    UNIQUE KEY `pk_id`
(
    `id`
)
  USING BTREE,
    KEY `idx_bookId`
(
    `bookId`
)
  USING BTREE
    ) ENGINE=InnoDB AUTO_INCREMENT=1445988184596992001 DEFAULT CHARSET=utf8mb4 COLLATE =utf8mb4_0900_ai_ci COMMENT='小说章节';


-- 小说具体内容表
DROP TABLE IF EXISTS `book_content`;
CREATE TABLE if not exists `book_content`
(
    `id` bigint
(
    20
) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `chapterId` bigint
(
    20
) unsigned NOT NULL COMMENT '章节ID',
    `content` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '小说章节内容',
    `createTime` datetime DEFAULT NULL,
    `updateTime` datetime DEFAULT NULL,
    PRIMARY KEY
(
    `id`
) USING BTREE,
    UNIQUE KEY `uk_chapterId`
(
    `chapterId`
)
  USING BTREE,
    UNIQUE KEY `pk_id`
(
    `id`
)
  USING BTREE
    ) ENGINE = InnoDB
    AUTO_INCREMENT = 4256332
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci COMMENT ='小说内容';