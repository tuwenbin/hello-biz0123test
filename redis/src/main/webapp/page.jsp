<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 分页开始 -->
  			第${page.currentPageNum }页/共${page.totalPage }页
  				<a href="${pageContext.request.contextPath }/servlet/Controller?op=showAllCustomer&num=1">首页</a>
  				<a href="${pageContext.request.contextPath }/servlet/Controller?op=showAllCustomer&num=${page.currentPageNum-1>0?page.currentPageNum-1:1}">上一页</a>
  				
  				<c:forEach begin="${page.startPage}" end="${page.endPage}" var="num">
  					<a href="${pageContext.request.contextPath }/servlet/Controller?op=showAllCustomer&num=${num}">${num }</a>
  				</c:forEach>
  				
  				<a href="${pageContext.request.contextPath }/servlet/Controller?op=showAllCustomer&num=${page.currentPageNum+1<page.totalPage?page.currentPageNum+1:page.totalPage}">下一页</a>
  				<a href="${pageContext.request.contextPath }/servlet/Controller?op=showAllCustomer&num=${page.totalPage}">尾页</a>
  				
  			<select id="num" onchange="jump(this)">
    			<c:forEach begin="1" end="${page.totalPage}" var="num">
    				<option value="${num}" ${page.currentPageNum==num?'selected="selected"':''}>${num}</option>
    			</c:forEach>
    		</select>&nbsp;&nbsp;
    		<input type="text" size="3" id="numInput"/><a href="javascript:jump1()">跳转</a>
  				<script type="text/javascript">
  					function jump(selectObj){
  						window.location.href="${pageContext.request.contextPath }/servlet/Controller?op=showAllCustomer&num="+selectObj.value;
  					}
  					function jump1(){
  						var numInputObj = document.getElementById("numInput");
  						//验证：必须是数字
  						if(!/^[1-9][0-9]*$/.test(numInputObj.value)){
  							alert("请输入正确的页码");
  							return;
  						}
  						//是否超出范围
  						if(numInputObj.value>${page.totalPage}){
  							alert("页码超出范围");
  							return;
  						}
  						window.location.href="${pageContext.request.contextPath }/servlet/Controller?op=showAllCustomer&num="+numInputObj.value;
  					}
  				</script>
	<!-- 分页结束 -->

