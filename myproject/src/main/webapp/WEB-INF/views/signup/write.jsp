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
$(function (){
	 $("#btnPasswd").click(function(){
	        var passwd = $("#passwd").val();
	        var passwd2 = $("#passwd2").val();
	        if(passwd != passwd2) {
	          alert("비밀번호가 일치하지 않습니다");
	          $("#passwd2").val('');
	          $("#passwd2").focus();
	          return;
	        } else{
	          alert("비밀번호가 일치합니다");
	          $("#name").focus();
	        }
	    });
	$("#btnOK").click(function(){
		var userid=$("#userid").val();
		var passwd=$("#passwd").val();
		var passwd2=$("#passwd2").val();
		var name=$("#name").val();
		var email=$("#email").val();
		var phonenum=$("#phonenum").val();
		var address=$("#address").val();
		if(userid==""){
			alert("아이디를 입력하세요.");
			$("#userid").focus();
			return;
		}
		if(passwd==""){
			alert("비밀번호를 입력하세요.");
			$("#passwd").focus();
			return;
		}
		if(passwd != passwd2){
			alert("비밀번호가 일치하지 않습니다.");
            $("#passwd2").focus();
            return;
        }
		if(name==""){
		alert("이름을 입력하세요.");
		$("#name").focus();
		return;
		}
		if(email==""){
			alert("이메일을 입력하세요.");
			$("#email").focus();
			return;
		}
		if(phonenum==""){
			alert("휴대폰번호를 입력하세요.");
			$("#phonenum").focus();
			return;
		}
		if(address==""){
			alert("주소를 입력하세요.");
			$("#address").focus();
			return;
		}
		document.form1.action="/myproject/signup/insert.do";
		document.form1.submit();
	});
});
</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>회원등록폼</h2>
<form name="form1" method="post">
	<table border="1" width="400px">
		<tr>
			<td>아이디</td>
			<td><input id="userid" name="userid"></td>			
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input id="passwd" type="password" name="passwd"></td>			
		</tr>
		<tr>
			<td>비밀번호 확인</td>
		    <td>
		    <input type="password" name="passwd2" id="passwd2">
		    <input type="button" name="btnPasswd" id="btnPasswd" value="확인">
		    </td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input id="name" name="name"></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input id="email" name="email"></td>
		</tr>
		<tr>
			<td>휴대폰번호</td>
			<td><input id="phonenum" name="phonenum"></td>
		</tr>
		<tr>
			<td>주소</td>
			<td><input id="address" name="address"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			<input type="button" id="btnOK" value="확인">			
			</td>		
		</tr>
	</table>
</form>
</body>
</html>