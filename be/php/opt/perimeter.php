<?php

/**
    周边

    减少文件类操作

    常用 PHP 场景的开销次序

    读写磁盘 读写数据库 读写内存 读写网络数据

    读写内存 << 读写数据库 < 读写磁盘 < 读写网络数据

    减少 PHP 发起的网络请求

    网络请求的坑

    对方接口的不确定因素
    网络稳定性

    设置超时时间
    连接超时
    读超时
    写超时

    将串行请求并行化
    使用 curl_multi_*()
    使用 swoole 扩展

    curl_multi_add_handle 向curl 批处理会话中添加单独的curl 句柄
    curl_multi_close 关闭一组 curl 句柄

    压缩 PHP 接口输出
    如何压缩 使用 Gzip 即可

    压缩输出的利与
    利 利于我们的数据输出 client 端能更快获取数据
       额外的 CPU 开销

    缓存重复计算内容

    什么情况下做输出内容的缓存
    多次请求 内容不变情况
    smarty 开启 caching

    旁路方案
    X.php ---> Process1 ---> Process2 ---> Process3 --->

    工具 XHPorf (源自 Fackbook 的 PHP 性能分析工具)

    通过分析 Wordpress 程序 做优化

    ab 压力测试
    vld opcode 代码分析 (php 扩展)

    PHP 性能瓶颈解决方法

    Opcode Cache: PHP 扩展 APC
    pecl.php.net php 扩展
    扩展实现  通过 PHP 扩展代替原 PHP 代码中高频逻辑

    Runtime 优化 HHVM
*/