#coding=UTF-8

import random
import time

# 100.10.143.167	2018-03-19 10:10:31	"GET /www/1 HTTP/1.1"	https://search.yahoo.com/search?p=极限挑战	404

# 分类数组
url_paths = [
    "www/1",
    "www/2",
    "www/3",
    "www/4",
    "www/6",
    "pianhua/130",
    "toukouxu/821"
]

#ip数组
ip_slices = [132, 156, 124, 10, 29, 167, 143, 187, 30, 100]

#状态码数组
status_code = [404, 302, 200]

#搜索跳转
http_referers = [
    "https://www.baidu.com/s?wd={query}",
    "https://www.sogou.com/web?qu={query}",
    "http://cn.bing.com/search?q={query}",
    "https://search.yahoo.com/search?p={query}"
]

#搜索关键词
search_keyword = [
    "猎场",
    "快乐人生",
    "极限挑战",
    "我的体育老师",
    "幸福满院"
]

def sample_url():
    return random.sample(url_paths, 1)[0]

def sample_ip():
    slice = random.sample(ip_slices, 4)
    return ".".join([str(item) for item in slice])

def sample_status():
    return random.sample(status_code, 1)[0]

def sample_referer():
    if random.uniform(0, 1) > 0.2:
        return "-"
    refer_str = random.sample(http_referers, 1)
    #print refer_str[0]
    query_str = random.sample(search_keyword, 1)
    #print query_str[0]
    return refer_str[0].format(query=query_str[0])

def generate_log(count = 10):
    #添加时间
    time_str = time.strftime("%Y-%m-%d %H:%M:%S",time.localtime())

    f = open("/home/ricky/data/spark/pro/access.log","w+")
    while count >= 1:
        query_log = "{ip}\t{localtime}\t\"GET /{url} HTTP/1.1\"\t{refer}\t{status_code}".format(url= sample_url(),ip = sample_ip(),refer = sample_referer(),status_code =sample_status(),localtime = time_str)
        print query_log
        f.write(query_log+"\n")
        count = count -1

if __name__ == '__main__':
    generate_log(100)
