<?php

require_once('./format.php');

$data = array(
	'id' => 1,
	'name' => 'apeng',
	'type' => array(1, 2, 3),
	'test' => array(1, 2, 3 => array(123, 'type'))
	);
Response::show(200, 'success', $data, 'array');