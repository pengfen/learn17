<?php

//1
$member['username'] = "mukewang";
$member['password'] = "mukewang";
$do = $_REQUEST['do'];


//2
$members['1']['username'] = "幕课网";
$members['1']['password'] = "mukewang";
$members['2']['username'] = "袁明贺";
$members['2']['password'] = "haha";
$members['2']['address'] = "北京市朝阳区";
$members['third']['members']['username']="我是第三个用户名";

//3
class addressClass {

    public $address = array();

    public function setAddress($array) {
        $this->address = $array;
    }

    public function getAddress() {
        return $this->address;
    }

}

$addressObj = new addressClass();

$addressObj->setAddress($members);


switch ($do) {
    case "first":
        echo json_encode($member);
        break;

    case "second":
        echo json_encode($members);
        break;

    case "third":
        echo json_encode($addressObj);
        break;
}
?>


