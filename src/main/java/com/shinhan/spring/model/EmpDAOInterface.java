package com.shinhan.spring.model;

import java.util.List;


public interface EmpDAOInterface {
	
	public int empUpdate(EmpDTO emp);
	
	// 수정
	public int empUpdate2(EmpDTO emp);
	
	// 삽입
	public int empInsert(EmpDTO emp);
	
	// 삭제
	public int empDeleteById(int empid);
	
	public List<EmpDTO> selectByCondition(Integer[] arr, String job, int salary, String hdate);

	// job과 부서로 직원 조회
	public List<EmpDTO> selectByJobAndDept(String job, int dept);
	
	// job으로 직원 조회
	public List<EmpDTO> selectByJob(String job);
	
	// 부서의 직원 조회
	public List<EmpDTO> selectByDept(int deptid);

	// 직원 번호로 직원의 정보를 상세보기 (1건 조회)
	public EmpDTO selectById(int empid);
	
	// 모든 직원 조회
	public List<EmpDTO> selectALL();
}

