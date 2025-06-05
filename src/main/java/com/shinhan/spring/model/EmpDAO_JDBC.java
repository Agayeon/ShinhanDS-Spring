package com.shinhan.spring.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shinhan.myapp.util.DBUtill;
import com.shinhan.myapp.util.DateUtill;


// db �۾� �ڵ�
// DAO (Data Access Object): DB�� CRUD �۾� (select, insert, update, delete)
// Statement�� SQL���� ������ ���... ���ε����� �������� ����
// PreparedStatement: Statement�� ��� ����, ���ε� ���� ����, sp ȣ�� ���� ����
// CallableStatement: sp ȣ�� ����


@Repository("empDAO")
public class EmpDAO_JDBC {
	
	//@Qualifier("dataSourceOriginal")
	@Autowired
	DataSource ds;
	
	Connection conn;
	
	// Stored Procedure �����ϱ� (���� ��ȣ�� �޾Ƽ� �̸��ϰ� �޿��� return)
	public EmpDTO execute_sp(int empid) {
		EmpDTO emp = null;
		//Connection conn = DBUtill.getConnection();
		CallableStatement st = null;
		String sql = "{call sp_empinfo2(?,?,?)}";
	
		try {
			conn = ds.getConnection();
			st = conn.prepareCall(sql);
			// �Ķ���� 3�� ����
			st.setInt(1,empid);
			// out �Ķ���� ����
			st.registerOutParameter(2, Types.VARCHAR);
			st.registerOutParameter(3, Types.DECIMAL);
			
			boolean result = st.execute();
			//true if the first result is a ResultSetobject; 
			//false if the first result is an updatecount or there is no result
			
			// �о����
			emp = new EmpDTO();
			String email = st.getString(2);
			double salary = st.getDouble(3);
			emp.setEmail(email);
			emp.setSalary(salary);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return emp;
	}
	
	public int empUpdate(EmpDTO emp) {
		int result = 0;
		// Connection conn = DBUtill.getConnection();
		PreparedStatement st = null;
		
		Map<String, Object> dynamicSQL = new HashMap<>();
		
		/* Ű ��
		 * FIRST_NAME aa
		 * LAST_NAME bb
		 */
		
		if(emp.getFirst_name()!=null) dynamicSQL.put("FIRST_NAME", emp.getFirst_name());
		if(emp.getLast_name()!=null) dynamicSQL.put("LAST_NAME", emp.getLast_name());
		if(emp.getSalary()!=null) dynamicSQL.put("SALARY", emp.getSalary());
		if(emp.getHire_date()!=null) dynamicSQL.put("HIRE_DATE", emp.getHire_date());
		if(emp.getEmail()!=null) dynamicSQL.put("EMAIL", emp.getEmail());
		if(emp.getPhone_number()!=null) dynamicSQL.put("PHONE_NUMBER", emp.getPhone_number());
		if(emp.getJob_id()!=null) dynamicSQL.put("JOB_ID", emp.getJob_id());
		if(emp.getCommission_pct()!=null) dynamicSQL.put("Commission_pct", emp.getCommission_pct());
		if(emp.getManager_id()!=null) dynamicSQL.put("manager_id", emp.getManager_id());
		if(emp.getDepartment_id()!=null) dynamicSQL.put("department_id", emp.getDepartment_id());
		
		// sql�� ó��
		String sql = " update employees set ";
	 	String sql2 = " where EMPLOYEE_ID = ? ";		
		
	 	for(String key:dynamicSQL.keySet()) {
	 		sql += key +"="+"?,";
	 	}
	 	sql = sql.substring(0, sql.length()-1); // �������� �޸� ������ �ȵ�
	 	sql += sql2;
	 	System.out.println(sql);
	 	
	 	// ? ó��
		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sql); 
			int i=1;
			for(String key:dynamicSQL.keySet()) {
		 		st.setObject(i++, dynamicSQL.get(key));
		 	}
			st.setInt(i, emp.getEmployee_id()); // ���� ���̵���� 
			result = st.executeUpdate(); // ������Ʈ
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	// ����
	public int empUpdate2(EmpDTO emp) {
		int result = 0;
		PreparedStatement st = null;
		String sql = "UPDATE employees SET " +
	             "FIRST_NAME = ?, " +
	             "LAST_NAME = ?, " +
	             "EMAIL = ?, " +
	             "PHONE_NUMBER = ?, " +
	             "HIRE_DATE = ?, " +
	             "JOB_ID = ?, " +
	             "SALARY = ?, " +
	             "COMMISSION_PCT = ?, " +
	             "MANAGER_ID = ?, " +
	             "DEPARTMENT_ID = ? " +
	             "WHERE EMPLOYEE_ID = ?";

		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sql);
			 st.setInt(11, emp.getEmployee_id());
		        st.setString(1, emp.getFirst_name());
		        st.setString(2, emp.getLast_name());
		        st.setString(3, emp.getEmail());
		        st.setString(4, emp.getPhone_number());
		        st.setDate(5, emp.getHire_date());  // ��¥ ��ȯ �ʿ�
		        st.setString(6, emp.getJob_id());
		        st.setDouble(7, emp.getSalary());
		        st.setDouble(8, emp.getCommission_pct());
		        st.setInt(9, emp.getManager_id());
		        st.setInt(10, emp.getDepartment_id());
		        result = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // SQL���� �غ��Ѵ�.
		
		return result;
	}
	
	// ����
	public int empInsert(EmpDTO emp) {
		int result = 0;
		PreparedStatement st = null;
		String sql = 
			    "INSERT INTO employees ("
			    + "EMPLOYEE_ID, "
			    + "FIRST_NAME, "
			    + "LAST_NAME, "
			    + "EMAIL, "
			    + "PHONE_NUMBER, "
			    + "HIRE_DATE, "
			    + "JOB_ID, "
			    + "SALARY, "
			    + "COMMISSION_PCT, "
			    + "MANAGER_ID, "
			    + "DEPARTMENT_ID"
			    + ") VALUES ("
			    + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?"
			    + ")";



		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sql);
			 st.setInt(1, emp.getEmployee_id());
		        st.setString(2, emp.getFirst_name());
		        st.setString(3, emp.getLast_name());
		        st.setString(4, emp.getEmail());
		        st.setString(5, emp.getPhone_number());
		        st.setDate(6, emp.getHire_date());  // ��¥ ��ȯ �ʿ�
		        st.setString(7, emp.getJob_id());
		        st.setDouble(8, emp.getSalary());
		        st.setDouble(9, emp.getCommission_pct());
		        st.setInt(10, emp.getManager_id());
		        st.setInt(11, emp.getDepartment_id());
			result = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // SQL���� �غ��Ѵ�.
		
		return result;
	}
	
	// ����
	public int empDeleteById(int empid) {
		int result = 0;
		// Connection conn = DBUtill.getConnection();
		PreparedStatement st = null;
		String sql = "delete from employees where employee_id = ?";
		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sql);
			st.setInt(1, empid);
			result = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // SQL���� �غ��Ѵ�.
		
		return result;
	}
	
	public List<EmpDTO> selectByCondition2(Integer[] arr, String job, int salary, String hdate) {
		List<EmpDTO> emplist = new ArrayList<>(); // ���� ���� ���� ���� ����
		// Connection conn = DBUtill.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		String deptStr = Arrays.stream(arr)
				.map(id->"?").collect(Collectors.joining(",")); // "?,?,?,..."
		
		String sql = "select * from employees"
				+ " where job_id like ?"
				+ " and salary >= ?"
				+ " and hire_date >= ?"
				+ " and department_id in ("+ deptStr + ")"; // ?,?,?,...
		
		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sql); // SQL���� �غ��Ѵ�.
			st.setString(1, "%"+job+"%"); // 1��° ?�� ���� setting�Ѵ�.
			st.setInt(2, salary); // 2��° ?�� ���� setting�Ѵ�.
			
			Date d = DateUtill.convertTOSQLDate(DateUtill.convertToDate(hdate));
			
			st.setDate(3, d); // 3��° ?�� ���� setting�Ѵ�.
			
			int col = 4;
			for(int i=0; i<arr.length;i++) {
				st.setInt(col++, arr[i]); // 4��° ?�� ���� setting�Ѵ�.
			}
			
			rs = st.executeQuery(); // �̹� �غ������Ƿ� execute�� ���� sql���� ����
			while(rs.next()) {
				EmpDTO emp = makeEmp(rs); // ���� ���� ���鼭 ��ü�� ����
				emplist.add(emp); // ���� ���� arrayList�� ����
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtill.dbDisconnect(conn, st, rs);
		}
		
		return emplist;
	}
	
	public List<EmpDTO> selectByCondition(Integer[] arr, String jobid, int salary, String hdate) {
		List<EmpDTO> emplist = new ArrayList<>();
		// Connection conn = DBUtill.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String deptStr = Arrays.stream(arr).map(id -> "?").collect(Collectors.joining(",")); // "?,?,?"
		String sql = "select * from employees" + " where job_id like ? "
		       + " and salary >= ?"
			   + " and hire_date >= ?"
				+ " and department_id  in (" + deptStr + ")"; // ?,?,?
		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sql); // SQL���� �غ��Ѵ�.
			st.setString(1, "%" + jobid + "%"); // 1��° ?�� ���� setting�Ѵ�.
			st.setInt(2, salary);
			Date d = DateUtill.convertTOSQLDate(DateUtill.convertToDate(hdate));
			st.setDate(3, d);
			int col = 4;
			for (int i = 0; i < arr.length; i++) {
				st.setInt(col++, arr[i]);
			}
			rs = st.executeQuery();
			while (rs.next()) {
				EmpDTO emp = makeEmp(rs);
				emplist.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtill.dbDisconnect(conn, st, rs);
		}
		return emplist;
	}
	
	// job�� �μ��� ���� ��ȸ
	public List<EmpDTO> selectByJobAndDept(String job, int dept) {
		List<EmpDTO> emplist = new ArrayList<>(); // ���� ���� ���� ���� ����
		// Connection conn = DBUtill.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		String sql = "select * from employees where job_id = ? and department_id = ?";
		
		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sql); // SQL���� �غ��Ѵ�.
			st.setString(1, job); // 1��° ?�� ���� setting�Ѵ�.
			st.setInt(2, dept); // 2��° ?�� ���� setting�Ѵ�.
			rs = st.executeQuery(); // �̹� �غ������Ƿ� execute�� ���� sql���� ����
			while(rs.next()) {
				EmpDTO emp = makeEmp(rs); // ���� ���� ���鼭 ��ü�� ����
				emplist.add(emp); // ���� ���� arrayList�� ����
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtill.dbDisconnect(conn, st, rs);
		}
		
		return emplist;
	}
	
	// job���� ���� ��ȸ
	public List<EmpDTO> selectByJob(String job) {
		List<EmpDTO> emplist = new ArrayList<>(); // ���� ���� ���� ���� ����
		// Connection conn = DBUtill.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		String sql = "select * from employees where job_id = ?";
		
		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sql); // SQL���� �غ��Ѵ�.
			st.setString(1, job); // 1��° ?�� ���� setting�Ѵ�.
			rs = st.executeQuery(); // �̹� �غ������Ƿ� execute�� ���� sql���� ����
			while(rs.next()) {
				EmpDTO emp = makeEmp(rs); // ���� ���� ���鼭 ��ü�� ����
				emplist.add(emp); // ���� ���� arrayList�� ����
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtill.dbDisconnect(conn, st, rs);
		}
		
		return emplist;
	}
	
	// �μ��� ���� ��ȸ
	public List<EmpDTO> selectByDept(int deptid) {
		List<EmpDTO> emplist = new ArrayList<>(); // ���� ���� ���� ���� ����
		// Connection conn = DBUtill.getConnection();
		Statement st = null;
		ResultSet rs = null;
		
		String sql = "select * from employees where department_id = " + deptid;
		
		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				EmpDTO emp = makeEmp(rs); // ���� ���� ���鼭 ��ü�� ����
				emplist.add(emp); // ���� ���� arrayList�� ����
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtill.dbDisconnect(conn, st, rs);
		}
		
		return emplist;
	}

	// ���� ��ȣ�� ������ ������ �󼼺��� (1�� ��ȸ)
	public EmpDTO selectById(int empid) {
		// Connection conn = DBUtill.getConnection();
		Statement st = null;
		ResultSet rs = null;
		EmpDTO emp = null;
		
		String sql = "select * from employees where employee_id = " + empid;
		
		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				emp = makeEmp(rs); // ���� ���� ���鼭 ��ü�� ����
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtill.dbDisconnect(conn, st, rs);
		}
		
		return emp;
	}
	
	// ��� ���� ��ȸ
	public List<EmpDTO> selectALL() {
		List<EmpDTO> emplist = new ArrayList<>(); // ���� ���� ���� ���� ����
		// Connection conn = DBUtill.getConnection();
		Statement st = null;
		ResultSet rs = null;
		
		String sql = "select * from employees order by 1 asc";
		
		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				EmpDTO emp = makeEmp(rs); // ���� ���� ���鼭 ��ü�� ����
				emplist.add(emp); // ���� ���� arrayList�� ����
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtill.dbDisconnect(conn, st, rs);
		}
		
		return emplist;
	}

	private EmpDTO makeEmp(ResultSet rs) throws SQLException {
		EmpDTO emp = EmpDTO.builder()
				.commission_pct(rs.getDouble("commission_pct"))
				.department_id(rs.getInt("department_id"))
				.email(rs.getString("email"))
				.employee_id(rs.getInt("employee_id"))
				.first_name(rs.getString("first_name"))
				.hire_date(rs.getDate("hire_date"))
				.job_id(rs.getString("job_id"))
				.last_name(rs.getString("last_name"))
				.manager_id(rs.getInt("manager_id"))
				.phone_number(rs.getString("phone_number"))
				.salary(rs.getDouble("salary"))
				.build();
		
		return emp;
	}
}
