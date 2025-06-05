<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>게시글 목록</h1>
<%@ include file="../common/header.jsp" %>
<table class="table striped">
	<tr>
		<th>번호</th><th>title</th><th>작성자</th><th>content</th><th>이미지1</th><th>이미지2</th>
	</tr>
	<c:forEach items="${boardList}" var="board">
		<tr>
			<td>${board.bno}</td>
			<td>${board.title}</td>
			<td>${board.writer}</td>
			<td>${board.content}</td>
			<td>${board.pic1}</td>
			<td>${board.pic2}</td>
		</tr>
	</c:forEach>
</table>
<a href="${cpath}/download.do?fileName=${file1}">
   <img
   style="border:5px dotted green;"
   alt="이미지1" src="${cpath}/uploadLocation/${file1}" width="100" height="100">
</a>
<a href="${cpath}/download.do?fileName=${file2}">
   <img
   style="border:5px dotted green;"
   alt="이미지1" src="${cpath}/uploadLocation/${file2}" width="100" height="100">
</a>
</body>