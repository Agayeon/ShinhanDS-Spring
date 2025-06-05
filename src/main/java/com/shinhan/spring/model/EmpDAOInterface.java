package com.shinhan.spring.model;

import java.util.List;


public interface EmpDAOInterface {
	
	public int empUpdate(EmpDTO emp);
	
	// ����
	public int empUpdate2(EmpDTO emp);
	
	// ����
	public int empInsert(EmpDTO emp);
	
	// ����
	public int empDeleteById(int empid);
	
	public List<EmpDTO> selectByCondition(Integer[] arr, String job, int salary, String hdate);

	// job�� �μ��� ���� ��ȸ
	public List<EmpDTO> selectByJobAndDept(String job, int dept);
	
	// job���� ���� ��ȸ
	public List<EmpDTO> selectByJob(String job);
	
	// �μ��� ���� ��ȸ
	public List<EmpDTO> selectByDept(int deptid);

	// ���� ��ȣ�� ������ ������ �󼼺��� (1�� ��ȸ)
	public EmpDTO selectById(int empid);
	
	// ��� ���� ��ȸ
	public List<EmpDTO> selectALL();
}

