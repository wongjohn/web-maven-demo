<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ include file="partials/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>爱乐游商务合作</title>
    <%@ include file="partials/head-config.jsp"%>
</head>

<body>
<!-- 页面导航 -->
<%@ include file="partials/nav.jsp"%>

<!--sub-banner start-->
<div class="sub-banner">
    <div class="business-banner"></div>
</div>
<!--sub-banner end-->

<!-- sub-container start -->
<div class="sub-container">
    <div class="business-title">
        <span onclick="shu(1,3,2)" id="s_3_1" class="sx_3_1_1"></span>
        <span onclick="shu(2,3,2)" id="s_3_2" class="sx_3_2"></span>
    </div>

    <!-- 合作伙伴 -->
    <div id="table_3_1" style="display: block;">
        <div class="business-container">
            <img src="images/business/hezuobiao.jpg">
        </div>

    </div>

    <!-- 联系方式 -->
    <div id="table_3_2" style="display: none;">
        <div class="business-container">
            <img src="images/business/lianxifangshizi2.png">
        </div>
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
                            case "partner" :
                                shu(1,3,2);
                                break;
                            case "contact" :
                                shu(2,3,2);
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