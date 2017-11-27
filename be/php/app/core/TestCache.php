<?php
require_once('./cache.php');

$data = array(
	'id' => 1,
	'name' => 'apeng',
	'type' => array(1, 2, 3),
	'test' => array(2, 3, 4 => array(100, 'type'))
	);

$file = new File();

// if ($file->cacheData('index_cache', $data)) { // 写入缓存
// 	echo 'success';
// } else {
// 	echo 'error';
// }

// if ($file->cacheData('index_cache')) { // 读取缓存
// 	var_dump($file->cacheData('index_cache')); exit;
// 	echo 'success';
// } else {
// 	echo 'error';
// }

if ($file->cacheData('index_cache', null)) { // 删除缓存
	echo 'success';
} else {
	echo 'error';
}