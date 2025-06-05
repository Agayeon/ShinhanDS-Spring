package com.shinhan.spring.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class EmpService {

    @Autowired
    @Qualifier("empMybatis")
    EmpDAOMybatis empDAO;
	
	
	public int empUpdate(EmpDTO emp) {
		return empDAO.empUpdate(emp);
	}
	
	public int empUpdate2(EmpDTO emp) {
		return empDAO.empUpdate2(emp);
	}
	
	public int empInsert(EmpDTO emp) {
		return empDAO.empInsert(emp);
	}
	
	public int empDeleteById(int empid) {
		return empDAO.empDeleteById(empid);
	}
	
	public List<EmpDTO> selectByJobAndDept(String job, int dept) {
		return empDAO.selectByJobAndDept(job, dept);
	}
	
	public List<EmpDTO> selectByJob(String job) {
		return empDAO.selectByJob(job);
	}
	
	public List<EmpDTO> selectByDept(int deptid) {
		return empDAO.selectByDept(deptid);
	}
	
	public EmpDTO selectById(int empid) {
		return empDAO.selectById(empid);
	}
	
	public List<EmpDTO> selectALL() {
		return empDAO.selectALL();
	}

	public List<EmpDTO> selectByCondition(Integer[] deptid, String job, int salary, String hdate, String date_check) {
		return empDAO.selectByCondition(deptid, job, salary, hdate, date_check);
	}
}
