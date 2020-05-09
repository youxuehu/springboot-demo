zhangsan    math:90,english:60
lisi    chinese:80,math:66,english:77
wangwu  chinese:66,math:55,english:80



--作业，返回每一门课程和对应的最高分的学生的姓名

--按照\t分割
zhangsan	math:90,english:60
lisi	chinese:80,math:66,english:77
wangwu  chinese:66,math:55,english:80

--两步：
1.lateral view explode
2.ROW_NUMBER() over

create table student_score_tmp1 (
      name string,
      scores string
)
ROW FORMAT delimited
fields terminated by '\t'
COLLECTION ITEMS TERMINATED BY ','
stored as textfile
location '/student_score_tmp';
;

insert overwrite directory "/tmp/out1/"
select course,name, score from
(
    select b.course, b.name, b.score,
    ROW_NUMBER() over (partition by course order by score desc) rowNum
    from
    (
        select s.name, split(a,':')[0] as course, split(a,':')[1] as score
        from student_score_tmp1 s lateral view explode(split(s.scores,',')) t as a
    )b
) c
where c.rowNum=1;



create table student_score_tmp (
      name string,
      scores Map<string, int>
)
ROW FORMAT delimited
fields terminated by '\t'
COLLECTION ITEMS TERMINATED BY ','
MAP KEYS TERMINATED BY ':'
stored as textfile
location '/student_score_tmp';

select course,name,score
from
(select name,course,score,ROW_NUMBER() over (partition by course order by score desc) rowNum
    from student_score_tmp
    lateral view explode(scores) s as course,score
)a
where rowNum=1
;