<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'right.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		.press {
		font-size:20px;
		color: transparent;
		background-color : black;
		text-shadow : rgba(255,255,255,0.5) 0 5px 6px, rgba(255,255,255,0.2) 1px 3px 3px;
		-webkit-background-clip : text;
		}
	</style>
  </head>
  
  <body style="background-color: #3914AF;margin: 0px;">
    <div style="background-color: #FFFFFF;width: 1084px;height: 438px;margin-top: 40px;margin-left: 135px;padding-left: 0px;">
    	<div style="height: 50px;width: 300px;"><span class="press">欢迎来到学生信息管理系统!</span></div>
    </div>
  </body>
</html>
