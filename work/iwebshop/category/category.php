<?php

/** 
update onramp_category set sort = '10', visibility = 0 where parent_id = 0;
update onramp_category set name = '进口家具', sort = 0, visibility = 1 where id = 27;
update onramp_category set visibility = 0 where parent_id = 27;
update onramp_category set visibility = 1 where id = 580 limit 1;
update onramp_category set name = '椅子', visibility = 1 where id = 581 limit 1;
update onramp_category set name = '席梦思', visibility = 1 where id = 589 limit 1;
update onramp_category set name = '实木家具', visibility = 1 where id = 433 limit 1;
insert into onramp_category(name,parent_id,visibility) values('餐桌', 27, 1);
insert into onramp_category(name,parent_id,visibility) values('衣柜', 27, 1);
update onramp_category set name = '厨卫设备', sort = 1, visibility = 1  where id = 26;
update onramp_category set visibility = 0 where parent_id = 26;
insert into onramp_category(name,parent_id,visibility) values('烤箱', 26, 1);
update onramp_category set visibility = 1 where id = 398 limit 1;
update onramp_category set name = '电暖抽屉', visibility = 1 where id = 422;
update onramp_category set visibility = 1 where id = 389 limit 1;
update onramp_category_extend set category_id = 26 where goods_id = 236 limit 1;
update onramp_category_extend set category_id = 389 where goods_id = 236 and category_id != 26;
update onramp_category set name = '炉具', visibility = 1 where id = 388;
update onramp_category set visibility = 1 where id = 393 limit 1;
update onramp_category set visibility = 1 where id = 395 limit 1;
update onramp_category_extend set category_id = 26 where goods_id = 235 limit 1;
update onramp_category_extend set category_id = 395 where goods_id = 235 and category_id != 26;
insert into onramp_category(name,parent_id,visibility) values('电冰箱', 26, 1);
update onramp_category set name = '洗碗机', visibility = 1 where id = 396;
update onramp_category_extend set category_id = 26 where goods_id = 234 limit 1;
update onramp_category_extend set category_id = 396 where goods_id = 234 and category_id != 26;
update onramp_category_extend set category_id = 26 where goods_id = 237 limit 1;
update onramp_category_extend set category_id = 396 where goods_id = 237 and category_id != 26;
update onramp_category set name = '洁具', visibility = 1 where id = 401; 
update onramp_category set visibility = 1 where id = 432; 
update onramp_category set name = '生活电器', sort = 2, visibility = 1  where id = 12;
update onramp_category set visibility = 0 where parent_id = 12;
insert into onramp_category(name,parent_id,visibility) values('洗衣机', 12, 1);
update onramp_category_extend set category_id = 12 where goods_id = 238 limit 1;
update onramp_category set name = '吸尘器', visibility = 1 where id = 213;
insert into onramp_category(name,parent_id,visibility) values('干衣机', 12, 1);
update onramp_category_extend set category_id = 12 where goods_id = 239 limit 1;
insert into onramp_category(name,parent_id,visibility) values('空气净化器', 12, 1);
update onramp_category set visibility = 1 where id = 574;
insert into onramp_category(name,parent_id,visibility) values('净水器', 12, 1);
insert into onramp_category(name,parent_id,visibility,sort) values('个人用品', 0, 1,2); 
update onramp_category set parent_id = 599, visibility = 1 where id = 549
update onramp_category_extend set category_id = 599 where goods_id in (41, 52, 110, 112) and category_id != 549;
insert into onramp_category(name,parent_id,visibility) values('口腔护理', 599, 1);
insert into onramp_category(name,parent_id,visibility) values('手表', 599, 1);
insert into onramp_category(name,parent_id,visibility) values('箱包', 599, 1);
update onramp_category set parent_id = 599, visibility = 1 where id = '125';
insert into onramp_category(name,parent_id,visibility) values('配饰', 599, 1);
-- 将一级分类的二级分类下所有的商品设置为删除状态 (is_del = 0 --- 0.0127秒 is_del = 1 --- 0.0079秒)
update onramp_goods set is_del = 1 where id in (select goods_id from onramp_category_extend where category_id in (select id from onramp_category where parent_id = 12 and visibility = 0));

-- 235 抽油烟机 236 灶具 237 洗碗机
insert into onramp_category_extend(goods_id, category_id) values(236, 389);
insert into onramp_category_extend(goods_id, category_id) values(235, 395);
insert into onramp_category_extend(goods_id, category_id) values(237, 396);

-- 合并为一句
insert into onramp_category_extend(goods_id, category_id) values(236, 389),(235, 395),(237, 396);

-- 238 洗衣机  239 干洗机
insert into onramp_category_extend(goods_id, category_id) values(238, 595);
insert into onramp_category_extend(goods_id, category_id) values(239, 596);

insert into onramp_category_extend(goods_id, category_id) values(238, 595),(239, 596);

*/
