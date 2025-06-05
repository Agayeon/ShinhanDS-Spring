package com.shinhan.spring.model;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class DeptDAOMybatis implements DeptDAOInterface{
	@Autowired
	SqlSession sqlSession;
	
	String namespace="com.firstzone.dept.";
	
	@Override
	public List<DeptDTO> selectALL() {
		List<DeptDTO> deptlist = sqlSession.selectList(namespace + "selectAll");
		log.info(deptlist.size() + "건 조회");
		return deptlist;
	}
	
	@Override
	public DeptDTO selectById(int deptid) {
		DeptDTO dept = sqlSession.selectOne(namespace + "selectById", deptid);
		log.info(dept.toString());
		return dept;
	}
	
	@Override
	public int deptInsert(DeptDTO dept) {
		int result = sqlSession.insert(namespace + "insert", dept);
		log.info(result + "입력(Mybatis)");
		return result;
	}
	
	@Override
	public int deptUpdate(DeptDTO dept) {
		int result = sqlSession.update(namespace + "update", dept);
		log.info(result + "수정(Mybatis)");
		return result;
	}

	@Override
	public int deptDeleteById(int deptid) {
		int result = sqlSession.update(namespace + "delete", deptid);
		log.info(result + "삭제(Mybatis)");
		return result;
	}
}