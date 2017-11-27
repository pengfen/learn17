<?php

namespace frontend\models;

use yii\db\ActiveRecord;
class Order extends ActiveRecord {

	// 根据订单查询对应所属会员
	public function getMember() {
		return $this->hasOne(Member::className(), ['id' => 'member_id'])->asArray();
	}
}