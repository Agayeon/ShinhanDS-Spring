package com.shinhan.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shinhan.spring.model.EmpDTO;
import com.shinhan.spring.model.EmpService;

import lombok.Getter;
import lombok.Setter;

@Controller
@RequestMapping("/auth")
public class loginController {
	
	@Autowired
	EmpService empService;
	
	@GetMapping("/login.do")
	public void f_getLoigin() {
		
	}
	
	@GetMapping("/logout.do")
	public String f_logout(HttpSession session) {
		session.invalidate();
		return "redirect:login.do";
	}
	
	@PostMapping(value="login.do", produces = "text/plain;charset=utf-8" )
	public @ResponseBody String g_postLogin(int userid, String pswd, HttpSession session) {
		EmpDTO emp = empService.selectById(userid);
		String message="";
		session.setAttribute("loginEmp", emp);
		if(emp==null) {
			message = "존재하지 않는 사용자입니다.";
		} else if(emp.getEmail().equals(pswd)) {
			message = emp.getFirst_name() + " " + emp.getLast_name() + "님 환영합니다.";
		} else {
			message = "비번 오류입니다.";
		}
 		return message;
	}

}
