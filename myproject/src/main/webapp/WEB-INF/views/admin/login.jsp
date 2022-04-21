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
		var userid=$("#userid").val(); //id가 userid인 태그에 입력한 value
		var passwd=$("#passwd").val();
		if(userid==""){ //빈값이면
			alert("아이디를 입력하세요.");
			$("#userid").focus(); //포커스 이동
			return; //함수 종료 
		}
		if(passwd==""){
			alert("비밀번호를 입력하세요.");
			$("#passwd").focus();
			return;
		}
		//폼데이터를 받을 주소 지정
		document.form1.action="/myproject/admin/login_check.do";
		//폼데이터를 제출
		document.form1.submit();
	});
});
</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>관리자 로그인</h2>
<form name="form1" method="post">
<table border="1" width="400px">
	<tr>
		<td>아이디</td>
		<td><input id="userid" name="userid"></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type="password" id="passwd" name="passwd"></td>
	</tr>	
	<tr>
		<td colspan="2" align="center">
			<button type="button" id="btnLogin">로그인</button>
			<!-- 컨트롤러의 모델에 저장한 변수 -->
			<c:if test="${message == 'error' }">
				<div style="color:red;">
					아이디 또는 비밀번호가 일치하지 않습니다.
				</div>
			</c:if>
			<!-- 파라미터 변수 -->
			<c:if test="${param.message == 'logout' }">
				<div style="color:red;">
					로그아웃되었습니다.
				</div>
			</c:if>			
		</td>
	</tr>
</table>
</form>
</body>
</html>







