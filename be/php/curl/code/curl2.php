<?php

/**
    在网络上下载一个网页并把内容中的 百度 替换为 吊丝 之后输出
*/

    $curlobj = curl_init();
    curl_setopt($curlobj, CURLOPT_URL, "http://www.baidu.com");
    curl_setopt($curlobj, CURLOPT_RETURNTRANSFER, true);
    // 执行之后不直接打印出来
    $output = curl_exec($curlobj);
    curl_close($curlobj);
    echo str_replace('百度', '吊丝', $output);