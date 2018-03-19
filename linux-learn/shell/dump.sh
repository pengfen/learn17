#!/bin/bash

#备份resource库
mysqldump -uroot -p123456 resource > res.sql

#备份多个表
mysqldump -uroot -p123456 resource --tables resource_techn_article > art.sql

#备份单个表
mysqldump -uroot -p123456 resource resource_techn_article > art.sql

#备份单个表部分数据
mysqldump -uroot -p123456 resource resource_techn_article --where="id>1000" > res.sql