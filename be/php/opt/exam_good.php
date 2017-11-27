<?php

$array_1 = $array_2 = range(1000, 2000);
shuffle($array_1);
shuffle($array_2);

$array_merged = array_merge($array_1, $array_2);
var_dump($array_1, $array_2, $array_merged);