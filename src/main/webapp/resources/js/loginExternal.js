
window.onload = function() {
	var remember = localStorage.getItem("remember");
	if (remember) {
		document.querySelector("#email").value = localStorage.getItem("email");
		document.querySelector("#pwd").value = localStorage.getItem("pwd");
		document.querySelector("input[name='remember']").checked = true;
	}

	// document.
	document.querySelector("#myfrm").onsubmit = function() {

		var remember = document.querySelector("input[name='remember']").checked;

		// localStorage : 브라우저에 저장, 브라우저가 닫혀도 사라지지 않고 남아있음
		/*		console.log(email);
				console.log(pwd);
				console.log(remember);*/

		if (remember) {
			var email = document.querySelector("#email").value;
			var pwd = document.querySelector("#pwd").value;
			localStorage.setItem("email", email);
			localStorage.setItem("pwd", pwd);
			localStorage.setItem("remember", remember);
		} else {
			localStorage.removeItem("email");
			localStorage.removeItem("pwd");
			localStorage.removeItem("remember");
		}

		// 서버 전송 취소 시에 사용
		// remember 체크 여부
		//return false;
	};
};
