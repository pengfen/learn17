#!/bin/bash

read -p "Please input a filename : " file

if [ -z "$file" ]
then
    echo "Error, please input a filename"
    exit 1
elif [ ! -e "$file" ]
then
    echo "Your input is not a file!"
    exit 2
elif [ -f "$file" ]
then
    echo "$file is a regulare file"
elif [ -d "$file" ]
then
    echo "$file is a directory"
else
    echo "$ifle is an other file"
fi

#if [ -f file ] 如果文件存在
#if [ -d ... ] 如果目录存在
#if [ -s file ] 如果文件存在且非空
#if [ -r file ] 如果文件存在且可读
#if [ -w file ] 如果文件存在且可写
#if [ -x file ] 如果文件存在且可执行

#if [ -n string ] 如果字符串非空 返回0(true)
#if [ -z string ] 如果字符串长度为0为真
