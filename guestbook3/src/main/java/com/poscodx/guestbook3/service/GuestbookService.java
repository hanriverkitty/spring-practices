package com.poscodx.guestbook3.service;

import java.sql.Connection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.poscodx.guestbook3.repository.GuestbookLogRepository;
import com.poscodx.guestbook3.repository.GuestbookRepositoryWithJdbcContext;
import com.poscodx.guestbook3.vo.GuestbookVo;

@Service
public class GuestbookService {
	
	@Autowired
	private GuestbookRepositoryWithJdbcContext guestbookRepository;
	
	@Autowired
	private GuestbookLogRepository guestbookLogRepository;
	
	public List<GuestbookVo> getContentsList(){
		return guestbookRepository.findAll();
	}
	public int deleteContents(Long no, String password) {
		guestbookLogRepository.update(no);
		return guestbookRepository.deleteByNo(no, password);
	}
	public void addContents(GuestbookVo vo) {
		TransactionSynchronizationManager.initSynchronization();
		Connection conn = DataSourceUtils.getConnection(dataSource);
		int count = guestbookLogRepository.update();
		if(count == 0) {
			guestbookLogRepository.insert();
		}
		guestbookRepository.insert(vo);
	}
}
