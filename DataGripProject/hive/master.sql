-- DDL
-- 创建database
create database if not exists hive_2020;
-- 显示database
show databases;
--显示数据库详情
desc database extended hive_2020;
--修改hive_2020的parameters
alter database hive_2020 set dbproperties ('createtime'='20200531');

--创建表
--内部表：删除表，数据也会被删除
create table if not exists student(
    id int,
    name string
) row format delimited fields terminated by ',' stored as textfile
location 'hdfs://master:9000/hive/warehouse/student';

-- 外部表:删除表，表会删除，数据不会被删除
create external table if not exists person(
    id int, name string,gender int
) row format delimited fields terminated by ',';
-- load person
load data local inpath '/root/person.txt' into table person;
-- select .. from person
select * from person;
-- 倒入本地文件数据到表student
load data local inpath '/root/student.txt' into table student;
-- 倒入hdfs文件数据到hive, 注意：倒入成功后，hdfs文件/student.txt将被删除
load data inpath '/student.txt' into table student;
-- insert 一条数据，插入中文会乱码
insert into table student values (29,'汤姆');
-- 建议使用这种语法插入数据，中文不会乱码 insert ... select ... form table_name;
insert into table student select id,name from student;
insert into table student select 999,'刘邦' from student where id = 1;
insert into table student
select decode(binary("30"),'utf-8'),decode(binary('杰克'),'utf-8') from student where id = 999;
-- 查询表数据
select * from student;
-- 排序order by
select * from student order by id;
-- 设置reduce 个数为3个
set mapred.reduce.tasks = 3;
-- 分组 group by , 必须和聚合函数一起使用，不能单独使用
select count(1), id,name from student group by id, name;
-- 查询总数
select count(*) from student;
-- 表详情
desc  student;

-- group by 的map端聚合
set hive.map.aggr = true;
select count(1) from person;
-- 数据倾斜
set hive.groupby.skewdata = true;
set mapred.reduce.tasks = 3;
select name, sum(id) from student group by name;
-------------

-- 修改表属性
-- 修改student表为外部表 'EXTERNAL'='TRUE'
alter table student set tblproperties ('EXTERNAL'='TRUE');
-- 修改student表为内部表 'EXTERNAL'='FALSE'
alter table student set tblproperties ('EXTERNAL'='FALSE');

-- 创建分区表
create table if not exists student_partition(
    id int, name string
) partitioned by (day string) row format delimited
fields terminated by ',';
load data local inpath '/root/student_partition.txt'
into table student_partition partition (day='20200601');
select * from student_partition;
select * from student_partition where day = '20200601';
-- 增加分区
alter table student_partition add partition (day = '20200602');
alter table student_partition add partition (day = '20200603');
alter table student_partition add partition (day = '20200604');
-- 插入数据到分区
insert into table student_partition partition(day = 20200602) values (1,'model');
-- 删除分区
alter table student_partition drop partition (day = '20200604');

-- 创建多个分区
create table if not exists student_partition2(
    id int, name string
) partitioned by (month string,day string) row format delimited fields terminated by ',';

-- 修改表名
alter table student_partition2 rename to new_student_partition;
-- 增加列
alter table new_student_partition add columns (description string);
-- 修改列
alter table new_student_partition change column description new_description string;
-- 替换列.将原来的列删除， 创建新的列
alter table new_student_partition replace columns (key string,value string);
-- 删除表
drop table new_student_partition;
-- 插入数据 into 追加插入
insert into table new_student_partition partition (month='5',day='31') values
('name','张三'),('age','18');
insert into table new_student_partition partition (month='5',day='31') values
('name','abc'),('age','20');
select * from new_student_partition;
-- 插入数据 overwrite 覆盖插入
insert overwrite table new_student_partition partition (month='5',day='31') values
('name','abc'),('age','20');
-- 复制分区数据
alter table new_student_partition add partition (month='6',day='01');
from new_student_partition insert into table new_student_partition partition (month='6',day='01')
select key,value where month='5' and day='31';
-- 复制表
create table if not exists copy_student_partition as select key,value from new_student_partition;
select * from copy_student_partition;
-- 将查询结果导出到本地文件
insert overwrite local directory '/copy_student_partition.txt' select * from copy_student_partition;
-- 将查询结果导出到本地文件, 按照,格式
insert overwrite local directory '/copy_student_partition'
row format delimited fields terminated by '\t'
select * from copy_student_partition;
-- 将查询结果导出到HDFS, 去掉local就是HDFS路径, 按照,格式
insert overwrite directory '/copy_student_partition'
row format delimited fields terminated by '\t'
select * from copy_student_partition;
-- hadoop 命令导出到本地
dfs -ls /hive/warehouse/hive_2020.db/copy_student_partition/000000_0;
dfs -cat /hive/warehouse/hive_2020.db/copy_student_partition/000000_0;
dfs -get /hive/warehouse/hive_2020.db/copy_student_partition/000000_0 /root/copy_student_partition.txt;
-- hive 终端导出数据
hive -e 'select * from hive_2020.copy_student_partition;' > /root/hive_copy_student_partition.txt;
-- export 命令导出到HDFS上，主要用于Hadoop集群hive表数据迁移
export table copy_student_partition to '/export/copy_student_partition';
-- import 倒入表, 注意结尾不能加分号 ; 否则会报错
import table import_copy_student_partition from '/export/copy_student_partition/'

-----表关联-----
select * from student;
select * from person;
-- inner join ==  join
select a.id, a.name,p.gender from hive_2020.student a inner join
    hive_2020.person p on a.id = p.id;
-- left outer join == left join  outer可以省略 , 左表的数据全部查出来
select a.id, a.name,p.gender from hive_2020.student a left join
    hive_2020.person p on a.id = p.id;

-- right join 和 left join 相反
select a.id, a.name,p.gender from hive_2020.student a right join
    hive_2020.person p on a.id = p.id;
-- full outer join === full join 全外连接 等价于 left join 和right join加起来
select a.id, a.name,p.gender from hive_2020.student a full join
    hive_2020.person p on a.id = p.id;
-- 每个join会起一个map reduce

-- 注意： 多表连接时，建议将大表放在后面，可以提高查询效率

----------hive hbase 整合------------
-- 创建hbase表
create 'hbase_20200531',{NAME => 'f1',VERSIONS => 1},{NAME => 'f2',VERSIONS => 1},
{NAME => 'f3',VERSIONS => 1}
-- put
put 'hbase_20200531','lxw1234.com','f1:c1','name1'
put 'hbase_20200531','lxw1234.com','f1:c2','name2'
put 'hbase_20200531','lxw1234.com','f2:c1','age1'
put 'hbase_20200531','lxw1234.com','f2:c2','age2'
put 'hbase_20200531','lxw1234.com','f3:c1','job1'
put 'hbase_20200531','lxw1234.com','f3:c2','job2'
put 'hbase_20200531','lxw1234.com','f3:c3','job3'

-- hive hbase
SET hbase.zookeeper.quorum=master:2181,slave1:2181,slave2:2181;
SET zookeeper.znode.parent=/hbase;
ADD jar /usr/local/src/apache-hive-1.2.2-bin/lib/hive-hbase-handler-1.2.2.jar;
CREATE EXTERNAL TABLE hbase_20200531 (
    rowkey string,
    f1 map<STRING,STRING>,
    f2 map<STRING,STRING>,
    f3 map<STRING,STRING>
) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler'
WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,f1:,f2:,f3:")
TBLPROPERTIES ("hbase.table.name" = "hbase_20200531");
select * from hbase_20200531;