<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 分页开始 -->
  			第${page.currentPageNum }页/共${page.totalPage }页
  				<a href="<%=basePath%>goStudentMain.action?num=1">首页</a>
  				<a href="<%=basePath%>goStudentMain.action?num=${page.currentPageNum-1>0?page.currentPageNum-1:1}">上一页</a>

  				<c:forEach begin="${page.startPage}" end="${page.endPage}" var="num">
  					<a href="<%=basePath%>goStudentMain.action?num=${num}">${num }</a>
  				</c:forEach>

  				<a href="<%=basePath%>goStudentMain.action?num=${page.currentPageNum+1<page.totalPage?page.currentPageNum+1:page.totalPage}">下一页</a>
  				<a href="<%=basePath%>goStudentMain.action?num=${page.totalPage}">尾页</a>


	<!-- 分页结束 -->

