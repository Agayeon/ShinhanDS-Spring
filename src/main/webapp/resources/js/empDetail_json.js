
function f_delete(){
	console.log("f_delete---구현");
	var empid = $("#empid").val();
	$.ajax({
		url:`${cpath}/emp/api/empdelete.do/${empid}`,
		type:"delete",
		success: function(responseData){
			console.log(responseData);
		}
	});
}


function f_update() {
	console.log("f_insert 구현");
	var obj = {};
	var arr = $("#myfrm").serializeArray();
	$.each(arr,function(index,item) {
		obj[item.name] = item.value;
	});
			
 	$.ajax({
		url:`${cpath}/emp/api/empupdate.do`,
		type: "put",
		data: JSON.stringify(obj),    
		contentType: "application/json;charset=utf-8",
		success : function(responseData) {
			console.log(responseData);
		}
	});
}

function f_make_jobSelect(joblist, emp_job) {
 	var output = "<select name='job_id'>";
 	$.each(joblist, function(index,job) {
 		var select_job = emp_job == job.jobid?"selected":"";
 		output += `<option ${select_job} >${job.jobid}</option>`;
 	});
 	return output + "</select>";

}  

function f_make_deptSelect(deptlist, emp_deptid) {
 	var output = "<select name='department_id'>";
 	$.each(deptlist, function(index,dept) {
 		var select_dept = dept.department_id == emp_deptid?"selected":"";
 		output += `<option value= "${dept.department_id}">${dept.department_name}</option>`;
 	});
 	return output + "</select>";

}   

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
			var cpath = "/spring";
			console.log(location.href);
			console.log(location.host);
			
			$.ajax({
				url:`${cpath}/emp/api/empdetail.do/\${empid}`,
				success: function(mapData){					
					//JSON객체를 받아서 화면 display다시하기 
				 	console.log(mapData);
				 	var emp = mapData.emp;
				 	var job_output = f_make_jobSelect(mapData.joblist);
				 	var job_output = f_make_deptSelect(mapData.deptlist);
				 					 	
				 var hdate = new Date(Number(`\${emp.hire_date}`));
				 	hdate = f_converDate(hdate);
				  var commission = `\${emp.commission_pct}`;
   			      if(commission == "null") {	  commission = "";  }
					
					var output = `
						<h1> \${emp.first_name}  직원상세보기</h1>
						 <form id="myfrm" action="${cpath}/emp/empdetail.do" method="post">
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
						 
			  
						  <label>부서선택:</label>
					    
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