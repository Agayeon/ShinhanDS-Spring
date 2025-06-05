package com.shinhan.spring.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// DTO (Data Transfer Object) (default ������, getter/setter �� �־�� �Ѵ�.)
// Į���� �̸��� Field�� ��ġ�ϴ� ���� ����
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter @Setter
@Data
public class EmpDTO {
	private Integer employee_id;
	private String first_name;
	private String last_name;
	private String email;
	private String phone_number;
	private Date hire_date;
	
	private String job_id;    
	private Double salary;
	private Double commission_pct;
	private Integer manager_id;
	private Integer department_id;
}
