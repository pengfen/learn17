#!/bin/bash
echo "启动zk服务 ..."

for i in 1 2 3
do
ssh ricky$i "source /etc/profile;/home/ricky/app/zookeeper/bin/zkServer.sh start"
done