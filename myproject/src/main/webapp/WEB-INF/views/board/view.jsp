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
	$("#btnList").click(function(){
		location.href="/myproject/board/list.do";
	});
	listReply("1"); //댓글 1페이지
	$("#btnReply").click(function(){
		reply();
	});
	$(".fileDrop").on("dragenter dragover",function(e){
		e.preventDefault(); //태그의 기본효과 무시
	});
	$(".fileDrop").on("drop",function(e){
		e.preventDefault();
		var files=e.originalEvent.dataTransfer.files; //첨부파일 배열
		var file=files[0]; //첫번째 파일
		var form_data=new FormData(); //폼 태그 생성
		form_data.append("file",file); //폼에 첨부파일 추가
		$.ajax({
			url: "/myproject/upload/ajax_upload",
			data: form_data, 
			dataType: "text",
			processData: false,
			contentType: false, 
			type: "post",
			success: function(data){
				var fileInfo=getFileInfo(data); 
				var html="<a href='"+fileInfo.get_link+"'>"+fileInfo.original_name+"</a><br>";
				html+="<input type='hidden' class='file' value='"+fileInfo.file_name+"'>";
				$("#uploadedList").append(html); //업로드 파일 목록에 추가 
			}
		});
	});	
	listAttach(); //첨부파일 리스트
	$("#uploadedList").on("click",".file_del",function(e){
		var that=$(this); //클릭한 첨부파일
		$.ajax({
			type:"post",
			url: "/myproject/upload/delete_file",
			data: { file_name: $(this).attr("data-src")},
			dataType: "text",
			success: function(result){
				if(result=="deleted"){ //삭제되었으면
					that.parent("div").remove(); //화면에서 제거 
				}
			}
		});
	});
	$("#btnUpdate").click(function(){
		var str="";
		// id가 uploadedList인 태그 내부의 class가 file인 태그들 (반복처리)
		$("#uploadedList .file").each(function(i){
			str+="<input type='hidden' name='files["+i+"]' value='"+$(this).val()+"'>";
		});
		$("#form1").append(str); //id가 form1인 태그에 추가 
		document.form1.action="/myproject/board/update.do";
		document.form1.submit();
	});
	$("#btnDelete").click(function(){
		if(confirm("삭제하시겠습니까?")){
			document.form1.action="/myproject/board/delete.do";
			document.form1.submit();
		}
	});	
});
function getFileInfo(file_name){
	var get_link="/myproject/upload/display_file?file_name="+file_name;
	original_name=file_name.substr(file_name.indexOf("_")+1);
	return { original_name: original_name, get_link: get_link, file_name: file_name};
}
function listAttach(){
	$.ajax({
		type:"post",
		url:"/myproject/board/list_attach/${dto.idx}",
		success:function(list){
			console.log("list:"+list);
			$(list).each(function(){
				var fileInfo=getFileInfo(this);
				var html="<div><a href='"+fileInfo.get_link+"'>"+fileInfo.original_name+"</a>&nbsp;&nbsp;";
				html+="<a href='#' class='file_del' data-src='"+this+"'>[삭제]</a></div>";
				$("#uploadedList").append(html);
			});
		}
	});
}
function reply(){
	var reply_text=$("#reply_text").val();
	var board_idx="${dto.idx}";
	var params={"reply_text": reply_text, "board_idx": board_idx};
	$.ajax({
		type:"post",
		url:"/myproject/reply/insert.do",
		data: params,
		success: function(){
			alert("댓글이 등록되었습니다.");
			listReply("1");
		}
	});
}
function listReply(num){
	$.ajax({
		url:"/myproject/reply/list.do?board_idx=${dto.idx}&cur_page="+num,
		success: function(result){
			$("#listReply").html(result);
		}
	});
}
function showModify(idx){
	$.ajax({
		url:"/myproject/reply/detail/"+idx,
		success: function(result){
			$("#modifyReply").html(result);
			$("#modifyReply").css("visibility","visible"); //화면에 표시
		}
	});
}
</script>
<style>
.fileDrop { width:600px; height:100px; border:1px dotted gray; background: gray; }
</style>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>게시물 보기</h2>
<form id="form1" name="form1" method="post">
<div>작성일자: <fmt:formatDate value="${dto.regdate}" pattern="yyyy-MM-dd HH:mm:ss"/> </div>
<div>조회수: ${dto.hit}</div>
<div>이름: ${dto.name}</div>
<div>제목: <input name="title" value="${dto.title}"></div>
<div style="width:80%">내용: <textarea rows="3" cols="80" name="contents" id="contents">${dto.contents}</textarea></div>
<div id="uploadedList"></div>
<div class="fileDrop"></div>
<div>
<input type="hidden" name="idx" value="${dto.idx}">
<c:if test="${sessionScope.userid == dto.writer }">
	<button type="button" id="btnUpdate">수정</button>
	<button type="button" id="btnDelete">삭제</button>
</c:if>
<button type="button" id="btnList">목록</button>
</div>
</form>
<div style="width:700px; text-align:center;">
<c:if test="${sessionScope.userid != null }">
	<textarea rows="5" cols="80" id="reply_text" placeholder="댓글을 작성하세요."></textarea>
	<br>
	<button type="button" id="btnReply">댓글쓰기</button>
</c:if>
</div>
<div id="listReply"></div>
<div id="modifyReply"></div>
</body>
</html>







