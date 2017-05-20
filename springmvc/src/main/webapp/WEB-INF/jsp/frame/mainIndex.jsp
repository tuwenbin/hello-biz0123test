<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'mainindex.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/style.css">
	<script type="text/javascript" src="<%=basePath%>/js/jquery-1.4.2.js"></script>
  </head>
  
  <body>
   	<iframe src="<%=basePath%>/toTop.action" width="100%" height="80px;" scrolling="no" frameborder="0">
   		
   	</iframe>
   	<div id="menu1" style="z-index: 99;background-color: #6A48D7;height: 50px;" >
   		<table>
   			<tr>
   				<td>
   					<div style="width: 150px;margin-left: 280px;color:#FFF;line-height:14px;font-size:14px;font-weight:bold;">
			   			当前用户 : ${user.username}
			   		</div>
   				</td>
   				<td>
   					
   		<div style="width: 600px;margin-left: 300px;">
   			<nav>
			  <ul>
			    <li>
			      <a href="<%=basePath%>/toMain.action" target="body">首页</a>
			      <ul>
			      </ul>
			    </li>
			    <li>
			      <a>学生管理</a>
			      <ul>
			        <li><a href="<%=basePath%>toStudentMain.action" target="body">学生列表</a></li>
			        <li><a href="<%=basePath%>toStudentAddForm.action" target="body">添加学生</a></li>
			      </ul>
			    </li>
			    <li>
			      <a>班级管理</a>
			      <ul>
			        <li><a href="<%=basePath%>toClassMain.action" target="body">班级列表</a></li>
			        <li><a href="<%=basePath%>toClassAddForm.action" target="body">新增班级</a></li>
			      </ul>
			    </li>
			    <li>
			      <a>学科管理</a>
			      <ul>
					  <li><a href="<%=basePath%>toSubjectMain.action" target="body">学科列表</a></li>
					  <li><a href="<%=basePath%>toSubjectAddForm.action" target="body">新增学科</a></li>
			      </ul>
			  </ul>
			</nav>
   		</div>
   				</td>
   			</tr>
   		</table>
   	</div>
   	<iframe src="<%=basePath%>/toMain.action" name="body" scrolling="auto" width="100%" height="80%" frameborder="0"></iframe>
  </body>
</html>
