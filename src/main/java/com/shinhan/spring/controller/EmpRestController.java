package com.shinhan.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shinhan.spring.model.EmpDTO;
import com.shinhan.spring.model.EmpService;

import lombok.extern.slf4j.Slf4j;

//@Controller
@RestController // 스프링 5버전부터 지원 = @Controller + @ResponseBody
@Slf4j 
@RequestMapping("/emp/api")
public class EmpRestController {
	
	@Autowired
	EmpService empService;
	@DeleteMapping(value="/empdelete.do/{empid}", produces = "text/plain; charset=utf-8")
	public String f7(@PathVariable("empid") int empid2) {
		log.info("empid: " + empid2);
		int result = empService.empDeleteById(empid2);
		return result>0?"delete 성공": "delete 실패";
	}
	
	@PostMapping(value="/empinsert.do", 
			produces = "text/plain; charset=utf-8",
			consumes= MediaType.APPLICATION_JSON_VALUE)
	public String f5(@RequestBody EmpDTO emp) {
		log.info(emp.toString());
		int result = empService.empInsert(emp);
		return result>0?"insert OK":"insert실패";
	}
	
	
	@PutMapping(value="/empupdate.do/{empid}", 
			produces = "text/plain; charset=utf-8",
			consumes= MediaType.APPLICATION_JSON_VALUE)
	public String f6(@RequestBody EmpDTO emp) {
		log.info(emp.toString());
		int result = empService.empUpdate(emp);
		return result>0?"update성공":"update 실패";
	}
	
	
	
	
	
	@GetMapping(value="/emplist-map.do", produces="application/json")
	public Map<Integer, EmpDTO> f4() {
		
		Map<Integer, EmpDTO> mapData = new HashMap<>();
		empService.selectALL().forEach(emp->mapData.put(emp.getEmployee_id(), emp));
		
		return mapData;
	}
	
	
	@GetMapping(value="/emplist.do", produces="application/json")
	public List<EmpDTO> f3() {
		return empService.selectALL();
	}
	
	@GetMapping(value="/empdetail.do/{empid}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String,Object> f2(@PathVariable("empid") int empid2) {
		Map<String, Object> mapData = new HashMap<>();
		mapData.put("emp", empService.selectById(empid2));
		mapData.put("deptlist", empService.selectALL());
		//mapData.put("joblist", empService.getAllJobs());
		return mapData;
	}
	
	@GetMapping(value="/emp.do", produces = "text/plain; charset=utf-8")
	public String f1() {
		return "직원API연습";
	}
	
}
