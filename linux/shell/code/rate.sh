#!/bin/bash

#获取当前信息
#[root@iZ94f2imuwkZ shall]# df -h | grep "/dev/xvda1" 
#/dev/xvda1       20G  3.7G   16G  20% /

#匹配信息中第五行
#[root@iZ94f2imuwkZ shall]# df -h | grep "/dev/xvda1" | awk '{print $5}'
#20%

#以%作为分隔符 匹配第一个值
#[root@iZ94f2imuwkZ shall]# df -h | grep "/dev/xvda1" | awk '{print $5}' | cut -d "%" -f1
#20

rate=$(df -h | grep "/dev/xvda1" | awk '{print $5}' | cut -d "%" -f1)
echo $rate;

# 注意逻辑判断时 如果报integer expression expected 时 [] 换成 [[]]
if [[ "$rate" -ge 80 ]]
then
    echo "Warning! /dev/xvsa1 if full!!"
fi
