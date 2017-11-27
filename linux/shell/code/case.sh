#!/bin/bash

echo "Please input your number (1-9) : "
read number
case "$number" in
1)
    echo "number : 1";;
2)
    echo "number : 2";;
*)
    echo "other number";;
esac
