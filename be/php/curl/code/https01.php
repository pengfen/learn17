
<?php
/**
 * 慕课网视频教学
 * 代码实例-PHP-cURL实战
 * 实例描述：下载网络上面的一个HTTPS的资源
 */
$curlobj = curl_init();			// 初始化
curl_setopt($curlobj, CURLOPT_URL, "https://ajax.aspnetcdn.com/ajax/jquery.validate/1.12.0/jquery.validate.js");		// 设置访问网页的URL
curl_setopt($curlobj, CURLOPT_RETURNTRANSFER, true);			// 执行之后不直接打印出来

// 设置HTTPS支持
date_default_timezone_set('PRC'); // 使用Cookie时，必须先设置时区
curl_setopt($curlobj, CURLOPT_SSL_VERIFYPEER, 0); // 对认证证书来源的检查从证书中检查SSL加密算法是否存在
curl_setopt($curlobj, CURLOPT_SSL_VERIFYHOST, 2); // 

$output=curl_exec($curlobj);	// 执行
curl_close($curlobj);			// 关闭cURL
echo $output;
?>