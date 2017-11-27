<?php
namespace frontend\controllers;

use YII;
use yii\web\Controller;
use yii\httpclient\Client;

/**
  * 备案请求接口地址
*/
class ApplicController extends Controller
{
	// 允许表单多次提交 (用于测试)
    public $enableCsrfValidation = false;

	// 商品备案
	public function actionGoodsapply(){
		$request = Yii::$app->request;
		$EData = $request->post('EData'); // 获取 post 请求数据
		if (!empty($EData)) {
			$data = urldecode($EData); 
			$data = (array)json_decode($data, true); // 把 json_decode() 后的对象当作数组使用 会出现错误 Cannot use object of type stdClass as array
			return json_encode(array(
				'status' => 'SUCCESS',
				'declaraNo' => 'SDFGHJKL',
				'errorCode' => '',
				'errorMsg' => '',
				));
		} else {
			return json_encode(array(
				'status' => 'FAILURE',
				'declaraNo' => 'SDFGHJKL',
				'errorCode' => 'ILLEGAL_SIGN',
				'errorMsg' => '签名错误',
				));
		}
		
	}

	// 订单备案
	public function actionOrderapply(){
		$request = Yii::$app->request;
		$EData = $request->post('EData');
		if (!empty($EData)) {
			$data = urldecode($EData); 
			$data = (array)json_decode($data, true); // 把 json_decode() 后的对象当作数组使用 会出现错误 Cannot use object of type stdClass as array
			return json_encode(array(
				'status' => 'SUCCESS',
				'errorCode' => '',
				'errorMsg' => '',
				'cargoes' => array(
					'cargoCode' => $data['cargoes']['cargoCode'],
					'declaraNo' => 'ABCEDRFGHJK'
					)
				));
		} else {
			return json_encode(array(
				'status' => 'FAILURE',
				'errorCode' => 'ILLEGAL_SIGN',
				'errorMsg' => '',
				'cargoes' => array(
					'cargoCode' => '1',
					'declaraNo' => ''
					)
				));
		}
	}

	// 商家备案
	public function actionSellerapply(){
		$request = Yii::$app->request;
		$EData = $request->post('EData');
		if (!empty($EData)) {
			$data = urldecode($EData); 
			$data = (array)json_decode($data, true); // 把 json_decode() 后的对象当作数组使用 会出现错误 Cannot use object of type stdClass as array
			//echo '调用成功';
			return json_encode(array(
				'status' => 'SUCCESS',
				'declaraNo' => 'SDFGHJKL',
				'errorCode' => '',
				'errorMsg' => '',
				));
		} else {
			return json_encode(array(
				'status' => 'FAILURE',
				'declaraNo' => 'SDFGHJKL',
				'errorCode' => 'ILLEGAL_SIGN',
				'errorMsg' => '签名错误',
				));
		}
	}

	// 品牌备案
	public function actionBrandapply(){
		$request = Yii::$app->request;
		$EData = $request->post('EData');
		if (!empty($EData)) {
			$data = urldecode($EData); 
			$data = (array)json_decode($data, true); // 把 json_decode() 后的对象当作数组使用 会出现错误 Cannot use object of type stdClass as array
			//echo '调用成功';
			return json_encode(array(
				'status' => 'SUCCESS',
				'declaraNo' => 'SDFGHJKL',
				'errorCode' => '',
				'errorMsg' => '',
				));
		} else {
			return json_encode(array(
				'status' => 'FAILURE',
				'declaraNo' => 'SDFGHJKL',
				'errorCode' => 'ILLEGAL_SIGN',
				'errorMsg' => '签名错误',
				));
		}
	}

	// 
	public function actionIndex(){
		var_dump('欢迎 调用成功');
	}
}