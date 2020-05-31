show databases;
show tables;
create table user(id bigint primary key auto_increment,
user_name varchar(255),user_password varchar(266),gmt_create datetime,gmt_update datetime,
is_delete tinyint(1));