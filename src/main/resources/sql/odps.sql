
CREATE TABLE `test_table` (
  `test_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `test_name` varchar(255) NOT NULL,
  `gmt_create` date DEFAULT NULL,
  `gmt_update` date DEFAULT NULL,
  PRIMARY KEY (`test_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=latin1


-----
CREATE TABLE `odps` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tableName` varchar(255) DEFAULT NULL,
  `description` text,
  `userName` varchar(255) DEFAULT NULL,
  `gmt_create` date DEFAULT NULL,
  `gme_update` date DEFAULT NULL,
  `is_delete` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10002 DEFAULT CHARSET=latin1


-- 批量插入随机数
delimiter $$
drop procedure if exists odps_pre;
delimiter $$
create procedure odps_pre() begin declare i int;
set
i = 10;
while i<10000 do
INSERT
	INTO
	db1.odps (tableName,
	description,
	userName,
	gmt_create,
	gme_update,
	is_delete)
VALUES(CONCAT('test',i),
CONCAT('desc',i),
CONCAT('',(SELECT CEILING(RAND()*900000+100000))),
NOW(),
NOW(),
0);
set
i = i + 1;

end while;

end
$$

-- 执行存储过程
call odps_pre();



-- 随机生成6位随机数
SELECT CEILING(RAND()*900000+100000);



SELECT id, tableName, description, userName, gmt_create, gme_update, is_delete
FROM db1.odps;


-- 插入测试数据
INSERT
	INTO
	db1.odps (tableName,
	description,
	userName,
	gmt_create,
	gme_update,
	is_delete)
VALUES('ODPS520289',
'this is a description!',
'WB000000',
NOW(),
NOW(),
0);