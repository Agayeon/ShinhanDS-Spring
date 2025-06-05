package com.shinhan.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shinhan.spring.model.DeptService;

@RequestMapping("/section02") // 폴더 안으로 넣으면 꼭 사용해야함
@Controller
public class SampleController {
	
	// 방법1
	@Autowired
	DeptService deptService; // Spring이 new해서 injection한다.
	
	// 방법2
//	SampleController(DeptService deptService) {
//		this.deptService = deptService;
//	}
	
	//@ResponseBody : 응답 문서에 data를 보낸다.
	// @ResponseBody가 없다면 jsp 페이지로 forward가 default이다.
	//요청의 형식이 맞지 않음 -> 400 에러
	@RequestMapping(value="/sample7.do", params = {"userid=a", "userpass", "!email"})
	public @ResponseBody String call() {
		return "parameter practice";
	}
	
	//배열도 가능
	@GetMapping({"/sample5.do", "/sample6.do"})
	public ModelAndView f5(Model model) { //모델은 jsp 페이지와 소통 Map<String, Object>
		ModelAndView mv = new ModelAndView();
		mv.addObject("subject","스프링프레임워크");
		mv.addObject("day",30);
		mv.addObject("dept", deptService.selectById(60));
		mv.addObject("deptlist", deptService.selectALL());
		mv.setViewName("section02/sample1");
		return mv;
		
	}
	
	@RequestMapping("/sample4.do")
	public ModelAndView f4(Model model) { //모델은 jsp 페이지와 소통 Map<String, Object>
		ModelAndView mv = new ModelAndView();
		mv.addObject("subject","스프링프레임워크");
		mv.addObject("day",30);
		mv.addObject("dept", deptService.selectById(60));
		mv.addObject("deptlist", deptService.selectALL());
		mv.setViewName("section02/sample1");
		return mv;
		
	}
	
	@RequestMapping(value="/sample3.do", method = RequestMethod.GET)
	public ModelAndView f3(Model model) { //모델은 jsp 페이지와 소통 Map<String, Object>
		ModelAndView mv = new ModelAndView();
		mv.addObject("subject","스프링프레임워크");
		mv.addObject("day",30);
		mv.addObject("dept", deptService.selectById(60));
		mv.addObject("deptlist", deptService.selectALL());
		mv.setViewName("section02/sample1");
		return mv;
		
	}
	
	@RequestMapping(value="/sample2.do", method = RequestMethod.GET)
	public String f2(Model model) { //모델은 jsp 페이지와 소통 Map<String, Object>
		model.addAttribute("subject","스프링프레임워크");
		model.addAttribute("day",30);
		model.addAttribute("dept", deptService.selectById(60));
		model.addAttribute("deptlist", deptService.selectALL());
		return "sample1"; //접두사(/WEB-INF/views/) + view 이름 + 접미사(.jsp)
	}
	
	@RequestMapping(value="/sample1.do", method = RequestMethod.GET)
		public void f1(Model model) { //모델은 jsp 페이지와 소통 Map<String, Object>
			model.addAttribute("subject","스프링프레임워크");
			model.addAttribute("day",30);
			model.addAttribute("deptlist", deptService.selectById(60));
			model.addAttribute("dept", deptService.selectALL());
		}
}
