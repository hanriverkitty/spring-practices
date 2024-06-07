package com.poscodx.guestbook3.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.poscodx.guestbook3.vo.GuestbookVo;

// @Repository
public class GuestbookRepositoryWithJdbcTemplate {
	private JdbcTemplate jdbcTemplate;
	
	public GuestbookRepositoryWithJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<GuestbookVo> findAll() {
		return jdbcTemplate.query(
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

	public int insert(GuestbookVo vo) {
		return jdbcTemplate.update(
				"insert into guestbook values(null,?,?,?,now())", 
				new Object[] {vo.getName(),vo.getPassword(),vo.getContents()});
	}
	
	public int deleteByNo(Long no, String password) {
		return jdbcTemplate.update(
				"delete from guestbook where no=? and password=?",
				new Object[] {no,password});
	}
}
