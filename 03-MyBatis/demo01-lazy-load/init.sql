use
my_db;

create table t_user
(
    user_id   bigint primary key,
    username  varchar(10),
    course_id bigint
);

create table t_course
(
    course_id   bigint primary key,
    course_name varchar(10)
);

-- 往课程表插入数据（course_id 与 t_user 的 course_id 关联）
INSERT INTO t_course (course_id, course_name)
VALUES (101, '数学'),
       (102, '语文'),
       (103, '英语'),
       (104, '编程');

-- 往用户表插入数据（course_id 对应上面的课程ID）
INSERT INTO t_user (user_id, username, course_id)
VALUES (1, '张三', 101),
       (2, '李四', 102),
       (3, '王五', 103),
       (4, '赵六', 101), -- 张三和赵六选同一门数学
       (5, '孙七', 104);