<?php

namespace pattern;

class Database
{
	private function __construct(){

	}

	static function getInstance(){
		if (self::$db) {
			return self::$db;
		} else {
			self::$db = new self();
			return self::$db;
		}
	}
}