<?php

/**
    PHP 中的 JSON 简介
        学习目的 JSON 起源 什么是 JSON JSON 的基本语法 JSON 的优点和缺点 相提并论
    怎么使用 JSON
    JSON 实战 使用 Javascript Ajax 初始化网站基本信息
    JSON 实战 使用 Javascript Ajax 通信操作 JSON
    JSON 使用过程中常见的错误
*/
    /**
    学习目的
    掌握并且能使用 JSON 数据格式进行通信交互
    理解 JSON 数据格式和 serialize 序列化 XML 以及数组的区别

    JSON 起源
    21 世纪初 Douglas Crockford 寻找一种简便的数据交换格式 能够在服务器之间交接数据 当时通用的数据交换语言是 XML 但是 Douglas Crockford 觉得 XML 的生成和解析都太麻烦 所以他提出了一种简化格式 也就是 Json

    Json 的规格非常简单 只用一个页面几百个字就能说清楚 而且 Douglas Crockford 声称这个规格永远不必升级 因为该规定的都规定了

    第一种类型是标量 scalar 也就是一个单独的字符串 string 或数字 numbers 比如 北京这个单独的词

    第二种类型是序列 sequence 也就是若干个相关的数据按照一定顺序并列在一起 以叫做数组 array 或列表 list 比如 北京,上海

    第三种类型是映射 mapping 也就是一个名/值对 Name/value 即数据有一个名称 还有一个与之相对应的值 这又称作散列 hash 或字典 dictionary 比如 首都:北京

    什么是 JSON
    JSON 就是 (JavaScript Object Notation) 它是一种轻量级的数据交换格式

    以下的语句就是 JSON
    {'name':'eric','age':24}
    {'first':{'name':'eric','age':24},'second':{'name':'apeng','age':24}}

    JSON 的基本语法
    JSON 的四个基本规则

    并列的数据之间用逗号 ',' 分隔
    映射用冒号 ":" 表示
    并列数据的集合 数组 用方括号 [] 表示
    映射的集合 对象 用大括号 {} 表示

    北京市的面积为 16800 平方公里 常住人口 1600 万人 上海市的面积为 6400 平方公里 常住人口 1800 万
    [{"城市":"北京","面积":16800,"人口":1600},
    {"城市":"上海","面积":6400,"人口":1800}]

    JSON 的优点
    数据格式比较简单 易于读写 格式都是压缩的 占用带宽小
    支持多种语言 包括 ActionScript C C# ColdFusion Java JavaScript Perl PHP Python Ruby 等服务器端语言 便于服务器端的解析

    JSON 缺点
    要求字符集必须是 Unicode 受约束性强
    语法过于严谨 必须遵循JSON 语法四个原则

    相提并论
    XML
    XML 是标准通用标记语言 SGML 的子集 非常适合 Web 传输
    XML 提供统一的方法来描述和交换独立于应用程序或供应商的结构化数据
    JSON
    JSON 基于 JavaScript Programming Language Standard ECMA-262 3rd Edition December 1999 的一个子集
    Serialize 
    Serialize 是一种类似于 JSON 的数据格式 但是 PHP 的 serialize 是将变是序列化 返回一个具有变量类型和结构的字符串表达式
    Array()
    基本数据类型 不能用于数据的传输和交替

    怎么使用 JSON
    JSON 数据格式和 serialize 数据格式的异同和使用
    PHP 中操作 JSON 的重要函数
    一维数组到 JSON 数据格式的转换
    多维数组到 JSON 数据格式的转换
    对象到 JSON 数据格式的转换
    如何解析一个 JSON 数据格式
    转换 JSON 数据格式到对象类型
    转换 JSON 数据格式到数组类型

    JSON 数据格式和 serialize 数据格式的异同和使用

    相同点
    都是把其他数据类型转换成一个可以传输的字符串
    都是结构性数据

    不同点
    serialize 序列化后的数据格式 保存数据原有类型
    JSON 数据格式要更简洁相比 serialize 序列化之后的数据格式

    使用场景
    JSON 适合数据量大 不要求保留原有数据类型的情况下使用
    serialize 适合 存储带有加密方式的数据串 防止数据被中途截取反序列化破解
    
    */
