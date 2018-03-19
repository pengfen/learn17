#!/usr/bin/php
<?php

/**
  * 用户日志信息
  * 手机号       时间(年月日时分秒) 基站标识(32位)　　　　　　　　　　　 １表示进入基站 0表示离开基站
  * 18688888888,20160327082400,16030401EAFB68F1E3CDF819735E1C66,1
  */

  $date = date("Ymd", time());
  $in_file = "/home/ricky/data/spark/basic/base.log";
  $out_file = "/home/ricky/data/spark/basic/bs_user_$date.log";
  function gen_user_log($num, $bs) {
      $info = "";
      for($i = 0; $i < $num; $i ++) {
          // 1. 随机生生手机号
          $mobile = 1;
          for($j = 0; $j < 10; $j ++) {
              $number = mt_rand(1, 9);
              if ($j == 0 && in_array($number, [1, 2, 9])) $number = mt_rand(3, 8);
              $mobile .= $number;
          }
          // 2. 处理时间
          $curr = time();
          $time = date("YmdHis", $curr);
          // 3. 随机取基站号
          $base_num = $bs[array_rand($bs)];
          // 4. 处理离开基站时间
          $interval = mt_rand(10000, 99999);
          $btime = date("YmdHis", ($curr + $interval));
          $info .= $mobile.",".$time.",".$base_num.","."1".PHP_EOL
          .$mobile.",".$btime.",".$base_num.","."0".PHP_EOL;
      }
      return $info;
  }

  // 处理基站数据
  $base = file_get_contents($in_file);
  //5c052e1a732279537aa212523bfd5a2a,72.124584,15.187978
  $base = explode("\n", $base);
  $bs = [];
  foreach($base as $item) {
      if (!$item) continue;
      $val = explode(",", $item);
      $bs[] = $val[0];
  }

  $info = gen_user_log(500, $bs);
  file_put_contents($out_file, $info, FILE_APPEND);