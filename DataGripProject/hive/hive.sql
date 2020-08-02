create table userinfo
(
    id bigint,
    name string,
    age int,
    email string,
    address string,
    telephone string ,
    sex string,
    gmt_create string,
    gmt_update string,
    is_deleted int
)partitioned by (day string)
row format delimited fields terminated by ',' stored as textfile;

