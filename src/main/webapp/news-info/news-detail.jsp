<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ include file="../partials/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>爱乐游-${companyNew.newsTitle}</title>
    <%@ include file="../partials/head-config.jsp"%>
</head>

<body>
<!-- 页面导航 -->
<%@ include file="../partials/nav.jsp"%>

<!-- 页面条幅 -->
<%@ include file="../partials/banner.jsp"%>

<!-- main-container start -->
<div class="main-container clearfix">
    <!-- 左侧区域 -->
    <%@ include file="../partials/left-section.jsp"%>

    <!-- 右侧区域 -->
    <div class="right-section">
        <div class="news-detail">
            <h2 class="news-title">${companyNew.newsTitle}</h2>
            <div class="author-info">
                <span>来源：${companyNew.newsSource}</span>
                <span>作者：${companyNew.newsAuthor}</span>
                <span>时间：<fmt:formatDate pattern="yyyy-MM-dd" value="${companyNew.newsDate}"/></span>
            </div>
            <div class="markdown">${companyNew.newsContent}</div>
        </div>
        <div class="other-news">
            <div class="former">
                <a class="highlight" href="${companyNew.previousShortcut}">上一页：</a>
                <span class="other-news-title">${companyNew.previousTitle}</span>
            </div>
            <div class="later">
                <a class="highlight" href="${companyNew.nextShortcut}">下一页：</a>
                <span class="other-news-title">${companyNew.nextTitle}</span>
            </div>
        </div>
    </div>
</div>
<!-- main-container end -->

<!-- 页面Footer导航 -->
<%@ include file="../partials/footer.jsp"%>
<script type="text/javascript" src="../jslib/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="../jslib/jq.Slide.js"></script>
<script type="text/javascript" src="../jslib/jquery.markdown-0.2.js"></script>
<script type="text/javascript" src="../js/aly-common.js"></script>
<script type="text/javascript" src="../js/aly-index.js"></script>
<script type="text/javascript">
    $(function () {
        $(".markdown").markdown();
    });
</script>
</body>
</html>
