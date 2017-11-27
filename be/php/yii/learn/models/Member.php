<?php

namespace frontend\models;

use yii\db\ActionRecord;

class Member extends ActiveRecord {

	// 获取会员的订单信息
	public function getOrders() {
		// all() 调用getOrders 时需要调用orders时不需要
		return $this->hasMany(Order::className(), ['member_id' => 'id'])->asArray();
	}
}