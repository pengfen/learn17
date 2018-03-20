#coding=utf-8

from pageobject_support import callable_find_by as find_by
from selenium import webdriver
import time

class BaiduSearchPage(object):
    def __init__(self, driver):
        self._driver = driver

    search_box = find_by(id_="kw")
    search_button = find_by(id_="su")

    def search(self, keywords):
        self.search_box().clear()
        self.search_box().send_keys(keywords)
        self.search_button().click()

if __name__ == '__main__':
    driver = webdriver.Chrome()
    driver.get("https://www.baidu.com")
    BaiduSearchPage(driver).search("selenium")
    time.sleep(5)
    driver.close()
