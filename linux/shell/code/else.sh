#!/bin/bash

echo "Please input your content : "
read content

if [[ "$content" -lt 10 ]]
then
    echo "The content is smaller than 10"
else
    echo "The content is bigger than 10"
fi
