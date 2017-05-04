<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">

    <title>My JSP 'supplier.jsp' starting page</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery-1.4.2.js"></script>
  </head>
    <div style="background-color: #FFFFFF;margin-top: 40px;" align="center">
    	<div style="height: 60px;padding-top: 20px;font-size: 18px;font-weight: 800"align="center">学生信息管理系统</div>
    	<!-- 进行操作 -->
	       <div style="height: 40px; margin-top: 30px;margin-left: 180px;" align="left">
	  		<table>
	  	 		<tr>
	  	 			<td>
	  	 				<a href="<%=basePath %>goStudentAddForm.action"><img src="images/index1_86.gif" width="74" height="31" border="0" id="add"/></a>&nbsp;
	  	 				<a id="deleteAll"><img src="images/index1_84.gif" width="74" height="31" border="0" /></a>
	  	 			</td>
	  	 		</tr>
	  	 	</table>
 	 	</div>
	   	<table width="80%" border="0" cellpadding="0" cellspacing="1" bgcolor="#BBD3EB">
	   		<thead>
	   			<tr>
	              <td height="27" align="center" background="images/index1_72.gif" bgcolor="#FFFFFF">&nbsp;</td>
	             <td height="27" align="center" background="images/index1_72.gif" bgcolor="#FFFFFF">
	                <label>
	                <input type="checkbox" name="checkbox" value="checkbox" id="checkall"/>
	                </label>
	              </td>
	              <td height="27" align="center" background="images/index1_72.gif" bgcolor="#FFFFFF"><b>学生</b></td>
	              <td height="27" align="center" background="images/index1_72.gif" bgcolor="#FFFFFF"><b>学生姓名</b></td>
	              <td height="27" align="center" background="images/index1_72.gif" bgcolor="#FFFFFF"><b>出生日期</b></td>
	              <td height="27" align="center" background="images/index1_72.gif" bgcolor="#FFFFFF"><b>平均分</b></td>
	              <td height="27" align="center" background="images/index1_72.gif" bgcolor="#FFFFFF"><b>备注</b></td>
	              <td height="27" align="center" background="images/index1_72.gif" bgcolor="#FFFFFF"><b>操作</b></td>
	            </tr>
	   		</thead>

	           <!-- 显示数据列表 -->
	           <tbody>
	           <c:set var="index" value="0" />
					<c:forEach var="student" items="${page.records}" varStatus="s">
						<tr>
	                     	 <td height="26" align="center" bgcolor="#FFFFFF">&nbsp;</td>
							 <td height="26" align="center" bgcolor="#FFFFFF">
		               		 	<input type="checkbox" name="checkbox" value="checkbox" class="checkbox"/>
			              	 </td>
							 <td style="display: none"><c:out value="${student.id}"></c:out></td>
			              	 <td height="26" align="center" bgcolor="#FFFFFF">${s.index+1}</td>
		                     <td height="26" align="center" bgcolor="#FFFFFF"><c:out value="${student.name}"></c:out></td>
		                     <td height="26" align="center" bgcolor="#FFFFFF"><fmt:formatDate value="${student.birthday}"/></td>
		                     <td height="26" align="center" bgcolor="#FFFFFF"><c:out value="${student.avgscore}"></c:out></td>
		                     <td height="26" align="center" bgcolor="#FFFFFF"><c:out value="${student.description}"></c:out></td>
		                     <td height="26" align="center" bgcolor="#FFFFFF"><a href="<%=basePath %>goStudentUpdateForm.action?deleteIds=${student.id}"  ><img src="images/index1_82.gif"  height="31" border="0" /></a><a href="<%=basePath %>deleteStudent.action?deleteIds=${student.id}" ><img src="images/index1_84.gif" width="74" height="31" border="0" /></a></td>
	                 	</tr>
					</c:forEach>
	           </tbody>
	       </table>
	       <br><br>
	       <%@include file="/common/page.jsp"%>
	    </div>

     <script type="text/javascript">
    	$().ready(function(){
    		$("#checkall").change(function(){
    			if($("#checkall").attr("checked")){
    				$(".checkbox").attr("checked",true);
    			}else{
    				$(".checkbox").attr("checked",false);
    			}
    		});
    	});
    	//进行删除操作
    	$("#deleteAll").click(function(){
    		var msg = "你真的确认要删除吗？";
			if(confirm(msg) == true){
				var checks = $("input[type='checkbox']");
				var deleteIds = "";
				//获取到删除的所有选中的id
				$(checks).each(function(index,element){
					if(index>=1){
						if($(element).attr("checked")){
							var s=$(element).parent().next();
							var text = $(s).html();
							deleteIds = deleteIds+text+",";
						}
					}
				});
				location.href = "deleteAllStudent.action?deleteIds="+deleteIds;
				$("#deleteAll").Attr("href","<%=basePath%>deleteAllStudent.action?deleteIds="+deleteIds);
				return true;
			}else{
				return false;
			}
		});

    </script>
  </body>
</html>
