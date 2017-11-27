<?php
namespace frontend\controllers;

use YII;
use yii\web\Controller;
use yii\httpclient\Client;

class CbtresultController extends Controller
{
    // 申报审核结果返回
	private $sellerResult = "http://dev.yii.com/index.php/v1/testcbt/sellerresult";
	private $brandResult = "http://dev.yii.com/index.php/v1/testcbt/brandresult";
	private $goodsResult = "http://dev.yii.com/index.php/v1/testcbt/goodsresult";
	private $orderResult = "http://dev.yii.com/index.php/v1/testcbt/orderresult";

	 /**
     * 基本数据
     */
    private function basicData(){

        return array(
            'verson' => 'v1.2', // 网关版本
            'commitTime' => date('YmdHis'), // 提交时间
            'serialNumber' => '流水号',
        );
    }

    /**
     * 拼装申报数据并返回响应结果
     * $data 原生数据
    */
    private function response($data, $url){
    	//echo $url;
        $basic = $this->basicData();

        $array = array_merge($basic, $data);
        $EData = json_encode($array); // 原生数据
        $client = new Client();
        $response = $client->createRequest()
            ->setMethod('post')
            ->setUrl($url)
            ->setData(['EData' => $EData])
            ->send();

        if ($response->isOk) {
            $res = json_decode($response->content);
            return $res;
        }
    }

	public function actionSeller(){
		// 商品数据
		$goods = array(
			'cargoCode' => '21345', // 商品编号
			'declaraNo' => '', // 申报备案号
			'status' => '1',
			'statusMsg' => '成功备案'
			);
		$arr = array(
			'cargoes' => $goods,
			);
		$response = $this->response($arr, $this->sellerResult);

        if ($response->status = "SUCCESS") {
            echo '调用成功';
        } else {
            echo '调用失败';
        }
	}

	public function actionBrand(){
		date_default_timezone_set("PRC");
		$startTime = strtotime('now');
		// $endTime = strtotime("8082 days"); // 20380118142601
		$endTime = strtotime('3 October 2037');
		// 商品数据
		$goods = array(
			'cargoCode' => '012938272', // 商品编号
			'declaraNo' => 'ABC1356', // 申报备案号
			'cargoCodeTS' => '01010700', // 税则号
			'cargoRate' => '10', // 税率
			'status' => '1', // 审核状态 1 成功 2 失败
			'statusMsg' => '成功备案', // 审核意见
			'effectStartTime' => date('YmdHis', $startTime),
			'effectEndTime' => date('YmdHis', $endTime)
			);
		$arr = array(
			'cargoes' => $goods,
			);
		$response = $this->response($arr, $this->brandResult);
echo "<pre>";
var_dump($response);
        if ($response->status = "SUCCESS") {
            echo '调用成功';
        } else {
            echo '调用失败';
        }
	}

	public function actionGoods(){
		// 商品数据
		$goods = array(
			'cargoCode' => '21345', // 商品编号
			'declaraNo' => '', // 申报备案号
			'status' => '1',
			'statusMsg' => '成功备案'
			);
		$arr = array(
			'cargoes' => $goods,
			);
		$response = $this->response($arr, $this->goodsResult);

        if ($response->status = "SUCCESS") {
            echo '调用成功';
        } else {
            echo '调用失败';
        }
	}

	public function actionOrder(){
		// 商品数据
		$goods = array(
			'cargoCode' => '21345', // 商品编号
			'declaraNo' => '', // 申报备案号
			'status' => '1',
			'statusMsg' => '成功备案'
			);
		$arr = array(
			'cargoes' => $goods,
			);
		$response = $this->response($arr, $this->orderResult);

        if ($response->status = "SUCCESS") {
            echo '调用成功';
        } else {
            echo '调用失败';
        }
	}
}
