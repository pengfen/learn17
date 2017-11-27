<?php

$email = $goodsResult['goodsList'][0]['sellerEmail']; // 获取商家邮件
$seller = $goodsResult['goodsList'][0]['seller_name']; // 获取商家名
$title = "订单提交成功 - {$order_no}"; // 邮件标题
$message = "新的订单提交成功"; // 内容中标题
$smtp = new SendMail();
$smtp->sendEail($accept_name, $email, $order_no, $this->order_id,  $seller, $title, $message);

public function sendEail($accept_name, $email, $order_no, $order_id,  $seller, $title, $message){
	$stie_config = new Config('site_config'); // 获取配置文件中的邮件
	$bcc = $stie_config->email; // 多个抄写人之间用 ,
	$content = mailTemplate::sendMail(array(), $accept_name, $order_id, $order_no, $seller, $message);
	$this->send($email, $title, $content, $bcc);
}