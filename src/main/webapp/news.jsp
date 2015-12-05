<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ include file="partials/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>爱乐游-动态信息</title>
    <%@ include file="partials/head-config.jsp"%>
</head>

<body>
<!-- 页面导航 -->
<%@ include file="partials/nav.jsp"%>

<!-- 页面条幅 -->
<%@ include file="partials/banner.jsp"%>

<!-- main-container start -->
<div class="main-container clearfix">
    <!-- 左侧区域 -->
    <%@ include file="partials/left-section.jsp"%>

    <!-- 右侧区域 -->
    <div class="right-section">
        <div class="news-header clearfix">
            <span onclick="shu(1,4,2)" id="s_4_1" class="sx_4_1_1"></span>
            <span onclick="shu(2,4,2)" id="s_4_2" class="sx_4_2"></span>
        </div>

        <!-- 公司动态 -->
        <div id="table_4_1" style="display: block;">
            <div class="news-container">
                <c:forEach var="companyNew" items="${companyNews}">
                    <div class="detail">
                        <img src="images/index/yuandian.png">
                        <span class="news-title">
                            <a title="${companyNew.newsTitle}" href="${fn:replace("news/shortcut", "shortcut" , companyNew.shortcut)}">${companyNew.newsTitle}</a>
                        </span>
                        <span class="news-date"><fmt:formatDate pattern="yyyy/MM/dd" value="${companyNew.newsDate}"/></span>
                        <img class="decoration" src="images/news/xuxian.png">
                    </div>
                </c:forEach>
            </div>
            <div class="other-news"><a href="javascript:void(0)">上一页</a> / <a href="javascript:void(0)">下一页</a></div>
        </div>

        <!-- 媒体动态 -->
        <div id="table_4_2" style="display: none;">
            <div class="news-container">
                <c:forEach var="mediaNew" items="${mediaNews}">
                    <div class="detail">
                        <img src="images/index/yuandian.png">
                        <span class="news-title">
                            <a title="${mediaNew.newsTitle}" href="${mediaNew.referenceLink}">${mediaNew.newsTitle}</a>
                        </span>
                        <span class="news-date"><fmt:formatDate pattern="yyyy/MM/dd" value="${mediaNew.newsDate}"/></span>
                        <img class="decoration" src="images/news/xuxian.png">
                    </div>
                </c:forEach>
            </div>
            <div class="other-news"><a href="javascript:void(0)">上一页</a> / <a href="javascript:void(0)">下一页</a></div>
        </div>
    </div>
</div>
<!-- main-container end -->

<!-- 页面Footer导航 -->
<%@ include file="partials/footer.jsp"%>
<script type="text/javascript" src="jslib/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="jslib/jq.Slide.js"></script>
<script type="text/javascript" src="js/aly-common.js"></script>
<script type="text/javascript" src="js/aly-index.js"></script>
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
                            case "company" :
                                shu(1,4,2);
                                break;
                            case "media" :
                                shu(2,4,2);
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
