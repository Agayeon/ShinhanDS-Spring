package com.shinhan.spring.model.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BoardDTO {
	 int bno;
	 String title ;
	 String content;
	 String writer;
	 String pic1;
	 String pic2;
	 
}
