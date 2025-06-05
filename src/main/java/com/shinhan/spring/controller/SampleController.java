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

@RequestMapping("/section02") // ���� ������ ������ �� ����ؾ���
@Controller
public class SampleController {
	
	// ���1
	@Autowired
	DeptService deptService; // Spring�� new�ؼ� injection�Ѵ�.
	
	// ���2
//	SampleController(DeptService deptService) {
//		this.deptService = deptService;
//	}
	
	//@ResponseBody : ���� ������ data�� ������.
	// @ResponseBody�� ���ٸ� jsp �������� forward�� default�̴�.
	//��û�� ������ ���� ���� -> 400 ����
	@RequestMapping(value="/sample7.do", params = {"userid=a", "userpass", "!email"})
	public @ResponseBody String call() {
		return "parameter practice";
	}
	
	//�迭�� ����
	@GetMapping({"/sample5.do", "/sample6.do"})
	public ModelAndView f5(Model model) { //���� jsp �������� ���� Map<String, Object>
		ModelAndView mv = new ModelAndView();
		mv.addObject("subject","�����������ӿ�ũ");
		mv.addObject("day",30);
		mv.addObject("dept", deptService.selectById(60));
		mv.addObject("deptlist", deptService.selectALL());
		mv.setViewName("section02/sample1");
		return mv;
		
	}
	
	@RequestMapping("/sample4.do")
	public ModelAndView f4(Model model) { //���� jsp �������� ���� Map<String, Object>
		ModelAndView mv = new ModelAndView();
		mv.addObject("subject","�����������ӿ�ũ");
		mv.addObject("day",30);
		mv.addObject("dept", deptService.selectById(60));
		mv.addObject("deptlist", deptService.selectALL());
		mv.setViewName("section02/sample1");
		return mv;
		
	}
	
	@RequestMapping(value="/sample3.do", method = RequestMethod.GET)
	public ModelAndView f3(Model model) { //���� jsp �������� ���� Map<String, Object>
		ModelAndView mv = new ModelAndView();
		mv.addObject("subject","�����������ӿ�ũ");
		mv.addObject("day",30);
		mv.addObject("dept", deptService.selectById(60));
		mv.addObject("deptlist", deptService.selectALL());
		mv.setViewName("section02/sample1");
		return mv;
		
	}
	
	@RequestMapping(value="/sample2.do", method = RequestMethod.GET)
	public String f2(Model model) { //���� jsp �������� ���� Map<String, Object>
		model.addAttribute("subject","�����������ӿ�ũ");
		model.addAttribute("day",30);
		model.addAttribute("dept", deptService.selectById(60));
		model.addAttribute("deptlist", deptService.selectALL());
		return "sample1"; //���λ�(/WEB-INF/views/) + view �̸� + ���̻�(.jsp)
	}
	
	@RequestMapping(value="/sample1.do", method = RequestMethod.GET)
		public void f1(Model model) { //���� jsp �������� ���� Map<String, Object>
			model.addAttribute("subject","�����������ӿ�ũ");
			model.addAttribute("day",30);
			model.addAttribute("deptlist", deptService.selectById(60));
			model.addAttribute("dept", deptService.selectALL());
		}
}
