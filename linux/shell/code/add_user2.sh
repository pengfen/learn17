练习 写一个脚本 完成以下要求
1. 添加3个用户user1,user2,user3 但要先判断用户是否存在 不存在而后再添加
2. 添加完成后 显示一共添加了几个用户 当然 不能包括因为事先存在而没有添加的
3. 最后显示当前系统上共有多少个用户

#!/bin/bash

! id user1 &> /dev/null && useradd user1 && echo "user1" | passwd --stdin user1 &> /dev/null || echo "user1 exists."
! id user2 &> /dev/null && useradd user2 && echo "user2" | passwd --stdin user2 &> /dev/null || echo "user2 exists."
! id user3 &> /dev/null && useradd user3 && echo "user3" | passwd --stdin user3 &> /dev/null || echo "user3 exists."

USERS=`wc -l /etc/passwd | cut -d: -f1`
echo "$USERS users"

[root@localhost work]# chmod +x adduser2.sh
[root@localhost work]# ./adduser2.sh 
26 /etc/passwd users
[root@localhost work]# ./adduser2.sh 
user1 exists.
user2 exists.
user3 exists.
26 /etc/passwd users

练习 写一个脚本 完成以下要求
给定一个用户
    1.如果其UID为0 就显示此为管理员
	2.否则 就显示其为普通用户
	
#!/bin/bash

NAME=user1
USERID=`id -u $NAME`
[ $USERID -eq 0 ] && echo "Admin" || echo "Common user."
[root@localhost work]# chmod +x third.sh
[root@localhost work]# ./third.sh 
Common user.

如果UID为0 那么 显示为管理员
否则 显示为普通用户
NAME=user16
USERID=`id -u $NAME`
if [ $USERID -eq 0 ]; then
    echo "Admin"
else
    echo "common user."
fi

NAME=user16
if [ `id -u $NAME` -eq 0 ]; then
    echo "Admin"
else
    echo "common user."
fi

条件判断 控制结构
单分支if语句
if 判断条件; then
    statement1
	statement2
	...
fi

双分支的if语句
if 判断条件; then
    statement1
	statement2
	...
else
    statement3
	statement4
	...
fi

#!/bin/bash
NAME=user1

if id $NAME &> /dev/null; then
    echo "$NAME exists."
fi

#!/bin/bash
NAME=user17

if id $NAME &> /dev/null; then
    echo "$NAME exists."
else
    #echo "$NAME not exists."
	useradd $NAME
	echo $NAME | passwd --stdin $NAME &> /dev/null
	echo "Add $NAME finished."
fi