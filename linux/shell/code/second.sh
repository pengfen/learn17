#!/bin/bash

LINES=`wc -l /etc/inittab`
echo $LINES

FINELINES=`echo $LINES | cut -d' ' -f1`
echo FINELINES

[ $FINELINES -gt 100 ] && echo "/etc/inittab is a big file." || echo "/etc/inittab is a small file."

#chmod +x second.sh
