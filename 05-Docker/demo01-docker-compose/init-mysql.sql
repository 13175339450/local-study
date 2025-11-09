USE docker_test;

CREATE TABLE `t_user` (
                          `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
                          `username` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '用户名',
                          `password` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '密码',
                          `sex` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '性别 0=女 1=男',
                          `deleted` TINYINT(4) UNSIGNED NOT NULL DEFAULT '0' COMMENT '删除标志，默认0不删除，1删除',
                          `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                          `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                          PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1114 DEFAULT CHARSET=utf8 COMMENT='用户表';