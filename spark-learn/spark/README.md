包介绍
spark 介绍
spark.basic spark基础

spark.rdd spark rdd

spark.utils spark操作相关工具类

项目 (以pro_开头的包)
spark.pro_user 用户在小区的停留时间

spark.pro_game spark操作游戏日志



spark sql 介绍
spark.sql

spark.data spark 操作数据

spark.pro_sql spark sql 项目



spark streaming 介绍
spark.stream spark streaming 相关介绍

spark.stream_flume ---> flume.stream (saprk streaming 整合 flume)

spark.stream_kafka ---> kafka.stream (saprk streaming 整合 kafka)

spark.stream_concord spark streaming 整合流

spark.pro_stream spark streaming 项目


spark.pro_log 日志统计

离线处理流程
1. 模拟产生日志(定时任务每分钟产生五百条)
2. shell脚本移到日志到指定目录(定时任务)
3. spark离线处理
4. php读取数据进行可视化
