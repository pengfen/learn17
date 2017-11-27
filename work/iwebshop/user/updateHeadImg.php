<?php

if ($picname) {
	// 如果有名字则上传成功
	$user_id = $this->user['user_id'];
	$user_obj = new IModel('user');
	$data = array(
		'head_ico' => $picname
	);
	$user_obj->setData($data);
	$where = 'id = '.$user_id;
	$res = $user_id->update($where);
	echo json_encode($res);
}


// 如果有名字则上传成功
$user_id = $this->user['user_id'];
$user_obj = new IModel('user');
$data = array(
	'head_ico' => $picname
);
$user_obj->setData($data);
$where = 'id = '.$user_id;
$res = $user_id->update($where);
if ($res) {
	$msg = '上传成功';
} else {
	$msg = '上传失败';
}

// PHP上传失败 ---> 获取错误信息
if (!empty($_FILES[$img]['error'])) {
	$error = $this->getUploadError($_FILES[$img]['error']);
}