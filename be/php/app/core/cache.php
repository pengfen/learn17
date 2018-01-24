<?php
/**
  * 缓存技术
  * 静态缓存 ---> 保存在磁盘上的静态文件 用 PHP 生成数据放入静态文件中
  * memcache redis 缓存
  *
  * php 操作缓存
  * 生成缓存 获取缓存 删除缓存
*/

class File {

	private $_dir;

	const EXT = '.txt'; // 文件后缀名

	public function __construct(){
		$this->_dir = dirname(__FILE__).'/files/';
	}

	public function cacheData($key, $value = '', $path = '') {
		$filename = $this->_dir.$path.$key.self::EXT;

		if ($value !== '') { // 将value 值写入缓存
			if (is_null($value)){ // 删除缓存
				return unlink($filename);
			}

			$dir = dirname($filename);
			if (!is_dir($dir)) {
				mkdir($dir, 0777);
			}

			file_put_contents($filename, json_encode($value)); // 写入缓存
		}

		if (!is_file($filename)) { // 读取缓存
			return FALSE;
		} else {
			return json_decode(file_get_contents($filename), true);
		}
	}
}