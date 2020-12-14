<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增成功</title>
<jsp:include page="Bootstrap.jsp"></jsp:include>
<style>
.statusMsg{
	color:red;
	text-align:center;
}
</style>
</head>
<body>
<jsp:include page="nav.jsp"></jsp:include>
	<table class="table table-dark table-striped">
    <thead>
      <tr>
        <th>活動名稱</th>
        <th>開始時間</th>
        <th>結束時間</th>
        <th>狀態</th>
        <th>活動描述</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>${camp.title}</td>
        <td>${camp.startTime}</td>
        <td>${camp.endTime}</td>
        <c:if test="${camp.status==true}">
        	<td>上架</td>
        </c:if>
        <c:if test="${camp.status==false}">
        	<td>上架</td>
        </c:if>  
        <td>${camp.description}</td>
      </tr>
    </tbody>
  </table>
  <h2 class="statusMsg">已新增成功</h2>

</html>