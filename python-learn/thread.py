#!/usr/bin/python
#coding=utf-8

# python中的多线程是伪线程 不能充分利用cpu中的多核 但是在IO等待型的场景下多线程还是可以提高效率
# python中的多线程有多种实现方式 利用threading包实现是比较普遍的做法

import threading
from time import ctime
from time import sleep

def music(func):
    for i in range(2):
        print("等待 %s. %s" %(func, ctime()))
        sleep(1)

def movie(func):
    for i in range(2):
        print("等待 %s. %s" %(func, ctime()))
        sleep(5)

threads = []
t1 = threading.Thread(target=music, args=(u'你好'))
threads.append(t1)
t2 = threading.Thread(target=movie, args=(u"不好"))
threads.append(t2)

# if __name__ == '__main__'
for t in threads:
    t.start()

#t.join()
print("%s" %ctime())