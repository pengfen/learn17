<?php
namespace frontend\controllers;
use yii\web\Controller;
use yii\web\Cookie;
class WelController extends Controller{
	public function actionIndex(){
		$req = \YII::$app->request;
		echo $req->get('id', 1);
		//echo $req->post('name', 'apeng');
		if ($req->isGet) {
		}
		echo $req->userIp;
		echo 'welcome to yii world';
	}
	// 响应
	public function actionResponse(){
		$res = \YII::$app->response;
		//$res->statusCode = '404';
		$res->headers->add('pragma', 'no-cache'); // 设置不缓存
		$res->headers->set('pragma', 'max-age=5');
		$res->headers->remove('pragma');
		// 跳转
		//$res->headers->add('location', 'http://dev.yii.com/?r=gii');
		//$this->redirect('http://dev.yii.com/?r=gii', 302);
		// 文件下载
		$res->headers->add('content-disposition', 'attachment; filename="a.txt"');
		// 下载与入口index.php 同一目录下的文件
		$res->sendFile('./a.txt');
	}
	// session
	public function actionSession(){
		$session = \YII::$app->session;
		$session->open(); // 开启session
		if ($session->isActive) { // 判断session 是否开启
			echo 'session is open';
		}
		// 对象方式存取
		//$session->set('name', 'apeng'); // 保存
		//echo $session->get('name'); // 获取
		//$sesion->remove('name') // 移除
		// 数组方式存取
		//$session['name'] = 'apeng';
		//echo $session['name'];
		//unset($session['name']);
	}
	// cookie
	public function actionCookies(){
		// 保存cookie
		$cookies = \YII::$app->response->cookies;
		$cookie_data = array('name'=>'apeng', 'value'=>'18'); // 设置值
		$cookies->add(new Cookie($cookie_data)); // 添加cookie
		//$cookies->remove(); // 移除
		// 获取cookie 值
		$cookies = \YII::$app->request->cookies;
		echo $cookies->getValue('apeng');
		echo $cookies->getValue('apeng', 20);
	}
	// 视图的创建
	public function actionWel(){
		return $this->renderPartial('index'); // 调用 views/wel/index.php 视图
		// return $this->renderPartial('index.php'); // 可以省略后缀后 .php
		// return $this->render('index.php'); // 视图加布局
	}
	// 数据传递
	public function actionData(){
		$str = 'welcome !';
		$arr = array(1, 2, 3);
		// 创建一个数组
		$data = array();
		// 把需要传递的数据放进数组中
		$data['data'] = $str;
		$data['arr'] = $arr;
		return $this->renderPartial('data', $data);
	}
	// 数据过滤
	public function actionFilter(){
		$str = "welcome <script>alert('嗨')</script>";
		$data = array();
		$data['data'] = $str;
		return $this->renderPartial('filter', $data);
	}
	public $layout = 'public';
	// 布局文件
	public function actionLayout(){
		return $this->render('layout'); // 默认为布局文件中 $content
	}
	// 数据块
	public function actionBlock(){
		return $this->render('block');
	}
	// 查询
	public function actionSelect(){
		$test = new \frontend\models\Test();
		// 查询数据
		// $sql = "select * from test where id=1";
		// $result = $test::findBySql($sql)->all();
		// 占位符
		// $sql = "select * from test where id=:id";
		// $result = $test::findBySql($sql, array(':id'=>1))->all();
		// id = 1
		// $result = $test::find()->where(['id'=>1])->all();
		// id > 0
		// $result = $test::find()->where(['>', 'id', '0'])->all();
		// id >= 1 并且  id <= 2
		// $result = $test::find()->where(['between', 'id', 1, 2])->all();
		// title  like '%test%';
		// $result = $test::find()->where(['like', 'name', 'test'])->all();
		// 降低内存的使用一 将查询结果转化成数组
		$result = $test::find()->where(['between', 'id', 1, 2])->asArray()->all();
		// 降低内存的使用二 批量查询
		foreach ($test::find()->batch(1) as $tests) {
			// 处理查询结果
			print_r(count($tests));
		}
		echo '<pre>';
		var_dump($result);
		echo '</pre>';
	}
	// 删除
	public function actionDelete(){
		$test = new \frontend\models\Test();
		// 删除数据
		// $result = $test::find()->where(['id'=>1])->all();
		// $result[0]->delete();
		// 占位符 deleteAll 不带条件会删除所有
		$test::deleteAll('id>:id', array(':id'=>0));
	}
	// 添加
	public function actionInsert(){
		$test = new \frontend\models\Test();
		// 增加数据
		// $test->id = 3;
		// $test->name = 'test3';
		$test->id = 'a';
		$test->validate();
		if ($test->hasErrors()){
			echo '数据不合法';
			exit;
		}
		$test->save();
	}
	// 修改
	public function actionUpdate(){
		$test = new \frontend\models\Test();
		// 修改数据
		$data  = $test::find()->where(['id'=>3])->one();
		$data->name = "test4";
		$data->save();
	}
	// 关联查询
	public function actionAssoci(){
		
		$member = new \frontend\models\Member();
		$order = new \frontend\models\Order();
		// 根据会员名 查询他的所拥有的订单信息
		// $memberOrder = $member::find()->where(['name'=>'apeng'])->one();
		// 将以下封装到 member 模型中
		// $orders = $memberOrder->hasMany($order::className(), ['member_id'=>'id'])->asArray()->all();
		//$orders = $memberOrder->getOrders();
		// $orders = $memberOrder->orders; // 调用不存在的属时 ---> __get ---> getOrders() ---> all()
		// 根据订单查询所属会员的信息
		$orderInfo = $order::find()->where(['id'=>1])->one();
		$memberInfo = $orderInfo->member;
		echo '<pre>';
		var_dump($memberInfo);
		echo '</pre>';
	}
	// 关联查询相关问题
	public function actionAssoci2(){
		$member = new \frontend\models\Member();
		$order = new \frontend\models\Order();
		// 解决关联查询缓存结果
		$memberInfo = $member::find()->where(['name'=>'apeng'])->one();
		$orderInfo = $memberInfo->orders; // 执行sql (select * from order where custom_id = ...)
		unset($memberInfo->orders); // 清除缓存
		$orderInfo2 = $memberInfo->orders; // 会再次执行以上sql 不清除则缓存会直接读取缓存
		// 解决多次查询
		$memberinfo2 = $member::find()->all(); // 执行 select * from customer
		// 会将 select * from order where custom_id = ... 转为 select * from order where custom_id in (...)
		$memberinfo3 = $member::find()->with('orders')->all();
		foreach ($memberinfo3 as $members) {
			$orders = $members->orders;
		}
	}
	////                   git 开发经验
	// ---> 多用客户端和工具 少用命令行 除非是在Linux 服务器上直接开发
	// ---> 每次提交前 diff 自己的代码 以免提交错误的代码
	// ---> 下班回家前 整理好自己的工作区
	// ---> 并行的项目 使用分支开发
	// ---> 遇到冲突时 搞明白冲突的原因 千万不要随意丢弃别人的代码
	// ---> 产品发布后 记得打tag 方便站起来拉分支修 bug
}