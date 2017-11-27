<?php

/**
     SPL 库的使用 (PHP 标准库)
     SplStack SplQueue SplHeap SplFixedArray 等数据结构类
     ArrayIterator AppendIterator Countable ArrayObject
     SPL 提供的函数
*/

    // Stack 先前后出
    $stack = new SplStack();
    $stack->push("data1");
    $stack->push("data2");

    echo $stack->pop();
    echo $stack->pop();

    // Queue 先前先出
    $queue = new SplQueue();
    $queue->enqueue("data1");
    $queue->enqueue("data2");

    echo $queue->dequeue();
    echo $queue->dequeue();

    // PHP 链式操作的实现
    $db->where()->limit()->order();

    // 传统
    $db = new IMooc\Database();
    $db->where("id=1");
    $db->where("name=apeng");
    $db->order("id desc");
    $db->limit(10);