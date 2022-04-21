<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<%@ include file="include/menu.jsp" %>
<div style="width:100%; height:151px;">
	<div style="width:100%; height:96px;"></div>
	<div style="width:100%; height:54px;"></div>
</div>
<div style="width:100%; height:400px; background-color:gray;"></div>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
