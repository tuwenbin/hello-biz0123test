<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.4.2.js"></script>
<script type="text/javascript">
$(function () {
    setTimeout(ChangeTime, 1000);
  });

  function ChangeTime() {
    var time;
    time = $("#time").text();
    time = parseInt(time);
    time--;

    if (time <= 0) {
      window.location.href = "/redis/list";
    } else {
      $("#time").text(time);
      setTimeout(ChangeTime, 1000);
    }

  }

</script>
</head>
<body>

	<div>欢迎来到精英学校学生管理系统...,<span id="time">3</span>秒钟自动跳到主页</div>

</body>
</html>