<?php

/**
  * 掌握如何设置定时任务常用命令
  * 掌握如何定时运行 PHP 程序
  *
  * 定时任务服务提供 crontab 命令来设定服务
  * crontab -e 编辑某个用户的 crontab 服务
  * crontab -l 列出某个用户的 crontab 服务
  * crontab -r 删除某个用户的 crontab 服务
  *
  * linux 下 sudo crontab -e 进入编辑定时任务
  * */1 * * * * /usr/bin/php /data/www/12.php 使用php 使用执行某个php 文件
  * 分 小时 日 月 星期 命令
  * 0-59 0-23 1-31 1-12 0-6 command
  * * 代表取值范围内的数字
  * / 代表每 比如每分钟等
  * sudo crontab -l
  * */1 * * * * php /data/www/cron.php 每分钟执行 cron.php
  * 50 7 * * * /sbin/service sshd start 每天 7:50 开启 ssh 服务
*/

/**
  * 如何设置每分钟插入数据到数据表中
  * 
  * cron.php 文件
  * <?php
  * $connect = mysql_connect('127.0.0.1', '', '');
  * mysql_select_db('test', $connect);
  * $sql = "insert into category('name', 'create_time' values('apeng', ".time()."))";
  * mysql_query($sql, $connect);
  * */1 * * * * /usr/bin/php /data/www/app/cron.php 
*/