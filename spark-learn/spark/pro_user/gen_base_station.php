#!/usr/bin/php
<?php

/**
  * 基站日志信息
  * 基站标识                          经度        纬度
  * 9F36407EAD0629FC166F14DDE7970F68,116.304864,40.050645,6
  * 数据源 /home/ricky/data/spark/basic/base.log
  */
    // 元数据
    $arr = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    , 1, 2, 3, 4, 5, 6, 7, 8, 9];
    $num = [1, 2, 3, 4, 5, 6, 7, 8, 9];

    $content = '';
    // 随机生成100个基站标识
    for($i = 0; $i < 100; $i ++) {
        $base = ''; // 基站标识
        for($j = 0; $j < 6; $j ++) {
            $base .= $arr[array_rand($arr)];
        }
        $longitude = ''; // 经度
        $latitude = ''; // 纬度
        for($k = 0; $k < 9; $k ++) {
            $longitude .= $num[array_rand($num)];
            $latitude .= $num[array_rand($num)];
            if ($k == 2) {
                if (intval($longitude) > 360) $longitude = substr($longitude, 1);
                if (intval($latitude) > 360) $latitude = substr($latitude, 1);
                $longitude .= ".";
                $latitude .= ".";
            }
        }
        $content .= md5($base).",".$longitude.",".$latitude.PHP_EOL;
    }
    $file = "/home/ricky/data/spark/basic/base.log";
    file_put_contents($file, $content);