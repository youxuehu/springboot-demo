create 'uu1','class'
-- 插入一行
put 'uu1','key1','class:name','zhangsan'
put 'uu1','key1','class:age','20'
put 'uu1','key1','class:gender','male'


put 'uu1','key2','class:name','lisi'
put 'uu1','key2','class:age','30'
put 'uu1','key2','class:gender','female'

put 'uu1','key3','class:name','wangwu'
put 'uu1','key3','class:age','40'
put 'uu1','key3','class:gender','male'


get 'uu1'
get 'uu1','key'
scan 'uu1'

-- 删除一行
delete 'uu1','key3','class:name'
delete 'uu1','key3','class:age'
delete 'uu1','key3','class:gender'
