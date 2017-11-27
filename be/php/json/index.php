<?php
header("Content-type:text/html;charset=utf-8");

/**
    PHP 中操作 JSON 的重要函数

    什么索引数组和关联数据
    $array_1 = array( 'name', 'age' );
    $array_2 = array( 1=>'name', 2=>'age' );

    json_encode() String json_encode(mixed $value [, int $options = 0]); 返回 value 值的 JSON 形式
    json_decode() mixed json_decode( string $json [, bool $assoc]);
    默认一个 JSON 格式的字符串并且把它转换为 PHP 变量

    索引数组定义
    $array_1 = array( 'name', 'age');
    产生数据 ["name","age"] 数组

    关产数组定义
    $array_2 = array(1=>'name', 2=>'age');
    产生数据 {"1":"name", "2":"age"} 对象
*/
function createHtmlTag($tag = "") {
    echo "<h1>$tag</h1><br/>";
}

createHtmlTag("Hello");

createHtmlTag("JSON鍜宻erialize 瀵规瘮");

$member = array("username","age");

var_dump($member);

$jsonObj = json_encode($member);

$serializeObj = serialize($member);

createHtmlTag($jsonObj);

createHtmlTag($serializeObj);


$asocArray=array("username"=>"ericwolf");
createHtmlTag(var_dump($asocArray));