<?php

/**
    手机短信的发送

    获取手机号 ---> 保存在数组中
    获取随机动态码 ---> 保存在参数数组中 用于替换模板中的参数
    调用发送方法 

    {
	  "mobiles": ["12345678901"],
	  "template": "YZM",
	  "template_params":
	  {
	    "code": "1234"
	  }
	}

	发送后回调
	$res = json_decode($result);
	if ($res->code == '000') {
	    return 'success';
	}

	如果不能进行 json_decode 如何处理

	使用 strpos 来处理
	if (strpos($result, '000')) {
	    return "success";
	} else {
	    return 'fail';
	}
*/
    