<?php

/**
    curl post 参数数据

    数组数据 (直接根据数组下标来获取数据)

    传递数据
    $post_string = array(
        'EData' => $EData,
        'SignMsg' => $SignMsg
    );
    curl_setopt($ch, CURLOPT_POSTFIELDS, $post_string);

    处理数据
    $EData = IReq::get('EData', 'post');
    $EData = $request->post('EData');

    字符串数据 (根据特殊分隔符来分隔获取对应的数据)
    $post_string = "Edata={$EData}&SignMsg={$SignMsg}";

    处理数据
    $postData = file_get_contents('php://input');
    $postData = explode('&', $postData);
*/