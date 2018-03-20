#coding=utf-8

from selenium import webdriver
from selenium.webdriver.support.select import Select
import os,time

# 打开chrome浏览器
driver = webdriver.Chrome()
file = "file:///media/ricky/项目/selenium/select.html"
driver.get(file)

sel = driver.find_element_by_xpath("//select[@id='status']")
Select(sel).select_by_value("0")
time.sleep(2)
Select(sel).select_by_value("1")
time.sleep(2)
Select(sel).select_by_value("2")
time.sleep(2)
Select(sel).select_by_value("3")
time.sleep(5)

driver.quit()
