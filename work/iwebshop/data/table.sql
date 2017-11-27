-- 创建表时的浏览sql
CREATE TABLE `XXXX_seller` (
`id`  int UNSIGNED NOT NULL AUTO_INCREMENT ,
`regCo`  varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '企业注册名称(简)' ,
`fullName`  varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '企业注册名称(全)' ,
`demesticBank`  varchar(64) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '境内企业的开户银行' ,
`demesticAccoNo`  varchar(64) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '境内企业银行账号' ,
`mailCo`  char(6) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '企业邮编' ,
`rgDate`  char(8) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '注册日期' ,
`regValidDate`  char(8) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '企业注册有效期' ,
`licenseId`  varchar(32) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '营业执照号' ,
`enFullCo`  varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '企业英文名称' ,
`enAddrCo`  varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '企业英文地址' ,
`addrCo`  varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '企业中文地址' ,
`contacCo`  varchar(32) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '联系人' ,
`telCo`  varchar(32) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '联系电话' ,
`invFundT`  decimal(10,4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '投资总额(万)' ,
`rgFund`  decimal(10,4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '注册资本(万)' ,
`currCode`  varchar(3) CHARACTER SET utf8 NOT NULL DEFAULT 'CNY' COMMENT '注册资金币制' ,
`taxyRgNo`  varchar(32) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '税务登记号' ,
`copGBCode`  varchar(32) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '组织机构代码' ,
`copRange`  varchar(4000) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '企业经营范围' ,
`lawMan`  varchar(32) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '法人姓名' ,
`lawManTel`  varchar(32) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '法人联系电话' ,
`ebusiType`  tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '贸易方式 1 直邮 2 自贸 3 兼有' ,
`ebusiFoodType`  tinyint(2) UNSIGNED NOT NULL DEFAULT '01' COMMENT '商品种类 01 食品/化妆品' ,
`operationCode`  tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '操作编码 1 新增 2 修改' ,
`declaraNo`  varchar(32) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '申报备案号' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8
COMMENT='国检商户备案信息表'
;

-- 手动导出表结构
CREATE TABLE `XXXXX_seller` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regCo` varchar(255) NOT NULL DEFAULT '' COMMENT '企业注册名称(简)',
  `fullName` varchar(255) NOT NULL DEFAULT '' COMMENT '企业注册名称(全)',
  `demesticBank` varchar(64) NOT NULL DEFAULT '' COMMENT '境内企业的开户银行',
  `demesticAccoNo` varchar(64) NOT NULL DEFAULT '' COMMENT '境内企业银行账号',
  `mailCo` char(6) NOT NULL DEFAULT '' COMMENT '企业邮编',
  `rgDate` char(8) NOT NULL DEFAULT '' COMMENT '注册日期',
  `regValidDate` char(8) NOT NULL DEFAULT '' COMMENT '企业注册有效期',
  `licenseId` varchar(32) NOT NULL DEFAULT '' COMMENT '营业执照号',
  `enFullCo` varchar(255) NOT NULL DEFAULT '' COMMENT '企业英文名称',
  `enAddrCo` varchar(255) NOT NULL DEFAULT '' COMMENT '企业英文地址',
  `addrCo` varchar(255) NOT NULL DEFAULT '' COMMENT '企业中文地址',
  `contacCo` varchar(32) NOT NULL DEFAULT '' COMMENT '联系人',
  `telCo` varchar(32) NOT NULL DEFAULT '' COMMENT '联系电话',
  `invFundT` decimal(10,4) unsigned NOT NULL DEFAULT '0.0000' COMMENT '投资总额(万)',
  `rgFund` decimal(10,4) unsigned NOT NULL DEFAULT '0.0000' COMMENT '注册资本(万)',
  `currCode` varchar(3) NOT NULL DEFAULT 'CNY' COMMENT '注册资金币制',
  `taxyRgNo` varchar(32) NOT NULL DEFAULT '' COMMENT '税务登记号',
  `copGBCode` varchar(32) NOT NULL DEFAULT '' COMMENT '组织机构代码',
  `copRange` varchar(4000) NOT NULL DEFAULT '' COMMENT '企业经营范围',
  `lawMan` varchar(32) NOT NULL DEFAULT '' COMMENT '法人姓名',
  `lawManTel` varchar(32) NOT NULL DEFAULT '' COMMENT '法人联系电话',
  `ebusiType` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '贸易方式 1 直邮 2 自贸 3 兼有',
  `ebusiFoodType` tinyint(2) unsigned NOT NULL DEFAULT '1' COMMENT '商品种类 01 食品/化妆品',
  `operationCode` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '操作编码 1 新增 2 修改',
  `declaraNo` varchar(32) NOT NULL DEFAULT '' COMMENT '申报备案号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `declaraNo` (`declaraNo`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='国检商户备案信息表';