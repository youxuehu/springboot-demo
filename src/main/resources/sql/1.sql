create table score1
(id string comment 'ID',
name string comment 'name',
Chinese double comment 'Chinese',
English double comment 'English',
math double comment 'math',
school string comment 'school',
class string comment 'class')
comment 'score1'
row format delimited fields terminated by ','
stored as textfile;

create table score
(id string comment 'ID',
name string comment 'name',
Chinese double comment 'Chinese',
English double comment 'English',
math double comment 'math')
comment 'score'
partitioned by(school string,class string)
row format delimited fields terminated by ','
stored as textfile;



load data inpath '/score.txt' into table score partition (school="school1",class="class1");