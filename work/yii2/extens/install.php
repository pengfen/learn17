<?php

/**
    yii2 所有的扩展都放在 vendor/yiisoft 目录中

    扩展使用 composer 来安装

    手动修改 extensions.php 文件
*/

     'yiisoft/yii2-httpclient' => // 扩展插件目录
      array (
          'name' => 'yiisoft/yii2-httpclient', // 扩展名
          'version' => '2.0.0', // 扩展插件版本号
          'alias' =>
              array (
                  '@yii/httpclient' => $vendorDir . '/yiisoft/yii2-httpclient',
              ),
      ),