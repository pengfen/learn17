<?php

/**
     redis 的安装

     windows 上的安装
     https://pecl.php.net/package/redis/2.2.7/windows
     查找对应的 php 版本及对应的 windows 版本 (64位及32位)
     Non Thread Safe (NTS) 非线程安全与 IIS 搭配环境
     Thread Safe (TS) 线程安全与 apache 搭配环境
     将下载解压后的 php_redis.dll 复制到 php/ext 文件目录下
     修改 php.ini 文件 打开 php_redis.dll 扩展
     重启服务器
     程序中使用 phpinfo() 检测
*/