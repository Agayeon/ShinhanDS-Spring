package com.shinhan.spring.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.shinhan.spring.model.DeptService;
import com.shinhan.spring.model.EmpDTO;
import com.shinhan.spring.model.EmpRequestDTO;
import com.shinhan.spring.model.EmpService;
import com.shinhan.spring.model.JobService;

@Controller
@RequestMapping("/emp")
public class EmpController {
	@GetMapping("/getEmpById.do")
	public @ResponseBody String f_empidChk(@RequestParam int empid) {
		EmpDTO emp = empService.selectById(empid);
		return emp==null?"0":"1";
	}
	
    @Autowired
    EmpService empService;
    
    @Autowired
    DeptService deptService;
    
    @Autowired
     JobService jobService;
    
    

    @GetMapping("/emplist.do")
    public String selectAll(Model model, HttpServletRequest request) {
    	
    	// redirect시 받은 정보를 얻기
    			Map<String, ?>flashMap = RequestContextUtils.getInputFlashMap(request);
    			if(flashMap!=null) {
    				model.addAttribute("resultMessage", flashMap.get("resultMessage"));
    			}
        model.addAttribute("emplist", empService.selectALL());
        model.addAttribute("deptlist", deptService.selectALL());
        model.addAttribute("joblist", jobService.getAllJobs()); // joblist도 jsp에 필요하니 추가
        return "emp/empAll";
    }

    
//    @PostMapping("/selectByCondition2.do") 
//    public String f_condition1(HttpServletRequest request) throws IOException {
//        BufferedReader reader = request.getReader();
//        StringBuilder jsonBuilder = new StringBuilder();
//        String line;
//        while ((line = reader.readLine()) != null) {
//            jsonBuilder.append(line);
//        }
//        String json = jsonBuilder.toString();
//        System.out.println(json);
//        // 1. JSON을 Object로 바꾼다(parse)
//        // 2. 해당 data 추출
//        // 3. 조건 조회
//        // 4. 결과를 jsp 페이지로 forward 하여 HTML로 만든 후 ajax 호출로 간다.
//        return "emp/empByCondition";
//    }
    
    @PostMapping("/selectByCondition.do") 
    public String f_condition1(@RequestBody Map<String, Object> data) throws IOException {
        System.out.println(data);

        for (String key : data.keySet()) {
          
            System.out.println(key + ":" + data.get(key));
        }

        return "emp/empByCondition";
    }


	
    @PostMapping("/selectByCondition2.do")
    public String f_condition(@RequestBody EmpRequestDTO data, Model model) throws IOException {
        System.out.println(data);
        Integer[] deptArr = data.getDeptid();
        model.addAttribute("emplist", empService.selectByCondition(deptArr, data.getJobid(), 
        		data.getSalary(), data.getHire_date(), data.getDate_check()));
        return "emp/empByCondition";
    }
    
    @GetMapping("/empinsert.do")
    public void get_insert(Model model) {
		model.addAttribute("joblist", jobService.getAllJobs());
        model.addAttribute("deptlist", deptService.selectALL());
    }
    
    @PostMapping("/empinsert.do")
    public String post_insert(@ModelAttribute EmpDTO emp, RedirectAttributes attr) {
        System.out.println(emp);

        int result = empService.empInsert(emp);
        attr.addFlashAttribute("resultMessage", result > 0 ? "등록 성공" : "등록 실패");
        return "redirect:emplist.do";
    }


    // 직원 상세보기
    @GetMapping("/empdetail.do")
    public void detail(@RequestParam("empid2") int empid, Model model) {
        model.addAttribute("emp", empService.selectById(empid));
        model.addAttribute("deptlist", deptService.selectALL());    // 부서 목록
        model.addAttribute("joblist", jobService.getAllJobs());     // 직무 목록
    }
    
    @PostMapping("/empupdate.do")
    public String empUpdate(@ModelAttribute EmpDTO emp, RedirectAttributes attr) {
    	int result = empService.empUpdate(emp);
    	attr.addFlashAttribute("resultMessage", result > 0 ? "수정 성공" : "수정 실패");
        return "redirect:emplist.do";
    }
    
    
    @GetMapping("/empdelete.do")
    public String delete(int empid,  RedirectAttributes attr) {
    	int result = empService.empDeleteById(empid);
    	attr.addFlashAttribute("resultMessage", result > 0 ? "삭제 성공" : "삭제 실패");
        return "redirect:emplist.do";
    }
    
//    @GetMapping("/getEmpById.do")
//	public @ResponseBody int getEmpById(int empid) {
//		int result = 0;
//		if(empService.selectById(empid) == null) {
//			return result;
//		}else {
//			result = 1;
//			return result;
//		}
//	}
    

    // 직원 등록 폼
//    @GetMapping("/empinsert.do")
//    public void insertForm() {
//    }

    // 직원 등록 처리
//    @PostMapping("/empinsert.do")
//    public String insertPost(@ModelAttribute("emp") EmpDTO emp, RedirectAttributes redirectAttr) {
//        int result = empService.empInsert(emp);
//        redirectAttr.addFlashAttribute("resultMessage", result > 0 ? "등록 성공" : "등록 실패");
//        return "redirect:emplist.do";
//    }
//
//    // 직원 수정
//    @PostMapping(value = "/empupdate.do", produces = "text/plain;charset=utf-8")
//    public @ResponseBody String update(EmpDTO emp, RedirectAttributes redirectAttr) {
//        int result = empService.empUpdate(emp);
//        redirectAttr.addFlashAttribute("resultMessage", result > 0 ? "수정 성공" : "수정 실패");
//        return "update result: " + (result > 0 ? "success" : "fail");
//    }

//    // 직원 삭제 (GET)
//    @GetMapping(value = "/empdelete.do", produces = "text/plain;charset=utf-8")
//    public String delete(int empid, RedirectAttributes redirectAttr) {
//        int result = empService.empDeleteById(empid);
//        redirectAttr.addFlashAttribute("resultMessage", result > 0 ? "삭제 성공" : "삭제 실패");
//        return "redirect:emplist.do";
//    }

    // 직원 여러명 삭제 (POST, AJAX)
//    @PostMapping(value = "/empdelete.do", produces = "text/plain;charset=utf-8")
//    public @ResponseBody String deleteMultiple(@RequestParam("del") int[] empidArr) {
//        int result = 0;
//        for (int empid : empidArr) {
//            empService.empDeleteById(empid);
//        }
//        return "delete result: success";
//    }
}
