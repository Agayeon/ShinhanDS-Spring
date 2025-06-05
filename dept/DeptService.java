package com.shinhan.myapp.aop.dept;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.java.Log;

@Service //Component 이지만 서비스 역할을 함
@Log
public class DeptService {
	// DeptDAO를 의존하고 있음 -> 스프링 사용 시 지워야 함
	//DeptDAO deptDAO = new DeptDAO();
	
	@Autowired
	DeptDAO deptDAO;
	
	public int deptDeleteById(int deptid) {
		int result = deptDAO.deptDeleteById(deptid);
		log.info("DeptService에서 로그 출력: "+ result+"건 delete");
		return result;
	}
	
	public int deptUpdate(DeptDTO dept) {
		int result = deptDAO.deptUpdate(dept);
		log.info("DeptService에서 로그 출력: "+ result+"건 update");
		return result;
	}
	
	public int deptInsert(DeptDTO dept) {
		int result = deptDAO.deptInsert(dept);
		log.info("DeptService에서 로그 출력: "+ result+"건 insert");
		return result;
	}
	
	public DeptDTO selectById(int deptid) {
		DeptDTO dept = deptDAO.selectById(deptid);
		log.info("DeptService에서 로그 출력: "+ dept.toString());
		return dept;
	}
	
	public List<DeptDTO> selectALL(){
		List<DeptDTO> deptlist = deptDAO.selectALL();
		log.info("DeptService에서 로그 출력: "+ deptlist.size()+"건 select");
		return deptlist;
	}
}
