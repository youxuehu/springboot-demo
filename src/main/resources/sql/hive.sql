CREATE TABLE test001 (
  id                int,
  name              string
)
CLUSTERED BY (id) INTO 2 BUCKETS STORED AS ORC
TBLPROPERTIES ("transactional"="true",
  "compactor.mapreduce.map.memory.mb"="2048",     -- specify compaction map job properties
  "compactorthreshold.hive.compactor.delta.num.threshold"="4",  -- trigger minor compaction if there are more than 4 delta directories
  "compactorthreshold.hive.compactor.delta.pct.threshold"="0.5" -- trigger major compaction if the ratio of size of delta files to
                                                                   -- size of base files is greater than 50%
);

insert into test001 VALUES(1,'tiger');
insert into test001 VALUES(2,'tom');
update test001 set name = 'jack' where id = 1;
delete from test001 where id = 2;


drop table userinfos;