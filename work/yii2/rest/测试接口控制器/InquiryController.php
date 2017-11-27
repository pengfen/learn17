<?php
namespace frontend\controllers;

use YII;
use yii\web\Controller;
use yii\httpclient\Client;

/**
  * 申报查询接口地址
*/
class InquiryController extends Controller
{
	public $enableCsrfValidation = false;

	// 商户信息查询
	public function actionSeller(){
		$request = Yii::$app->request;
		$EData = $request->get('EData'); // 获取 get 请求数据
		if (!empty($EData)) {
			$data = json_decode($EData);
			if (!empty($data->declaraNo)) {
				return json_encode(array('status'=>1));
			} else {
				return json_encode(array('status'=>0));
			}
		}
	}

	// 品牌信息查询
	public function actionBrand(){
		$request = Yii::$app->request;
		$EData = $request->get('EData');
		if (!empty($EData)) {
			$data = json_decode($EData);
			if (!empty($data->declaraNo)) {
				return json_encode(array('status'=>1));
			} else {
				return json_encode(array('status'=>0));
			}
		}
	}

	// 商品信息查询
	public function actionGoods(){
		$request = Yii::$app->request;
		$EData = $request->get('EData');
		if (!empty($EData)) {
			$data = json_decode($EData);
			if (!empty($data->declaraNo)) {
				return json_encode(array('status'=>1));
			} else {
				return json_encode(array('status'=>0));
			}
		}
	}

	// 订单信息查询
	public function actionOrder(){
		$request = Yii::$app->request;
		$EData = $request->get('EData');
		if (!empty($EData)) {
			$data = json_decode($EData);
			if (!empty($data->declaraNo)) {
				return json_encode(array('status'=>1));
			} else {
				return json_encode(array('status'=>0));
			}
		}
	}
}