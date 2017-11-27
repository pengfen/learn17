<?php

/**
    接口介绍

    基础接口的内容介绍
    事件推送 --- 订阅公众账号
    消息响应 --- 发送普通消息 接收普通消息
    基础接口 --- 获取 access_key 获取微信服务器地址

    验证配置
    应用接入 API 接口步骤
    填写服务器 URL token
    注意 只支持 80 端口

    请填写接口配置信息 (开发者模式 --- 修改配置) 此信息需要你拥有自己的服务器资源
    填写的 URL 需要正确响应微信发送的 Token 验证 
    URL http://
    Token

    应用接入 API 接口步骤
    验证服务器地址的有效性 加密/校验流程如下
    将 token timestamp nonce 三个参数进行字典序排序
    将三个参数字符串拼接成一个字符串进行 sha1 加密
    开发者获得加密后的字符串可与 signature 对比 标识该请求来源于微信

    ==================================

    使用 winscp 连接阿里云服务器
    使用 sublime 编辑
    进入微信公众平台 ---> 开发 --- 基本配置 --- 修改配置
    url: http://120.24.36.66/test.php
    token: apeng
    密钥 (随机生成)
    消息加解密方式 兼容模式

    构建基于 TP 框架的验证
    通过 winscp 上传基本 TP 框架
    修改 /home/wwwroot/default/Application/Home/Conf/config.php 文件 (连接数据库)
    在服务器上进入 mysql -uroot -p
    创建数据库
*/