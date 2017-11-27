<?php

namespace pattern;

// 注册树模式 
class Register
{
	protected static $objects;

	static function set($alias, $object)
	{
		self::$objects[$alias] = $object;
	}

	function _unset($alias)
	{
		unset(self::$objects[$alias]);
	}
}