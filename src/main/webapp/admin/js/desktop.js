/*Desktop*/
var alyDesktop = angular.module("alyDesktop", ["ui", "ui.bootstrap", "ngCookies", "ngGrid", "ui.router", "common"]);
alyDesktop.value("ui.config", {
    date: {
        changeMonth: true,
        changeYear: true,
        showMonthAfterYear: true,
        dateFormat: "yy-mm-dd",
        monthNamesShort: ["01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"], // For formatting
        dayNamesMin: ["日","一","二","三","四","五","六"] // Column headings for days starting at Sunday
    },
    dateFormat: "yy-mm-dd"
});
/*公共组件*/
/*删除操作提示框*/
alyDesktop.service("confirmDialog", ["$rootScope", function ($rootScope) {
    $rootScope.confirmDeleteShowFlag = false;
    $rootScope.deleteConfiguration = {
        message: "删除消息提示",
        callback: function () {}//确认删除的回调函数
    };
    var _confirmDelete = function (_configuration) {
        $rootScope.deleteConfiguration = _configuration;
        $rootScope.confirmDeleteShowFlag = true;
    };
    $rootScope.confirmToDelete = function () {//确认删除
        if($rootScope.deleteConfiguration.callback) {
            $rootScope.deleteConfiguration.callback();
        }
        $rootScope.confirmDeleteShowFlag = false;
    };
    return {
        confirmDelete: function (_config) {
            _confirmDelete(_config);
        }
    };
}]);
/*操作结果提示框*/
alyDesktop.service("alertMessage", ["$rootScope", "$timeout", function ($rootScope, $timeout) {
    var defaultSuccessConfig = {
        type: "success",
        title: "操作成功",
        message: "操作成功提示"
    };
    var defaultFailureConfig = {
        type: "warning",
        title: "操作失败",
        message: "操作失败提示"
    };
    $rootScope.alertMessageShowFlag = false;
    $rootScope.configuration = null;
    var _alertTimeout = null;
    $rootScope.alertMessage = function (_configuration) {
        $rootScope.configuration = _configuration;
        if(_alertTimeout) {
            $timeout.cancel(_alertTimeout);
        }
        _alertTimeout = $timeout(function () {
            $rootScope.alertMessageShowFlag = true;
        }, 500);
    };
    return {
        alertSuccess: function (_config) {
            $rootScope.alertMessage(angular.extend(angular.copy(defaultSuccessConfig), _config));
        },
        alertFailure: function (_config) {
            $rootScope.alertMessage(angular.extend(angular.copy(defaultFailureConfig), _config));
        }
    };
}]);
/*表单编辑*/
alyDesktop.service("entityEdit", ["$rootScope", function ($rootScope) {
    var _confirmToSaveDefault = function (entity) {//“保存”按钮默认状态的操作
        $rootScope.editModalFlag = false;
    };
    $rootScope.config = {//默认设置
        entity: null,
        templateUrl: null,
        title: null,
        confirmToSave: _confirmToSaveDefault
    };
    /*表单编辑模态框、打开关闭状态*/
    $rootScope.editModalFlag = false;
    $rootScope.entity = null;
    $rootScope.confirmToSave = function () {
        $rootScope.config.confirmToSave($rootScope.entity);
        $rootScope.editModalFlag = false;
    };
    $rootScope.closeEditModal = function () {//关闭模态框
        $rootScope.editModalFlag = false;
    };
    return {
        edit: function (_config) {
            angular.extend($rootScope.config, _config);
            $rootScope.entity = _config.entity;
            $rootScope.editModalFlag = true;
        }
    };
}]);
alyDesktop.directive("entityEditForm", ["$compile", "$templateCache", function ($compile, $templateCache) {
    return function ($scope, $element, $attrs) {
        $attrs.$observe("entityEditForm", function (templateUrl) {
            var html = $templateCache.get(templateUrl);
            $element.html($compile(html)($scope));
        });
    };
}]);
/*此处有个bug： 切换ui-router的状态时，控制模态框显示关闭状态的boolean变量会自己变成false，现在临时使用这个解决方案，以后找到原因之后再修理。*/
alyDesktop.directive("modal", ["$rootScope", "$timeout", function ($rootScope, $timeout) {//bug fix
    return function ($scope, $element, $attrs) {
        $scope.$watch($attrs.modal, function (newValue) {
            if(newValue === false) {
                $timeout(function () {
                    if($(".modal-open").length) {
                        $rootScope[$attrs.modal] = true;
                    }
                });
            }
        });
    };
}]);
/*输入的内容为拼音，空格都变为英文减号（-）*/
alyDesktop.directive("ngChange", ["$parse", function ($parse) {
    return function ($scope, $element, $attrs) {
        var getter = $parse($attrs.ngModel);
        var setter = getter.assign;
        $scope.clean = function () {
            var _pinyin = getter($scope).replace(/\s+/, "-")
                .replace(/[^a-z0-0-]/i, '');
            setter($scope, _pinyin);
        };
    };
}]);
alyDesktop.run(["$rootScope", "$state", "$stateParams", "$templateCache", function ($rootScope, $state, $stateParams, $templateCache) {
    // It's very handy to add references to $state and $stateParams to the $rootScope
    // so that you can access them from any scope within your applications.For example,
    // <li ng-class="{ active: $state.includes('contacts.list') }"> will set the <li>
    // to active whenever 'contacts.list' or one of its decendents is active.
    $rootScope.$state = $state;
    $rootScope.$stateParams = $stateParams;

    $rootScope.overlay = {message: "正在加载... ..."};
    $rootScope.commonModalOptions = {
        dialogClass: 'modal aly-dialog',
        backdropFade: true,
        dialogFade: true,
        keyboard: true,
        backdrop: true,
        backdropClick: true
    };

    $templateCache.put("job-template.html", "<div class=\"modal-body form-horizontal\">\n    <div class=\"control-group\">\n        <label class=\"control-label\">岗位名称：</label>\n        <div class=\"controls\">\n            <input type=\"text\" placeholder=\"岗位名称\" ng-model=\"entity.recruitPosition\" condition-focus=\"editModalFlag\">\n        </div>\n    </div>\n    <div class=\"control-group inline\">\n        <label class=\"control-label\">工作地点：</label>\n        <div class=\"controls\">\n            <input type=\"text\" placeholder=\"比如北京\" ng-model=\"entity.workPlace\">\n        </div>\n    </div>\n    <div class=\"control-group inline\">\n        <label class=\"control-label\">工作性质：</label>\n        <div class=\"controls\">\n            <input type=\"text\" placeholder=\"比如全职\" ng-model=\"entity.workProperty\">\n        </div>\n    </div>\n    <div class=\"control-group inline\">\n        <label class=\"control-label\">招聘人数：</label>\n        <div class=\"controls\">\n            <input type=\"number\" placeholder=\"招聘人数\" ng-model=\"entity.recruitNum\">\n        </div>\n    </div>\n    <div class=\"control-group inline\">\n        <label class=\"control-label\">ID：</label>\n        <div class=\"controls\">\n            <span class=\"uneditable-input\">{{entity.recruitId}}</span>\n        </div>\n    </div>\n    <div class=\"control-group\">\n        <label class=\"control-label\">岗位责任：</label>\n        <div class=\"controls\">\n            <textarea rows=\"4\" ng-model=\"entity.responsibilities\"></textarea>\n        </div>\n    </div>\n    <div class=\"control-group\">\n        <label class=\"control-label\">任职资格：</label>\n        <div class=\"controls\">\n            <textarea rows=\"5\" ng-model=\"entity.requirements\"></textarea>\n        </div>\n    </div>\n    <div class=\"control-group\">\n        <label class=\"control-label\">其他：</label>\n        <div class=\"controls\">\n            <textarea rows=\"1\" ng-model=\"entity.others\"></textarea>\n        </div>\n    </div>\n</div>");
    $templateCache.put("company-new.html", "<div class=\"modal-body form-horizontal\">\n                    <div class=\"control-group\">\n                        <label class=\"control-label\">标题：</label>\n                        <div class=\"controls\">\n                            <input type=\"text\" placeholder=\"标题\" ng-model=\"entity.newsTitle\" condition-focus=\"editModalFlag\">\n                        </div>\n                    </div>\n                    <div class=\"control-group\">\n                        <label class=\"control-label\">拼音简述：</label>\n                        <div class=\"controls\">\n                            <input type=\"text\" placeholder=\"拼音简述\" ng-model=\"entity.shortcut\" ng-change=\"clean()\">\n                        </div>\n                    </div>\n                    <div class=\"control-group inline\">\n                        <label class=\"control-label\">来源：</label>\n                        <div class=\"controls\">\n                            <input type=\"text\" placeholder=\"来源\" ng-model=\"entity.newsSource\">\n                        </div>\n                    </div>\n                    <div class=\"control-group inline\">\n                        <label class=\"control-label\">作者：</label>\n                        <div class=\"controls\">\n                            <input type=\"text\" placeholder=\"作者\" ng-model=\"entity.newsAuthor\">\n                        </div>\n                    </div>\n                    <div class=\"control-group inline\">\n                        <label class=\"control-label\">新闻日期：</label>\n                        <div class=\"controls\">\n                            <input type=\"text\" placeholder=\"新闻日期\" ng-model=\"entity.newsDate\" ui-date ui-date-format>\n                        </div>\n                    </div>\n                    <div class=\"control-group inline\">\n                        <label class=\"control-label\">ID：</label>\n                        <div class=\"controls\">\n                            <span class=\"uneditable-input\">{{entity.newsId}}</span>\n                        </div>\n                    </div>\n\n                    <div class=\"control-group\">\n                        <label class=\"control-label\">正文：</label>\n                        <div class=\"controls\">\n                            <textarea rows=\"10\" ng-model=\"entity.newsContent\"></textarea>\n                        </div>\n                    </div>\n                </div>");
    $templateCache.put("media-new.html", "<div class=\"modal-body form-horizontal\">\n                    <div class=\"control-group\">\n                        <label class=\"control-label\">标题：</label>\n                        <div class=\"controls\">\n                            <input type=\"text\" placeholder=\"标题\" ng-model=\"entity.newsTitle\" condition-focus=\"editModalFlag\">\n                        </div>\n                    </div>\n                    <div class=\"control-group\">\n                        <label class=\"control-label\">引用连接：</label>\n                        <div class=\"controls\">\n                            <input type=\"text\" placeholder=\"引用连接\" ng-model=\"entity.referenceLink\">\n                        </div>\n                    </div>\n                    <div class=\"control-group inline\">\n                        <label class=\"control-label\">新闻日期：</label>\n                        <div class=\"controls\">\n                            <input type=\"text\" placeholder=\"新闻日期\" ng-model=\"entity.newsDate\" ui-date ui-date-format>\n                        </div>\n                    </div>\n                    <div class=\"control-group inline\">\n                        <label class=\"control-label\">ID：</label>\n                        <div class=\"controls\">\n                            <span class=\"uneditable-input\">{{entity.newsId}}</span>\n                        </div>\n                    </div>\n                </div>");
    $templateCache.put("online-game.html", "<div class=\"modal-body form-horizontal\">\n                    <div class=\"control-group\">\n                        <label class=\"control-label\">游戏名称：</label>\n                        <div class=\"controls\">\n                            <input type=\"text\" placeholder=\"游戏名称\" ng-model=\"entity.gameName\" condition-focus=\"editModalFlag\">\n                        </div>\n                    </div>\n                    <div class=\"control-group inline\">\n                        <label class=\"control-label\">游戏简述：</label>\n                        <div class=\"controls\">\n                            <input type=\"text\" placeholder=\"比如射击游戏\" ng-model=\"entity.additionInfo\">\n                        </div>\n                    </div>\n                    <div class=\"control-group inline\">\n                        <label class=\"control-label\">游戏官网：</label>\n                        <div class=\"controls\">\n                            <input type=\"text\" placeholder=\"游戏官网\" ng-model=\"entity.officialWebsiteUrl\">\n                        </div>\n                    </div>\n                    <div class=\"control-group inline\">\n                        <label class=\"control-label\">游戏类型：</label>\n                        <div class=\"controls\">\n                            <span class=\"uneditable-input\">{{entity.gameType}}</span>\n                        </div>\n                    </div>\n                    <div class=\"control-group inline\">\n                        <label class=\"control-label\">ID：</label>\n                        <div class=\"controls\">\n                            <span class=\"uneditable-input\">{{entity.gameId}}</span>\n                        </div>\n                    </div>\n                    <div class=\"control-group\">\n                        <label class=\"control-label\">游戏图标：</label>\n                        <div class=\"controls\">\n                            <input type=\"text\" placeholder=\"游戏图标\" ng-model=\"entity.iconImageUrl\">\n                        </div>\n                    </div>\n                    <div class=\"control-group\">\n                        <label class=\"control-label\">二维码图：</label>\n                        <div class=\"controls\">\n                            <input type=\"text\" placeholder=\"二维码图\" ng-model=\"entity.erweimaImageUrl\">\n                        </div>\n                    </div>\n                    <div class=\"control-group\">\n                        <label class=\"control-label\">游戏软文：</label>\n                        <div class=\"controls\">\n                            <textarea rows=\"8\" ng-model=\"entity.description\"></textarea>\n                        </div>\n                    </div>\n                </div>");
    $templateCache.put("casual-game.html", "<div class=\"modal-body form-horizontal\">\n                    <div class=\"control-group\">\n                        <label class=\"control-label\">游戏名称：</label>\n                        <div class=\"controls\">\n                            <input type=\"text\" placeholder=\"游戏名称\" ng-model=\"entity.gameName\" condition-focus=\"editModalFlag\">\n                        </div>\n                    </div>\n                    <div class=\"control-group\">\n                        <label class=\"control-label\">游戏简述：</label>\n                        <div class=\"controls\">\n                            <input type=\"text\" placeholder=\"比如宠物养成\" ng-model=\"entity.additionInfo\">\n                        </div>\n                    </div>\n                    <div class=\"control-group inline\">\n                        <label class=\"control-label\">游戏类型：</label>\n                        <div class=\"controls\">\n                            <span class=\"uneditable-input\">{{entity.gameType}}</span>\n                        </div>\n                    </div>\n                    <div class=\"control-group inline\">\n                        <label class=\"control-label\">ID：</label>\n                        <div class=\"controls\">\n                            <span class=\"uneditable-input\">{{entity.gameId}}</span>\n                        </div>\n                    </div>\n                    <div class=\"control-group\">\n                        <label class=\"control-label\">游戏图标：</label>\n                        <div class=\"controls\">\n                            <input type=\"text\" placeholder=\"游戏图标\" ng-model=\"entity.iconImageUrl\">\n                        </div>\n                    </div>\n                    <div class=\"control-group\">\n                        <label class=\"control-label\">游戏软文：</label>\n                        <div class=\"controls\">\n                            <textarea rows=\"8\" ng-model=\"entity.description\"></textarea>\n                        </div>\n                    </div>\n                </div>");
}]);
//工作台主界面
alyDesktop.controller("AlyDesktopController", ["$scope", "$cookies", "$cookieStore", "Path", "authToken", function ($scope, $cookies, $cookieStore, Path, authToken) {
    $scope.loginUser = {//登录的用户信息
        username: authToken.getUsername()
    };
    //跳转到主页面
    var locationToIndex = function () {
        window.location.href = Path.getOrigin() + "/../";
    };
    //退出登录
    $scope.logout = function () {
        authToken.removeToken();
        authToken.removeUsername();
        locationToIndex();
    };

    if(!authToken.isAuthenticated()) {//如果没有登录
        locationToIndex();//跳转到主页面
    }
    $scope.overlay.message = null;//加载完毕
}]);

//使用说明
alyDesktop.controller("ManualController", ["$scope", function ($scope) {

}]);

//新闻动态
alyDesktop.controller("NewsController", ["$scope", "confirmDialog", "entityEdit", "$http", "Path", "alertMessage", function ($scope, confirmDialog, entityEdit, $http, Path, alertMessage) {
    //公司动态
    var apiCompanyNews = "api/news/company";
    $scope.allCompanyNews = [];
    $scope.createCompanyNew = function () {//打开模态框
        entityEdit.edit({
            title: "创建公司动态",
            entity: new CompanyNew(),
            templateUrl: "company-new.html",
            confirmToSave: _confirmToSaveCompanyNewAdd
        });
    };
    var _confirmToSaveCompanyNewAdd = function (entity) {//“保存”按钮新增状态的操作
        return $http.post(Path.getUri(apiCompanyNews), entity)
            .success(function (data, status, headers, config) {
                $scope.allCompanyNews.splice(0, 0, data);
                $scope.selectedCompanyNew = data;
                alertMessage.alertSuccess({message: "创建公司动态成功！"});
            })
            .error(function (data, status, headers, config) {
                alertMessage.alertFailure({message: data})
            });
    };
    var _confirmToSaveCompanyNewEdit = function (entity) {//“保存”按钮编辑状态的操作
        return $http.post(Path.getUri(apiCompanyNews + "/" + entity.newsId), entity)
            .success(function (data, status, headers, config) {
                angular.extend($scope.selectedCompanyNew, entity);
                alertMessage.alertSuccess({message: "保存公司动态成功！"})
            })
            .error(function (data, status, headers, config) {
                alertMessage.alertFailure({message: data})
            });
    };
    $scope.selectedCompanyNew = null;//选中的记录
    $scope.selectCurrentCompanyNew = function (_companyNew) {//选中当前传入的记录
        $scope.selectedCompanyNew = _companyNew;
    };
    $scope.editCurrentCompanyNew = function (_companyNew, $event) {//编辑当前的记录
        $scope.selectCurrentCompanyNew(_companyNew);
        entityEdit.edit({
            title: "编辑公司动态",
            entity: angular.copy(_companyNew),
            templateUrl: "company-new.html",
            confirmToSave: _confirmToSaveCompanyNewEdit
        });
        $event.stopPropagation();
    };
    var _deleteCurrentCompanyNewCallback = function () {
        $http.delete(Path.getUri(apiCompanyNews + "/" + $scope.selectedCompanyNew.newsId))
            .success(function (data, status, headers, config) {
                $scope.allCompanyNews.splice($scope.allCompanyNews.indexOf($scope.selectedCompanyNew), 1);
                $scope.selectedCompanyNew = null;
                alertMessage.alertSuccess({message: "删除公司动态成功！"})
            })
            .error(function (data, status, headers, config) {
                alertMessage.alertFailure({message: data});
            });
    };
    $scope.deleteCurrentCompanyNew = function (_companyNew, $event) {//删除当前的记录
        $event.stopPropagation();
        $scope.selectCurrentCompanyNew(_companyNew);
        confirmDialog.confirmDelete({
            message: "标题为：\"" + _companyNew.newsTitle + "\"的公司动态。",
            callback: _deleteCurrentCompanyNewCallback
        });
    };
    $http.get(Path.getUri(apiCompanyNews))
        .success(function (data, status, headers, config) {
            $scope.allCompanyNews = data;
        })
        .error(function (data, status, headers, config) {
            alertMessage.alertFailure({message: data});
        });

    //媒体动态
    var apiMediaNews = "api/news/media";
    $scope.allMediaNews = [];
    $scope.createMediaNew = function () {//打开模态框
        entityEdit.edit({
            title: "创建媒体动态",
            entity: new MediaNew(),
            templateUrl: "media-new.html",
            confirmToSave: _confirmToSaveMediaNewAdd
        });
    };
    var _confirmToSaveMediaNewAdd = function (entity) {//“保存”按钮新增状态的操作
        return $http.post(Path.getUri(apiMediaNews), entity)
            .success(function (data, status, headers, config) {
                $scope.allMediaNews.splice(0, 0, data);
                $scope.selectedMediaNew = data;
                alertMessage.alertSuccess({message: "创建媒体动态成功！"})
            })
            .error(function (data, status, headers, config) {
                alertMessage.alertFailure({message: data});
            });
    };
    var _confirmToSaveMediaNewEdit = function (entity) {//“保存”按钮编辑状态的操作
        return $http.post(Path.getUri(apiMediaNews + "/" + $scope.selectedMediaNew.newsId), entity)
            .success(function (data, status, headers, config) {
                angular.extend($scope.selectedMediaNew, entity);
                alertMessage.alertSuccess({message: "保存媒体动态成功！"});
            })
            .error(function (data, status, headers, config) {
                alertMessage.alertFailure({message: data});
            });
    };
    $scope.selectedMediaNew = null;//选中的记录
    $scope.selectCurrentMediaNew = function (_mediaNew) {//选中当前传入的记录
        $scope.selectedMediaNew = _mediaNew;
    };
    $scope.editCurrentMediaNew = function (_mediaNew, $event) {//编辑当前的记录
        $scope.selectCurrentMediaNew(_mediaNew);
        entityEdit.edit({
            title: "编辑媒体动态",
            entity: angular.copy(_mediaNew),
            templateUrl: "media-new.html",
            confirmToSave: _confirmToSaveMediaNewEdit
        });
        $event.stopPropagation();
    };
    var _deleteCurrentMediaNewCallback = function () {
        $http.delete(Path.getUri(apiMediaNews + "/" + $scope.selectedMediaNew.newsId))
            .success(function (data, status, headers, config) {
                $scope.allMediaNews.splice($scope.allMediaNews.indexOf($scope.selectedMediaNew), 1);
                $scope.selectedMediaNew = null;
                alertMessage.alertSuccess({message: "删除媒体动态成功！"})
            })
            .error(function (data, status, headers, config) {
                alertMessage.alertFailure({message: data});
            });
    };
    $scope.deleteCurrentMediaNew = function (_mediaNew, $event) {//删除当前的记录
        $event.stopPropagation();
        $scope.selectCurrentMediaNew(_mediaNew);
        confirmDialog.confirmDelete({
            message: "标题为：\"" + _mediaNew.newsTitle + "\"的媒体动态。",
            callback: _deleteCurrentMediaNewCallback
        });
    };

    $http.get(Path.getUri(apiMediaNews))
        .success(function (data, status, headers, config) {
            $scope.allMediaNews = data;
        })
        .error(function (data, status, headers, config) {
            alertMessage.alertFailure({message: data});
        });
}]);

//招聘信息
alyDesktop.controller("RecruitController", ["$scope", "confirmDialog", "entityEdit", "$http", "Path", "alertMessage", function ($scope, confirmDialog, entityEdit, $http, Path, alertMessage) {
    /*查询到的所有招聘信息*/
    var apiUri = "api/recruits";
    $scope.allJobs = [];
    $scope.createNewRecruit = function () {//创建招聘信息
        entityEdit.edit({
            title: "创建招聘信息",
            entity: new Recruits(),
            templateUrl: "job-template.html",
            confirmToSave: _confirmToSaveAdd
        });
    };
    var _confirmToSaveAdd = function (entity) {//“保存”按钮新增状态的操作
        $http.post(Path.getUri(apiUri), entity)
            .success(function (data, status, headers, config) {
                $scope.allJobs.splice(0, 0, data);
                $scope.selectedJob = data;
                alertMessage.alertSuccess({message: "创建招聘信息成功！"})
            })
            .error(function (data, status, headers, config) {
                alertMessage.alertFailure({message: data});
            })
    };
    var _confirmToSaveEdit = function (entity) {//“保存”按钮编辑状态的操作
        return $http.post(Path.getUri(apiUri + "/" + $scope.selectedJob.recruitId), entity)
            .success(function (data, status, headers, config) {
                angular.extend($scope.selectedJob, entity);
                alertMessage.alertSuccess({message: "保存招聘信息成功！"})
            })
            .error(function (data, status, headers, config) {
                alertMessage.alertFailure({message: data});
            });
    };
    $scope.selectedJob = null;//选中的记录
    $scope.selectCurrentJob = function (_job) {//选中当前传入的记录
        $scope.selectedJob = _job;
    };
    $scope.editCurrentJob = function (_job, $event) {//编辑当前的记录
        $scope.selectCurrentJob(_job);
        entityEdit.edit({
            title: "编辑招聘信息",
            entity: angular.copy(_job),
            templateUrl: "job-template.html",
            confirmToSave: _confirmToSaveEdit
        });
        $event.stopPropagation();
    };
    var _deleteCurrentJobCallback = function () {
        return $http.delete(Path.getUri(apiUri + "/" + $scope.selectedJob.recruitId))
            .success(function (data, status, headers, config) {
                $scope.allJobs.splice($scope.allJobs.indexOf($scope.selectedJob), 1);
                $scope.selectedJob = null;
                alertMessage.alertSuccess({message: "删除招聘信息成功！"});
            })
            .error(function (data, status, headers, config) {
                alertMessage.alertFailure({message: data});
            });
    };
    $scope.deleteCurrentJob = function (_job, $event) {//删除当前的记录
        $event.stopPropagation();
        $scope.selectCurrentJob(_job);
        confirmDialog.confirmDelete({
            message: "职位名称为：\"" + _job.recruitPosition + "\"的招聘信息。",
            callback: _deleteCurrentJobCallback
        });
    };

    function _getAllJobs () {//查询所有的招聘信息
        return $http.get(Path.getUri(apiUri))
            .success(function (data, status, headers, config) {
                $scope.allJobs = data;
            })
            .error(function (data, status, headers, config) {
                alertMessage.alertFailure({message: data});
            });
    }
    _getAllJobs();
}]);

//旗下游戏
alyDesktop.controller("GamesController", ["$scope", "confirmDialog", "entityEdit", "$http", "Path", "alertMessage", function ($scope, confirmDialog, entityEdit, $http, Path, alertMessage) {
    /*网络游戏*/
    var apiGames = "api/games";
    var defaultOnlineGame = new Games({gameType: "网络游戏"});
    $scope.allOnlineGames = [];
    $scope.createOnlineGame = function () {//打开模态框
        entityEdit.edit({
            title: "创建网络游戏",
            entity: angular.copy(defaultOnlineGame),
            templateUrl: "online-game.html",
            confirmToSave: _confirmToSaveOnlineGameAdd
        });
    };
    var _confirmToSaveOnlineGameAdd = function (entity) {//“保存”按钮新增状态的操作
        return $http.post(Path.getUri(apiGames), entity)
            .success(function (data, status, headers, config) {
                $scope.allOnlineGames.splice(0, 0, data);
                $scope.selectedOnlineGame = data;
                alertMessage.alertSuccess({message: "创建网络游戏成功！"});
            })
            .error(function (data, status, headers, config) {
                alertMessage.alertFailure({message: data});
            })
    };
    var _confirmToSaveOnlineGameEdit = function (entity) {//“保存”按钮编辑状态的操作
        return $http.post(Path.getUri(apiGames + "/" + $scope.selectedOnlineGame.gameId), entity)
            .success(function (data, status, headers, config) {
                angular.extend($scope.selectedOnlineGame, entity);
                alertMessage.alertSuccess({message: "保存网络游戏成功！"});
            })
            .error(function (data, status, headers, config) {
                alertMessage.alertFailure({message: data});
            });
    };
    $scope.selectedOnlineGame = null;//选中的记录
    $scope.selectCurrentOnlineGame = function (_onlineGame) {//选中当前传入的记录
        $scope.selectedOnlineGame = _onlineGame;
    };
    $scope.editCurrentOnlineGame = function (_onlineGame, $event) {//编辑当前的记录
        $scope.selectCurrentOnlineGame(_onlineGame);
        entityEdit.edit({
            title: "编辑网络游戏",
            entity: angular.copy(_onlineGame),
            templateUrl: "online-game.html",
            confirmToSave: _confirmToSaveOnlineGameEdit
        });
        $event.stopPropagation();
    };
    var _deleteCurrentOnlineGameCallback = function () {
        $http.delete(Path.getUri(apiGames + "/" + $scope.selectedOnlineGame.gameId))
            .success(function (data, status, headers, config) {
                $scope.allOnlineGames.splice($scope.allOnlineGames.indexOf($scope.selectedOnlineGame), 1);
                $scope.selectedOnlineGame = null;
                alertMessage.alertSuccess({message: "删除网络游戏成功！"});
            })
            .error(function (data, status, headers, config) {
                alertMessage.alertFailure({message: data});
            });
    };
    $scope.deleteCurrentOnlineGame = function (_onlineGame, $event) {//删除当前的记录
        $event.stopPropagation();
        $scope.selectCurrentOnlineGame(_onlineGame);
        confirmDialog.confirmDelete({
            message: "名称为：\"" + _onlineGame.gameName + "\"的网络游戏。",
            callback: _deleteCurrentOnlineGameCallback
        });
    };

    /*休闲游戏*/
    var defaultCasualGame = new Games({gameType: "休闲游戏"});
    $scope.allCasualGames = [];
    $scope.createCasualGame = function () {//打开模态框
        entityEdit.edit({
            title: "创建休闲游戏",
            entity: angular.copy(defaultCasualGame),
            templateUrl: "casual-game.html",
            confirmToSave: _confirmToSaveCasualGameAdd
        });
    };
    var _confirmToSaveCasualGameAdd = function (entity) {//“保存”按钮新增状态的操作
        return $http.post(Path.getUri(apiGames), entity)
            .success(function (data, status, headers, config) {
                $scope.allCasualGames.splice(0, 0, data);
                $scope.selectedCasualGame = data;
                alertMessage.alertSuccess({message: "创建休闲游戏成功！"});
            })
            .error(function (data, status, headers, config) {
                alertMessage.alertFailure({message: data});
            });
    };
    var _confirmToSaveCasualGameEdit = function (entity) {//“保存”按钮编辑状态的操作
        return $http.post(Path.getUri(apiGames + "/" + $scope.selectedCasualGame.gameId), entity)
            .success(function (data, status, headers, config) {
                angular.extend($scope.selectedCasualGame, entity);
                alertMessage.alertSuccess({message: "保存休闲游戏成功！"});
            })
            .error(function (data, status ,headers, config) {
                alertMessage.alertFailure({message: data});
            })
    };
    $scope.selectedCasualGame = null;//选中的记录
    $scope.selectCurrentCasualGame = function (_casualGame) {//选中当前传入的记录
        $scope.selectedCasualGame = _casualGame;
    };
    $scope.editCurrentCasualGame = function (_casualGame, $event) {//编辑当前的记录
        $scope.selectCurrentCasualGame(_casualGame);
        entityEdit.edit({
            title: "编辑休闲游戏",
            entity: angular.copy(_casualGame),
            templateUrl: "casual-game.html",
            confirmToSave: _confirmToSaveCasualGameEdit
        });
        $event.stopPropagation();
    };
    var _deleteCurrentCasualGameCallback = function () {
        $http.delete(Path.getUri(apiGames + "/" + $scope.selectedCasualGame.gameId))
            .success(function (data, status, headers, config) {
                $scope.allCasualGames.splice($scope.allCasualGames.indexOf($scope.selectedCasualGame), 1);
                $scope.selectedCasualGame = null;
                alertMessage.alertSuccess({message: "删除休闲游戏成功！"});
            })
            .error(function (data, status, headers, config) {
                alertMessage.alertFailure({message: data});
            })
    };
    $scope.deleteCurrentCasualGame = function (_casualGame, $event) {//删除当前的记录
        $event.stopPropagation();
        $scope.selectCurrentCasualGame(_casualGame);
        confirmDialog.confirmDelete({
            message: "名称为：\"" + _casualGame.gameName + "\"的休闲游戏。",
            callback: _deleteCurrentCasualGameCallback
        });
    };

    function _getAllGames() {
        return $http.get(Path.getUri(apiGames))
            .success(function (data, status, headers, config) {
                $scope.allOnlineGames = data.filter(function (_data) {
                    return "网络游戏" === _data.gameType;
                });
                $scope.allCasualGames = data.filter(function (_data) {
                    return "休闲游戏" === _data.gameType;
                })
            })
            .error(function (data, status, headers, config) {
                alertMessage.alertFailure({message: data});
            });
    }
    _getAllGames();
}]);

//设置
alyDesktop.controller("SettingsController", ["$scope", "$http", "Path", "alertMessage", function ($scope, $http, Path, alertMessage) {
    $scope.errorMessage = "";
    $scope.changePasswordInfo = {
        oldPassword: "",
        newPassword: "",
        confirmPassword: ""
    };
    var apiUsers = "api/users";
    $scope.confirmChangePassword = function () {
        if($scope.changePasswordInfo.confirmPassword != $scope.changePasswordInfo.newPassword) {
            $scope.errorMessage = "请再次确认您要设置的密码";
            return;
        }
        $http.post(Path.getUri(apiUsers + "/password"), {oldPassword: $scope.changePasswordInfo.oldPassword,
            newPassword: $scope.changePasswordInfo.newPassword})
            .success(function (data, status, headers, config) {
                $scope.changePasswordInfo = {
                    oldPassword: "",
                    newPassword: "",
                    confirmPassword: ""
                };
                alertMessage.alertSuccess({message: "修改密码成功！"});
            })
            .error(function (data, status, headers, config) {
                alertMessage.alertFailure({message: data});
            });
    };
}]);

//关于本站
alyDesktop.controller("AboutController", ["$scope", function ($scope) {

}]);

//设置各个菜单状态对应的Controller
alyDesktop.config(["$stateProvider", "$urlRouterProvider", "$httpProvider",
    function ($stateProvider, $urlRouterProvider, $httpProvider) {
        $urlRouterProvider
            .otherwise('/');
        // Use $stateProvider to configure your states.
        $stateProvider
            .state("manual", {//使用说明
                url: "/",
                templateUrl: 'partials/manual.html',
                controller: "ManualController"
            })
            .state("news", {//新闻动态
                url: "/news",
                templateUrl: 'partials/news.html',
                controller: "NewsController"
            })
            .state("recruit", {//招聘信息
                url: "/recruit",
                templateUrl: 'partials/recruit.html',
                controller: "RecruitController"
            })
            .state("games", {//旗下游戏
                url: "/games",
                templateUrl: 'partials/games.html',
                controller: "GamesController"
            })
            .state("settings", {//设　　置
                url: "/settings",
                templateUrl: 'partials/settings.html',
                controller: "SettingsController"
            })
            .state("about", {//关于本站
                url: "/about",
                templateUrl: 'partials/about.html',
                controller: "AboutController"
            });

        $httpProvider.interceptors.push('authInterceptor');
    }]);

