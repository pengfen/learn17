<?php

// http://www.app.com/list.php?page=1&pagesize=12
require_once('../basic/format.php');
require_once('../core/cache.php'); // 加载缓存文件

$file = new File();
$data = $file->cacheData('index_cache');
if ($data) {
	return Response::show(200, '首页数据获取成功', $data);
} else {
	return Response::show(400, '首页数据获取失败', $data);
}
exit;

$page = isset($_GET['page'])?$_GET['page']:1;
$pageSize = isset($_GET['pagesize'])?$_GET['pagesize']:1;

if (!is_numeric($page) || !is_numeric($pagesize)) {
	return Response::show(401, '数据不合法', '', 'xml');
}

$offset = ($page - 1) * $pagesize;
$sql = "select * from areas";
