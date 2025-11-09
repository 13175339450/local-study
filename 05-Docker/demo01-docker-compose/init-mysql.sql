USE docker_test;

CREATE TABLE `t_user` (
                          `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
                          `username` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '用户名',
                          `password` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '密码',
                          PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1114 DEFAULT CHARSET=utf8 COMMENT='用户表';