package com.shinhan.spring.model;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.shinhan.myapp.util.DBUtill;

import lombok.Setter;

@Repository
public class DeptDAO implements DeptDAOInterface {
	
	@Autowired
	DataSource ds;

	Connection conn;

	Statement st;
	PreparedStatement pst;

	ResultSet rs;

	int resultCount;
	
	static final String SELECT_ALL = "select * from departments order by 1";
	static final String SELECT_DETAIL = "select * from departments where department_id = ?";
	static final String INSERT = "insert into departments values(?,?,?,?)";
	static final String UPDATE = "update departments set "
								+ " department_name=?,manager_id=?,location_id=? "
								+ " where department_id=?";
	static final String DELETE = "delete from departments where department_id=?";
	

	public int deptDeleteById(int deptid) {
		int result = 0;
		// conn = DBUtill.getConnection();
		
		try {
			conn = ds.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		pst = null;
		
		try {
			pst = conn.prepareStatement(DELETE);
			pst.setInt(1, deptid);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	public int deptUpdate(DeptDTO dept) {
		int result = 0;
		conn = DBUtill.getConnection();
		pst = null;
		
		try {
			pst = conn.prepareStatement(UPDATE);
			pst.setInt(4, dept.getDepartment_id());
			pst.setString(1, dept.getDepartment_name());
			pst.setInt(2, dept.getManager_id());
			pst.setInt(3, dept.getLocation_id());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	public int deptInsert(DeptDTO dept) {
		int result = 0;
		// conn = DBUtill.getConnection();
		
		try {
			conn = ds.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		pst = null;
		
		try {
			pst = conn.prepareStatement(INSERT);
			pst.setInt(1, dept.getDepartment_id());
			pst.setString(2, dept.getDepartment_name());
			pst.setInt(3, dept.getManager_id());
			pst.setInt(4, dept.getLocation_id());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	public DeptDTO selectById(int deptid) {
		// conn = DBUtill.getConnection();
		
		try {
			conn = ds.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		pst = null;
		rs = null;
		DeptDTO dept = null;
		
		try {
			pst = conn.prepareStatement(SELECT_DETAIL);
			pst.setInt(1, deptid);
			rs = pst.executeQuery();
			if(rs.next()) {
				dept = makeDept(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtill.dbDisconnect(conn, st, rs);
		}
		return dept;
	}
	

	public List<DeptDTO> selectALL(){
		List<DeptDTO> deptlist = new ArrayList<>();
		// conn = DBUtill.getConnection();
	 
			
		 
		st = null;
		rs = null;
		
		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(SELECT_ALL);
			while(rs.next()) {
				DeptDTO dept = makeDept(rs);
				deptlist.add(dept);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtill.dbDisconnect(conn, st, rs);
		}
		return deptlist;
	}

	private DeptDTO makeDept(ResultSet rs) throws SQLException {
		DeptDTO dept = DeptDTO.builder()
				.department_id(rs.getInt("department_id"))
				.department_name(rs.getString("department_name"))
				.manager_id(rs.getInt("manager_id"))
				.location_id(rs.getInt("location_id"))
				.build();
		return dept;
	}
}
