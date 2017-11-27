<?php

$arr = array(
	'id' => 1,
	'name' => 'apeng'
	);

// 方法 json_encode($val) 该函数只能接受utf-8 编码 否则返回空
echo json_encode($arr);
echo "<br/>";
$data = "只输出utf-8的json数据";
echo $data;
$str = iconv("UTF-8", "GBK", $data);
echo json_encode($str);