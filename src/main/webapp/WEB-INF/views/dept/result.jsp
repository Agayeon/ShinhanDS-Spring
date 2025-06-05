<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="refresh" content="2,url=deptlist.do">
<title>Insert title here</title>
</head>
<body>
	<h1>DB입력 후 보이는 창임 : @ModelAttribute 연습</h1>
	<p>입력성공:${dept}</p>
	<script>
		var message = "${dept}";
		if(message!="") {
			alert("입력성공");
		} else {
			alert("입력실패");
		}
		
	
	</script>
</body>
</html>