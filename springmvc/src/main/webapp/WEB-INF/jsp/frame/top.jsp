<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'top.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/top.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/style.css">
	  <script type="text/javascript" src="js/jquery-1.4.2.js"></script>
  </head>
  
  <body>
     <div id="b_top">
     	<div id="b_top1">
     		<div id="b_top11">
     			<img alt="" src="images/index1_06.gif">
     			<a href="#"><strong class="font2">帮助</strong></a>
     		</div>
     	</div>
     	<div id="b_top2">
     		<div id="biaoti">学生信息管理系统</div>
     	</div>
     </div>
     <div id="b_top12">
     			<img alt="" src="images/index1_08.gif">
     			<a href="<%=basePath%>resige.action" onclick="top.location.href='<%=basePath%>resige.action'"><strong class="font2">退出</strong></a>
     </div>
  </body>

</html>
