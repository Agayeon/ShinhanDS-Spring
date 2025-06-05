$(function () {
  // 실시간 검색 기능
  $("#search2").on("keyup", function(){
    var inputData = $(this).val();
    console.log(inputData);
    $("tbody tr").hide();
    $(`td:contains(${inputData})`).parent().show();
  });

  // 조회 버튼 클릭 시 Ajax 실행
  $("#dept_job").on("click", function() {
    const select = document.getElementById("deptid");
    const selectedValues = Array.from(select.selectedOptions).map(op => op.value);

    var jobid = $("#jobid").val();
    var salary = $("#salary").val();
    var hire_date = $("#hire_date").val();
    var date_check = $("#date_check").prop("checked"); 
    
    console.log($(select).val());
    console.log(jobid);
    console.log(salary);
    console.log(hire_date);
    console.log(date_check);

    var jsonData = {
      "jobid": jobid,
      "deptid": selectedValues,
      "salary": salary,
      "hire_date": hire_date,
      "date_check" : date_check
    };

    $.ajax({
      url: "selectByCondition2.do",
      contentType: "application/json",
      type: "post",
      data: JSON.stringify(jsonData),
      success: function(responseData) {
        $("#here").html(responseData);
      }
    });
  });

  // 페이지 로딩 후 자동 조회 실행
  $("#dept_job").trigger("click");
});
