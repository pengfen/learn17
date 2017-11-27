!function(n){function e(t){if(a[t])return a[t].exports;var s=a[t]={exports:{},id:t,loaded:!1};return n[t].call(s.exports,s,s.exports,e),s.loaded=!0,s.exports}var a={};return e.m=n,e.c=a,e.p="",e(0)}([function(module,exports,__webpack_require__){eval("var __WEBPACK_AMD_DEFINE_ARRAY__, __WEBPACK_AMD_DEFINE_RESULT__;'use strict';\n\n!(__WEBPACK_AMD_DEFINE_ARRAY__ = [__webpack_require__(6), __webpack_require__(5), __webpack_require__(11), __webpack_require__(10)], __WEBPACK_AMD_DEFINE_RESULT__ = function (utils, marked, hljs, loading) {\n    var jdata = $('#jinja-data').data();\n\n    var md = $('#editor').val();\n    var preview = $('#preview');\n\n    md = marked(md);\n    preview.html(md);\n    preview.find('a').attr('target', '_blank');\n    hljs.initHighlightingOnLoad();\n\n    // 开始实验 post\n    function startLabPost(url, next, data) {\n        $.post(url, data || {}).done(function (data) {\n            if (data.status == 'success') {\n                if ($(window).width() < 769) {\n                    loading.hide();\n                    window.location.href = next;\n                } else {\n                    setTimeout(function () {\n                        loading.hide();\n                        window.location.href = data.nexturl;\n                    }, 2000);\n                }\n            } else {\n                if (data.nexturl) {\n                    window.location.href = data.nexturl;\n                } else {\n                    loading.hide();\n                    utils.flashMessage(data.status, data.message);\n                }\n            }\n        });\n    }\n\n    // 开始实验\n    function startLab() {\n        var evaluationCourseModal = $('#start-evaluation-course');\n\n        // 当前没有正在进行的实验\n        $('.lab-item-start').on('click', function () {\n            var url = $(this).data('url');\n            var next = $(this).data('mobileUrl');\n\n            if ($(this).hasClass('is-evaluation-course')) {\n                evaluationCourseModal.modal('show');\n                evaluationCourseModal.find('.start-confirm').off('click').on('click', function () {\n                    evaluationCourseModal.modal('hide');\n                    loading.show();\n                    startLabPost(url, next);\n                });\n            } else if ($(this).hasClass('member-vm')) {\n                // 会员开始实验\n                var vipModal = $('#vip-startlab-modal');\n                vipModal.modal('show');\n\n                $('.newvm', vipModal).off('click').on('click', function () {\n                    vipModal.modal('hide');\n                    loading.show();\n                    startLabPost(url, next);\n                });\n                $('.oldvm', vipModal).off('click').on('click', function () {\n                    var data = { 'new_env': 0 };\n                    vipModal.modal('hide');\n                    loading.show();\n                    startLabPost(url, next, data);\n                });\n            } else {\n                loading.show();\n                startLabPost(url, next);\n            }\n\n            return false;\n        });\n\n        // 当前有正在进行的实验\n        $('.lab-item-start-newlab').on('click', function () {\n            if ($(this).hasClass('lab-item-started')) {\n                window.location.href = $('#header-continue-lab').attr('href');\n            } else {\n                var url = $(this).data('url');\n                var next = $(this).data('mobileUrl');\n\n                if ($(this).hasClass('is-evaluation-course')) {\n                    evaluationCourseModal.find('.start-newlab').html('<strong>一个实验正在进行，是否停止它，开始新实验？</strong>');\n                    evaluationCourseModal.modal('show');\n                    evaluationCourseModal.find('.start-confirm').off('click').on('click', function () {\n                        evaluationCourseModal.modal('hide');\n                        loading.show();\n\n                        $.post(jdata.startNewlabUrl).done(function (data) {\n                            if (data.status == \"success\") {\n                                // 延迟3秒\n                                setTimeout(function () {\n                                    startLabPost(url, next);\n                                }, 3000);\n                            } else {\n                                loading.hide();\n                                utils.flashMessage(data.status, data.message);\n                            }\n                        });\n                    });\n                } else if ($(this).hasClass('member-vm')) {\n                    // 会员开始实验\n                    var vipModal = $('#vip-startlab-modal');\n                    vipModal.find('.start-newlab').html('<strong>一个实验正在进行，是否停止它，开始新实验？</strong>');\n                    vipModal.modal('show');\n\n                    $('.newvm', vipModal).off('click').on('click', function () {\n                        vipModal.modal('hide');\n                        loading.show();\n                        $.post(jdata.startNewlabUrl).done(function (data) {\n                            if (data.status == 'success') {\n                                setTimeout(function () {\n                                    startLabPost(url, next);\n                                }, 3000);\n                            } else {\n                                loading.hide();\n                                utils.flashMessage(data.status, data.message);\n                            }\n                        });\n                    });\n                    $('.oldvm', vipModal).off('click').on('click', function () {\n                        vipModal.modal('hide');\n                        loading.show();\n                        $.post(jdata.startNewlabUrl).done(function (data) {\n                            if (data.status == 'success') {\n                                setTimeout(function () {\n                                    var data = { 'new_env': 0 };\n                                    startLabPost(url, next, data);\n                                }, 3000);\n                            } else {\n                                loading.hide();\n                                utils.flashMessage(data.status, data.message);\n                            }\n                        });\n                    });\n                } else {\n                    $('#start-newlab').modal('show');\n                    $('.start-newlab-confirm').off('click').on('click', function () {\n                        $('#start-newlab').modal('hide');\n                        loading.show();\n\n                        $.post(jdata.startNewlabUrl).done(function (data) {\n                            if (data.status == \"success\") {\n                                // 延迟3秒\n                                setTimeout(function () {\n                                    startLabPost(url, next);\n                                }, 3000);\n                            } else {\n                                loading.hide();\n                                utils.flashMessage(data.status, data.message);\n                            }\n                        });\n                    });\n                }\n            }\n        });\n    }\n\n    startLab();\n}.apply(exports, __WEBPACK_AMD_DEFINE_ARRAY__), __WEBPACK_AMD_DEFINE_RESULT__ !== undefined && (module.exports = __WEBPACK_AMD_DEFINE_RESULT__));\n\n/*****************\n ** WEBPACK FOOTER\n ** ./src/course/labDoc.js\n ** module id = 0\n ** module chunks = 15\n **/\n//# sourceURL=webpack:///./src/course/labDoc.js?")},function(module,exports){eval('module.exports = lib;\n\n/*****************\n ** WEBPACK FOOTER\n ** external "lib"\n ** module id = 1\n ** module chunks = 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27\n **/\n//# sourceURL=webpack:///external_%22lib%22?')},function(module,exports,__webpack_require__){eval("module.exports = (__webpack_require__(1))(36);\n\n/*****************\n ** WEBPACK FOOTER\n ** delegated ./node_modules/bootstrap/dist/js/npm.js from dll-reference lib\n ** module id = 2\n ** module chunks = 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27\n **/\n//# sourceURL=webpack:///delegated_./node_modules/bootstrap/dist/js/npm.js_from_dll-reference_lib?")},,function(module,exports,__webpack_require__){eval("var __WEBPACK_AMD_DEFINE_ARRAY__, __WEBPACK_AMD_DEFINE_RESULT__;'use strict';\n\n!(__WEBPACK_AMD_DEFINE_ARRAY__ = [__webpack_require__(2)], __WEBPACK_AMD_DEFINE_RESULT__ = function (bs) {\n\n    var jdata = $('#jinja-data').data();\n\n    // 解析表单serialize字符串为JSON\n    (function ($) {\n        $.fn.serializeJSON = function () {\n            var serializeObj = {};\n            var array = this.serializeArray();\n            var str = this.serialize();\n\n            $(array).each(function () {\n                if (serializeObj[this.name]) {\n                    if ($.isArray(serializeObj[this.name])) {\n                        serializeObj[this.name].push(this.value);\n                    } else {\n                        serializeObj[this.name] = [serializeObj[this.name], this.value];\n                    }\n                } else {\n                    serializeObj[this.name] = this.value;\n                }\n            });\n            return serializeObj;\n        };\n    })(jQuery);\n\n    // 显示 flash 提示信息\n    function flashMessage(status, message, timeout, callback) {\n        var fn;\n\n        if (timeout && typeof timeout === 'function' && !callback) {\n            fn = timeout;\n            timeout = null;\n        } else if (callback && typeof callback === 'function') {\n            fn = callback;\n        }\n\n        var flashModal = $('#flash-message'),\n            timeout = parseInt(timeout) || 3000,\n            status = status == 'error' ? 'danger' : status,\n            html = '' + '<div class=\"alert alert-' + status + '\" style=\"text-align: center;\">' + message + '</div>';\n\n        flashModal.find('.modal-dialog').html(html);\n        flashModal.modal('show');\n        flashModal.on('hide.bs.modal', function () {\n            // 运行回调函数\n            fn && fn();\n        });\n\n        setTimeout(function () {\n            flashModal.modal('hide');\n        }, timeout);\n    }\n\n    // 显示 modal 提示信息\n    function modalMessage(status, message, title) {\n        var msgModal = $('#modal-message'),\n            status = status == 'error' ? 'danger' : status,\n            html = '' + '<div class=\"alert alert-' + status + '\" style=\"text-align: center;\">' + message + '</div>';\n\n        if (title && typeof title === 'string') {\n            msgModal.find('.modal-title').html(title);\n        }\n\n        msgModal.find('.modal-body').html(html);\n        msgModal.modal('show');\n    }\n\n    // 用户排名\n    function usersTop(url, documentObj, loadMethod) {\n        /**\n         * 用户排名\n         *\n         * @params {string} url 获取用户排名的url\n         * @params {object} documentObj 加载用户排名到页面的jquery document对象\n         * @params {string} loadMethod 加载用户排名到页面的方式\n         *     默认为 $(domObj).append(html);\n         *     也可以使用 $(domObj).html(html);\n         *     传入'append'或者'html'会调用对应方法\n         *\n         */\n\n        $.get(url).done(function (data) {\n            if (data.status == 'success') {\n                var ranks = data.rank;\n                var html = '';\n\n                html += '' + '<div class=\"sidebox users-top\">' + '<div class=\"sidebox-header users-top-header\">' + '<h4 class=\"sidebox-title\">学习排名<span>（有效学习时间）</span></h4>' + '</div>';\n\n                for (var i = 0, len = ranks.length; i < len; i++) {\n                    html += '' + '<div class=\"clearfix sidebox-body users-top-body\">' + '<div class=\"pull-left users-top-left\">' + '<a class=\"pull-left\" href=\"' + ranks[i].home_page + '\">' + '<img class=\"users-top-img\" src=\"' + ranks[i].avatar + '\">';\n\n                    if (ranks[i].is_member) {\n                        html += '<span class=\"user-vip\"><img src=\"' + jdata.vipIcon + '\"></span>';\n                    }\n\n                    html += '' + '</a>' + '<div class=\"pull-left\">' + '<span>' + ranks[i].name + '</span><br>' + '<span class=\"users-top-study-time\">' + ranks[i].study_time + ' 分钟</span>' + '</div>' + '</div>' + '<div class=\"pull-right users-top-right\">' + '<img src=\"' + ranks[i].image + '\">' + '</div>' + '</div>';\n                }\n\n                html += '' + '</div>';\n\n                if (loadMethod == 'html') {\n                    $(documentObj).html(html);\n                } else {\n                    $(documentObj).append(html);\n                }\n            }\n        });\n    }\n\n    return {\n        flashMessage: flashMessage,\n        modalMessage: modalMessage,\n        usersTop: usersTop\n    };\n}.apply(exports, __WEBPACK_AMD_DEFINE_ARRAY__), __WEBPACK_AMD_DEFINE_RESULT__ !== undefined && (module.exports = __WEBPACK_AMD_DEFINE_RESULT__));\n\n/*****************\n ** WEBPACK FOOTER\n ** ./src/base/utils.js\n ** module id = 4\n ** module chunks = 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27\n **/\n//# sourceURL=webpack:///./src/base/utils.js?")},function(module,exports,__webpack_require__){eval("module.exports = (__webpack_require__(1))(67);\n\n/*****************\n ** WEBPACK FOOTER\n ** delegated ./node_modules/marked/lib/marked.js from dll-reference lib\n ** module id = 5\n ** module chunks = 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27\n **/\n//# sourceURL=webpack:///delegated_./node_modules/marked/lib/marked.js_from_dll-reference_lib?")},function(module,exports,__webpack_require__){eval("var __WEBPACK_AMD_DEFINE_ARRAY__, __WEBPACK_AMD_DEFINE_RESULT__;'use strict';\n\n!(__WEBPACK_AMD_DEFINE_ARRAY__ = [__webpack_require__(2), __webpack_require__(4), __webpack_require__(9), __webpack_require__(5), __webpack_require__(8), __webpack_require__(7)], __WEBPACK_AMD_DEFINE_RESULT__ = function (bs, utils, io, marked) {\n\n    var jdata = $('#base-data').data();\n\n    initBootstrapPlugins();\n    flaskFlash();\n    vipClick();\n    lottery();\n\n    window.io = io;\n\n    // marked 配置\n    marked.setOptions({\n        renderer: new marked.Renderer(),\n        gfm: true,\n        tables: true,\n        breaks: false,\n        pedantic: false,\n        sanitize: true,\n        smartLists: true,\n        smartypants: false,\n        block_rules: {\n            latex: {\n                reg: /^( *\\$\\$[^\\$]+\\$\\$)+/,\n                lex: function lex(src, cap) {\n                    src = src.substring(cap[0].length);\n                    cap = cap[0].replace(/\\$/gm, '');\n                    return { src: src, text: cap };\n                },\n                parse: function parse(cap) {\n                    return '<p>' + katex.renderToString(cap) + '</p>';\n                }\n            }\n        },\n        inline_rules: {\n            latex: {\n                reg: /^\\$\\$([^\\$])+\\$\\$/,\n                parse: function parse(src, cap) {\n                    src = src.substring(cap[0].length);\n                    cap = cap[0].replace(/\\$/gm, '');\n                    return {\n                        src: src,\n                        text: katex.renderToString(cap)\n                    };\n                }\n            }\n        },\n        inlineTextReg: /^[\\s\\S]+?(?=[\\\\<!\\[_*`\\$]| {2,}\\n|$)/\n    });\n    // from https://github.com/chjj/marked/issues/642\n    marked.Lexer.rules.gfm.heading = marked.Lexer.rules.normal.heading;\n    marked.Lexer.rules.tables.heading = marked.Lexer.rules.normal.heading;\n\n    $('.switch-btn input[type=checkbox]').bootstrapSwitch({\n        onText: '是',\n        offText: '否',\n        onColor: 'success'\n    });\n\n    // 点击统计\n    $('body').on('click', '.stat-event', function () {\n        var name = $(this).data('stat');\n        $.post('/api/logstash', { name: name });\n    });\n\n    // 初始化Bootstrap插件\n    // tooltip & popover\n    function initBootstrapPlugins() {\n        $(function () {\n            $('[data-toggle=\"tooltip\"]').tooltip();\n            $('[data-toggle=\"popover\"]').popover();\n        });\n    }\n\n    // 显示来自后台的 flash\n    function flaskFlash() {\n        if (jdata.flash) {\n            $('#flash').modal('show');\n            setTimeout(function () {\n                $('#flash').modal('hide');\n            }, 3000);\n        }\n    }\n\n    // 点击VIP用户头像旁的V标志\n    function vipClick() {\n        $('.header .user-vip').on('click', function () {\n            var url = $(this).data('url');\n            window.location.href = url;\n            return false;\n        });\n    }\n\n    // 活动广告\n    function activityModal() {\n        var now = Date.now();\n        var s = localStorage.activeTime;\n        var x = (now - s) / 1000 / 60 / 60;\n        var day = new Date().getDate();\n\n        if (x < 1) {\n            return false;\n        }\n\n        localStorage.activeTime = now;\n        $('#double12').modal('show');\n        $('#doubel12 a').on('click', function () {\n            $('#double12').modal('hide');\n        });\n    }\n\n    function lottery() {\n        if (!jdata.isLogin || $('#lottery-modal').length) {\n            return false;\n        }\n\n        var fone = localStorage.fone;\n        var deadline = localStorage.lottery;\n        var today = new Date();\n\n        today = today.getDate();\n\n        if (Number(deadline) != today || !fone) {\n            $('#lottery-modal').modal('show');\n\n            localStorage.lottery = today;\n            localStorage.fone = 1;\n        }\n    }\n\n    // 学校提示\n    $(function () {\n        $('input[name=school]').on('keypress keyup', function () {\n            $('.college-suggestion').remove();\n            var self = this;\n            var key = $(this).val();\n            $.get('/search/college', { search: key }).done(function (data) {\n                if (data.data.length) {\n                    var res = data.data;\n                    var html = '<div class=\"college-suggestion\">';\n\n                    for (var i = 0; i < res.length; i++) {\n                        html += '<a>' + res[i] + '</a>';\n                    }\n                    html += '</div>';\n\n                    $(self).parent().append(html);\n                    $('.college-suggestion').css({\n                        position: 'absolute',\n                        padding: '4px',\n                        border: 'solid 1px #eee',\n                        'border-top': 'none',\n                        background: '#fff',\n                        'z-index': 2000\n                    }).find('a').css({\n                        display: 'block',\n                        padding: '6px 8px',\n                        color: '#4c5157',\n                        'font-size': '13px'\n                    }).hover(function () {\n                        $(this).css({\n                            color: '#fff',\n                            background: '#0c9',\n                            'text-decoration': 'none'\n                        });\n                        $(self).val($(this).text());\n                    }, function () {\n                        $(this).css({\n                            color: '#4c5157',\n                            background: '#fff'\n                        });\n                    });\n\n                    $('.college-suggestion').on('click', function () {\n                        $('.college-suggestion').remove();\n                    });\n                }\n            });\n        });\n    });\n\n    // 即时搜索\n    $(function () {\n        $('.navbar-form input[name=search]').on('keypress keyup', function () {\n            var self = this;\n            var key = $(this).val();\n            $.get('/search/json', { search: key }).done(function (data) {\n                if (data.data.length) {\n                    var res = data.data;\n                    var html = '<div class=\"search-autocomplete\">';\n\n                    for (var i = 0; i < res.length; i++) {\n                        html += '<a href=\"/search?search=' + encodeURIComponent(res[i]) + '\">' + res[i] + '</a>';\n                    }\n                    html += '</div>';\n\n                    $('body').append(html);\n                    $('.search-autocomplete').css({\n                        position: 'fixed',\n                        top: $(self).offset().top + $(self).height() + 14,\n                        left: $(self).offset().left + 1,\n                        width: $(self).width() + 24,\n                        padding: '4px',\n                        border: 'solid 1px #eee',\n                        'border-top': 'none',\n                        background: '#fff',\n                        'z-index': 2000\n                    }).find('a').css({\n                        display: 'block',\n                        padding: '6px 8px',\n                        color: '#4c5157',\n                        'font-size': '13px'\n                    }).hover(function () {\n                        $(this).css({\n                            color: '#fff',\n                            background: '#0c9',\n                            'text-decoration': 'none'\n                        });\n                        $(self).val($(this).text());\n                    }, function () {\n                        $(this).css({\n                            color: '#4c5157',\n                            background: '#fff'\n                        });\n                    });\n\n                    $('.search-autocomplete').on('mouseleave click', function () {\n                        $('.search-autocomplete').remove();\n                    });\n                } else {\n                    $('.search-autocomplete').remove();\n                }\n            });\n        });\n    });\n\n    // 控制表单字数显示\n    $(function () {\n        $('.words-ctrl form input, .words-ctrl form textarea').on('keyup keypress', function () {\n            var min = $(this).attr('min');\n            var max = $(this).attr('max');\n            var count = $(this).val().length;\n            var str = '';\n\n            if (min !== undefined && max !== undefined) {\n                if (count < min) {\n                    str = '<span class=\"text-danger\">字数太少，至少需要' + min + '字</span>';\n                } else if (count > max) {\n                    str = '<span class=\"text-danger\">字数太多，不能超过' + max + '字</span>';\n                } else {\n                    str = '<span class=\"text-success\">还能输入' + (max - count) + '字</span>';\n                }\n\n                $(this).parent().find('.help-block').html(str);\n            }\n        });\n    });\n\n    $('#flash-modal').modal();\n\n    $('#new-features').modal('show').find('.btn').on('click', function () {\n        window.open($(this).data('href'), 'blank');\n    });\n\n    return utils;\n}.apply(exports, __WEBPACK_AMD_DEFINE_ARRAY__), __WEBPACK_AMD_DEFINE_RESULT__ !== undefined && (module.exports = __WEBPACK_AMD_DEFINE_RESULT__));\n\n/*****************\n ** WEBPACK FOOTER\n ** ./src/base/base.js\n ** module id = 6\n ** module chunks = 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27\n **/\n//# sourceURL=webpack:///./src/base/base.js?")},function(module,exports,__webpack_require__){eval('var __WEBPACK_AMD_DEFINE_ARRAY__, __WEBPACK_AMD_DEFINE_RESULT__;\'use strict\';\n\n!(__WEBPACK_AMD_DEFINE_ARRAY__ = [__webpack_require__(2)], __WEBPACK_AMD_DEFINE_RESULT__ = function () {\n    var qqGroupOptions = {\n        placement: \'top\',\n        html: true,\n        title: \'实验楼用户群\',\n        content: \'<a target="_blank" href="http://shang.qq.com/wpa/qunwpa?idkey=c080b8e55a975b6c9cf38cbaa8c9af764ddae862bdb4e78682af132862e3c57e">469785612<br>(在校生学习群)</a><a target="_blank" href="http://shang.qq.com/wpa/qunwpa?idkey=c89a6f4810351a227cb5a8db5f2dd43331da23316d8c55ecaf37fb2c858785b9">群1 : 241818371 </a><a>群2：235772301</a><a>群3：450412940</a>\',\n        template: \'<div class="popover footer-qq-group" role="tooltip"><div class="arrow"></div><h3 class="popover-title footer-qq-group-header"></h3><div class="popover-content footer-qq-group-body"></div></div>\',\n        trigger: \'click\'\n    };\n    $(\'.footer-qq-item\').popover(qqGroupOptions);\n}.apply(exports, __WEBPACK_AMD_DEFINE_ARRAY__), __WEBPACK_AMD_DEFINE_RESULT__ !== undefined && (module.exports = __WEBPACK_AMD_DEFINE_RESULT__));\n\n/*****************\n ** WEBPACK FOOTER\n ** ./src/base/footer.js\n ** module id = 7\n ** module chunks = 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27\n **/\n//# sourceURL=webpack:///./src/base/footer.js?')},function(module,exports,__webpack_require__){eval("module.exports = (__webpack_require__(1))(33);\n\n/*****************\n ** WEBPACK FOOTER\n ** delegated ./node_modules/bootstrap-switch/dist/js/bootstrap-switch.js from dll-reference lib\n ** module id = 8\n ** module chunks = 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27\n **/\n//# sourceURL=webpack:///delegated_./node_modules/bootstrap-switch/dist/js/bootstrap-switch.js_from_dll-reference_lib?")},function(module,exports,__webpack_require__){eval("module.exports = (__webpack_require__(1))(72);\n\n/*****************\n ** WEBPACK FOOTER\n ** delegated ./node_modules/socket.io-client/lib/index.js from dll-reference lib\n ** module id = 9\n ** module chunks = 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27\n **/\n//# sourceURL=webpack:///delegated_./node_modules/socket.io-client/lib/index.js_from_dll-reference_lib?")},function(module,exports,__webpack_require__){eval("var __WEBPACK_AMD_DEFINE_ARRAY__, __WEBPACK_AMD_DEFINE_RESULT__;'use strict';\n\n!(__WEBPACK_AMD_DEFINE_ARRAY__ = [__webpack_require__(2)], __WEBPACK_AMD_DEFINE_RESULT__ = function () {\n    var loading = '' + '<div class=\"loading-modal\">' + '<i class=\"fa fa-spinner fa-pulse\"></i>' + '</div>';\n\n    $('body').append(loading);\n\n    $('.loading-modal').css({\n        display: 'none',\n        position: 'fixed',\n        top: 0,\n        left: 0,\n        right: 0,\n        bottom: 0,\n        background: 'rgba(0,0,0,0.5)',\n        'text-align': 'center',\n        'z-index': 1600\n    });\n    $('.loading-modal i').css({\n        'margin-top': $(window).height() * 0.4,\n        color: '#fff',\n        'font-size': '35px'\n    });\n\n    $(window).on('resize', function () {\n        $('.loading-modal i').css({\n            'margin-top': $(window).height() * 0.4\n        });\n    });\n\n    // 显示\n    function show(container) {\n        if (container) {\n            $('.loading-modal').css({\n                top: container.offset().top,\n                left: container.offset().left,\n                width: container.width(),\n                height: container.height()\n            });\n        }\n        $('.loading-modal').show();\n    }\n\n    // 隐藏\n    function hide() {\n        $('.loading-modal').css({\n            top: 0,\n            left: 0,\n            width: 'auto',\n            height: 'auto'\n        });\n        $('.loading-modal').hide();\n    }\n\n    return {\n        show: show,\n        hide: hide\n    };\n}.apply(exports, __WEBPACK_AMD_DEFINE_ARRAY__), __WEBPACK_AMD_DEFINE_RESULT__ !== undefined && (module.exports = __WEBPACK_AMD_DEFINE_RESULT__));\n\n/*****************\n ** WEBPACK FOOTER\n ** ./src/modules/loading.js\n ** module id = 10\n ** module chunks = 1 2 3 4 5 6 7 8 11 15\n **/\n//# sourceURL=webpack:///./src/modules/loading.js?")},function(module,exports){eval('module.exports = hljs;\n\n/*****************\n ** WEBPACK FOOTER\n ** external "hljs"\n ** module id = 11\n ** module chunks = 1 2 3 4 6 7 8 10 15 16 19\n **/\n//# sourceURL=webpack:///external_%22hljs%22?')}]);