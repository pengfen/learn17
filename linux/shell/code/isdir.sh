#!/bin/bash

read -t 30 -p "Please input a dir : " dir
if [ -d "$dir" ]
then
    echo "you are right";
else
    echo "you are wrong"
fi

