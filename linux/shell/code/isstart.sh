#!/bin/bash

#[root@iZ94f2imuwkZ shall]# ps aux | grep httpd
#root      4572  0.0  0.1 103176   844 pts/0    S+   15:31   0:00 grep httpd

test=$(ps aux | grep httpd ) # 截取 httpd 进程 并把结果赋予变量 test
if [ -n "$test" ] # 如果test 的值不为空 则执行 then 中命令
then
    echo "$(date) httpd is ok "
else
    # 启动命令 /etc/rc.d/init.d/httpd start &>/dev/null
    echo "$(date) restart httpd !!" # >> 可通过 > 写入到日志中
fi
