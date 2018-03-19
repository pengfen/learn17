#!/usr/bin/php
<?php

/**
  * 学科日志信息
  * 日期(年月日时分秒) 学科地址　　　　　　　
  * 20160321102628	http://java.cn/java/course/hadoop.shtml
  */

  $out = "/home/ricky/data/spark/rdd/subject.log";
  function gen_subject($num) {
      $domain = ["http://java.cn/java/course/", "http://net.cn/net/", "http://php.cn/php/course/", "http://spark.cn/spark/course/"];
      $subject = [["java.html", "javaee.html", "android.html"],
      ["teacher.html", "net.html", "video.html"],
      ["linux.html", "php.html", "nginx.html", "mysql.html"],
      ["spark.html", "hadoop.html", "flume.html", "kafka.html", "zookeeper.html"]];
      $info = "";
      for($i = 0; $i < $num; $i ++) {
          $index = array_rand($domain);
          $time = date("YmdHis", time());
          $info .= $time."\t".$domain[$index].$subject[$index][array_rand($subject[$index])].PHP_EOL;
      }
      return $info;
  }

  $info = gen_subject(500);
  file_put_contents($out, $info, FILE_APPEND);