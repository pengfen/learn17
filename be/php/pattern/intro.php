<?php

/**
    PHP 面向对象高级特性
    11 种 PHP 设计模式
    PSR-0, Composer, Phar 等最流行的技术

    目标
    掌握 PHP 各类设计模式
    具备设计纯面向对象框架和系统的能力

    选择编程字体
    必须选择等宽字体
    常见的等宽编程字体包括 Courier New, Consolas
    推荐使用 Source Code Pro 是由 ADobe 公司专门为程序员设计 免费开源

    PHP 运行环境搭建 推荐使用 EasyPHP
    绿色软件 重装系统之后仍然可用
    集成了 Apache MySQL PHPMyadmin
*/
    
    // 工厂模式
    $db = pattern\factory::Database();