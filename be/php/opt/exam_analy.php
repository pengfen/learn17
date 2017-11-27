<?php

/**
    vim -0 bad.php good.php # 打开二个文件做类比

    ab -n100 -c10 http://ip地址/exam_bad.php
    Requests per second: 28
    Time per request: 35

    ab -n100 -c10 http://ip地址/exam_good.php
    Requests per second: 392
    Time per request: 2.5
*/