package com.shinhan.spring.model.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)  //tracsaction 전파규칙 설정   
public class AccountServiceImpl {
	
	@Autowired
	AccountDAOMybatis dao;

	public void aa() {
		dao.update1();
		dao.update2();
	}
}
