package com.shinhan.spring.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository("empMybatis")
public class EmpDAOMybatis implements EmpDAOInterface{

	@Autowired
	SqlSession sqlSession;
	String namespace = "com.firstzone.emp.";
	
	@Override
	public int empUpdate(EmpDTO emp) {
		int result = sqlSession.update(namespace + "update", emp);
		log.info(result + "건 update됨(Mybatis)");
		return result;
	}

	@Override
	public int empUpdate2(EmpDTO emp) {
		int result = sqlSession.update(namespace + "update", emp);
		log.info(result + "건 update됨(Mybatis)");
		return result;
	}

	@Override
	public int empInsert(EmpDTO emp) {
		int result = sqlSession.insert(namespace + "insert", emp);
		log.info(result + "건 입력됨(Mybatis)");
		return result;
	}

	@Override
	public int empDeleteById(int empid) {
		int result = sqlSession.delete(namespace + "delete", empid);
		log.info(result + "건 삭제됨(Mybatis)");
		return result;
	}
	
	// 인터페이스가 구현되어 있으므로 반드시 구현한다
	@Override
	public List<EmpDTO> selectByCondition(Integer[] arr, String jobid, int salary, String hdate) {
		return null;
	}

	public List<EmpDTO> selectByCondition(Integer[] arr, String jobid, int salary, String hdate, String date_check) {
		EmpRequestDTO dto = EmpRequestDTO.builder()
			.deptid(arr)
			.jobid(jobid)
			.salary(salary)
			.hire_date(hdate)
			.date_check(date_check)
			.build();
		List<EmpDTO> emplist = sqlSession.selectList(namespace + "selectByCondition", dto);
		log.info(emplist.size() + "조회됨(Mybatis)..info");
		log.info(emplist.size() + "조회됨(Mybatis)..debug");
		log.info(emplist.size() + "조회됨(Mybatis)..warn");
		return emplist;
	}

	@Override
	public List<EmpDTO> selectByJobAndDept(String job, int dept) {
		Map<String, Object> mapData = new HashMap<>();
		mapData.put("jobid", job);
		mapData.put("deptid", dept);
		List<EmpDTO> emplist = sqlSession.selectOne(namespace + "selectByJobAndDept", job);
		log.info(emplist.size() + "조회됨(Mybatis)");
		return emplist;
	}

	@Override
	public List<EmpDTO> selectByJob(String job) {
		List<EmpDTO> emp = sqlSession.selectOne(namespace + "selectlist", job);
		log.info(emp.size() + "조회됨(Mybatis)");
		return emp;
	}

	@Override
	public List<EmpDTO> selectByDept(int deptid) {
		List<EmpDTO> emplist = sqlSession.selectOne(namespace + "selectByDept", deptid);
		log.info(emplist.size() + "조회됨(Mybatis)");
		return emplist;
	}
	
	

	@Override
	public EmpDTO selectById(int empid) {
		EmpDTO emp = sqlSession.selectOne(namespace + "selectById", empid);
		if(emp!=null) {
			log.info(emp!=null?emp.toString():"0건" + "조회됨(Mybatis)");
		}
		return emp;
	}

	@Override
	public List<EmpDTO> selectALL() {
		List<EmpDTO> emplist = sqlSession.selectList(namespace + "selectALL");
		log.info(emplist.size() + "건 조회(Mybatis)");
		return emplist;
	}
	
}
