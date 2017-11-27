<?php
$curl=curl_init('http://www.baidu.com'); // 初始化
curl_exec($curl); // 执行
curl_close($curl); // 关闭

/**
    cmd
    cd 进入文件所在目录
    php -f 文件名

    把网页抓取下来写入某个文件
    php -f 文件名 > 另一文件名
    进入文件所在目录 打开文件
*/
?>