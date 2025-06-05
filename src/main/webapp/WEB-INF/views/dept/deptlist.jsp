<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>부서 목록조회</title>

<script>
var message = "${resultMessage}";
if(message!="") {
	alert(message);
}
</script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<%@ include file="../common/header.jsp" %>
<h1>부서 목록조회</h1>

<form action="${cpath}/dept/deptinsert.do" method="get">
    <button type="submit" class="btn btn-success mb-3">신규부서 등록</button>
</form>


<button type="button" class="btn btn-primary" data-bs-toggle="modal" 
data-bs-target="#myModal" id="showInsertBtn">부서등록(모달)</button>


<form action="${cpath}/dept/deptdelete.do" method="post">
<button type="submit" class="btn btn-danger mb-3">선택삭제</button>
 <table class="table table-bordered" style="display: inline-table;">
			<thead>
				<tr>
					<th>부서코드</th>
					<th>부서이름</th>
					<th>매니저번호</th>
					<th>지역코드</th>
					<th>삭제</th>	
					<th>선택</th>
				</tr>
			</thead>
			<tbody>



	      <c:forEach items="${deptListData}" var="dept">
	         <tr>
	           <td><a href="deptdetail.do?deptid=${dept.department_id}">${dept.department_id}</a>
	           <button type="button" class="btn btn-secondary" data-bs-toggle="modal" 
	           data-deptid="${dept.department_id}" 
	           data-deptname="${dept.department_name}" 
	           data-mid="${dept.manager_id}" 
	           data-loc="${dept.location_id}" 
	           data-bs-target="#myModal" id="showDetailBtn">상세보기(모달)</button>
	           </td>
	           <td>${dept.department_name}</td>
	           <td>${dept.manager_id}</td>
	           <td>${dept.location_id}</td>
	           <td>
	              <a href="deptdelete.do?deptid=${dept.department_id}">
	                     <img src="${cpath}/resources/img/delete.png"/>
	              </a>
	           </td>
	           <td>
	              <input type="checkbox" name="del" value="${dept.department_id}" >삭제
	           </td>
	         </tr>
	      </c:forEach>
	   </tbody>
	 </table>
 </form>
 
<!-- The Modal -->
<div class="modal" id="myModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">부서등록</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
      
        <form id="myfrm"> 
				 <label>부서코드 :</label> 
				 <input  type="number" name="department_id" required="required"   autofocus="autofocus" > <br>
				 <label>부서이름 :</label> 
				 <input name="department_name" required="required"> <br>
				 <label>메니저번호 :</label> 
				 <input type="number" name="manager_id" placeholder="존재하는 직원" > <br>
				 <label>지역코드 :</label> 
				 <input type="number" name="location_id" placeholder="존재하는 지역코드" > <br>
				 <br>
			</form>
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
      	<input type="hidden" id="job" value="">
        <button type="button" class="btn btn-success" id="saveBtn">저장</button>
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
      </div>

    </div>
  </div>
</div>
<script>
	$(function(){
		
		$("#showInsertBtn").on("click", function(){
			$(".modal-title").text($(this).text());
			$("#job").val("insert");
		});
		
		$("#showDetailBtn").on("click", function(){
			$(".modal-title").text($(this).text());
			$("#job").val("update");
		});
		
		// 모달이 보여지는 사건이 발생하면 받은 data를 body의 input에 넣기
		$("#myModal").on("show.bs.modal", function(event){
			var deptid = $(event.relatedTarget).data("deptid");
			var deptname = $(event.relatedTarget).data("deptname");
			var mid = $(event.relatedTarget).data("mid");
			var loc = $(event.relatedTarget).data("loc");
			
			$("[name='department_id']").val(deptid);
			$("[name='department_name']").val(deptname);
			$("[name='manager_id']").val(mid);
			$("[name='location_id']").val(loc);
		});
		
		$("#saveBtn").on("click", f_display);

	});
	
	  function f_display(){
			 
		  var dataObj = {"job":$("#job").val(),
				   "department_id" : $("[name='department_id']").val(),
				   "department_name" : $("[name='department_name']").val(),
				   "manager_id" : $("[name='manager_id']").val(),
				   "location_id" : $("[name='location_id']").val()
		       };
		  $.ajax({
			  url:"deptSaveAjax.do",
			  type:"post",
			  data: dataObj,
			  success: f_saveSuccess
		  });
	  };
	
	
	function f_saveSuccess(responseData){
		// 입력 건수 받기
		if(responseData > 0){
			alert("DB저장성공");
		}else{
			alert("DB저장실패");
		}
		
		$('#myModal').hide();
		location.reload(); // location 창에 있는 것 그대로 다시 로드(url 불필요)
	}
	
	
	
</script>
</div>
</div>
</body>
</html>