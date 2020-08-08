create external table nginx_log
(
    ip string,
    request_time string,
    request_method string,
    request_path string,
    http_protocol string,
    response int ,
    byte_size string
)
row format delimited fields terminated by ',' stored as textfile
location "hdfs://localhost:9000/user/flume/nginx/nginx-log-clean/";

alter table nginx_log change column byte_size byte_size string;
-- 求 UV
select count(a.ip) from (select ip, count(ip) as ct from nginx_log group by ip) as a;
-- 求PV
select request_path, count(request_path) as ct from nginx_log group by request_path;


create table if not exists nginx_pv (
    request_path string,
    visit_count bigint
)row format delimited fields terminated by '\t' stored as textfile;
insert overwrite nginx_pv select request_path, count(request_path) as ct from nginx_log group by request_path;


-- hive 查询数据写入HDFS
set mapred.reduce.tasks = 1;
insert overwrite directory '/xx/xx/'
ROW FORMAT DELIMITED FIELDS TERMINATED BY '##'
select ip, count(ip) from nginx_log group by ip;