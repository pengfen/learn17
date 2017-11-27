<?php

/**
    新浪博客客户端执接口 (MAPI)

    平均 TPS 提升 154%
    峰值 TPS 提升 168%
    响应速度提升  160%
    Web 集群冗余提升了 3 倍
    节省服务器 777 台

    v4
    系统 Apache + PHP 5.3
    MAPI 和 HTML 5 一套代码 底层耦合
        model 与 HTML 5 底层复用
        两个团队负责 互相干扰稳定性
    框架大而全 冗余代码多 一直在不断加逻辑
        框架本身损耗高
        学习成功高 开发周期长
        CPU 内存也成为瓶颈 单机 QPS 提升困难

    v5
    升级系统架构 LNMP
    升级框架 换成 Yaf
    MAPI Html5 解耦
    使用扩展
    并行 异步

    升级架构
    PHP 5.4
        和 PHP 5.3 兼容性好
        大量 Bug 修复 大量的新特性 (Closure, Slow log等)
        性能提升 30%
    Nginx
        异步 轻量 单机并发更多
        配置更灵活

    Yaf
    框架小而美 快
    框架和业务解耦合
    足够灵活 支持插件
    基于命名空间的文件目录组织
    学习成本低

    解耦
    第一个 PHP 并行 RPC 框架
    高效 轻量 易用
    支持 HTTP Socket
    支持 Msgpack Json
    Page-Card 解析 RPC (消息箱 Html5 广告)

    使用扩展
    Weibo Conf
        php-fpm 启动时 Parse
        Fork 的时候利用 COW(Copy on Write) 而避免使用共享内存

    Weibo Utils
        运算密集型
        业务无关

    使用扩展
    php-jsond
        基于 Flex 和 Bison 实现 
        比 php-json 快 2-8 倍 越大越明显
    Opcache (Zend Optimizer Plus)
        7%-10% 性能提升
    Gettext

    curl multi 并行化
    Profile 页需要请求 13 个接口
    10 个接口无状态 无依赖
    串行模式下需 1.3s
    并行后 只要 200 ms 

    坑
    Nginx - Content - Length 没有导致验证码不显示
    Weibo_conf - ini 文件中写了 Json 数据
    Memcached 新老版本不兼容
    Curl Multi - 单例 没重置状态

*/