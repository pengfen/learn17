<?php
/**
 * 慕课网视频教学
 * 代码实例-PHP-cURL实战
 * 实例描述：通过调用WebService查询北京的当前天气
 * cmd
 * cd
 * php -f 文件名 > 另一文件名
 */
$data = 'theCityName=北京';
$curlobj = curl_init();	
curl_setopt($curlobj, CURLOPT_URL, "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx/getWeatherbyCityName");  
curl_setopt($curlobj, CURLOPT_HEADER, 0); 
curl_setopt($curlobj, CURLOPT_RETURNTRANSFER, 1);  
curl_setopt($curlobj, CURLOPT_POST, 1);  
curl_setopt($curlobj, CURLOPT_POSTFIELDS, $data);  // post 数据库 post 请求要设置请求头
curl_setopt($curlobj, CURLOPT_HTTPHEADER, array("application/x-www-form-urlencoded; charset=utf-8", 
	"Content-length: ".strlen($data)
	)); 
$rtn = curl_exec($curlobj);   
if(!curl_errno($curlobj)){
	// $info = curl_getinfo($curlobj); 
	// print_r($info);
	echo $rtn;  
} else {
  echo 'Curl error: ' . curl_error($curlobj);
}
curl_close($curlobj);
?>