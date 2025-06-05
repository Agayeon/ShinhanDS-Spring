package com.shinhan.spring.model;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface DeptDAOInterface {
	

	public int deptDeleteById(int deptid);

	public int deptUpdate(DeptDTO dept);

	public int deptInsert(DeptDTO dept);

	public DeptDTO selectById(int deptid);
	

	public List<DeptDTO> selectALL();

}
