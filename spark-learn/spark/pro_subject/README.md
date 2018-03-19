根据日志统计出每天站点所呆时间最长的前十个用户

1. 编写日志产生脚本 gen_subject.php(产生用户日志)

2. 编写定时任务每分钟产生五百条
*/1 * * * *  /usr/bin/php /home/ricky/script/spark/rdd/gen_subject.php

3. 编写脚本每天晚零点将产生的日志移动到某目录
59 23 * * *  /home/ricky/script/spark/rdd/move_subject.sh

4. 编写代码进行清洗 UrlCountClean
参考
[UrlCount](https://github.com/pengfen/spark-learn/blob/master/src/main/scala/spark/rdd/UrlCount.scala)
[AdvUrlCount](https://github.com/pengfen/spark-learn/blob/master/src/main/scala/spark/rdd/AdvUrlCount.scala)
[UrlCountPartition](https://github.com/pengfen/spark-learn/blob/master/src/main/scala/spark/rdd/UrlCountPartition.scala)

6. 编写实体类 User

7. 编写dao UserDao

6. 入库 DataLoad