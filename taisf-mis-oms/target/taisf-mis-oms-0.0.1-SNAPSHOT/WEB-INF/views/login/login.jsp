<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html id="html_login">
<head>
    <meta charset="utf-8">
    <title>平台管理系统登录</title>
    <link rel="stylesheet" href="style/login.css?v=22233333">
    <link rel="stylesheet" href="style/bootstrap.min.css" />
    <script type="application/javascript" src="script/jquery-2.1.1.min.js"></script>
    <script src="script/jquery.cookie.js"></script>
	<script type="text/javascript">
       //判断窗口是否是主窗口
       if (top != window) 
    	//如果不是,就在顶层页面打开当前窗口
       {
        top.location.href = window.location.href;  

       } 
    (function (doc, win) {   
        var docEl = doc.documentElement,   
        resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',   
        recalc = function () {   
        var clientWidth = doc.body.clientWidth;   
        if (!clientWidth) return;   
        docEl.style.fontSize = 100 * (clientWidth / 1440) + 'px';   
    };   
    if (!doc.addEventListener) return;   
    win.addEventListener(resizeEvt, recalc, false);   
    doc.addEventListener('DOMContentLoaded', recalc, false);   
    })(document, window);   
</script>
</head>
<body>
<div id="contain">
    <div id="box" >
        <div id="run">
            <img src="img/login_title.png">
        </div>
        <div id="bg" >
            <form class="form-horizontal" onsubmit="return false">
                <div class="divUserName">
                    <input type="text" class="form-control" id="inputEmail3" placeholder="请输入用户名" >
                </div>
                <div class="divPassWord">
                    <input type="password" class="form-control" id="inputPassword3" placeholder="请输入密码">
                </div>
                <div class="divRemPass" id="toR">
                    <div class="checkbox">
                        <label>
                            <input type="checkbox" id="remPass">记住密码
                        </label>
                    </div>
                </div>
                <div class="divSubmit">
                    <button type="submit" class="btn btn-default" id="login">登录</button>
                </div>
            </form>
        </div>
        <span id="aler"></span>
    </div>
</div>
<script src="script/login.js"></script>
</body>
</html>