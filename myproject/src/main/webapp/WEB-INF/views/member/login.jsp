<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
$(function(){
	$("#btnLogin").click(function(){
		var userid=$("#userid").val();
		var passwd=$("#passwd").val();
		if(userid == ""){
			alert("아이디를 입력하세요.");
			$("#userid").focus();
			return;
		}
		if(passwd == ""){
			alert("비밀번호를 입력하세요.");
			$("#passwd").focus();
			return;
		}
		document.form1.action="/myproject/member/login_check.do";
		document.form1.submit();
	});
});

</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<div align="center" style="margin-top:20%;">
<h2>집꾸미기 로그인</h2>
<form name="form1" method="post">

	<input id="userid" name="userid" placeholder="아이디" style=" border-radius:5px; border:1px solid gray; width:300px; height:30px;"><br>
	<input type="password" id="passwd" name="passwd" placeholder="비밀번호" style=" border-radius:5px; border:1px solid gray; width:300px; height:30px; margin-top:5px;"><br>
	<button type="button" id="btnLogin" style="font-size:16px; font-weight:bold; border-radius:5px; border:1px solid gray; width:310px; height:50px; margin-top:5px; margin-bottom:5px; background-color:red; color:white;">로그인</button>
	<c:if test="${message == 'error'}">
		<div style="color:red"> 아이디 또는 비밀번호가 일치하지 않습니다.</div>
	</c:if>
	<c:if test="${message == 'logout'}">
		<div style="color:red"> 로그아웃 되었습니다.</div>
	</c:if>
	<c:if test="${param.message == 'nologin'}">
		<div style="color:blue">로그인 하신 후 사용하세요.</div>
	</c:if>
</form>
</div>
</body>
</html>