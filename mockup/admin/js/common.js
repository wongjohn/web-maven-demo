/**公共Directive、Service、Filter等等*/
angular.module("common.services", [])
    .service("Path", function () {
        var reg = /^\//;
        return {
            getUri: function (url) {
                if (reg.test(url)) {
                    return url;
                } else {
                    return this.getOrigin() + "/../../" + url;
                }
            },
            getOrigin: function () {
                return window.location.protocol + "//" + window.location.host + window.location.pathname;
            }
        };
    })
    /**Token认证信息存储*/
    .factory("authToken", ["$window", function ($window) {
        var storage = $window.localStorage;
        var cachedToken, cachedUsername;
        var userToken = 'userToken', username = "username";

        var authToken = {

            setToken: function(token){
                cachedToken = token;
                storage.setItem(userToken, token);
            },
            getToken: function(){
                if (!cachedToken){
                    cachedToken = storage.getItem(userToken);
                }
                return cachedToken;
            },
            isAuthenticated: function(){
                return !!authToken.getToken();
            },
            removeToken: function(){
                cachedToken = null;
                storage.removeItem(userToken)
            },
            setUsername: function(_username) {
                cachedUsername = _username;
                storage.setItem(username, _username);
            },
            getUsername: function () {
                if (!cachedUsername){
                    cachedUsername = storage.getItem(username);
                }
                return cachedUsername;
            },
            removeUsername: function () {
                cachedUsername = null;
                storage.removeItem(username)
            }

        };
        return authToken;
    }])
    /**发出HTTP请求时，进行拦截，添加HTTP Headers头信息*/
    .factory('authInterceptor', function (authToken) {
        return {
            request: function(config){
                var token = authToken.getToken();

                if (token){
                    config.headers.Authorization = 'Bearer ' + token;
                }
                return config;
            },
            response: function(response){
                return response;
            }
        };
    });

angular.module("common.directive", [])
/**
 * 监听表达式的值，如果表达式的值为true，则将焦点放到该DOM元素上。
 */
    .directive("conditionFocus", [function () {
        return function ($scope, $element, $attrs) {
            var dereg = $scope.$watch($attrs.conditionFocus, function (newValue, oldValue) {
                if(newValue) {
                    $element.focus();
                }
            });
            $element.bind("$destroy", function () {//如果DOM元素被销毁了，则$watch的监听也停止
                dereg();
            });
        };
    }])
/**
 * 封装jQuery UI的draggable功能（拖拽）。
 */
    .directive("draggable", function () {
        return function ($scope, $element, $attrs) {
            $element.draggable({handle: ".modal-header"})
        };
    })
    /**封装jQuery Markdown插件。*/
    .directive("markdown", function () {
        return function ($scope, $element, $attrs) {
            $attrs.$observe("markdown", function (content) {
                $element.html($.markdown(content));
            });
        };
    });

angular.module("common", ["common.services", "common.directive"]);
