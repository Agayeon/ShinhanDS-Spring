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
	private Integer manager_id; // 기본형은 null을 넣을 수 없다.
	private Integer location_id;    
}
