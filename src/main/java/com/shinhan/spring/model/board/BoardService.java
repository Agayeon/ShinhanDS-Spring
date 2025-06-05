package com.shinhan.spring.model.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BoardService {
	
	@Autowired
	BoardDAO boardDAO ;

	public List<BoardDTO> selectAll() {
		return boardDAO.selectAll();
	}

	public BoardDTO selectById(int bno) {
		return boardDAO.selectById(bno);
	}

	public int insertDept(BoardDTO board) {
		return boardDAO.insertDept(board);
	}
}
