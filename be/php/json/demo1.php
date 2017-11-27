<?php
//header("Content-type:html;charset=utf-8");
$array_1 = array(); //一维数据
$array_2 = array(); //多维数组

$array_1['username'] = "ericwolf";
$array_1['age'] = 25;

$array_2['member']['yuanminghe']['username'] = "ericwolf";
$array_2['member']['yuanminghe']['age'] = 25;

$array_2['member']['zhangsan']['username']="yuanminghe";
$array_2['member']['zhangsan']['age']= 26;

//print_r($array_2);

$jsonObj_1 = json_encode($array_1);


//echo $jsonObj_1;

$jsonObj_2 = json_encode($array_2);

//echo $jsonObj_2;

class muke{
    public $name ="public Name";
    protected  $ptName = "protected Name";
    private $pName = "private Name";
    
    public function getName(){
        return $this->name;
    }
    
    
}
$mukeObj = new muke();

$obj2Json = json_encode($mukeObj);
//echo $obj2Json;
//print_r($mukeObj);

$jsonStr = '{"key":"value","key1":"value1"}';

$json2Array = json_decode($jsonStr);

//print_r($json2Array);