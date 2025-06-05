<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 style="color: red">존재하지 않는 주소입니다.</h1>
	<h2>{message}</h2>
	<h2>{url}</h2>
	<p>${pageContext.request.servletContext.contextPath }</p>

</body>
</html>