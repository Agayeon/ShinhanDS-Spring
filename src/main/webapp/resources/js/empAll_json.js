var obj = {
"employee_id": 14,
"first_name": null,
"last_name": "라스트네임",
"email": "이메일",
"phone_number": null,
"hire_date": 1748444400000,
"job_id": "play",
"salary": null,
"commission_pct": null,
"manager_id": null,
"department_id": 10
}


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

function f_update(){
	console.log("f_update---구현");
	var obj = {};
	var arr = $("#myfrm").serializeArray();
	console.log(arr);
	$.each(arr, function(index, item) {
	obj[item.name] = item.value;
	});
	
	$.ajax({
		url:`${cpath}/emp/api/empupdate.do`,
		type:"put",
		data: JSON.stringify(obj),
		contentType:"application/json;charset=utf-8",
		success: function(responseData){
			console.log(responseData);
		}
	});
}

function f_insert() {
	console.log("f_insert 구현");
			
	$.ajax({
		url:`${cpath}/emp/api/empinsert.do`,
		type: "post",
		data: JSON.stringify(obj),    
		contentType: "application/json;charset=utf-8",
		success : function(responseData) {
			console.log(responseData);
		}
	})
}

function f_selectById() {
	console.log("f_selectById 구현");
	var empid = $("#empid").val();
	$.ajax ({
		url:`${cpath}/emp/api/empdetail.do/${empid}`,
		success: function(responseData) {
			console.log(responseData);
		}
	});
}

function f_selectALL() {
	console.log("f_selectALL 구현");
	$.ajax({
		url:"${cpath}/emp/api/emplist.do",
		success: f_selectAll_print
	});
}



function f_selectAll_print( responseArr){
   var output = "";
   var cpath = "/spring";
   var v_last = responseArr.length - 1;
  
   $.each(responseArr, function(index, emp){       
       var message1 =  index==0?"처음":"";
       var message2 =  index==v_last?"끝":"";
      
       var hdate = new Date(Number(`${emp.hire_date}`));
       hdate = hdate.toISOString(); // .slice(0,10);  //2025-06-05T10:06:00
       hdate = hdate.split("T")[0];
       
       var odd_img = `<img src="${cpath}/resources/img/delete.png" >`;
       var even_img = `<img src="${cpath}/resources/img/delete3.png" width="30" height="30">`;
       var img_output = odd_img;
       if(index%2==0) img_output = even_img;
     
       output += `
     <tr>
       <td>${index+1}
       <span>👌❤️</span>             
       <span>${message1}</span>
       <span>${message2}</span>
       </td>
       
       <td>직원 </td>
       <td><a href="${cpath}/emp/empdetail.do?empid2=${emp.employee_id}">${emp.employee_id}</a></td>
       <td><a href="${cpath}/emp/empdetail.do?empid2=${emp.employee_id}">${emp.first_name}</a></td>
       <td>${emp.last_name}</td>
       <td>${emp.salary}</td>
       <td>${emp.email}</td>
       <td>${emp.department_id}</td>
       <td>${emp.commission_pct}</td>
       <td>${emp.manager_id}</td>
       <td>${emp.phone_number}</td>
       <td>${emp.job_id}</td>
       <td>${hdate} </td>     
       <td><a href="${cpath}/emp/empdelete.do?empid=${emp.employee_id}">
                 ${img_output}
           </a>
       </td>
     </tr>
   `;
     
   });
   $("#here").html(output);
}

