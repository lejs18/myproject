<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<a href="/myproject">Home</a> |
<a href="/myproject/shop/product/list.do">상품목록</a> | 
<a href="/myproject/shop/cart/list.do">장바구니</a> | 
<a href="/myproject/board/list.do">게시판</a> |
<a href="/myproject/board/list.do">게시판</a> |
<a href="/myproject/board/list.do">게시판</a> |



<div style="text-align:right">
<c:choose>
	<c:when test="${sessionScope.userid == null }">
		<a href="/myproject/signup/signup.do">회원가입</a> |
		<a href="/myproject/member/login.do">로그인</a> | 
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
<hr>








