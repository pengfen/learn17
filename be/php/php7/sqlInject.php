<?php

/**
    SQL 注入类型

    Union Based (Interger Based String Based)
    Error Based (Error Based Double Query)
    Blind SQL injection (Time Based Boolean Based)

    SQL 注入分类
    Inband
    Out-of-Band
    Inferential

    SQL 注入防御
    magic_quotes_gpc (版本 5.3 过期 5.4移除, $_SERVER, gctcnv() 输入输出流 SQL中的 in/limit/order ...)
    使用预处理语句 (PDO MySQLi)
    转义/过滤 intval() mysql_real_escape_string()

    正确的过滤
    使用合理的字符集 (utf-8)
    宽字节注入 (受字符集影响)

    XSS 类型
    反射型 (Reflected)
    存储型 (Stored)
    DOM 型
    其他类型

    XSS 危害
    窃取 Cookie
    Keylogging
    Phishing (钓鱼)
    Dos 攻击
    其他

    XSS 防御
    转义 / 编码 (htmlspecialchars())
    过滤 strip_tags()
    CSP(Content Security Policy)
    第三方库 HTMLPurifier htmLawed Zend_Filter_Input

    会话却持
    XSS 嗅探 中间人攻击

    会话毒化 Cookie 注入

    会话攻击防御 
    session_regenerate_id 二级令牌 检测 UA 和用户 IP

    File Inclusion (文件包含)

    文件包含漏洞防御
    白名单
    $page_files = array(
        'about' => 'about.html',
        'photos' => 'photos.html',
        'contact' => 'contact.html',
        'home' => 'home.html'
    );

    if (in_array($_GET['page'], array_keys($page_files))) {
	    include $page_files[$_GET['page']];
    } else {
	    include $page_files['home'];
    }

    其他防御手段 PHP 配置 服务器配置

    Nullbyte
    NULL 是控制字符 值为 0
    字符集中的 NULL unicode:\u0000
    C 语言中的 NULL

    PHP 中受影响的函数
    copy() is_file() file_put_contents() file() glob() is_dir() file_exists() fileatime() filectime() filegroup() fileinode() filemtime() fileowner() fileperms() filesize() filetype() fopen() is_executable() is_link() is_readable() is_writable() lchgrp() lchown() link() linkinfo() lstat() mkdir() pathinfo() popen() readfile() realpath() rename() rmdir() stat() symlink() touch()

    NULLbyte 防御
    替换 $input = str_replace(chr(0).''.$input);
    白名单
    升级PHP版本

    CSRF (跨站请求伪造)
    CSRF 漏洞防御 
    Cookie Hashing 验证码 One-Time Tokens

    命令执行
    exec passthru shell_exec 

    代码执行总结
    eval() preg_replace($pattern, $replacement, $subject) dynamic variable (动态变量) create_function
    其他 (ob_start() unserialize() array_map())

    代码执行防御
    禁用 过滤特殊字符 使用白名单

    其他漏洞
    逻辑漏洞 
    目录遍历
    文件上传
    会话劫持
    LDAP 注入
    http 拆分

    PHP 安全配置
    PHP 有大约 283 个配置项 安全相关的配置项大概有 27 项 常见的有
    allow_url_fopen
    enable_dl
    disable_functions
    display_errors
    expose_php
    allow_url_include
    file_uploads
    open_basedir
    ...

    PHP 漏洞防御
    知己知彼 
        代码审计 漏洞扫描

    WAF 
        Mod_security suhosin
*/