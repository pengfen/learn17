<?php

/**
    Unix 网络编程
    select setream_select / socket_select
    nginx memcache libevent & epoll 异步的核心
    PHP 的 libevent 扩展 event 扩展

    PHP 实现 Server 的好处是什么
    普通 LAMP程序 PHP的所有对象请求时创建 请求结束时全部销毁 对于普通 PHP 程序来说避免了内存 漏 对于大型网站来说 这是严重的资源浪费

    PHP-Server 中每次请求仅销毁与请求相关的对象 与请求无关的全局对象都不需要销毁 直接在下一次请求中复用 比如一个大数组 PHP-fpm 每次都要构建 HashTable 而 PHP-Server 程序完全不需要

    PHPer 可以操控的范围更大了 局部缓存 Once 操作 写文件合并 长连接 对象持久化 数据库连接池等都可以做

    为什么要用 C 扩展实现

    性能 C 代码实现的类和函数比 PHP 性能强 10-100 倍
    内存 C 扩展中对内存的控制可以精确到 bit
    数据结构 C 扩展中可以针对不同的场景使用最优数据结构 而 PHP 代码中只有数组可用
    直接操作底层 不需要依赖大量第三方扩展 而且粒度更小 如基于 pcntl 实现的信号处理 必须依赖 PHP 的 tick 机制 性能很差
    原子操作 C 语言可以使用 atomic 操作实现处旋锁 原子自增 / 自减
*/

    // 异步
    function read_cd($socket, $flag, $base) {
    	fread($socket);
    	fwrite("hello world\n");
    }

    function accept_cb($socket, $flag, $base) {
    	$conn = stream_socket_accept($socket, 0);
    	stream_set_blocking($conn, 0);
    	$event = event_new();
    	event_set($event, $conn, (EV_READ | EV_PERSIST, 'read_cb'), $base);
    	event_base_set($event, $base);
    	event_add($event);
    }

    $serv = stream_socket_server("tcp://0.0.0.0:8000", $errno, $errstr);
    for ($i = 0; $i < 8; $i ++) {
    	if (pcntl_fork() == 0) {
    		$base = event_base_new();
    		$event = event_new();
    		event_set($event, $socket, (EV_READ | EV_PERSIST, 'read_cb'), $base);
    		event_add($base);
    		event_base_loop($base);
    		exit(0);
    	}
    }