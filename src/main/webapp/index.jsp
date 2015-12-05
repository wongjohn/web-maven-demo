<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ include file="partials/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>爱乐游 —— 爱游戏、爱事业、爱生活！</title>
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
        <div class="state-container">
            <!-- 公司动态 -->
            <div class="company-state">
                <div class="title"><!-- 标题 -->
                    <div class="clearfix">
                        <img class="name" src="images/index/gongsidongtai.png">
                        <a href="news"><img class="more" src="images/index/more.png"></a>
                    </div>
                    <img class="decoration" src="images/index/zhuangshitiao.png">
                </div>
                
                <c:forEach var="companyNew" items="${companyNews}">
                    <div class="detail">
                        <img src="images/index/yuandian.png">
                        <span class="news-title">
                            <a title="${companyNew.newsTitle}" href="${fn:replace("news/shortcut", "shortcut" , companyNew.shortcut)}">${companyNew.newsTitle}</a>
                        </span>
                        <span class="news-date"><fmt:formatDate pattern="yyyy/MM/dd" value="${companyNew.newsDate}"/></span>
                        <img class="decoration" src="images/index/xuxian.png">
                    </div>
                </c:forEach>
            </div>

            <!-- 媒体动态 -->
            <div class="media-state">
                <div class="title"><!-- 标题 -->
                    <div class="clearfix">
                        <img class="name" src="images/index/meitidongtai.png">
                        <a href="news?tab=media"><img class="more" src="images/index/more.png"></a>
                    </div>
                    <img class="decoration" src="images/index/zhuangshitiao.png">
                </div>

                <c:forEach var="mediaNew" items="${mediaNews}">
                    <div class="detail">
                        <img src="images/index/yuandian.png">
                        <span class="news-title">
                            <a title="${mediaNew.newsTitle}" href="${mediaNew.referenceLink}">${mediaNew.newsTitle}</a>
                        </span>
                        <span class="news-date"><fmt:formatDate pattern="yyyy/MM/dd" value="${mediaNew.newsDate}"/></span>

                        <img class="decoration" src="images/index/xuxian.png">
                    </div>
                </c:forEach>
            </div>
        </div>

        <div class="hot-games">
            <div class="title"><!-- 标题 -->
                <div class="clearfix">
                    <img class="name" src="images/index/remenyouxi.png">
                    <a href="games"><img class="more" src="images/index/more.png"></a>
                </div>
                <img class="decoration" src="images/index/zhuangshitiao2.png" >
            </div>

            <div class="online-game clearfix">
                <img class="description" src="images/index/wangluoyouxi.png">
                <a href="http://lt.qq.com"><img src="images/index/1.png"></a>
                <a href="http://xj.5agame.com"><img src="images/index/2.png"></a>
                <a href="http://cb.5agame.com/"><img src="images/index/3.png"></a>
            </div>

            <div class="break-line">
                <img src="images/index/xuxian2.png">
            </div>

            <div class="casual-game clearfix">
                <img class="description" src="images/index/xiuxianyouxi.png">
                <a href="games?tab=casual"><img src="images/index/4.png"></a>
                <a href="games?tab=casual"><img src="images/index/5.png"></a>
                <a href="games?tab=casual"><img src="images/index/6.png"></a>
            </div>
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
</body>
</html>
