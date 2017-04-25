<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">

    <title>My JSP 'add.jsp' starting page</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/stu_add.css">
  </head>

	<body style="background-color: #6A48D7;margin: 0px;" >
		<div style="background-color: #6A48D7;height: 438px;margin-top: 70px;" align="center">
	 	<div class="basic-grey" >
	 		<form action="<%=basePath %>add" method="post" class="STYLE-NAME" >
				<h1 align="center">添加学生
					<span></span>
					</h1>
				<label>
					<span>学生姓名 :</span>
					<input id="name" type="text" name="name" placeholder="Student Name" />
				</label>
				<label>
					<span>出生日期 :</span>
					<input id="name" type="text" name="birthday" placeholder="格式为:yyyy-MM-dd" />
				</label>
				<label>
					<span>平均分 :</span>
					<input id="name" type="text" name="avgscore" placeholder="Student avgscore" />
				</label>
				<label>
					<span>备注 :</span>
					<textarea id="message" name="description" placeholder="Student description"></textarea>
				</label>
				<label>
					<span>&nbsp;</span>
					<input type="submit" class="button" value="Save" />
				</label>
			</form>
	 	</div>
	 	</div>
	</body>
</html>
