<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <meta charset="utf-8">
    <title>Paper Stack</title>
    <link rel="stylesheet" type="text/css" href="css/login.css" />
</head>
<body>
<div class="container">
    <section id="content">
        <form action="<%=basePath%>login.action">
            <h1>学生信息管理系统</h1>
            <div>
                <input type="text" placeholder="Username" required="" id="username" name="username"/>
            </div>
            <div>
                <input type="password" placeholder="Password" required="" id="password" name="password"/>
            </div>
            <div>
                <input type="submit" value="Log in" />
                <a href="#">Lost your password?</a>
                <a href="#">Register</a>
            </div>
        </form><!-- form -->
        <div class="button">
            <a href="#">Download source file</a>
        </div><!-- button -->
    </section><!-- content -->
</div><!-- container -->
</body>
</html>
