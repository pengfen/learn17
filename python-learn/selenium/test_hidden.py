#coding=utf-8

from selenium import webdriver
from selenium.webdriver.support.select import Select
import os,time

driver = webdriver.Chrome()
file_path = "file:///media/ricky/项目/selenium/hidden.html"
driver.get(file_path)
time.sleep(3)

js = 'document.querySelectorAll("select")[0].style.display="block";'
driver.execute_script(js)
time.sleep(3)

sel = driver.find_element_by_tag_name("select")
Select(sel).select_by_value("test2")
time.sleep(3)

#关闭打开的浏览器
driver.quit()
