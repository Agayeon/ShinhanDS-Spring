package com.shinhan.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shinhan.spring.model.account.AccountServiceImpl;

@Controller
public class AccountController {

	@Autowired
	AccountServiceImpl accService;
	
	@GetMapping("/acc")
	public @ResponseBody String f1() {
		accService.aa();
		return "OK";
	}
}
