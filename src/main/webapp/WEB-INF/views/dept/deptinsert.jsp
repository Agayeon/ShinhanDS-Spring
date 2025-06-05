<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<script>
	//default event에 대해, 이미 제공되는 이베트 핸들러가 있다.
	window.onload = function(event) {
		
		var frmObj = document.querySelector("#myfrm");
	//1 inline 방식으로 이벤트와 이벤트 핸들러 연결
	function call() {
		var deptnameObj = document.querySelector('input[name="department_name"]').value;
		var deptname =deptnameObj.value;
		if(deptname.length < 5) {
			alert("부서이름은 5자리 이상입니다.");	
			deptnameObj.focus();
			return false;
	}
		
		
		frmObj.onsubmit = function() {
			var deptnameObj = document.querySelector.('input[name="department_name"]').value;
			var deptname =deptnameObj.value;
			if(deptname.length < 5) {
				alert("부서이름은 5자리 이상입니다.");
				deptnameObj.focus();
				//default event를 취소
				// 1. return false;
		
				// 2. preventDefault()
				
				return false;
				event.preventDefault();
				return;
			}
			
			alert("서버에 전송된다.");
		};
	};
</script>

<body>

<%-- <p>${sessionScope.dept} --%>
<%@ include file="../common/header.jsp" %>

<img alt="" src="${cpath}/resources/img/d.jpg" width=150px; height=150px;>
 <h1>부서 입력</h1>
<form id="myfrm" action="deptinsert.do" method="post">
     <input type="hidden" name="job" value="insert" >
	 <label>부서코드 :</label> 
	 <input  type="number" name="department_id" required="required"   autofocus="autofocus" > <br>
	 <label>부서이름 :</label> 
	 <input name="department_name" required="required"> <br>
	 <label>메니저번호 :</label> 
	 <input type="number" name="manager_id" placeholder="존재하는 직원" > <br>
	 <label>지역코드 :</label> 
	 <input type="number" name="location_id" placeholder="존재하는 지역코드" > <br>
	 <input type="submit" value="입력">
	 <input type="reset" value="다시입력">
	 <br>
	 부서소개:
	 <textarea rows="5" cols="80"></textarea>

</form>

 
</body>
</html>





