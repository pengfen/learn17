<?php

/**
    魔法函数
*/

class test{
	private $var = "123";
	public function __get($varname) {
		return $this->var;
	}
}

$i = 0;
while($i < 10000) {
	$i ++;
	$test = new test();
	$test->var;
}