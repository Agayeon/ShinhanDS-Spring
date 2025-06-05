package net.firstzone.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// Controller는 POJO(plain Old Java Object)로 만든다.
// @Controller : FrontController인 DispatcherServlet이 Controller라고 인식한다.

@Controller
public class BoardController {
	
	@RequestMapping("/board/list")
	public String f1() {
		return "NewFile"; //forward
	}
	@RequestMapping("/board/list2")
	public @ResponseBody String f2() {
		return "NewFile"; //  response.getWriter().append("ffff");

	}
}

 