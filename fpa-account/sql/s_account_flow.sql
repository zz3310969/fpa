CREATE TABLE s_account_flow
(
    id bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    serial_number varchar(255),
    user_id bigint(20) COMMENT '用户ID',
    account_id bigint(20) COMMENT '账户id',
    amount int(11) COMMENT '金额',
    create_time datetime COMMENT '开始时间',
    last_time datetime COMMENT '完成时间',
    remark varchar(255) COMMENT '备注',
    last_balance int(11) COMMENT '剩余金额',
    tag1 varchar(255),
    tag2 varchar(255),
    tag3 varchar(255),
    tag4 varchar(255),
    tag5 varchar(255)
);
