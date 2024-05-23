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

--作者信息表
CREATE TABLE author_info (
id bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
userId bigint(20) unsigned NOT NULL COMMENT '用户ID',
inviteCode varchar(20) NOT NULL COMMENT '邀请码',
penName varchar(20) NOT NULL COMMENT '笔名',
phone varchar(20) DEFAULT NULL COMMENT '手机号码',
chat_account varchar(50) DEFAULT NULL COMMENT 'QQ或微信账号',
email varchar(50) DEFAULT NULL COMMENT '电子邮箱',
workDirection tinyint(3) unsigned DEFAULT NULL COMMENT '作品方向;0-男频 1-女频',
status tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '0：正常;1-封禁',
createTime datetime DEFAULT NULL COMMENT '创建时间',
updateTime datetime DEFAULT NULL COMMENT '更新时间',
PRIMARY KEY (id),
UNIQUE KEY uk_userId (userId),
UNIQUE KEY pk_id (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='作者信息';