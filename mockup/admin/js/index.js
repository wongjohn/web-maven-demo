/*爱乐游官网后台管理*/
var aly = angular.module("aly", ["ui.bootstrap", "ngCookies", "common"]);

aly.controller("AlyMainController", ["$scope", "$timeout", "$http", "Path", "$q", "$location", "authToken", function ($scope, $timeout, $http, Path, $q, $location, authToken) {
    //内容正在加载时，提示消息
    $scope.overlay = {message: "正在加载... ..."};

    //登录设置
    $scope.alyLoginModalOptions = {
        dialogClass: 'modal aly-dialog',
        backdropFade: true,
        dialogFade: true,
        keyboard: true,
        backdrop: true,
        backdropClick: true
    };

    //用户登录
    $scope.loginInfo = {//用户登录时，需要填写的信息
        username: "",
        password: ""
    };
    $scope.errorMessage = "无效的用户名或密码";//登陆时，如果有错误，显示错误
    //跳转到工作台页面
    var locationToDesktop = function () {
        window.location.href = Path.getOrigin() + "/../desktop.html";
    };
    $scope.confirmLogin = function () {//确认登录
        authToken.setToken("eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE0MjI0MTM0NTUsInN1YiI6ImpvaG4iLCJuYmYiOjE0MjE5ODE0NTUsImF1ZCI6Imh0dHA6XC9cL3d3dy41YWdhbWUuY29tXC9hZG1pbiIsImlzcyI6Imh0dHA6XC9cL3d3dy41YWdhbWUuY29tIiwianRpIjoiMzk5NTlmOWQtYzRlOS00NGQ2LWJjOTgtMmFiNDFlZjlkYTI1IiwiaWF0IjoxNDIxOTgxNDU1fQ.CX2Cmqwo29ju6aSX_wOd6t4kRhscWwhEGSlmFxTw6bc");
        authToken.setUsername("用户名");
        //跳转到工作台页面
        locationToDesktop();
    };
    if(authToken.isAuthenticated()) {//如果已经登录过了
        locationToDesktop();//直接跳转到工作台
    }

    //内容正在加载，消息提示关闭
    $scope.overlay = {message: ""};
}]);

