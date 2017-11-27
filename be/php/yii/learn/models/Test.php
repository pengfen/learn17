<?php

namespace frontend\models;

use yii\db\ActiveRecord;

class Test extends ActionRecord {

	// 校验
	public function rules() {
		return [
		    ['id', 'integer'],
		    ['name', 'string', 'length' => [0,5]]
		];
	}
}