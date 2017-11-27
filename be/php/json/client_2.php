<?php

//一维数组转换
$member['username'] = "yuanminghe";
$member['less'] = "mukewang";
$member['age'] = 25;
$member['address'] = "北京市朝阳区";

//echo json_encode($member);

$member_2['username'] = "ericwolf";
$member_2['less'] = "mukewang";
$member_2['age'] = 26;
$member_2['address'] = "北京市东城区";


//二维数组
$members = array(
    "1" => $member,
    "member2" => $member_2
);

//echo json_encode($members);

class member {

    public $username, $age;

    function setUsername($username) {
        $this->username = $username;
    }

    function setAge($age) {
        $this->age = $age;
    }

    function getUsername() {
        return $this->username;
    }

    function getAge() {
        return $this->age;
    }

}
$memberObj = new member;
$memberObj->setUsername($member['username']);
$memberObj->setAge($member['age']);

echo json_encode($memberObj);

function toUtf($value){
    if($value){
        return icon_to_utf8($value);
    }else{
        return false;
    }
}
?>
