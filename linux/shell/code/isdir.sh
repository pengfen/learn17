#!/bin/bash

#read
#-t 等待时间(秒数) -t 30 等待输入时间30秒
#-p 提示信息
read -t 30 -p "Please input a dir : " dir
if [ -d "$dir" ]
then
    echo "you are right";
else
    echo "you are wrong"
fi

#localhost:~/peng/test/shell$ chmod +x isdir.sh 
#localhost:~/peng/test/shell$ ./isdir.sh 
#请输入一个目录: isdir.sh
#你输入的不是目录
#localhost:~/peng/test/shell$ ./isdir.sh 
#请输入一个目录: /var
#你输入的是目录

