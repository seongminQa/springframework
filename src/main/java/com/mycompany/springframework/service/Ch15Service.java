package com.mycompany.springframework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.springframework.dao.mybatis.Ch13AccountDao;
import com.mycompany.springframework.dto.Ch15Account;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Ch15Service {
	// Dao를 이용해야 하니 주입받자.
	@Autowired
	private Ch13AccountDao accountDao;
	
	// 계좌 조회
	public List<Ch15Account> getAccountList() {
		List<Ch15Account> accountList = accountDao.selectAll();
		return accountList;
	}
	
	// db에서 데이터를 가져와서 그 데이터를 다시 수정하는 작업 (서비스 비즈니스 로직)
	
	@Transactional // 이 어노테이션만 붙여주면 - 출금 작업, 입금작업 중 하나라도  실패한다면 모두 실패처리를 해준다.
	public void transfer(int fromAno, int toAno, int amount) {
		// 출금 작업
		Ch15Account fromAccount = accountDao.selectByAno(fromAno); // 출금 계좌에 대한 정보 얻기
		if(fromAccount == null) {
			throw new RuntimeException("출금 계좌가 없음");
		}
		 fromAccount.setBalance(fromAccount.getBalance() - amount);
		 accountDao.updateBalance(fromAccount);
		 // 출금 금액이 충분한지에 대한 것은 수업 때 안한다. 우리가 추가해보자.
		
		// 입금 작업
		Ch15Account toAccount = accountDao.selectByAno(toAno); // 출금 계좌에 대한 정보 얻기
		if(toAccount == null) {
			throw new RuntimeException("입금 계좌가 없음");
		}
		toAccount.setBalance(toAccount.getBalance() + amount);
		 accountDao.updateBalance(toAccount);
	}
}
