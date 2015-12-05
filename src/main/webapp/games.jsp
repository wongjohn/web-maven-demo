<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ include file="partials/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>爱乐游旗下游戏</title>
    <%@ include file="partials/head-config.jsp"%>
</head>

<body>
<!-- 页面导航 -->
<%@ include file="partials/nav.jsp"%>

<!--sub-banner start-->
<div class="sub-banner">
    <div class="game-banner"></div>
</div>
<!--sub-banner end-->

<!-- sub-container start -->
<div class="sub-container">
    <div class="game-title">
        <span onclick="shu(1,1,2)" id="s_1_1" class="sx_1_1_1"></span>
        <span onclick="shu(2,1,2)" id="s_1_2" class="sx_1_2"></span>
    </div>

    <!-- 网络游戏 -->
    <div id="table_1_1" style="display: block;">
        <c:forEach var="game" items="${onlineGames}">
            <div class="game-container">
                <div class="icon">
                    <a href="${game.officialWebsiteUrl}" target="_blank"><img src="${game.iconImageUrl}"></a>
                </div>
                <div class="info limit-width">
                    <div class="title">
                        <a class="game-name" href="${game.officialWebsiteUrl}" target="_blank">${game.gameName}</a>
                        <div class="game-addition-info">${game.additionInfo}</div>
                    </div>
                    <div class="description">
                        <p>${game.description}</p>
                    </div>
                    <div class="links clearfix">
                        <a href="${game.officialWebsiteUrl}" target="_blank" class="website"></a>
                    </div>
                    <div class="erweima">
                        <img src="${game.erweimaImageUrl}">
                        <div class="erweima-text">扫描二维码直接下载游戏</div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <!-- 休闲游戏 -->
    <div id="table_1_2" style="display: none;">
        <c:forEach var="game" items="${casualGames}">
            <div class="game-container">
                <div class="icon">
                    <a href="javascript:void(0);"><img src="${game.iconImageUrl}"></a>
                </div>
                <div class="info">
                    <div class="title">
                        <a class="game-name" href="javascript:void(0);">${game.gameName}</a>
                        <div class="game-addition-info">${game.additionInfo}</div>
                    </div>
                    <div class="description">
                        <p>${game.description}</p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<!-- sub-container end -->

<!-- 页面Footer导航 -->
<%@ include file="partials/footer.jsp"%>
<script type="text/javascript" src="jslib/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/aly-common.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        var _searchString = window.location.search;
        if(_searchString) {//如果有检索条件，例如"?tab=honor"
            _searchString = _searchString.substring(1, _searchString.length);
            var _searchStrParams = _searchString.split("&");
            if(_searchStrParams) {//如果截取到参数
                $(_searchStrParams).each(function (_index, _paramStr){
                    var _paramKeyAndValues = _paramStr.split("=");
                    if("tab" == _paramKeyAndValues[0]) {
                        switch(_paramKeyAndValues[1]) {
                            case "online" :
                                shu(1,1,2);
                                break;
                            case "casual" :
                                shu(2,1,2);
                                break;
                        }
                    }
                });
            }
        }
    });
</script>
</body>
</html>
