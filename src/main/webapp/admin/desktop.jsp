<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false"%>
<!doctype html>
<html data-ng-app="alyDesktop">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>爱乐游官网后台管理</title>
    <link href="../images/favicon.ico" rel="shortcut icon">
    <link href="../images/favicon.ico" rel="icon">
    <link href="../images/favicon.ico" rel="bookmark">
    <link href="../images/favicon16x16.png" rel="icon" sizes="16x16">
    <link href="../images/favicon32x32.png" rel="icon" sizes="32x32">
    <link href="assets/css/bootstrap.css" rel="stylesheet">
    <link href="assets/css/bootstrap-responsive.css" rel="stylesheet">
    <link href="assets/css/jquery-ui.css" rel="stylesheet">
    <link href="assets/css/select2.css" rel="stylesheet">
    <link href="assets/css/zTreeStyle.css" rel="stylesheet">
    <link href="assets/css/ng-grid.css" rel="stylesheet">
    <link href="assets/css/prettify.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>
<body data-ng-cloak class="ng-cloak desktop">
<h1 class="hide">爱乐游官网后台管理</h1>
<div data-ng-show="overlay.message" class="overlay">
    <p class="message ng-binding" data-ng-bind="overlay.message">正在加载... ...</p>
</div>
<div data-ng-controller="AlyDesktopController">
    <div class="sidebar">
        <aside class="nav">
            <h2 class="hide">右侧菜单</h2>
            <div class="nav-profile">
                <a class="nav-user-link"> <i class="icon-white icon-user"></i>&nbsp;<span data-ng-bind="loginUser.username || '未登录'"></span></a>
                <a class="nav-sign-out pull-right" data-ng-click="logout()"> <i class="icon-white icon-off"></i>&nbsp;退出</a>
            </div>
            <div class="nav-search">
                <form id="location-bar">
                    <input type="text" name="site-search" value="" id="site-search" placeholder="Search…" spellcheck="false">
                    <ul class="list-group search-bar-tip" style="display: none;">
                        <li class="list-group-item help">Search Tips</li>
                        <li class="list-group-item help"><b>#tagname</b><br>Search a specific tag</li>
                        <li class="list-group-item help"><b>@username</b><br>Search a specific user</li>
                        <li class="list-group-item help"><b>keywords</b><br>Search specific keywords</li>
                        <li class="list-group-item help"><b>@username #tagname</b><br>Search tags for a specific user</li>
                        <li class="list-group-item help"><b>@username keywords</b><br>Search keywords for a specific user</li>
                        <li class="list-group-item help"><b>http://</b><br>Search a specific web page on Delicious</li>
                    </ul>
                </form>
            </div>
            <ul class="nav-links unstyled">
                <li data-ng-class="{on: $state.includes('manual')}"><a data-ui-sref="manual"> <i class="icon-white icon-pencil"></i>&nbsp;使用说明</a></li>
                <li data-ng-class="{on: $state.includes('news')}"><a data-ui-sref="news"> <i class="icon-white icon-tasks"></i>&nbsp;新闻动态</a></li>
                <li data-ng-class="{on: $state.includes('recruit')}"><a data-ui-sref="recruit"> <i class="icon-white icon-th"></i>&nbsp;人事招聘</a></li>
                <li data-ng-class="{on: $state.includes('games')}"><a data-ui-sref="games"> <i class="icon-white icon-list-alt"></i>&nbsp;旗下游戏</a></li>
                <li data-ng-class="{on: $state.includes('settings')}"><a data-ui-sref="settings"> <i class="icon-white icon-cog"></i>&nbsp;设　　置</a></li>
                <li data-ng-class="{on: $state.includes('about')}"><a data-ui-sref="about"> <i class="icon-white icon-book"></i>&nbsp;关于本站</a></li>
            </ul>
        </aside>
    </div>
    <section class="main-container" data-ui-view data-ng-animate="{enter:'fade-enter'}">
    </section>
</div>
<!-- 删除确认对话框 -->
<div class="hide" data-draggable data-modal="confirmDeleteShowFlag" data-options="commonModalOptions">
    <div class="modal-header btn-danger">
        <button type="button" class="close" data-ng-click="confirmDeleteShowFlag=false">&times;</button>
        <h5>删除确认</h5>
    </div>
    <div class="modal-body">
        <div>
            <span><strong>您确认要删除这条记录？</strong></span>
        </div>
        <div class="delete-info">
            {{deleteConfiguration.message}}
        </div>
    </div>
    <div class="modal-footer">
        <button class="btn" data-ng-click="confirmDeleteShowFlag=false">取消</button>
        <button class="btn btn-primary" data-ng-click="confirmToDelete()" data-condition-focus="confirmDeleteShowFlag">确定</button>
    </div>
</div>
<!-- 消息提示框 -->
<div class="hide" data-draggable data-modal="alertMessageShowFlag" data-options="commonModalOptions">
    <div class="modal-header btn-{{configuration.type}}">
        <button type="button" class="close" data-ng-click="alertMessageShowFlag=false">&times;</button>
        <h5>{{configuration.title}}</h5>
    </div>
    <div class="modal-body">
        <div>
            <span><strong>{{configuration.title}}！</strong></span>
        </div>
        <div class="success-info">
            {{configuration.message}}
        </div>
    </div>
    <div class="modal-footer">
        <button class="btn btn-primary" data-ng-click="alertMessageShowFlag=false" data-condition-focus="alertMessageShowFlag">确定</button>
    </div>
</div>
<!-- 表单编辑 -->
<div class="hide" data-modal="editModalFlag" data-options="commonModalOptions">
    <div class="modal-header btn-info">
        <button type="button" class="close" data-ng-click="editModalFlag=false">&times;</button>
        <h5>{{config.title}}</h5>
    </div>
    <div data-entity-edit-form="{{config.templateUrl}}"></div>
    <div class="modal-footer">
        <button class="btn" data-ng-click="editModalFlag=false">取消</button>
        <button class="btn btn-primary" data-ng-click="confirmToSave()">保存</button>
    </div>
</div>
<script src="assets/js/jquery.js"></script>
<script src="../jslib/jquery.markdown-0.2.js"></script>
<script src="assets/js/jquery-ui.js"></script>
<script src="assets/js/jquery-ui-timepicker-addon.js"></script>
<script src="assets/js/jquery-ui-timepicker-zh-CN.js"></script>
<script src="assets/js/jquery.ztree.core.js"></script>
<script src="assets/js/bootstrap.js"></script>
<script src="assets/js/select2.js"></script>
<script src="assets/js/mousetrap.js"></script>
<script src="assets/js/mousetrap-global.js"></script>
<script src="assets/js/angular.js"></script>
<script src="assets/js/angular-ui.js"></script>
<script src="assets/js/angular-ui-router.js"></script>
<script src="assets/js/angular-cookies.js"></script>
<script src="assets/js/ui-bootstrap-tpls.js"></script>
<script src="assets/js/ng-grid.js"></script>
<script src="assets/js/prettify.js"></script>
<script src="js/model.js"></script>
<script src="js/common.js"></script>
<script src="js/desktop.js"></script>
</body>
</html>

