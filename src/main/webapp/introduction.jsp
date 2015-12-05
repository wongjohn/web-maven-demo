<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ include file="partials/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>爱乐游公司简介</title>
    <%@ include file="partials/head-config.jsp"%>
</head>

<body>
<!-- 页面导航 -->
<%@ include file="partials/nav.jsp"%>

<!--sub-banner start-->
<div class="sub-banner">
    <div class="introduction-banner"></div>
</div>
<!--sub-banner end-->

<!-- sub-container start -->
<div class="sub-container">
    <div class="introduction-title">
        <span onclick="shu(1,2,5)" id="s_2_1" class="sx_2_1_1"></span>
        <span onclick="shu(2,2,5)" id="s_2_2" class="sx_2_2"></span>
        <span onclick="shu(3,2,5)" id="s_2_3" class="sx_2_3"></span>
        <span onclick="shu(4,2,5)" id="s_2_4" class="sx_2_4"></span>
        <span onclick="shu(5,2,5)" id="s_2_5" class="sx_2_5"></span>
    </div>

    <!-- 公司简介 -->
    <div id="table_2_1" style="display: block;">
        <div class="introduction-container brief-info">
            <div class="introduction-logo">
                <img src="images/introduction/briefinfo/logo.png">
            </div>
            <div class="company-info">
                <h2>北京爱乐游信息技术有限公司</h2>
                <p>北京爱乐游信息技术有限公司（简称：爱乐游5agame）成立于2008年底，位于北京市海淀区上地信息产业基地。自成立以来，专注手机游戏的研发与运营；产品种类多样，主要以休闲类和网游类为主。公司研发的产品《雷霆战机》曾经连续两个月进入全球IOS游戏收入排行榜前十位。凭借其卓越的研发能力和业务运营能力，逐渐发展成为国内最优秀的手游研发和发行商之一。</p>
                <p>爱游戏、爱事业、爱生活，我们是爱乐游—5agame！</p>
            </div>
        </div>
    </div>

    <!-- 企业文化 -->
    <div id="table_2_2" style="display: none;">
        <div class="introduction-container">
            <img src="images/introduction/culture/wenhua.png">
        </div>
    </div>

    <!-- 所获荣誉 -->
    <div id="table_2_4" style="display: none;">
        <div class="introduction-container">
            <img src="images/introduction/honor/rongyu2.png">
        </div>
    </div>

    <!-- 团队建设 -->
    <div id="table_2_5" style="display: none;">
        <div class="introduction-container">
            <img src="images/introduction/team/tuandui.png">
        </div>
    </div>

</div>
<!-- 大事记 -->
<div id="table_2_3" style="display: none;">
    <div class="dashiji"></div>
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
                            case "introduction" :
                                shu(1,2,5);
                                break;
                            case "culture" :
                                shu(2,2,5);
                                break;
                            case "dashiji" :
                                shu(3,2,5);
                                break;
                            case "honor" :
                                shu(4,2,5);
                                break;
                            case "team" :
                                shu(5,2,5);
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