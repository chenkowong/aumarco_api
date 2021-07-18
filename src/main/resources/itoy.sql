SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- 类别表
-- ----------------------------
DROP TABLE IF EXISTS itoy_sort;
CREATE TABLE itoy_sort
(
    id               int(18) unsigned NOT NULL AUTO_INCREMENT,
    sort_name        varchar(50) NOT NULL COMMENT '博客分类，唯一',
    sort_alias       varchar(15)  DEFAULT NULL COMMENT '类别别名',
    sort_description varchar(255) DEFAULT NULL COMMENT '类别描述',
    create_time      datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP (3),
    update_time      datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP (3) ON UPDATE CURRENT_TIMESTAMP (3),
    delete_time      datetime(3) DEFAULT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY sort_name_del (sort_name, delete_time)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;
-- ----------------------------
-- 宝贝表
-- ----------------------------
DROP TABLE IF EXISTS itoy_toy;
CREATE TABLE itoy_toy
(
    id                 int(18) unsigned NOT NULL AUTO_INCREMENT,
    toy_no             varchar(100)     NOT NULL COMMENT '宝贝编号',
    toy_title          varchar(100)     DEFAULT NULL COMMENT '宝贝标题',
    toy_cover          varchar(500)     NOT NULL COMMENT '宝贝封面',
    toy_urls           varchar(5000)    NOT NULL COMMENT '宝贝图片',
    toy_content        varchar(1000)    NOT NULL COMMENT '宝贝内容',
    toy_amount         int(20)          NOT NULL COMMENT '宝贝价格',
    toy_alias_amount   int(20)          NOT NULL COMMENT '宝贝辅助价格',
    toy_views          int(20)          NOT NULL COMMENT '浏览量',
    toy_like_count     int(20)          NOT NULL COMMENT '点赞数',
    toy_comment_count  int(20)          NOT NULL COMMENT '评论数',
    toy_exchange_count int(20)          NOT NULL COMMENT '交换申请人数',
    toy_number         int(20)          NOT NULL COMMENT '宝贝数量',
    create_time datetime(3)             NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    update_time datetime(3)             NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    delete_time datetime(3)             DEFAULT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY toy_no_del (toy_no, delete_time)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;
-- ----------------------------
-- 宝贝-类别中间表
-- ----------------------------
DROP TABLE IF EXISTS itoy_toy_sort;
CREATE TABLE itoy_toy_sort
(
    id      int(10) unsigned NOT NULL AUTO_INCREMENT,
    toy_id int(10) unsigned NOT NULL COMMENT '宝贝id',
    sort_id int(10) unsigned NOT NULL COMMENT '分类id',
    PRIMARY KEY (id),
    KEY toy_id_sort_id (toy_id, sort_id) USING BTREE COMMENT '联合索引'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;
-- ----------------------------
-- 宝贝-用户中间表
-- ----------------------------
DROP TABLE IF EXISTS itoy_toy_user;
CREATE TABLE itoy_toy_user
(
    id      int(10) unsigned NOT NULL AUTO_INCREMENT,
    toy_id  int(10) unsigned NOT NULL COMMENT '宝贝id',
    user_id int(10) unsigned NOT NULL COMMENT '分类id',
    status  int(10) unsigned NOT NULL COMMENT '宝贝状态',
    remark  varchar(100)     DEFAULT NULL  COMMENT '备注',
    PRIMARY KEY (id),
    KEY toy_id_user_id (toy_id, user_id) USING BTREE COMMENT '联合索引'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;
-- ----------------------------
-- 交换订单表
-- ----------------------------
DROP TABLE IF EXISTS itoy_exchange_order;
CREATE TABLE itoy_exchange_order
(
    id               int(18) unsigned NOT NULL AUTO_INCREMENT,
    order_no         varchar(100)     NOT NULL COMMENT '订单编号',
    request_toy_id   int(18)          NOT NULL COMMENT '申请宝贝',
    request_user_id  int(18)          NOT NULL COMMENT '申请用户',
    exchange_user_id int(18)          NOT NULL COMMENT '交换用户',
    exchange_toy_id  int(18)      DEFAULT NULL COMMENT '交换宝贝',
    status           int(18)          NOT NULL COMMENT '订单状态',
    remark           varchar(255) DEFAULT NULL COMMENT '订单描述',
    create_time      datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP (3),
    update_time      datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP (3) ON UPDATE CURRENT_TIMESTAMP (3),
    delete_time      datetime(3) DEFAULT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY order_no_del (order_no, delete_time)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;
