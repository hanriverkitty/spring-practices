package com.poscodx.guestbook3.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.poscodx.guestbook3.repository.template.JdbcContext;
import com.poscodx.guestbook3.repository.template.StatementStrategy;
import com.poscodx.guestbook3.vo.GuestbookVo;

@Repository
public class GuestbookRepositoryWithJdbcContext {
	private JdbcContext jdbcContext;
	
	public GuestbookRepositoryWithJdbcContext(JdbcContext jdbcContext) {
		this.jdbcContext = jdbcContext;
	}
	
	public List<GuestbookVo> findAll() {
		return jdbcContext.query(
				"select no, name, password,contents, date_format(reg_date, '%Y-%m-%d %T') from guestbook order by reg_date desc", 
				new RowMapper<GuestbookVo>() {
					@Override
					public GuestbookVo mapRow(ResultSet rs, int rowNum) throws SQLException{
						GuestbookVo vo = new GuestbookVo();
						vo.setNo(rs.getLong(1));
						vo.setName(rs.getString(2));
						vo.setContents(rs.getString(4));
						vo.setDate(rs.getString(5));
						return vo;
					}
		});
	}
	
	public int deleteByNo(Long no, String password) {
		return jdbcContext.update(
				"delete from guestbook where no=? and password=?",
				new Object[] {no,password});
//		return jdbcContext.executeUpdate(new StatementStrategy() {
//			@Override
//			public PreparedStatement makeStatement(Connection connection) throws SQLException {
//				PreparedStatement pstmt = connection.prepareStatement("delete from guestbook where no=? and password=?");
//				pstmt.setLong(1, no);
//				pstmt.setString(2, password);
//				return pstmt;
//			}
//		});
	}
	
	public int insert(GuestbookVo vo) {
		return jdbcContext.update(
				"insert into guestbook values(null,?,?,?,now())", 
				new Object[] {vo.getName(),vo.getPassword(),vo.getContents()});
//		return jdbcContext.executeUpdate(new StatementStrategy() {
//			@Override
//			public PreparedStatement makeStatement(Connection connection) throws SQLException {
//				PreparedStatement pstmt = connection.prepareStatement("insert into guestbook values(null,?,?,?,now())");
//				pstmt.setString(1, vo.getName());
//				pstmt.setString(2, vo.getPassword());
//				pstmt.setString(3, vo.getContents());
//				return pstmt;
//			}
//		});
	
	}
}
