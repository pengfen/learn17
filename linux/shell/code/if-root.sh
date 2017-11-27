#!/bin/bash

#[root@iZ94f2imuwkZ shall]# env | grep "USER" # 匹配当前用户
#USER=root
#[root@iZ94f2imuwkZ shall]# env | grep "USER" | cut -d "=" -f2 # 以 = 作为分隔符截取第二个
#root

# = 前后不能加空格
test=$(env | grep "USER" | cut -d "=" -f2)
echo $test;
# [] 前后需要加空格
if [ "$test" == root ]
then
   echo "current user is root."
fi

