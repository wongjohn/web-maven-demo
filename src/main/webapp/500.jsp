<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<%@ page contentType="text/html; charset=UTF-8" isErrorPage="true"%>
<html>
<head>
<title>500</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta http-equiv="Cache-control" content="no-cache" />
</head>
<body>
<%
//String original = (String) request.getAttribute("javax.servlet.forward.request_uri");
//String queryString = request.getQueryString();
//System.out.println("=====+++++500 url:"+original+"?"+queryString);
if (exception != null){
	exception.printStackTrace(System.out);
}
%>
网络繁忙,请稍后再试!(出错码:500)<br/>
</body>
</html>
