<?php

$redis = new redis();
$redis->connect('192.168.233.130', 6379);
$redis->select(2);

// 
$redis->lpush('list', 'header1');
$redis->lpush('list', 'header2');

echo '列表长度是 : '.$redis->lsize('test');

$redis->rpush('list', 'tail1');
$redis->rpush('list', 'tail2');

echo '列表长度是 : '.$redis->llen('test');

$redis = Redis::connection('cache_nick'); // 连接数据库
// 向数据库中添加元素
var_dump($redis -> lPush('favorite_fruit','cherry'));
var_dump($redis -> lPush('favorite_fruit','banana'));

// 测试数据库中取出数据
$len = $redis -> lLen('favorite_fruit'); // 获取长度
for ($i = 1; $i <= $len; $i ++) {
    var_dump($redis -> rPop('favorite_fruit')); // 获取元素并移除
}
var_dump($redis -> lLen('favorite_fiuit')); // 如果长度为0 则退出
