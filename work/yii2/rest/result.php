<?php

/**
    测试平台 ---> 正式平台

    actionSeller 原始数据

    注意开始时间 失效时间 对时间的处理
*/
    // 设置时区
    date_default_timezone_set("PRC");
    // 开始时间现在
    $startTime = strtotime('now');
    // 结束时间
    $endTime = strtotime("3 October 2037");
    // 数据
    $goods = array('effectStartTime' => date('YmdHis', $startTime),
			'effectEndTime' => date('YmdHis', $endTime)
			);


  