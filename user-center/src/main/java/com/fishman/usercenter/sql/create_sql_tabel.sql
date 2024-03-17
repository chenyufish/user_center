-- auto-generated definition
create table user
(
    id           bigint auto_increment comment 'id'
        primary key,
    username     varchar(256)                       null comment '用户昵称',
    userAccount  varchar(256)                       null comment '账号',
    avatarUrl    varchar(1024)                      null comment '用户头像',
    gender       tinyint                            null comment '性别',
    userPassword varchar(512)                       not null comment '密码',
    phone        varchar(128)                       null comment '电话',
    email        varchar(512)                       null comment '邮箱',
    userStatus   int      default 0                 not null comment '状态 0 - 正常',
    createTime   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint  default 0                 not null comment '是否删除（逻辑删除）',
    userRole     int      default 0                 not null comment '用户权限 0- 普通用户 1 - 管理员',
    planetCode   varchar(512)                       null comment '星球编号'
);


# 这是mariadb的建表语言
# -- CREATE TABLE user (
#                       id bigint auto_increment primary key comment 'id',
#                       username varchar(256) null comment '用户昵称',
#                       userAccount varchar(256) null comment '账号',
#                       avatarUrl varchar(1024) null comment '用户头像',
#                       gender tinyint null comment '性别',
#                       userPassword varchar(512) not null comment '密码',
#                       phone varchar(128) null comment '电话',
#                       email varchar(512) null comment '邮箱',
#                       userStatus int default 0 not null comment '状态 0 - 正常',
#                       createTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP null comment '创建时间',
#                       updateTime TIMESTAMP null comment '更新时间',
#                       isDelete tinyint default 0 not null comment '是否删除（逻辑删除）',
#                       userRole int default 0 not null comment '用户权限 0- 普通用户 1 - 管理员',
#                       planetCode varchar(512) null comment '星球编号'
# );
#
# DELIMITER //
# CREATE TRIGGER set_updateTime BEFORE UPDATE ON user_center.user
#     FOR EACH ROW
# BEGIN
#     SET NEW.updateTime = CURRENT_TIMESTAMP;
# END//
# -- DELIMITER ;


