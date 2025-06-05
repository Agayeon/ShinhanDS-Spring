package com.shinhan.myapp.aop.dept;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	
	public static void main(String[] args) {
		f2();
	}
 
	private static void f2() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("aop9.xml");
		DeptService service = ctx.getBean("deptService", DeptService.class);
		List<DeptDTO> deptlist = service.selectALL();
		deptlist.stream().forEach(dept->System.out.println(dept));
	}
 
}