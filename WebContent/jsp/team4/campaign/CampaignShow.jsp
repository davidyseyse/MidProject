<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<jsp:include page="Bootstrap.jsp"></jsp:include>

<title>活動查詢</title>
<style>

.title{	
	text-align:center;
}

.btnForm{
	display:inline-block;
}
</style>
</head>
<body>

<jsp:include page="nav.jsp"></jsp:include>

<div class="container">
  <h1 class="title">查詢的活動</h1>            
  <table id="CampTable" class="table table-dark table-striped">
    <thead>
      <tr>
        <th>活動名稱</th>
        <th>開始時間</th>
        <th>結束時間</th>
        <th>狀態</th>
        <th>活動描述</th>
        <th>操作</th>
      </tr>
    </thead>
    <c:set var="count" value="0"/>
    <tbody>
    <c:forEach var="camp" items="${campaigns}">
      <c:set var="count" value="${count+1}"/>
      <tr>
        <td>${camp.title}</td>
        <td>${camp.startTime}</td>
        <td>${camp.endTime}</td>
        <c:if test="${camp.status}">
        	<td>上架</td>
        </c:if>
        <c:if test="${!camp.status}">
        	<td>上架</td>
        </c:if>  
        <td>${camp.description}</td>
        <td>
        <form class="btnForm"action="CamaignDeleteServlet" method="post">
        	<input  name="queryStr" value="${queryBean.queryStr}" type="hidden" />
        	<input  name="queryType" value="${queryBean.type}" type="hidden" />
        	<input  name="campId" value="${camp.id}" type="hidden" />
        	<button class="deleteBtn btn btn-success" type="submit">刪除</button>
        </form>
        <form class="btnForm" action="" method="post">
        	<input  name="campId" value="${camp.id}" type="hidden" />
        	<button class="deleteBtn btn btn-success" type="submit">修改</button>
        </form>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <h4>共${count}筆資料</h4>
  
</div>

<script>


  
  
  
</script>
	
</body>
</html>