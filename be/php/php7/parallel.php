<?php

/**
    一个 PHP Web 程序的执行过程

    请求开始 (Get/Post/Cookie/Session) ---> MySql 数据库查询/Redis 查询 ---> 模板渲染输出 HTML/json_encode ---> 请求结束 (回收所有内存和资源)

    Web 服务也只是整个服务端的一部分而已 Web 之外还有 FTP 文件服务 SMTP 邮件 聊天 PUSH 消息等

    扩展
    底层通信
    stream 
    sockets
    异步 并发
    libevent/event
    pcntl/posix
    共享内存 消息队列
    pthread
    sysvsem/sysvmsg
    shmop/sysvhm
*/

    // 第一个 Server 阻塞 + fork 子进程
    $serv = stream_socket_server("tcp://0.0.0.0:8000", $errno, $errstr) or die("create server failed");

    while(1){
    	$conn = stream_socket_accept($serv);
    	if (pcntl_fork() == 0) {
    		$request = fread($conn);
    		// do some thing
    		// $response = "hello world"
    		fwrite($response);
    		fclose($conn);
    		exit(0);
    	}
    }

    // 第二个 Server 改良版
    $serv = stream_socket_server("tcp://0.0.0.0:8000", $errno, $errstr) or die("create server failed");

    for ($i = 0; $i < 32; $i ++) { 
   	    if (pcntl_fork() == 0) {
   	    	while (1) {
   	    		$conn = stream_socket_accept($serv);
   	    		if ($conn == false) continue;
   	    		$request = fread($conn);
	    		// do some thing
	    		// $response = "hello world"
	    		fwrite($response);
	    		fclose($conn);
   	    	}
   	    	exit(0);
   	    }
    }
    