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
// ��� �ּ� �տ� /dept ����
@RequestMapping("/dept")

public class DeptController {
	
	// �μ� ���� ��������
	@Autowired
	DeptService deptService;
	
	// list�� �󼼺���
	@GetMapping("/deptlist.do")
	public void selectAll(Model model, HttpServletRequest request, HttpSession session) {
		String ip = request.getRemoteAddr();
		String uri = request.getRequestURI();
		System.out.println("�� ��û�� ���� Ŭ���̾�Ʈ�� IP �ּ�: " + ip);
		System.out.println("��û �ּ�" + uri);
		
	    EmpDTO emp = EmpDTO.builder()
	            .first_name("������")
	            .build();
		session.setAttribute("loginEmp", emp);
		// redirect�� ���� ������ ���
		Map<String, ?>flashMap = RequestContextUtils.getInputFlashMap(request);
		if(flashMap!=null) {
			model.addAttribute("", flashMap.get("resultMessage"));
		}
		model.addAttribute("deptListData", deptService.selectALL());
	}
	
	// �Է�
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
	
	// @ModelAttribute("dept") : param���� ���� data�� �ٽ�
	@PostMapping("/deptinsert.do")
	public String insertPost(@ModelAttribute("dept") DeptDTO dept) {
		int result = deptService.deptInsert(dept);
		return "dept/result"; //forward
	}
	
	// detail�� �󼼺���
	// RequestParam("deptid") ���� ���ɤ�
	@GetMapping("/deptdetail.do")
	public String detail(int deptid, Model model) {
		
		model.addAttribute("dept", deptService.selectById(deptid));
		return "dept/deptdetail";
	}
	
	// @Modelatturbute : view �� �ѱ��
	@PostMapping(value= "/deptdetail.do", produces="text/plain;charset=utf-8")
	public @ResponseBody String update(DeptDTO dept, RedirectAttributes redirctAttr) {
		System.out.println("jsp�� form���� ���� data:" + dept);
		int result = deptService.deptUpdate(dept);
		redirctAttr.addFlashAttribute("resultMessage", result>0?"��������(Flash)":"��������(flash");
		return "update result" + (result>0?"success":"fail");
	}
	
	@GetMapping(value = "/deptdelete.do", produces = "text/plain;charset=utf-8")
	public String delete(int deptid, RedirectAttributes redirectAttr) {
	    int result = deptService.deptDeleteById(deptid);
	    redirectAttr.addFlashAttribute("resultMessage", result>0?"��������(Flash)":"��������(flash");
	    return "redirect:deptlist.do"; // ����û
	}
	
	// ���� �̸��� ������ ���� ������(�迭)�޾Ƽ� ó��
	@PostMapping(value= "/deptdelete.do", produces="text/plain;charset=utf-8")
	public @ResponseBody String detail(@RequestParam("del") int[] deptidArr) {
		int result = 0;
		for(int deptid:deptidArr) {
			deptService.deptDeleteById(deptid);
		}
		return "delete result" + (result>0?"success":"fail");
	}
	
	
	
	
}
