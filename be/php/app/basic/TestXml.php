<?php
require_once('./info_format.php');

$data = array(
	'id' => 1,
	'name' => 'apeng',
	'type' => array(1, 2, 3)
	);
Response::xmlEncode(200, 'success', $data);