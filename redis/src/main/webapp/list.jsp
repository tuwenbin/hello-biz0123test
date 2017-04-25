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
	<%! int i = 1; %>
    <div style="background-color: #FFFFFF;margin-top: 40px;" align="center">
    	<div style="height: 60px;padding-top: 20px;font-size: 18px;font-weight: 800"align="center">学生信息管理系统</div>
    	<!-- 进行操作 -->
	       <div style="height: 40px; margin-top: 30px;margin-left: 180px;" align="left">
	  		<table>
	  	 		<tr>
	  	 			<td>
	  	 				<a href="<%=basePath %>addUI"><img src="images/index1_86.gif" width="74" height="31" border="0" id="add"/></a>&nbsp;
	  	 				<a  id="updateA" ><img src="images/index1_82.gif" width="74" height="31" border="0" id="update"/></a>&nbsp;
	  	 				<img src="images/index1_84.gif" width="74" height="31" border="0" id="deleteAll"/>
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
	            </tr>
	   		</thead>

	           <!-- 显示数据列表 -->
	           <tbody>
	           <c:set var="index" value="0" />
					<c:forEach var="student" items="${page.records}" varStatus="s">
					<c:set var="index" value="${index+1}" /></td>
						<tr>
	                     	 <td height="26" align="center" bgcolor="#FFFFFF">&nbsp;</td>
	                     	 <td height="26" align="center" bgcolor="#FFFFFF">
		               		 	<input type="checkbox" name="checkbox" value="checkbox" class="checkbox"/>
			              	 </td>
			             	 <td style="display: none"><c:out value="${student.id}"></c:out>

			              	 <td height="26" align="center" bgcolor="#FFFFFF">${index}</td>
		                     <td height="26" align="center" bgcolor="#FFFFFF"><c:out value="${student.name}"></c:out></td>
		                     <td height="26" align="center" bgcolor="#FFFFFF"><fmt:formatDate value="${student.birthday}"/></td>
		                     <td height="26" align="center" bgcolor="#FFFFFF"><c:out value="${student.avgscore}"></c:out></td>
		                     <td height="26" align="center" bgcolor="#FFFFFF"><c:out value="${student.description}"></c:out></td>
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
				//异步请求到delete方法
				$.ajax({
			          url:"delete",
			          type:"get",
			          dataType: "txt",
			          data:{
			        	  deleteIds : deleteIds
			          },
			          error:function(){
			        	  window.location.reload();
			          },
			          success:function()
	                  {
	                     window.location.reload();
	                  }
			        });
				return true;
			}else{
				return false;
			}
		});
    	//对选中的一项进行修改
    	$("#update").click(function(){
    		var check = document.getElementsByTagName("input");
    		var n = 0;
    		for(var i=0;i<check.length;i++){
    			if(check[i].checked == true){
        			n=n+1;
        		}
    		}
    		if(n==0){
    			if(confirm("请选择一项!") == true){
    				return false;
    			}else{
    				return false;
    			}
    		}
    		if(n>1){
    			if(confirm("只能选择一项!") == true){
    				return false;
    			}else{
    				return false;
    			}
    		}
    		//获取到修改的id
    		var checks = $("input[type='checkbox']");
			var deleteIds = "";
			//获取到修改的所有选中的id
			$(checks).each(function(index,element){
				if(index>=1){
					if($(element).attr("checked")){
						var s=$(element).parent().next();
						var text = $(s).html();
						deleteIds = text;
					}
				}
			});

    		location.href = "updateUI?deleteIds="+deleteIds;
    		$("#updateA").Attr("href","updateUI?deleteIds="+deleteIds);
    	});
    </script>
  </body>
</html>
