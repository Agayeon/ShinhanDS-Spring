package com.shinhan.spring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter @Setter
@Data
public class DeptDTO {
	private Integer department_id;  
	private String department_name;
	private Integer manager_id; // �⺻���� null�� ���� �� ����.
	private Integer location_id;    
}
