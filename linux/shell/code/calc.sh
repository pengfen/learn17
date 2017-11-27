#!/bin/bash

read -t 30 -p "Please input num1 : " num1
read -t 30 -p "Please input num2 : " num2
read -t 30 -p "Please input a operator : " ope

if [ -n "$num1" -a -n "$num2" ]
then
    test1=$(echo $num1 | sed 's/[0-9]//g')
    test2=$(echo $num2 | sed 's/[0-9]//g')
    if [ -z "$test1" -a -z "$test2" ]
    then
        if [ "$ope" == "+"];
        then
            sum=$(($num1 + $num2))
        elif [ "$ope" == '-']
        then
            sum=$(($num1 - $num2))
        elif [ "$ope" == '*']
        then
            sum=$(($num1 * $num2))
        elif [ "$ope" == '/']
        then
            sum=$(($num1 / $num2))
        else
            echo "Please enter a valid symbol"
            exit 10
        fi
    else
        echo "Please enter a valid value"
        exit 11
    fi
else
    echo "you are rong"
    exit
fi

echo " $num1 $ope $num2 : $sum"

