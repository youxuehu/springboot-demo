create table db_save_front(
    id bigint primary key not null auto_increment,
    key_string varchar(255) not null,
    value_string varchar(511) not null,
    gmt_create timestamp null default current_timestamp,
    gmt_update timestamp null
);

create table permission(
    id bigint primary key not null auto_increment,
    userNumber varchar(255)
);

create table execution_log(
    id bigint primary key not null auto_increment,
    gmt_create timestamp,
    job_id varchar(32),
    content text
);

create table admin_operate_log(
    id bigint primary key not null auto_increment,
    operator varchar(255),
    gmt_create timestamp,
    content text
);
create table zk_data(
    id bigint primary key not null auto_increment,
    gmt_create datetime,
    gmt_update datetime,
    root varchar(128),
    path varchar(256),
    data longblob
);