<?php

/**

    千分位相关函数

    后端程序
	util::exchange($price) 自动将价格中补充千分位
	number_format($price, 2) 千分位格式化
	util:: restore($price) 去除价格中的千分位

	模板中使用 {echo:number_format($price, 2)}

	前端页面
	<script src="{theme:javascript/numeral.js}" type="text/javascript" ></script>
	引入 numeral.js 文件

	Numeral().unformat($price) 去除价格中的千分位
	Numberal($price).format(‘0,0.00’) 千分位格式化

	对于传入的对象 千分位格式化
	Test(numeral())
	Function test($num){
	$num.set($price);
	$num.format(‘0,0.00’); 
	}


*/