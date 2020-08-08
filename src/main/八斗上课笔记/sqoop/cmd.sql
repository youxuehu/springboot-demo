-- 查看所有表
sqoop list-tables --connect jdbc:mysql://localhost:3306/hive_db --username root --password zhf123.. --driver com.mysql.cj.jdbc.Driver

-- hive表导入到mysql表
create table if not exists nginx_pv (
    request_path text,
    visit_count bigint
);
create table if not exists nginx_uv (
    ip varchar(20)
);

sqoop export --connect jdbc:mysql://localhost:3306/test --username root --password zhf123.. --table pv --export-dir /usr/hive/warehouse/nginx_pv --input-fields-terminated-by '\t'
sqoop export --connect jdbc:mysql://localhost:3306/test --username root --password zhf123.. --table uv --export-dir /usr/hive/warehouse/nginx_uv --input-fields-terminated-by '\t'