<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ include file="partials/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>加入爱乐游</title>
    <%@ include file="partials/head-config.jsp"%>
</head>

<body>
<!-- 页面导航 -->
<%@ include file="partials/nav.jsp"%>

<!--sub-banner start-->
<div class="sub-banner">
    <div class="recruit-banner"></div>
</div>
<!--sub-banner end-->

<!-- sub-container start -->
<div class="sub-container">
    <!-- 左侧 start -->
    <div class="recruit-left">
        <div class="search">
            <input type="text" id="search_content" class="text">
            <input id="search_button" class="search-btn" type="button">
        </div>
        <div class="jobs">
            <a href="javascript:void(0);" class="job1" id="all"></a>
            <a href="javascript:void(0);" class="job2" id="program"></a>
            <a href="javascript:void(0);" class="job3" id="art"></a>
            <a href="javascript:void(0);" class="job4" id="engineer"></a>
            <a href="javascript:void(0);" class="job5" id="operation"></a>
            <a href="javascript:void(0);" class="job6" id="market"></a>
            <a href="javascript:void(0);" class="job7" id="business"></a>
            <a href="javascript:void(0);" class="job8" id="function"></a>
        </div>
    </div>
    <!-- 左侧 end -->

    <!-- 右侧 start -->
    <div id="job_all" class="recruit-right" style="display: block;">
        <c:forEach var="recruit" items="${recruits}">
            <div class="job-container">
                <div class="decoration-line">
                    <img src="images/join/xian1.png">
                </div>
                <div class="job-info">
                    <div class="title">${recruit.recruitPosition}</div>
                    <div class="info">
                        <span><strong>工作地点：</strong>${recruit.workPlace}</span>
                        <span><strong>工作性质：</strong>${recruit.workProperty}</span>
                        <span><strong>招聘人数：</strong>${recruit.recruitNum}人</span>
                    </div>
                </div>
            <div class="job-detail-infos">
                <div class="responsibilities">
                    <h3>岗位职责</h3>
                    <div class="markdown">${recruit.responsibilities}</div>
                </div>
                <div class="requirements">
                    <h3>任职资格</h3>
                    <div class="markdown">${recruit.requirements}</div>
                </div>
                <div class="others">
                    <h3>其他</h3>
                    <div class="markdown">${recruit.others}</div>
                </div>
            </div>
                <div class="job-links clearfix">
                    <a href="javascript:void(0);" class="job-detail"></a>
                    <a href="javascript:void(0);" class="join-in"></a>
                </div>
            </div>
        </c:forEach>
    </div>
    <!-- 右侧 end -->
</div>
<!-- sub-container end -->

<!-- 页面Footer导航 -->
<%@ include file="partials/footer.jsp"%>
<script type="text/javascript" src="jslib/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="jslib/jquery.markdown-0.2.js"></script>
<script type="text/javascript" src="js/aly-common.js"></script>
<script type="text/javascript">
    $(function () {
        var count = 0;
        //Markdown
        $(".markdown").each(function (_index, _element) {
            try {//IE下会有异常
                $(_element).markdown();
            } catch (e) {
                console.info(e);
            }
        });

        $(".job-detail").bind("click", function (_event) {
            var jobContainer = $(_event.srcElement || _event.currentTarget || _event.target).parent().parent();
            if(jobContainer.hasClass("open")) {
                jobContainer.removeClass("open");
            } else {
                jobContainer.addClass("open");
            }
        });
    });
</script>
</body>
</html>