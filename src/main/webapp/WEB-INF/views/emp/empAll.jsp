<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="cpath" value="${pageContext.servletContext.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
 <script src="${cpath}/resources/js/empAll.js"></script>
 <script src="${cpath}/resources/js/empAll2.js"></script>
 <script src="${cpath}/resources/js/empAll_json.js"></script>
 <link rel="stylesheet" href="${cpath}/resources/css/empAll.css">
 
<!-- 빈 파비콘 (브라우저 요청 방지) -->
<link rel="icon" href="data:;base64,iVBORw0KGgo=">


<script>
var cpath = "${cpath}";
var name = "신한 DS 금융 소프트아카데미";
console.log(cpath);
console.log(`\${name} 학습 중`);
$(function() {
	var message = "${resultMessage}";
	if(message!="") {
		alert(message);
	}
	$("#dept_job").trigger("click");
	
});
</script>


<script>

window.onload = function() {
	// alert("load 시 수행된다.")	
	
	
	const btn = document.querySelector("#search");
	
	btn.onclick =  employee_search;
};

function employee_search() {
    const minSalary = parseInt(document.querySelector("input[type='number']").value);
    const rows = document.querySelectorAll("tbody tr");

    rows.forEach(row => {
      const salary = parseInt(row.children[4].innerText); 
      if (salary >= minSalary) {
    	  row.style.border = "2px solid red";
    	  row.style.color = "darkred";
    
      } else {
        row.style.border = "";
        row.style.color = "";
      }
    });
  }


		 

</script>


<script>
$(function() {
	$("#btnSelect").on("click", f_selectALL);
	$("#btnDetail").on("click", f_selectById);
	$("#btnInsert").on("click", f_insert);
	$("#btnUpdate").on("click", f_update);
	$("#btnDelete").on("click", f_delete);
});


</script>



<title>직원 목록</title>


</head>



<body>
<div id="container">
<%-- 정적자원, jsp를 합쳐서 컴파일한다.(.java) -> class -> jsp 실행 --%>
<%@ include file="../common/header.jsp" %>

	<h3>Restful API사용하기</h3>
	 	<button id="btnSelect" class="btn btn-secondary">전체조회</button>
	 	<input type="number" id="empid" value="100">
	 	<button id="btnDetail" class="btn btn-secondary">상세보기</button>
	 	<button id="btnInsert" class="btn btn-secondary">입력</button>
	 	<button id="btnUpdate" class="btn btn-secondary">수정</button>
	 	<button id="btnDelete" class="btn btn-secondary">삭제</button>
	 	<hr>
	<a class = "btn btn-primary" href='${cpath}/auth/logout.do'>로그아웃</a>
  <h1 class="title">직원 목록</h1>
  <a href="${cpath}/emp/empinsert.do"> 신규 직원 등록</a>
  <hr>
부서로 조회:
<select id="deptid" multiple="multiple">
	<option value="0" selected="selected">부서전체</option>
	<c:forEach items="${deptlist}" var="dept">
		<option value="${dept.department_id}"}>
			${dept.department_id}-${dept.department_name}
		</option>
	</c:forEach>
</select>


직책으로조회:
<select id="jobid">
	<option value="all" selected="selected">직책전체</option>
	<c:forEach items="${joblist}" var="job">
		<option>${job.jobId}</option>
	</c:forEach>
</select>

급여(이상) : <input type="number" id="salary" value="500">
입사일(이상) : <input type="date" id="hire_date" value="2000-01-01">
<input type="checkbox" id="date_check">
<button id="dept_job">조건조회</button>
<!-- (부서+직책)으로 조회하기:
<button id="dept_job">함께 조회</button> -->
<hr>
<hr>급여조회: <input type="number" id="salary" value="1000"> 이상
입사일: <input type="date" id="hire_date" value="2000-01-01"> 이후
급여: <input type = "number" id="salary"> 이상
	<button id="search" class="btn btn-outline-warning" style="font-weight: bold;" >직원 찾기(스타일변경)</button>
찾을 문자: <input type = "text" id="search2">
	<hr>
<input type="">부서로 조회</input>
<button>직원 찾기</button>
<input type="">찾을 문자</input>

<hr>
<table class="table table-striped">
  <caption>모든직원 List</caption>

  <thead>
      <tr>
      	<th>순서</th>
     	<th>로그인여부</th>
        <th>직원번호</th>
        <th>이름</th>
        <th>성</th>
        <th>이메일</th>
        <th>전화번호</th>
        <th>입사일</th>
    
        <th>직책</th>
        <th>급여</th>
        <th>커미션</th>
        <th>매니저</th>
       	<th>부서</th>
        <th>삭제</th>
   
      </tr>
    </thead>
	<tbody id="here">

    </tbody>
  </table>
</div>
</body>


</html>
