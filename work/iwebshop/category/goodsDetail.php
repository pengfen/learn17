<?php

/**
    商品详情 ---> 购物车流程

    获取商品规格信息
    判断规格显示类型 1 文字 2 图片

    js 中获取规格相关参数
    var str = '';
    $(".selectSpec").each(function (){
        var _id = $(this).attr("data");
        var name = $('#name_' + _id).text(); // 获取参数名 
        var text = $("#selectpick_span_select_" + _id).text(); // 获取参数内容
        str += name + ':' + text + ',';
    });

    UpdateCart // 更新购物车信息
    $cartInfo[$type][$gid] = $num
    $cartInfo[$type]['value'][$gid] = $str;
    数据内容
    array(
        'goods' => array(
            '334' => 1, // 商品id => 商品数量
            'value' => array(
                '334' => $str, // 商品id => 商品规格参数
            )
        )
    )

    cartFormat() // 改造数据结构
    // 如果 数组中含有 value 则去除
    if(in_array('value', $goodsIdArray)){
		$key = array_keys($goodsIdArray, 'value'); // 获取值为value 的键
		unset($goodsIdArray[$key[0]]);
	}
	$result['goods']['id'] = $goodsIdArray;
	$param = '';

	if (isset($cartValue['goods']['value'])) {
		foreach ($cartValue['goods']['value'] as $key => $val) {
			if ($gid == $key) { // 商品id 与参数对应的id 相等时 则添加参数
				$param = $cartValue['goods']['value'];
				unset($cartValue['goods']['value']);
			}
		}
	}

	数据生成

	array(
        'goods' => array(
            'id' => array( // 商品id
                0 => 331,
                1 => 332
            )
            'data' => array(
                331 => array(
                    'id' => 331,
                    'type' => goods,
                    'goods_id' => 331,
                    'brand_name' => '',
                    'market_price' => '',
                    'count' => 1,
                    'param' =>
                ),
                332 => array(
                    'id' => 332,
                    'type' => goods,
                    'goods_id' => 332,
                    'brand_name' => '',
                    'market_price' => '',
                    'count' => 1,
                    'param' => array(
                        '332' => ''
                    )
                )
            )
        )
    )

*/