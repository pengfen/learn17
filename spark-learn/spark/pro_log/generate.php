#!/usr/bin/php
<?php
//100.10.143.167	2018-03-19 10:10:31	"GET /www/1 HTTP/1.1"	https://search.yahoo.com/search?p=极限挑战	404

$date = date("Ymd", time());
$input = "/home/ricky/data/spark/pro/access_$date.log";

# 分类数组
$url_paths = [
    "www/1",
    "www/2",
    "www/3",
    "www/4",
    "www/6",
    "pianhua/130",
    "toukouxu/821"
];

#ip数组
$ip_slices = [132, 156, 124, 10, 29, 167, 143, 187, 30, 100];

#状态码数组
$status_code = [404, 302, 200];

#搜索跳转
$http_referers = [
    "https://www.baidu.com/s?wd=",
    "https://www.sogou.com/web?qu=",
    "http://cn.bing.com/search?q=",
    "https://search.yahoo.com/search?p="
];

#搜索关键词
$search_keyword = [
    "猎场",
    "快乐人生",
    "极限挑战",
    "我的体育老师",
    "幸福满院"
];

$info['url'] = $url_paths;
$info["ip"] = $ip_slices;
$info["status"] = $status_code;
$info["refer"] = $http_referers;
$info["search"] = $search_keyword;

function gen_log($info, $num = 10) {
    //100.10.143.167	2018-03-19 10:10:31	"GET /www/1 HTTP/1.1"	https://search.yahoo.com/search?p=极限挑战	404

    $data = "";
    for($i = 0; $i < $num; $i ++) {
        // 1. 处理ip
        $ip = $info["ip"];
        for($j = 0; $j < 4; $j ++) {
            $data .= $ip[array_rand($ip)];
            if ($j < 3) $data .= ".";
        }

        // 2. 处理时间
        $time = date("Y-m-d H:i:s", time());
        $data .= "\t".$time;

        // 3. 处理请求ip
        $url = $info["url"];
        $type = $url[array_rand($url)];
        $data .= "\t"."\"GET ".$type." HTTP/1.1\"";

        // 4. 处理refer
        if (mt_rand(1, 10) < 3) {
            $data .= "\t-";
        } else {
            $referers = $info["refer"];
            $refer = $referers[array_rand($referers)];
            $search = $info["search"];
            $keyword = $search[array_rand($search)];
            $data .= "\t".$refer.$keyword;
        }

        // 5. 处理status
        $status = $info["status"];
        $data .= "\t".$status[array_rand($status)].PHP_EOL;
    }

    return $data;
}
$data = gen_log($info, 100);
file_put_contents($input, $data, FILE_APPEND);
