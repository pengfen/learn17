#!/bin/bash

#判断 WWW 目录是否存在
if [ ! -d "/opt/WWW" ]; then
    cd /opt
    #从github上拉取代码 初次拉使用克隆
    git clone https://github.com/pengfen/WWW.git;
else
    cd /opt/WWW
    #从github上拉取代码 第二次或多次使用更新
    git pull origin master
fi

if [ ! -d "/home/wwwroot/resource" ]; then
    #cp复制目录时不需要创建目录 它会自动创建目录 如果已有目录 它会把WWW目录也复制到resource
    #mkdir /home/wwwroot/resource;
    #将拉取的代码复制到项目目录 (以后改进使用 ssh登录到第三方主机)
    cp -rp /opt/WWW/ /home/wwwroot/resource
    cd /home/wwwroot/resource
    #修改文件属性变可执行文件 (注意github上项目不上传web/index.php文件) 有时使用 chmod 755 init
    chmod a+x init
    ./init
else
    #构建 frontend backend的 views controllers models
    array=(/backend/filters /backend/views /backend/controllers /backend/models)
    
	#循环遍历获取数组中的元素
    for data in ${array[@]}
    do
        #拼接要被重命名的目录
        dir1="/home/wwwroot/resource"${data}
	#获取当前日期 如20160903
        date_now=`date +%Y%m%d`
	#拼接被重命名后的目录
        dir2=${dir1}${date_now}
	#重命名
        mv /home/wwwroot/resource${data} $dir2
         
	#获取 backend 目录
        dir3=`echo ${data} | cut -d "/" -f 2`    
        #复制文件		
        cp -rp /opt/WWW${data} /home/wwwroot/resource/${dir3}
	#删除备份的目录 (可以考虑清除昨天 使用另一个脚本)
        rm -rf $dir2;
    done

    #构建 frontend backend的 web 目录
    array=(/console/script /backend/web/css /backend/web/images)

    for data in ${array[@]}
    do
        dir1="/home/wwwroot/resource"${data}
        if [ -d $dir1 ]; then
            date_now=`date +%Y%m%d`
            dir2=${dir1}${date_now}
            mv /home/wwwroot/resource${data} $dir2
        fi

        #获取数组中的 backend 目录
        dir3=`echo ${data} | cut -d "/" -f 2`  
        #获取数组中的 web 目录
        dir4=`echo ${data} | cut -d "/" -f 3`
        cp -rp /opt/WWW${data} /home/wwwroot/resource/${dir3}/${dir4}
        #判断变量是否存在 存在就执行删除操作
        if [ ! -z $dir2 ]; then 
            rm -rf $dir2;
        fi
    done

    #备份以前项目的代码
    #dir1="/home/wwwroot/resource/backend/views";
    #获取当前日期 如20160903
    #date_now=`date +%Y%m%d`;
    #dir2=${dir1}${date_now};
    #mv /home/wwwroot/resource/backend/views $dir2;
    #cp -rp /opt/WWW/backend/views /home/wwwroot/resource/backend
    #rm -rf $dir2;

    #备份以前项目的代码
    #dir1="/home/wwwroot/resource/backend/controllers";
    #获取当前日期 如20160903
    #date_now=`date +%Y%m%d`;
    #dir2=${dir1}${date_now};
    #mv /home/wwwroot/resource/backend/controllers $dir2;
    #cp -rp /opt/WWW/backend/controllers /home/wwwroot/resource/backend
    #rm -rf $dir2;

    #备份以前项目的代码
    #dir1="/home/wwwroot/resource/backend/models";
    #获取当前日期 如20160903
    #date_now=`date +%Y%m%d`;
    #dir2=${dir1}${date_now};
    #mv /home/wwwroot/resource/backend/models $dir2;
    #cp -rp /opt/WWW/backend/models /home/wwwroot/resource/backend
    #rm -rf $dir2;

fi

#将拉取的代码复制到项目目录 (以后改进使用 ssh登录到第三方主机)
#cp -rp /opt/WWW /home/wwwroot/resource
#cd /home/wwwroot/resource
#修改文件属性变可执行文件 (注意github上项目不上传web/index.php文件) 有时使用 chmod 755 init
#chmod a+x init
#./init


#!/bin/bash

#array=(resource/views resource/controllers)

#echo ${array[0]}
#循环遍历获取数组中的元素
#for data in ${array[@]}
#do
#    echo ${data}
#done


#array=(/resource/backend/views /resource/backend/controllers)

#for data in ${array[@]}
#do
#     dir1="/home/wwwroot"${data};
#     echo $dir1;
#获取数组中的 backend 目录
#dir2=`echo ${data} | cut -d "/" -f 3`;
#获取数组中的 resource 目录
#dir3=`echo ${data} | cut -d "/" -f 2`
#直接拼接 拼接后的目录是 /backend/resource
#echo /${dir2}/${dir3}
#done

