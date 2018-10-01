CREATE TABLE s_account
(
    id bigint(20) PRIMARY KEY NOT NULL COMMENT '主键' AUTO_INCREMENT,
    account_code varchar(255) COMMENT '账户编码',
    user_id bigint(20) COMMENT '用户ID',
    balance int(11) COMMENT '余额',
    freeze_balance int(11) COMMENT '冻结余额',
    account_type varchar(255) COMMENT '账户类型',
    create_time datetime COMMENT '创建时间',
    account_non_locked int(11) COMMENT '是否未过期',
    enabled int(11) COMMENT '是否可用',
    third_part_id varchar(255) COMMENT '第三方账户'
);

ALTER TABLE s_account COMMENT = '账户';