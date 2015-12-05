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
    $scope.errorMessage = "";//登陆时，如果有错误，显示错误
    //跳转到工作台页面
    var locationToDesktop = function () {
        window.location.href = Path.getOrigin() + "/../desktop";
    };
    $scope.confirmLogin = function () {//确认登录
        $http.post(Path.getUri("api/users/validate"), $scope.loginInfo)
            .success(function (data, status, header, config) {
                authToken.setToken(data.token);
                authToken.setUsername(data.username);
                //跳转到工作台页面
                locationToDesktop();
            })
            .error(function (data, status, header, config) {
                $scope.errorMessage = data;
            });
    };
    if(authToken.isAuthenticated()) {//如果已经登录过了
        locationToDesktop();//直接跳转到工作台
    }

    //内容正在加载，消息提示关闭
    $scope.overlay = {message: ""};
}]);

