<?php

/**
  * 一 读取数据库方式开发首页接口
  * 从数据库获取信息 ---> 封装 ---> 生成接口数据
  * 应用场景 数据 时效性 比较高的系统
  * 
  * 二 读取缓存方式开发首页接口
  * 从数据库获取信息 ---> 封装 (---> 缓存 ---> 再次请求) ---> 返回数据
  * 应用场景 减少数据库压力
  *
  * 定时读取缓存方式开发首页接口
  * 数据库 ---> crontab ---> 缓存
  * http 请求 ---> 缓存 ---> 封装并返回数据
  *
  * 掌握如何获取数据
  * 掌握如何将获取的数据生成通信数据
  *
  * 分析部分 App 首页
  * 安装 start bluestacks 安卓模拟器
  * 
  * http请求 ---> 服务器 ---> 查询数据 ---> 返回数据
  * 
*/