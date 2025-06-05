
// 이벤트 속성에 이벤트가 발생했을 때 수행할 로직을 넣는다.
// 이벤트핸들러(리스너) - 함수
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