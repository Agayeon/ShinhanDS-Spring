package com.shinhan.spring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class EmpRequestDTO {
	Integer[] deptid;
	String jobid;
	int salary;
	String hire_date;
	String date_check;
}
