<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>도서목록</h2>
<table border="1">
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>저자</th>
		<th>가격</th>
		<th>수량</th>
<c:forEach var="row" items="${list }">
	<tr>
		<td>${row.idx }</td>
		<td>${row.title }</td>
		<td>${row.author }</td>
		<td>${row.price }</td>
		<td>${row.amount }</td>
	</tr>
</c:forEach>
	</tr>
</table>
</body>
</html>