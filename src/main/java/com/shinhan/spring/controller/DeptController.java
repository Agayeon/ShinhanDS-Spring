package com.shinhan.spring.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.shinhan.spring.model.DeptDTO;
import com.shinhan.spring.model.DeptService;
import com.shinhan.spring.model.EmpDTO;


@Controller
// 모든 주소 앞에 /dept 쓰자
@RequestMapping("/dept")

public class DeptController {
	
	// 부서 서비스 가져오기
	@Autowired
	DeptService deptService;
	
	// list로 상세보기
	@GetMapping("/deptlist.do")
	public void selectAll(Model model, HttpServletRequest request, HttpSession session) {
		String ip = request.getRemoteAddr();
		String uri = request.getRequestURI();
		System.out.println("이 요청을 보낸 클라이언트의 IP 주소: " + ip);
		System.out.println("요청 주소" + uri);
		
	    EmpDTO emp = EmpDTO.builder()
	            .first_name("주인장")
	            .build();
		session.setAttribute("loginEmp", emp);
		// redirect시 받은 정보를 얻기
		Map<String, ?>flashMap = RequestContextUtils.getInputFlashMap(request);
		if(flashMap!=null) {
			model.addAttribute("", flashMap.get("resultMessage"));
		}
		model.addAttribute("deptListData", deptService.selectALL());
	}
	
	// 입력
	@GetMapping("/deptinsert.do")
	public void inserForm() {
	}
	
	@PostMapping("/deptSaveAjax.do")
	public @ResponseBody String deptSaveAjax(@RequestParam("job") String job, DeptDTO dept) {
		int result = 0;
		if(job.equals("insert")) {
			result= deptService.deptInsert(dept);
		} else {
			result= deptService.deptUpdate(dept);
		}
		return result + "";
	}
	
	// @ModelAttribute("dept") : param으로 받은 data를 다시
	@PostMapping("/deptinsert.do")
	public String insertPost(@ModelAttribute("dept") DeptDTO dept) {
		int result = deptService.deptInsert(dept);
		return "dept/result"; //forward
	}
	
	// detail로 상세보기
	// RequestParam("deptid") 생략 가능ㄴ
	@GetMapping("/deptdetail.do")
	public String detail(int deptid, Model model) {
		
		model.addAttribute("dept", deptService.selectById(deptid));
		return "dept/deptdetail";
	}
	
	// @Modelatturbute : view 에 넘기기
	@PostMapping(value= "/deptdetail.do", produces="text/plain;charset=utf-8")
	public @ResponseBody String update(DeptDTO dept, RedirectAttributes redirctAttr) {
		System.out.println("jsp의 form으로 들어온 data:" + dept);
		int result = deptService.deptUpdate(dept);
		redirctAttr.addFlashAttribute("resultMessage", result>0?"수정성공(Flash)":"수정실패(flash");
		return "update result" + (result>0?"success":"fail");
	}
	
	@GetMapping(value = "/deptdelete.do", produces = "text/plain;charset=utf-8")
	public String delete(int deptid, RedirectAttributes redirectAttr) {
	    int result = deptService.deptDeleteById(deptid);
	    redirectAttr.addFlashAttribute("resultMessage", result>0?"수정성공(Flash)":"수정실패(flash");
	    return "redirect:deptlist.do"; // 제요청
	}
	
	// 같은 이름의 변수의 값이 여러개(배열)받아서 처리
	@PostMapping(value= "/deptdelete.do", produces="text/plain;charset=utf-8")
	public @ResponseBody String detail(@RequestParam("del") int[] deptidArr) {
		int result = 0;
		for(int deptid:deptidArr) {
			deptService.deptDeleteById(deptid);
		}
		return "delete result" + (result>0?"success":"fail");
	}
	
	
	
	
}
