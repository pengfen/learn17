<?php

/**
    shal 加密算法

    string shal(string $str[, bool $raw_output=false])
    计算字符串的shal 散列值
    $str 加密的字符串
    $raw_output 如果可选的 raw_output 参数被设置为 TRUE 那么 shal 摘要将以 20 字符长度的原始格式返回 否则返回值是一个 40 字符长度的十六进制数字

    返回 shal 散列值字符串
*/

    echo shal('apeng');
    echo "<h1/>";
    echo shal('cao', true);
    echo "<h1/>";