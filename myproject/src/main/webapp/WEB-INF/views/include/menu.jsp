<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
a{
	text-decoration:none;
}
.header{
	display: flex;
	-webkit-box-align:center;
	align-items:center;
	width:100%;
	height:96px;
	margin: 0px auto;
	
}
.menu{
	display:flex;
	-webkit-box-align:center;
	align-items:center;
	width:100%;
	height:54px;
	margin:0px auto;
}
.home{
	width:168px;
	height:38px;
}
.search{
	width:420px;
	height:48px;
}
.logotxt{
	width:140px;
	height:48px;
	margin-right:20px;
}
.cart{
	width:30px;
	height:25px;
}
.left{
	margin-left:auto;
}
.center{
	margin:0 auto;
}
.login{
	width:20px;
	height:10px;
}
.txt{
	color:deepgreen;
	font-weight:bold;
}
</style>
<div class="header">
	<a href="/myproject" >
		<img class="home" src="/myproject/images/houselogo.jpg">
	</a>
<!--  	<div class="logotxt">
	</div>-->
	<div class="center">
		<a href="/myproject/shop/product/list.do">
			<img class="search" src="/myproject/images/search2.jpg">
		</a>
	</div>
	<div class="left">
		<a href="/myproject/shop/cart/list.do">
			<img class="cart" src="/myproject/images/cart2.jpg">
		</a>
	</div>
</div>
<div class="menu">
<!-- <a href="/myproject">Home</a> |
 <a href="/myproject/shop/product/list.do">상품목록</a> | 
<a href="/myproject/shop/cart/list.do">장바구니</a> | -->
<a href="/myproject/board/list.do">게시판</a>



<div class="left txt">
<c:choose>
    <c:when test="${sessionScope.userid == null}">
        <a href="/myproject/signup/write.do">회원가입</a>
        <a href="/myproject/member/login.do">로그인</a>
        <a href="/myproject/admin/login.do">관리자 로그인</a>
    </c:when>
    <c:otherwise>
        ${sessionScope.name}님이 로그인중입니다.
        <c:choose>
            <c:when test="${sessionScope.userid == 'admin'}">
                <a href="/myproject/admin/logout.do">관리자 로그아웃</a>
            </c:when>
            <c:otherwise>
                <a href="/myproject/member/logout.do">로그아웃</a>
            </c:otherwise>
        </c:choose>
    </c:otherwise>
</c:choose>
</div>
</div>
<hr>