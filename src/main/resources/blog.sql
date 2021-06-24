SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- 类别表
-- ----------------------------
DROP TABLE IF EXISTS blog_sort;
CREATE TABLE sort
(
    id               int(18) unsigned NOT NULL AUTO_INCREMENT,
    sort_name        varchar(50) NOT NULL COMMENT '博客分类，唯一',
    sort_alias       varchar(15)  DEFAULT NULL COMMENT '类别别名',
    sort_description varchar(255) DEFAULT NULL COMMENT '类别描述',
    parent_sort_id   int(18) DEFAULT NULL COMMENT '父级博客分类',
    create_time      datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP (3),
    update_time      datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP (3) ON UPDATE CURRENT_TIMESTAMP (3),
    delete_time      datetime(3) DEFAULT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY sort_name_del (sort_name, delete_time)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

-- ----------------------------
-- 博客表
-- ----------------------------
DROP TABLE IF EXISTS blog;
CREATE TABLE blog
(
    id              int(18) unsigned NOT NULL AUTO_INCREMENT,
    user_id         int(18)          NOT NULL COMMENT '博客发表用户',
    blog_title      varchar(100)          NOT NULL COMMENT '博客标题',
    blog_cover      varchar(500)     DEFAULT NULL COMMENT '博客封面',
    blog_markdown   varchar(500)      NOT NULL COMMENT '博客内容-markdown',
    blog_content    varchar(500)      NOT NULL COMMENT '博客内容',
    blog_views      int(20)       NOT NULL COMMENT '浏览量',
    blog_like_count int(20)       NOT NULL COMMENT '点赞数',
    blog_comment_count int(20)    NOT NULL COMMENT '评论数',
    create_time datetime(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    update_time datetime(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    delete_time datetime(3)               DEFAULT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY blog_title_del (blog_title, delete_time)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;
-- ----------------------------
-- 博客-类别中间表
-- ----------------------------
DROP TABLE IF EXISTS blog_sort;
CREATE TABLE blog_sort
(
    id      int(10) unsigned NOT NULL AUTO_INCREMENT,
    blog_id int(10) unsigned NOT NULL COMMENT '博客id',
    sort_id int(10) unsigned NOT NULL COMMENT '分类id',
    PRIMARY KEY (id),
    KEY blog_id_sort_id (blog_id, sort_id) USING BTREE COMMENT '联合索引'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;
-- ----------------------------
-- 访问者表
-- ----------------------------
DROP TABLE IF EXISTS visitor;
CREATE TABLE visitor
(
    id      int(18) unsigned NOT NULL AUTO_INCREMENT,
    cip     varchar(50)          NOT NULL COMMENT 'IP地址',
    cid     varchar(50)          NOT NULL COMMENT '城市编号',
    cname   varchar(100)         NOT NULL COMMENT '城市名称',
    count   int(50)                       COMMENT '访问次数',
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;
-- ----------------------------
-- 博客-访问者表
-- ----------------------------
DROP TABLE IF EXISTS blog_visitor;
CREATE TABLE blog_visitor
(
    id         int(10) unsigned NOT NULL AUTO_INCREMENT,
    blog_id    int(10) unsigned NOT NULL COMMENT '博客id',
    visitor_id int(10) unsigned NOT NULL COMMENT '访问者id',
    count      int(50)                   COMMENT '访问次数',
    PRIMARY KEY (id),
    KEY blog_id_visitor_id (blog_id, visitor_id) USING BTREE COMMENT '联合索引'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;
