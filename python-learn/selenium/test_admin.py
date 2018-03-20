#!/usr/bin/python
#coding=utf-8
from selenium import webdriver
import os,time

driver = webdriver.Chrome()
driver.get("http://self.admin.yb1v1.com:8889")
time.sleep(2)

driver.find_element_by_xpath("/html/body/div[1]/div[1]/a[1]").click()

driver.find_element_by_id("email").send_keys("caopeng@163.com")
driver.find_element_by_id("password").send_keys("123456")
time.sleep(2)

driver.find_element_by_xpath("//button[@type='submit']").click()
time.sleep(3)

driver.quit()

