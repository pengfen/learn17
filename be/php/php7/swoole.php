<?php

/**
    Swoole 扩展

    swoole_http_server + redis-async + mysql-async + tcp-client-async 编写多进程全异步的 Web 程序

    TCP-server + TCP-Client 实现 SOA 服务器 php-fpm 中使用 TCP-Client + select 实现并发请求 PHP 实现 4 层架构 服务化治理

    swoole_websocket_server 实现 WebIM 和 PUSH 系统

    基于异步任务实现非响应逻辑的异步化 如在Http 请求中发送邮件 发送短信
*/

    // 服务器端
    $serv = new swoole_server('127.0.0.1', 9501);

    $serv->on('connect', function($serv, $fd){
    	echo "Client: connected.\n";
    });

    $serv->on('receive', function($serv, $fd, $from_id, $data) {
    	$serv->send($fd, 'Swoole', $data);
    	$serv->close($fd);
    });

    $serv->on('close', function($serv, $fd) {
    	echo "Client: Closed.\n";
    });

    $serv->start();

    // 客户端
    $client = new swoole_client(SWOOLE_SOCK_TCP, SWOOLE_SOCK_ASYNC);

    $client->on('connect', function($cli) {
    	$cli->send("hello world\n");
    });

    $client->on('receive', function($cli, $data) {
    	echo "Received: ".$data."\n";
    });

    $client->on("error", function($cli) {
    	echo "Connect failed\n";
    });

    $client->on("close", function($cli) {
    	echo "Connection close\n";
    });

    $client->connect("127.0.0.1", 9501, 0.5);

    // 异步 MySQL
    $config = array(
    	'host' => '127.0.0.1',
    	'user' => 'root',
    	'password' => 'root',
    	'database' => 'test',
    	);

    $pool = new Swoole\Async\MySQL($config, 100);
    for ($i = 0; $i < 10000; $i ++) {
    	$pool->query("show tables", function($mysqli, mysqli_result $result) {
    		var_dump($result->fetch_all());
    	});
    }

    // 异步 Redis
    require __DIR__.'/src/Swoole/Async/RedisClient.php';
    $redis = new Swoole\Async\RedisClient('127.0.0.1');

    $redis->select('2', function() use ($redis) {
    	$redis->set('key', 'value-rango', function ($result, $success) use ($redis) {
    		for ($i = 0; $i < 3; $i ++) {
    			$redis->get('key', function ($result, $success) {
    				echo "redis ok:\n";
    				var_dump($success, $result);
    			})
    		}
    	})
    })