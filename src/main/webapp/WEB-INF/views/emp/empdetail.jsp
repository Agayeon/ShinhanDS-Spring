<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <%--정적자원, jsp를 합쳐서 컴파일한다.(.java)->class->jsp실행  --%>
 <%@ include file="../common/header.jsp" %>
 <c:set var="cpath" value="${pageContext.request.contextPath}" />
 
 
 <script>
   var cpath = "${pageContext.request.contextPath}";
</script>
 <h2>JSON이용 상세보기</h2>
 <input type="number" id="empid" value="100">
 <button class="btn btn-primary" id="btnDetail">상세보기(JSON)</button>
 <button class="btn btn-primary" id="btnUpdate">수정(JSON)</button>
 <button class="btn btn-primary" id="btnDelete">삭제(JSON)</button>
 
 <script src="${cpath}/resources/js/empDetail_json.js"></script>
  <link rel="stylesheet" href="${cpath}/resources/css/empdetail.css">
 <script>
     $( function(){
    	 $("#btnDetail").on("click",f_selectById );
    	 $("#btnUpdate").on("click", f_update);
    	 $("#btnDelete").on("click", f_delete);
     });
     
     function f_convertDate(date) {
    	  let [y, m, d] = date.toLocaleDateString("ko-KR")
    	    .replace(/\.$/, '')
    	    .split('. ')
    	    .map(s => s.padStart(2, '0'));
    	  return `${y}-${m}-${d}`;
    	}
     function f_selectById(){
			console.log("f_selectById---구현");
			var empid = $("#empid").val();

			
			$.ajax({
				url:`${cpath}/emp/api/empdetail.do/\${empid}`,
				success: function(emp){					
					//JSON객체를 받아서 화면 display다시하기 
					
				  var hdate = new Date(Number(`\${emp.hire_date}`));
				  hdate = f_converDate(hdate);
				  var commission = `\${emp.commission_pct}`;
   			      if(commission == "null") {	  commission = "";  }
   			      
   					
					var output = `
						<h1> ${emp.first_name}  직원상세보기</h1>
						 <form action="${cpath}/emp/empdetail.do" method="post">
						 <input type="hidden" value="update" name="jobselect">
						  <label>직원번호:</label>
						  <input readonly="readonly"  type="number" name="employee_id" value="\${emp.employee_id }"><br>
						 <label>이름:</label>
						  <input name="first_name" value="\${emp.first_name }"><br>
						 <label>성:</label>
						  <input name="last_name" value="\${emp.last_name }"><br>
						  <label>이메일:</label>
						  <input name="email" value="\${emp.email }"><br>
						  <label>전화번호:</label>
						  <input name="phone_number" value="\${emp.phone_number }"><br>
						  
						  <hr>
						  <label>직책선택:</label>
						  <input name="job_id" value= "\${emp.job_id}">			 
			  
						  <label>부서선택:</label>
					      <input type="number" name="department_id" value= "\${emp.department_id}">
						  <hr> 
						  <label>manager_id:</label>
						  <input type="number" name="manager_id" value="\${emp.manager_id }"><br>
						   
						  <label>Salary:</label>
						  <input type="number" name="salary" value="\${emp.salary }"><br>
						  <label>commission_pct:</label>
						  <input type="text" name="commission_pct" value="\${commission }"><br>
						  <label>입사일:</label>
						  <input type="date" name="hire_date" value="\${hdate}"><br>
						  <input type="submit" value="수정하기">
						  
						 </form>
					`;
					$("#container").html(output);
				}
			});
		}
 </script>
 
  <div id="container">
	 <h1> ${emp.first_name}  직원상세보기</h1>
	 <form action="${cpath}/emp/empdetail.do" method="post">
	 <input type="hidden" value="update" name="jobselect">
	  <label>직원번호:</label>
	  <input readonly="readonly"  type="number" name="employee_id" value="${emp.employee_id }"><br>
	 <label>이름:</label>
	  <input name="first_name" value="${emp.first_name }"><br>
	 <label>성:</label>
	  <input name="last_name" value="${emp.last_name }"><br>
	  <label>이메일:</label>
	  <input name="email" value="${emp.email }"><br>
	  <label>전화번호:</label>
	  <input name="phone_number" value="${emp.phone_number }"><br>
	  
	  <hr>
	  <label>직책선택:</label>
	  <select name="job_id">
	    <c:forEach items="${joblist}" var="job">
	       <option  ${job.jobId==emp.job_id?"selected":"" }>${job.jobId}</option>
	    </c:forEach>
	  </select>
	  
	  <label>부서선택:</label>
	  <select name="department_id">
	     <c:forEach items="${deptlist}" var="dept">
	        <option  value="${dept.department_id}" ${dept.department_id==emp.department_id?"selected":"" }  >
	            ${dept.department_name}
	        </option>
	     </c:forEach>
	  </select>
	 
	  <hr> 
	  <label>manager_id:</label>
	  <input type="number" name="manager_id" value="${emp.manager_id }"><br>
	   
	  <label>Salary:</label>
	  <input type="number" name="salary" value="${emp.salary }"><br>
	  <label>commission_pct:</label>
	  <input type="text" name="commission_pct" value="${emp.commission_pct }"><br>
	  <label>입사일:</label>
	  <input type="date" name="hire_date" value="${emp.hire_date }"><br>
	  <input type="submit" value="수정하기">
	  
	 </form>
</div>

</body>
</html>




