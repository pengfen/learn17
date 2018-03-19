intro.html　介绍

techn.html

flume.html 数据采集

click_flow.conf

启动采集
在部署了flume的nginx服务器上，启动flume的agent，命令如下：
bin/flume-ng agent --conf ./conf -f ./conf/weblog.properties.2 -n agent

注意：启动命令中的 -n 参数要给配置文件中配置的agent名称
