<?php

/**
    curl 实战

    用 cURL 做一个简单的网页爬虫
    用 cURL 获取天气信息
    用 cURL 操作 FTP 服务器中的数据
    用 cURL 访问 HTTPS 资源
*/
    // cmd ---> 进入php 所在目录 ---> php -f XX.php
    // php -f XX.php > baidu.html
    $curl = curl_init("http://www.baidu.com");
    curl_exec($curl);
    curl_close($curl);