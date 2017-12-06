练习 写一个脚本
判断当前系统上是否有用户的默认shell为bash
如果有 就显示有多少个这类用户 否则 就显示没有这类用户
grep "bash$" /etc/passwd &> /dev/null
RETVAL=$?
if [ $RETVAL -eq 0 ]; then

if grep "bash$" /etc/passwd &> /dev/null; then
提示 “引用”一个命令的执行结果 要使用命令引用 比如 RESAULTS=`wc -l /etc/passwd | cut -d: -f1`
    使用一个命令的执行状态结果 要直接执行此命令 一定不能引用 比如 if id user1一句中的id命令就一定不能加引号
	如果想把一个命令的执行结果赋值给某变量 要使用命令引用 比如USERID=`id -u user1`
	如果想把一个命令的执行状态结果保存下来 并作为命令执行成功与否的判断条件 则需要先执行此命令 而后引用其状态结果 如
	id -u user1
	RETVAL=$?
	此句绝对不可以写为RETVAL=`id -u user1`

	`` 反引号 --- 引用命令执行结果
	
#!/bin/bash
#RETVAL=`grep "\<bash$" /etc/passwd &> /dev/null`
grep "\<bash$" /bin/bash &> /dev/null
RETVAL=$?

if [ $RETVAL -eq 0 ]; then
    USERS=`grep "\<bash$" /etc/passwd | wc -l`
	echo "有 $USERS 个用户默认shell为bash"
else
    echo "No such user."
fi


#!/bin/bash

grep "\<bash$" /etc/passwd &> /dev/null
RETVAL=$?

if [ $RETVAL -eq 0 ]; then
    AUSER=`grep "\<bash$" /etc/passwd | head -l | cut -d: f1`
	echo "$AUSER is one of such users."
else
    echo "No such user."
fi

练习 写一个脚本
给定一个文件 比如/etc/inittab
判断这个文件中是否有空白行
如果有 则显示其空白行数 否则 显示没有空白行
#!/bin/bash
FILE=/etc/inittab

if [ ! -e $FILE ]; then
    echo "No $FILE."
	exit 8
fi

if grep "^$" $FILE &> /dev/null; then
    echo "Total blank lines: `grep "^$" $FILE | wc -l`."
echo
    echo "No blank lines."
fi

练习 写一个脚本
给定一个用户 判断其UID与GID是否一样
如果一样 就显示此用户为"good guy" 否则 就显示此用户为"bad guy"
#!/bin/bash
USERNAME=user1
USERID=`id -u $USERNAME`
GROUPID=`id -g $USERNAME`
if [ $USERID -eq $GROUPID ]; then
    echo "Good guy."
else
    echo "Bad guy."
fi

进一步要求 不使用id命令获取其id号
#!/bin/bash
USERNAME=user1

if ! grep "^$USERNAME\>" /etc/passwd &> /dev/null; then
    echo "No such user: $USERNAME."
	exit 1
fi

USERID=`grep "^$USERNAME\>" /etc/passwd | cut -d: -f3`
GROUPID=`grep "^$USERNAME\>" /etc/passwd | cut -d: -f4`
if [ $USERID -eq $GROUPID ]; then
    echo "Good guy."
else
    echo "Bad guy."
fi

exit: 退出脚本

练习 写一个脚本
给定一个用户 获取其密码警告期限
而后判断用户最近一次修改密码时间距今天是否已经小于警告期限
提示 算术运算的方法$[$A-$B] 表示变量A的值减去变量B的值的结果
如果小于 则显示warning 否则 就显示OK

[root@localhost ~]# date +%s
1512519319
TIMESTAMP=`date +%s`
let USEDAYS=$TIMESTAMP/86400
echo $USEDAYS

圆整 丢弃小数点后的所有内容

#!/bin/bash

W=` grep "student" /etc/shadow | cut -d: -f6`
S=` date +%s`
T=` expr $S/86400`
L=` grep "^student" /etc/shadow | cut -d: -f5`
N=` grep "^student" /etc/shadow | cut -d: -f3`
$Y=$[ $L - $[$T-$N]]

if [$SY -lt $W ]; then
    echo "warning"
else
    echo "OK"
fi

练习 写一个脚本
判定命令历史中历史命令的总条目是否大于1000 如果大于 则显示"some command will gone" 否则显示"OK"
echo $HISTSIZE

#!/bin/bash
HISTSIZE=` history | tail -1 | cut -d' ' -f3`
if [ HISTSIZE -gt 1000 ]; then
    echo "Some command will gone"
else
    echo "OK"
fi

shell中如何进行算术运算
1. let 算术运算表达式
let C=$A+$B
2. $[算术运算表达式]
C=$[$A+$B]
3.$((算术运算表达式))
C=$(($A+$B))
4.expr算术运算表达式 表达式中各操作数及运算符之间要有空格 而且要使用命令引用
C=`expr $A + $B`
[root@localhost ~]# A=3
[root@localhost ~]# B=6
[root@localhost ~]# C=$A+$B
[root@localhost ~]# echo $C
3+6
[root@localhost ~]# let C=$A+$B
[root@localhost ~]# echo $C
9

测试方法
[ expression ]
[[ expression ]]
test expression

bash中常用的条件测试有三种
整数测试 --gt --le --ne --eq --ge --lt

INT1=63
INT2=77
[ $INT1 -eq $INT2 ]
[[ $INT1 -eq $INT2 ]]
test $INT1 -eq $INT2

if [ `grep "^$USERNAME\>" /etc/passwd | cut -d: -f3` -eq 0 ]; then

文件测试
-e FILE 测试文件是否存在
-f FILE 测试文件是否为普通文件
-d FILE 测试指定路径是否为目录
-r FILE 测试当前用户对指定文件是否有读取权限
-w
-x

[ -e /etc/inittab ]
[ -x /etc/rc.d/rc.sysinit ]

多分支的if语句
if 判断条件1; then
    statement1
	...
elif 判断条件2; then
    statement2
	...
elif 判断条件3; then
    statement3
	...
fi

#!/bin/bash

FILE=/etc/inittab

if [ -e $FILE ]; then
    echo "OK"
else
    echo "No such file."
fi

测试脚本是否有语法错误
bash -n 脚本
bash -x 脚本 单步执行

练习 写一个脚本
给定一个文件
如果是一个普通文件 就显示之
如果是一个目录 也显示之
否则 此为无法识别之文件
#!/bin/bash

FILE=/etc/rc.d/rc.sysinit

if [ ! -e $FILE ]; then
    echo "文件不存在."
    exit 6
fi

if [ -f $FILE ]; then
    echo "普通文件."
elif [ -d $FILE ]; then
    echo "目录."
else
    echo "未知文件."
fi

定义脚本退出状态码

exit: 退出脚本
exit #
如果脚本没有明确定义退出状态码 那么 最后执行的一条命令的退出码即为脚本的退出状态码

bash变量的类型
本地变量(局部变量)
环境变量
位置变量 $1,$2 ...
特殊变量 $? $#(参数的个数) $*(参数列表) $@(参数列表)

first.sh /etc/fstab /etc/inittab
$1 /etc/fstab
$2 /etc/inittab

练习 写一脚本
能接受一个参数(文件路径)
判定 此参数如果是一个存在的文件 就显示"OK" 否则就显示"No such file."
#!/bin/bash

if [ $# -lt 1 ]; then
    echo "使用: first.sh arg1 [arg2 ...]"
	exit 7
fi

if [ -e $1 ]; then
    echo "OK."
else
    echo "No such file."
fi
first.sh /etc/fstab

#!/bin/bash

echo $1
shift #移除
echo $1
shift
echo $1

练习 写一个脚本
给脚本传递两个参数(整数)
显示此两者之和 之积
#!/bin/bash

if [ $# -lt 2 ]; then
    echo "使用: cacl.sh arg1 arg2"
	exit 8
fi

echo "两整数之和是: $[$1+$2]."
echo "两整数之积是: $[$1\*$2]."