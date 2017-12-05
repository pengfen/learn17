练习 写一个脚本 完成以下任务
1.添加5个用户 user1 ... user5
2.每个用户的密码同用户名 而且要求 添加密码完成后不显示passwd命令的执行结果信息
3.每个用户添加完成后 都要显示用户某某已经成功添加

useradd user1
echo "user1" | passwd --stdin user1 &> /dev/null
echo "Add user1 successfully."

条件判断
    如果用户不存在
	    添加用户 给密码并显示添加成功
	否则
	    显示如果已经存在 没有添加

bash中如何实现条件判断
条件测试类型 整数测试 字符测试 文件测试

条件测试的表达式 [expression] [[expression]] test expression

整数比较
    --eq 测试两个整数是否相等 比如 $A -eq $B
	--ne 测试两个整数是否不等 不等 为真 不等 为假
	--gt 测试一个数是否大于另一个数 大于 为真 否则 为假
	--lt 测试一个数是否小于另一个数 小于 为真 否则 为假
	--ge 大于或等于
	--le 小于或等于

A=3
B=6
[ $A -eq $B ] #[]中括号两边必有一个空格
echo $?

命令的之间逻辑关系 
    逻辑与 && 
	    第一个条件为假时 第二条件不用再判断 最终结果已经有
		第一个条件为真时 第二条件必须得判断
	逻辑或 ||

如果用户user6不存在 就添加用户user6
! id user6 && useradd user6
id user6 || useradd user6

如果/etc/inittab文件的行数大于100 就显示好大的文件
[ `wc -l /etc/inittab | cut -d'' -f1` -gt 100] && echo "Large file."
[root@localhost work]# echo `wc -l /etc/inittab`
17 /etc/inittab
[root@localhost work]# echo `wc -l /etc/inittab | cut -d' ' -f1` #以空格区分
17

变量名称 
    1.只能包含字母 数字和下划线 并且不能数字开头 
	2.不应该跟系统中已有的环境变量重名
	3.最好做到见名知义

如果用户存在 就显示用户已存在 否则 就添加此用户
id user1 && echo "user1 exists." || useradd user1

如果用户不存在 就添加 否则 显示其已经存在
! id user1 && useradd user1 || echo "user1 exists."

如果用户不存在 添加并且给密码 否则 显示其已经存在
！id user1 && useradd user1 && echo "user1" | passwd --stdin user1 || echo "user1 exists."
