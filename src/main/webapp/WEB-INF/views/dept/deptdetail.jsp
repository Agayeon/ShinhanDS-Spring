<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="../common/header.jsp" %>
 <h1> ${dept.department_name }  부서 상세보기</h1>
 <form action="${cpath}/dept/deptdetail.do" method="post">
   <input type="hidden" name="job" value="update">
	 <label>부서코드 :</label> 
	 <input readonly="readonly"  type="number" name="department_id"  value="${dept.department_id}"> <br>
	 <label>부서이름 :</label> 
	 <input name="department_name" value="${dept.department_name}" > <br>
	 <label>메니저번호 :</label> 
	 <input type="number" name="manager_id" value="${dept.manager_id}"> <br>
	 <label>지역코드 :</label> 
	 <input type="number"  name="location_id" value="${dept.location_id}"> <br>
	 <input type="submit" value="수정">
 </form>
 
</body>
</html>



