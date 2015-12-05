<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<%@ page contentType="text/html; charset=UTF-8" isErrorPage="true"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>404</title>
<meta http-equiv="Cache-control" content="no-cache" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<body>
<%
//String original = (String) request.getAttribute("javax.servlet.forward.request_uri");
//String queryString = request.getQueryString();
//System.out.println("=====+++++404 url:"+original+"?"+queryString);
if (exception != null){
	exception.printStackTrace(System.out);
}
%>

很抱歉,您访问的页面不存在或已被删除。(出错码:404)<br/>
</body>
</html>