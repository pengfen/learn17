<?php
header("content-type:text/html;charset=utf-8");
$str = "apeng";
echo md5($str);
echo "<hr/>";
echo md5($str, true);

/**
    create database imooc;
    use imooc;
    create table user(
        id smallint unsigned auto_increment key,
        username varchar(50) not null,
        password varchar(50) not null
    );
*/

// 注册
$act = $_REQUEST['act'];
$username = $_POST['username'];
$password = md5($_POST['password']);
mysql_connect('localhost', 'root', 'root');
mysql_select_db('test');
mysql_set_charset('utf8');
if ($act == 'reg') {
	$sql = "insert user(username, password) values('{$username}','{$password}')";
	$result = mysql_query($sql);
	if ($result) {
		echo "注册成功, 3秒钟后跳转到登陆页面";
		echo "<meta http-equiv='refresh' content='3;url=login.html'/>";
	} else {
		echo "注册失败";
		echo "<meta http-equiv='refresh' content='1;url=reg.html'/>";
	}
} elseif ($act == "login") {
	//
}