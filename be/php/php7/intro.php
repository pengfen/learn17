<?php

/**
    PHP NG 
    Abstract Syntanx Tree 抽象语法树
    Int64 Improvement 64位支持
    Uniform variable syntax 统一变量语法
    Native TLS 本地 TLS
    Consistently foreach behaviors
    New <=>.??operaters
    Return Type Declarations
    Scalar Type Declarations
    Exceptions in Engine
    And Dozens features

    php5 PHP ---> Parser ---> Opcodes ---> Execution
    php7 PHP ---> Parser ---> AST ---> Opcodes ---> Execution

    Inet64 Improvement
    > 2GB string
    System independent 64bits long
    > 2GB file uploading
    Fully 64bits intgers eross platforms

    <=>
    $a<=>$b ($a > $b?1:(($a < $b)?-1:0)

    ??
    $b = isset($a['x'])?$a['x']:null
    $b = $a['x']??null

    Faster zend_qsort 快排
    Refactor zend_qsort for better performance
    Hybrid Sorting Algo(Quick Sort and Selection Sort)
    < 16 elements do stable sorting
    $array = array(0 => 0, 1 => 0); asort($array);
    PHP5 array(1 => 0, 0 => 0)
    PHP7 array(0 => 1, 1 => 0)
*/