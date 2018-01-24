#!/bin/bash

echo "Please input your content : "
echo "请输入一个数: "
# read 获取当前输入的内容 相当于 java Scanner sc = new Scanner(System.in); sc.nextInt()
read content

if [[ "$contemt" -lt 10 ]]
then
    echo "The content is smaller then 10"
fi

# if []; then 或者
#if
#then 同一行时使用;隔开

#localhost:~/peng/test$ chmod +x if.sh
#localhost:~/peng/test$ ./if.sh
#Please input your content: 
#10
#localhost:~/peng/test$ ./if.sh
#Please input your content: 
#1
#小于10

