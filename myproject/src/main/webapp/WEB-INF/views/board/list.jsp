<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
$(function(){
	$("#btnWrite").click(function(){
		location.href="/myproject/board/write.do";
	});
});
function list(page){
	location.href="/myproject/board/list.do?cur_page="+page+"&search_option=${map.search_option}&keyword=${map.keyword}";
}
</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>게시판</h2>
<form name="form1" method="post" action="/myproject/board/list.do">
<select name="search_option">
	<option value="all" <c:out value="${map.search_option == 'all' ? 'selected' : '' }" /> >이름+내용+제목</option>
	<option value="name" <c:out value="${map.search_option == 'name' ? 'selected' : '' }" /> >이름</option>
	<option value="contents" <c:out value="${map.search_option == 'contents' ? 'selected' : '' }" /> >내용</option>
	<option value="title" <c:out value="${map.search_option == 'title' ? 'selected' : '' }" /> >제목</option>
</select>
<input name="keyword" value="${map.keyword}">
<input type="submit" value="조회">
<c:if test="${sessionScope.userid != null }">
	<button type="button" id="btnWrite">글쓰기</button>
</c:if>
</form>
${map.count}개의 게시물이 있습니다.
<table border="1" width="600px">
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>이름</th>
		<th>날짜</th>
		<th>조회수</th>
	</tr>
<c:forEach var="row" items="${map.list}">
	<tr>
		<td>${row.idx}</td>
		<td><a href="/myproject/board/detail.do?idx=${row.idx}&cur_page=${map.page_info.curPage}&search_option=${map.search_option}&keyword=${map.keyword}">${row.title}</a>
			<c:if test="${row.cnt > 0 }">
				<span style="color:red">( ${row.cnt} )</span>
			</c:if>
		</td>
		<td>${row.name}</td>
		<td><fmt:formatDate value="${row.regdate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
		<td>${row.hit}</td>
	</tr>
</c:forEach>	
	<tr>
		<td colspan="5" align="center">
			<c:if test="${map.page_info.curBlock > 1 }">
				<a href="javascript:list('1')">[처음]</a>
			</c:if>
			<c:if test="${map.page_info.curBlock > 1 }">
				<a href="javascript:list('${map.page_info.prevPage}')">[이전]</a>
			</c:if>			
			<c:forEach var="num" begin="${map.page_info.blockBegin}" end="${map.page_info.blockEnd}">
				<c:choose>
					<c:when test="${num == map.page_info.curPage}">
						<span style="color:red">${num}</span>&nbsp;
					</c:when>
					<c:otherwise>
						<a href="javascript:list('${num}')">${num}</a>&nbsp;
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${map.page_info.curBlock <= map.page_info.totBlock}">
				<a href="javascript:list('${map.page_info.nextPage}')">[다음]</a>
			</c:if>
			<c:if test="${map.page_info.curBlock <= map.page_info.totPage}">
				<a href="javascript:list('${map.page_info.totPage}')">[끝]</a>
			</c:if>			
		</td>
	</tr>
</table>
</body>
</html>







