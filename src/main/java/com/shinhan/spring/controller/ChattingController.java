package com.shinhan.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChattingController {
	@GetMapping("/chat")
	public String chat() {
		return "chat/chatting";
	}
}
