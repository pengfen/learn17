http://php.net/manual/zh/   php 手册

basic         php 基础
curl          php-curl
file          php文件相关
frame         php相关框架
script        php相关脚本
test          php面试考点

php-excel.html  使用php生成excel文件

php 安装 参看server目录

resource 项目修改
1. 修改表结构
mysql> alter table resource_techn_article rename to resource_techn_article_old;

CREATE TABLE `resource_techn_article` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL DEFAULT '0' COMMENT '用户id',
  `title` varchar(30) NOT NULL DEFAULT '' COMMENT '文章标题',
  `summary` varchar(200) DEFAULT '' COMMENT '文章摘要',
  `img` varchar(255) NOT NULL DEFAULT '' COMMENT '文章图片',
  `content` text NOT NULL COMMENT '文章内容',
  `pid` int(11) NOT NULL DEFAULT '0' COMMENT '文章所属分类id',
  `state` tinyint(1) DEFAULT '0' COMMENT '添加状态 0 后台添加 1 前后添加',
  `views` int(11) DEFAULT '0' COMMENT '浏览量',
  `addtime` int(10) unsigned DEFAULT '0' COMMENT '添加时间',
  `mid` int(10) unsigned DEFAULT '0' COMMENT '修改者',
  `updatetime` int(10) unsigned DEFAULT '0' COMMENT '修改时间',
  `uuid` int(10) unsigned DEFAULT '0' COMMENT '创建者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='技术文章表';

2. 修改模型
复制文件并改名 Technarticleold.php

3. 修改控制器
复制文件并改名 TechnarticleoldController.php

4. 修改视图