SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- 类别表
-- ----------------------------
-- DROP TABLE IF EXISTS blog_sort;
-- CREATE TABLE sort
-- (
--     id               int(18) unsigned NOT NULL AUTO_INCREMENT,
--     sort_name        varchar(50) NOT NULL COMMENT '博客分类，唯一',
--     sort_alias       varchar(15)  DEFAULT NULL COMMENT '类别别名',
--     sort_description varchar(255) DEFAULT NULL COMMENT '类别描述',
--     parent_sort_id   int(18) DEFAULT NULL COMMENT '父级博客分类',
--     create_time      datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP (3),
--     update_time      datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP (3) ON UPDATE CURRENT_TIMESTAMP (3),
--     delete_time      datetime(3) DEFAULT NULL,
--     PRIMARY KEY (id),
--     UNIQUE KEY sort_name_del (sort_name, delete_time)
-- ) ENGINE = InnoDB
--   DEFAULT CHARSET = utf8mb4
--   COLLATE = utf8mb4_general_ci;

-- ----------------------------
-- 博客表
-- ----------------------------
-- DROP TABLE IF EXISTS blog;
-- CREATE TABLE blog
-- (
--     id              int(18) unsigned NOT NULL AUTO_INCREMENT,
--     user_id         int(18)          NOT NULL COMMENT '博客发表用户',
--     blog_title      varchar(100)          NOT NULL COMMENT '博客标题',
--     blog_cover      varchar(500)     DEFAULT NULL COMMENT '博客封面',
--     blog_markdown   varchar(500)      NOT NULL COMMENT '博客内容-markdown',
--     blog_content    varchar(500)      NOT NULL COMMENT '博客内容',
--     blog_views      int(20)       NOT NULL COMMENT '浏览量',
--     blog_like_count int(20)       NOT NULL COMMENT '点赞数',
--     blog_comment_count int(20)    NOT NULL COMMENT '评论数',
--     create_time datetime(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
--     update_time datetime(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
--     delete_time datetime(3)               DEFAULT NULL,
--     PRIMARY KEY (id),
--     UNIQUE KEY blog_title_del (blog_title, delete_time)
-- ) ENGINE = InnoDB
--   DEFAULT CHARSET = utf8mb4
--   COLLATE = utf8mb4_general_ci;
-- ----------------------------
-- 博客-类别中间表
-- ----------------------------
-- DROP TABLE IF EXISTS blog_sort;
-- CREATE TABLE blog_sort
-- (
--     id      int(10) unsigned NOT NULL AUTO_INCREMENT,
--     blog_id int(10) unsigned NOT NULL COMMENT '博客id',
--     sort_id int(10) unsigned NOT NULL COMMENT '分类id',
--     PRIMARY KEY (id),
--     KEY blog_id_sort_id (blog_id, sort_id) USING BTREE COMMENT '联合索引'
-- ) ENGINE = InnoDB
--   DEFAULT CHARSET = utf8mb4
--   COLLATE = utf8mb4_general_ci;
-- ----------------------------
-- 访问者表
-- ----------------------------
-- DROP TABLE IF EXISTS visitor;
-- CREATE TABLE visitor
-- (
--     id      int(18) unsigned NOT NULL AUTO_INCREMENT,
--     cip     varchar(50)          NOT NULL COMMENT 'IP地址',
--     cid     varchar(50)          NOT NULL COMMENT '城市编号',
--     cname   varchar(100)         NOT NULL COMMENT '城市名称',
--     count   int(50)                       COMMENT '访问次数',
--     PRIMARY KEY (id)
-- ) ENGINE = InnoDB
--   DEFAULT CHARSET = utf8mb4
--   COLLATE = utf8mb4_general_ci;
-- ----------------------------
-- 博客-访问者表
-- ----------------------------
-- DROP TABLE IF EXISTS blog_visitor;
-- CREATE TABLE blog_visitor
-- (
--     id         int(10) unsigned NOT NULL AUTO_INCREMENT,
--     blog_id    int(10) unsigned NOT NULL COMMENT '博客id',
--     visitor_id int(10) unsigned NOT NULL COMMENT '访问者id',
--     count      int(50)                   COMMENT '访问次数',
--     PRIMARY KEY (id),
--     KEY blog_id_visitor_id (blog_id, visitor_id) USING BTREE COMMENT '联合索引'
-- ) ENGINE = InnoDB
--   DEFAULT CHARSET = utf8mb4
--   COLLATE = utf8mb4_general_ci;
-- ----------------------------
-- 微信用户表
-- ----------------------------
-- DROP TABLE IF EXISTS wxuser;
-- CREATE TABLE wxuser
-- (
--     id               int(18) unsigned NOT NULL AUTO_INCREMENT,
--     open_id          varchar(100)     NOT NULL COMMENT 'open_id',
--     skey             varchar(100)     NOT NULL COMMENT 'UUID唯一值',
--
--     session_key      varchar(100)     NOT NULL COMMENT 'session_key',
--     city             varchar(255)     NULL DEFAULT NULL COMMENT '市',
--     province         varchar(255)     NULL DEFAULT NULL COMMENT '省',
--     country          varchar(255)     NULL DEFAULT NULL COMMENT '国',
--     avatar_url       varchar(255)     NULL DEFAULT NULL COMMENT '头像',
--     gender           tinyint(11)      NULL DEFAULT NULL COMMENT '性别',
--     nick_name        varchar(255)     NULL DEFAULT NULL COMMENT '网名',
--     create_time      datetime(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP (3),
--     update_time      datetime(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP (3) ON UPDATE CURRENT_TIMESTAMP (3),
--     delete_time      datetime(3)      DEFAULT NULL,
--     PRIMARY KEY (id)
-- ) ENGINE = InnoDB
--   DEFAULT CHARSET = utf8mb4
--   COLLATE = utf8mb4_general_ci;
-- ----------------------------
-- 用户-微信账号表
-- ----------------------------
-- DROP TABLE IF EXISTS user_wxuser;
-- CREATE TABLE user_wxuser
-- (
--     id         int(10) unsigned NOT NULL AUTO_INCREMENT,
--     user_id    int(10) unsigned NOT NULL COMMENT '用户id',
--     wxuser_id  int(10) unsigned NOT NULL COMMENT '微信账号id',
--     PRIMARY KEY (id),
--     KEY user_id_wxuser_id (user_id, wxuser_id) USING BTREE COMMENT '联合索引'
-- ) ENGINE = InnoDB
--   DEFAULT CHARSET = utf8mb4
--   COLLATE = utf8mb4_general_ci;
-- ----------------------------
-- 时间轴表
-- ----------------------------
-- DROP TABLE IF EXISTS timeline;
-- CREATE TABLE timeline
-- (
--     id               int(18) unsigned NOT NULL AUTO_INCREMENT,
--     title            varchar(255) NOT NULL COMMENT '时间轴标题',
--     date             varchar(50)  DEFAULT NULL COMMENT '时间轴',
--     path             varchar(255) DEFAULT NULL COMMENT '图片',
--     parent_id        int(18) DEFAULT NULL COMMENT '父级时间轴',
--     radio            int(18) DEFAULT NULL COMMENT '是否视频',
--     create_time      datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP (3),
--     update_time      datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP (3) ON UPDATE CURRENT_TIMESTAMP (3),
--     delete_time      datetime(3) DEFAULT NULL,
--     PRIMARY KEY (id),
--     UNIQUE KEY title_del (title, delete_time)
-- ) ENGINE = InnoDB
--   DEFAULT CHARSET = utf8mb4
--   COLLATE = utf8mb4_general_ci;

-- ----------------------------
-- 评论表
-- ----------------------------
DROP TABLE IF EXISTS comment;
CREATE TABLE comment
(
    id                  int(18)     unsigned NOT NULL AUTO_INCREMENT,
    name                int(18)              NOT NULL COMMENT '用户昵称',
    email               varchar(100)         NOT NULL COMMENT '用户邮箱',
    website             varchar(500)     DEFAULT NULL COMMENT '用户网站',
    comment             varchar(500)         NOT NULL COMMENT '评论',
    reply               varchar(500)     DEFAULT NULL COMMENT '回复用户',
    parent_id           int(20)              NOT NULL COMMENT '父级评论',
    comment_like_count  int(20)          DEFAULT 0    COMMENT '点赞数',
    is_owner            int(20)          DEFAULT 0    COMMENT '是否博主',
    create_time datetime(3)                  NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    update_time datetime(3)                  NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    delete_time datetime(3)              DEFAULT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY name_del (name, delete_time)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;
