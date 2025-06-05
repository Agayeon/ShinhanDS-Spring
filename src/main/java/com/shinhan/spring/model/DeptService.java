package com.shinhan.spring.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import lombok.extern.java.Log;

@Service // Component ������ ���� ������ ��
@Log
public class DeptService {
	// DeptDAO�� �����ϰ� ���� -> ������ ��� �� ������ ��
	// DeptDAO deptDAO = new DeptDAO();

	@Autowired // Ÿ���� ������ injection, ���� Ÿ���� ������ ������ �̸����� injection
	@Qualifier("deptDAOMybatis") // ���� �̸��� Bean�� injection�Ѵ�
	DeptDAOInterface deptDAO;

	public int deptDeleteById(int deptid) {
		int result = deptDAO.deptDeleteById(deptid);
		log.info("DeptService���� �α� ���: " + result + "�� delete");
		return result;
	}

	public int deptUpdate(DeptDTO dept) {
		int result = deptDAO.deptUpdate(dept);
		log.info("DeptService���� �α� ���: " + result + "�� update");
		return result;
	}

	public int deptInsert(DeptDTO dept) {
		int result = deptDAO.deptInsert(dept);
		log.info("DeptService���� �α� ���: " + result + "�� insert");
		return result;
	}

	public DeptDTO selectById(int deptid) {
		DeptDTO dept = deptDAO.selectById(deptid);
		log.info("DeptService���� �α� ���: " + dept.toString());
		return dept;
	}

	public List<DeptDTO> selectALL() {
		List<DeptDTO> deptlist = deptDAO.selectALL();
		log.info("DeptService���� �α� ���: " + deptlist.size() + "�� select");
		return deptlist;
	}
}
