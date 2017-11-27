<?php

require_once('./info_format.php');
$arr = array(
	'id' => 1,
	'name' => 'apeng'
	);

Response::json(200, '数据返回成功', $arr);