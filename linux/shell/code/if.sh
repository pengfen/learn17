#!/bin/bash

echo "Please input your content : "
# read 获取当前输入的内容 相当于 java Scanner sc = new Scanner(System.in); sc.nextInt()
read content

if [[ "$contemt" -lt 10 ]]
then
    echo "The content is smaller then 10"
fi
