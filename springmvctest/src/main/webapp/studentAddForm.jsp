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
	<link rel="stylesheet" type="text/css" href="css/stu_add.css">
	<script type="text/javascript" src="js/jquery-1.4.2.js"></script>

  </head>
	<body style="background-color: #6A48D7;margin: 0px;" >
		<div style="background-color: #6A48D7;height: 438px;margin-top: 70px;" align="center">
	 	<div class="basic-grey" >
	 		<form action="<%=basePath%>addStudent.action" method="post" class="STYLE-NAME" name="form1" >
				<h1 align="center">添加学生
					<span></span>
					</h1>
				<label>
					<span>学生姓名 :</span>
					<input  type="text" name="name" placeholder="Student Name" />
				</label>
				<label>
					<span>出生日期 :</span>
					<input id="birthday" type="text" name="birthday" placeholder="格式为:yyyy-MM-dd" />
				</label>
				<label>
					<span>平均分 :</span>
					<input id="avgscore" type="text" name="avgscore"  placeholder="成绩在1-100之间"/>
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
	<
  <script type="text/javascript">
	  $("#birthday").blur(function (){
	      var birthday = $("#birthday").val();
	      var reg = /((^((1[8-9]\d{2})|([2-9]\d{3}))([-\/\._])(10|12|0?[13578])([-\/\._])(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))([-\/\._])(11|0?[469])([-\/\._])(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))([-\/\._])(0?2)([-\/\._])(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)([-\/\._])(0?2)([-\/\._])(29)$)|(^([3579][26]00)([-\/\._])(0?2)([-\/\._])(29)$)|(^([1][89][0][48])([-\/\._])(0?2)([-\/\._])(29)$)|(^([2-9][0-9][0][48])([-\/\._])(0?2)([-\/\._])(29)$)|(^([1][89][2468][048])([-\/\._])(0?2)([-\/\._])(29)$)|(^([2-9][0-9][2468][048])([-\/\._])(0?2)([-\/\._])(29)$)|(^([1][89][13579][26])([-\/\._])(0?2)([-\/\._])(29)$)|(^([2-9][0-9][13579][26])([-\/\._])(0?2)([-\/\._])(29)$))/;
	      if(!reg.test(birthday)){
	          alert("请输入正确的日期格式!");
	          return;
		  }
	  } );
	  $("#avgscore").blur(function(){
	      var avgscore = $("#avgscore").val();
	      if(avgscore<0||avgscore>100){
	          alert("请输入正确的成绩!");
	          return;
		  }
	  });
  </script>
</html>
