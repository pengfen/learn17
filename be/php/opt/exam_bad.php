<?php

// 准备两个数组 数组内容随机
$array_1 = array();
$array_1 = array();

for ($i = 0; $i < rand(1000, 2000); $i ++) {
	$array_1[] = rand();
}

for ($i = 0; $i < rand(1000, 2000); $i ++) {
	$array_2[] = rand();
}

// 将两个数组合并为一个数组
// 重复的元素仅保存一次
$array_merged = array();

/**

   先将数组1 逐个加入到目标数组中 之后 遍历数组2 对数组2 内每个元素
   看看是否在数组1中存在

   若存在 忽略该元素 若不存在 将该元素添加进去
*/

foreach($array_1 as $v) {
	$array_merged[] = $v;
}

foreach($array_2 as $v) {
	if (!in_array($v, $array_merged)) {
		$array_merged[] = $v;
	}
}

// $array_merged 就是我们最终的目标数组
var_dump( $array_1, $array_2, $array_merged);

