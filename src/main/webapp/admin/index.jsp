<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false"%>
<!DOCTYPE html>
<html data-ng-app="aly">
<head>
    <title>爱乐游官网后台管理</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="../images/favicon.ico" rel="shortcut icon">
    <link href="../images/favicon.ico" rel="icon">
    <link href="../images/favicon.ico" rel="bookmark">
    <link href="../images/favicon16x16.png" rel="icon" sizes="16x16">
    <link href="../images/favicon32x32.png" rel="icon" sizes="32x32">
    <link href="assets/css/bootstrap.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>
<body class="aly ng-cloak" data-ng-cloak data-ng-controller="AlyMainController">
<h1 class="hide">爱乐游官网后台管理</h1>

<!-- 网页正在加载时，“加载”消息提示 -->
<div data-ng-show="overlay.message" class="overlay">
    <p class="message ng-binding" data-ng-bind="overlay.message">正在加载... ...</p>
</div>

<!-- 网站Logo、登录、注册按钮等内容 -->
<header>
    <h2 class="hide">爱乐游官网后台管理</h2>
    <div class="container">
        <div class="row">
            <div class="span2">
                <h3 class="logo"></h3>
            </div>
            <div class="span10">
                <h3 class="description">爱乐游官网后台管理</h3>
            </div>
        </div>
    </div>
</header>

<!-- 其他设备上的应用，登陆按钮 -->
<section class="submenu">
    <div class="container">
        <div class="row">
            <div class="span6 other-device">
                在其他设备上使用
                <a class="btn">iPhone</a>
                <a class="btn">iPad</a>
                <a class="btn">Android</a>
            </div>
        </div>
    </div>
</section>

<!-- 使用图片滑动等效果，吸引用户查看主旨宣传 -->
<section class="slogan-login">
    <h2 class="hide">Slogan Slide</h2>
    <div class="container">
        <div class="row">
            <%--<!-- 不再使用Flash，不好用 -->--%>
            <%--<div class="cu3er_resize span8">--%>
                <%--<div id="cu3er-container"><a href="http://www.adobe.com/go/getflashplayer"> <img src="assets/img/get_flash_player.gif" alt="Get Adobe Flash player" /></a></div>--%>
            <%--</div>--%>
            <form data-ng-submit="confirmLogin()" name="login" class="span6 offset3 login-form form-horizontal" novalidate>
                <div class="control-group">
                    <p class="text-error pagination-centered" data-ng-show="!!errorMessage">{{errorMessage}}</p>
                </div>
                <div class="control-group">
                    <label class="control-label" for="username">用户名</label>
                    <div class="controls">
                        <div class="input-prepend">
                            <span class="add-on"><i class="icon-user"></i></span>
                            <input class="span2" placeholder="用户名" id="username" name="username" type="text" data-ng-model="loginInfo.username" autofocus required/>
                        </div>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="password">密　码</label>
                    <div class="controls">
                        <div class="input-prepend">
                            <span class="add-on"><i class="icon-pencil"></i></span>
                            <input class="span2" placeholder="密码" id="password" name="password" type="password" data-ng-model="loginInfo.password" required>
                        </div>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <button data-ng-disabled="login.$invalid" class="btn span2 btn-primary btn-large" type="submit">登录</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</section>

<!-- 一些声明 -->
<footer class="footer">
    <div class="container">
        <p class="row">
            © Copyright 爱乐游. All Rights Reserved.
        </p>
    </div>
</footer>
<%--<script src="assets/swfobject/swfobject.js"></script>--%>
<%--<script type="text/javascript">--%>
    <%--var flashvars = {};--%>
    <%--flashvars.xml = "assets/swfobject/config.xml";--%>
    <%--flashvars.font = "assets/swfobject/font.swf";--%>
    <%--var attributes = {};--%>
    <%--attributes.wmode = "transparent";--%>
    <%--attributes.id = "slider";--%>
    <%--swfobject.embedSWF("assets/swfobject/cu3er.swf", "cu3er-container", "620", "400", "9", "expressInstall.swf", flashvars, attributes);--%>
<%--</script>--%>
<script src="assets/js/jquery.js"></script>
<script src="../jslib/jquery.markdown-0.2.js"></script>
<script src="assets/js/angular.js"></script>
<script src="assets/js/angular-cookies.js"></script>
<script src="assets/js/ui-bootstrap-tpls.js"></script>
<script src="js/common.js"></script>
<script src="js/index.js"></script>
</body>
</html>