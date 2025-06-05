<%@ page language="java" contentType="text/html; charset=UTF-8"
	session="true" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	


<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<c:set var="cpath" value="${pageContext.request.contextPath}" />


<script>

var obj = {
		"employee_id": 17,
		"first_name": null,
		"last_name": "라스트네임",
		"email": "이메일1212",
		"phone_number": null,
		"hire_date": 1748444400000,
		"job_id": "play",
		"salary": null,
		"commission_pct": null,
		"manager_id": null,
		"department_id": 10
		}


$(function(){
	$("#btnInsert").on("click", f_insert);
});

function f_insert() {
	console.log("f_insert 구현");
	var obj = {};
	var arr = $("#myfrm").serializeArray();
	$.each(arr,function(index,item) {
		obj[item.name] = item.value;
	});
			
 	$.ajax({
		url:`${cpath}/emp/api/empinsert.do`,
		type: "post",
		data: JSON.stringify(obj),    
		contentType: "application/json;charset=utf-8",
		success : function(responseData) {
			console.log(responseData);
		}
	});
}

</script>

</head>




<body>

<button id="btnInsert">입력(JSON이용)</button>

	<%-- request --> session --> application --%> 
<%-- 정적자원, jsp를 합쳐서 컴파일한다.(.java) -> class -> jsp 실행 --%>
<%@ include file="../common/header.jsp" %>



<!--동적자원, 각각의 JSP를 컴파일 후에 합친다. JSTL은 자동으로 contextpath인식 -->

<!-- HTML 태그는 contextpath 인식 못함 -> cpath를 꼭 붙여야 함 -->
<img src = "${cpath}/resources/img/d.jpg"/ width=150px; height=150px;>
	<h1>신규 직원 등록</h1>
	
<form id="myfrm" action="${cpath}/emp/empinsert.do" method="post"	>
<input type="hidden" name="jobselect" value="insert">
    <label>직원번호:</label>
    <input type="number" name="employee_id" >
    <span id="here">???</span>
	<br>
    <label>이름:</label>
    <input type="text" name="first_name"  ><br>

    <label>성:</label>
    <input type="text" name="last_name"  ><br>
    
    <label>전화번호:</label>
    <input type="tel" name="phone_number"><br>
 	<label>이메일:</label>
    	<input type="text" name="email"><br>
    

   <label>입사일:</label>
    <input type="date" name="hire_date"><br>
    
    
    <label>급여:</label>
    <input name="salary" ><br>

   

    <label>커미션:</label>
    <input type="number" name="commission_pct"  ><br>

    <label>manager_id:</label>
    <input type="number" name="manager_id" ><br>

    
    <hr>
 <label>dept_id:</label>
    <select name="department_id">
    <c:forEach items="${deptlist}" var="dept">
    	 <option value="${dept.department_id}">${dept.department_name}</option>
    </c:forEach>
    </select>

<label for="job_id">job_id:</label>
<select name="job_id" >
	<c:forEach items="${joblist}" var="job">
		<option "${job.jobId}" ${job.jobId == emp.job_id ? "selected" : ""}> ${job.jobId}
</option>
	</c:forEach>
</select>

    </select>

 
	<hr>
    <input type="submit" value="입력">
</form>
<script>
	$(function() {
		var isSave= true;
		$("#myfrm").on("submit", function () {
			if(!isSave) {
				$("input[name='employee_id']").focus();
				event.preventDefault();
				return;
			}
		})
		
		$("input[name='employee_id']").on("change", function () {
				var input_empid = $(this).val();
				
				$.ajax({
					url:"getEmpById.do?empid=" + input_empid ,
					success : function (responseData) {
						//responseData : 0이면 없음 (가능), -1 : 있음 (불가능)
						
						if(responseData==0)  {
							$("#here").html("가능한 아이디입니다.")
							$("#here").css("color", "blue");
							isSave = true;
						} else {
							$("#here").html("불가능 아이디입니다.")
							$("#here").css("color", "red");
							$(this).val("");
							$(this).focus();
							isSave = false;
						}
					}
				});
		});
	});
</script>
</body>
</html>