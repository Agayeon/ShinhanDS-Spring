package com.shinhan.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shinhan.spring.model.DeptDTO;
import com.shinhan.spring.model.DeptService;



@Controller
@RequestMapping("/first") // �������� �ּҴ� class level���� �ۼ�
public class HelloController {
	
	@Autowired
	DeptService deptService;
	
	@RequestMapping(value = "/hello.do", method = RequestMethod.GET)
	public String f1(Model model) {
		model.addAttribute("myname", "����");
		model.addAttribute("major", "�İ�");

		return "section01/hello";
	}

	@RequestMapping(value = "/hello2.do", method = RequestMethod.GET)
	public ModelAndView f2(Model model) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("myname", "����");
		mv.addObject("major", "computer");
		mv.setViewName("section01/hello");

		return mv;
	}	
	
	//@RequestMapping(value = "/hello3.do", method = RequestMethod.GET)
	@GetMapping("/hello3.do")
	public ModelAndView f3(Model model) {
		DeptDTO dept = deptService.selectById(60);
		System.out.println(dept);
		ModelAndView mv = new ModelAndView();
		mv.addObject("myname", "����");
		mv.addObject("major", "sw");
		mv.setViewName("section01/hello");

		return mv;
	}
}
