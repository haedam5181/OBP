package com.office.calender.board;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.office.calender.board.page.Criteria;

@Component
public class BoardDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public int write_confirm(BoardVo boardVo) {
		System.out.println("[BoardDao] write_confirm() CALLED!!");
		
		String sql = "INSERT INTO tbl_board(m_id, "
										 + "b_title, "
										 + "b_body, "
										 + "b_reg_date, "
										 + "b_mod_date) "
										 + "VALUES(?, ?, ?, NOW(), NOW())";
		
		int result = -1;
		
		try {
			
			result = jdbcTemplate.update(sql, 
											boardVo.getM_id(), 
											boardVo.getB_title(), 
											boardVo.getB_body());
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return result;
	}

	public int getMaxNo() {
		System.out.println("[BoardDao] getMaxNo() CALLED!!");
		
		String sql = "SELECT MAX(b_no) FROM tbl_board";
		
		int maxNo = 0;
		
		try {
			
			maxNo = jdbcTemplate.queryForObject(sql, Integer.class);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return maxNo;
		
	}

	public int updateGroup(int maxNo) {
		System.out.println("[BoardDao] updateGroup() CALLED!!");
		
		String sql = "UPDATE tbl_board SET b_group = ? WHERE b_no = ?";
		
		int result = -1;
		
		try {
			
			result = jdbcTemplate.update(sql, maxNo, maxNo);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return result;
		
	}

	public List<BoardVo> list(String m_id, Criteria criteria) {
		System.out.println("[BoardDao] list() CALLED!!");
		
		String sql =  "SELECT * FROM tbl_board "
					+ "WHERE m_id = ? "
					+ "ORDER BY b_group DESC, b_step ASC "
					+ "LIMIT ?, ?";
		
		List<BoardVo> boardVos = new ArrayList<BoardVo>();
		
		try {
			
			boardVos = jdbcTemplate.query(sql, new RowMapper<BoardVo>() {

				@Override
				public BoardVo mapRow(ResultSet rs, int rowNum) throws SQLException {
					
					return new BoardVo(rs.getInt("b_no"), 
									   rs.getString("m_id"), 
									   rs.getString("b_title"), 
									   rs.getString("b_body"), 
									   rs.getInt("b_hit"), 
									   rs.getInt("b_group"), 
									   rs.getInt("b_step"), 
									   rs.getInt("b_indent"), 
									   rs.getString("b_reg_date"), 
									   rs.getString("b_mod_date"));
					
				}
				
			}, m_id, criteria.getSkip(), criteria.getAmount());
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return boardVos;
		
	}

	public BoardVo body(int b_no) {
		System.out.println("[BoardDao] body() CALLED!!");
		
		String sql =  "SELECT * FROM tbl_board "
					+ "WHERE b_no = ?";
	
		List<BoardVo> boardVos = new ArrayList<BoardVo>();
		
		try {
			
			boardVos = jdbcTemplate.query(sql, new RowMapper<BoardVo>() {
	
				@Override
				public BoardVo mapRow(ResultSet rs, int rowNum) throws SQLException {
					
					return new BoardVo(rs.getInt("b_no"), 
									   rs.getString("m_id"), 
									   rs.getString("b_title"), 
									   rs.getString("b_body"), 
									   rs.getInt("b_hit"), 
									   rs.getInt("b_group"), 
									   rs.getInt("b_step"), 
									   rs.getInt("b_indent"), 
									   rs.getString("b_reg_date"), 
									   rs.getString("b_mod_date"));
					
				}
				
			}, b_no);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return boardVos.size() > 0 ? boardVos.get(0) : null;
		
	}

	public int delete(int b_no) {
		System.out.println("[BoardDao] delete() CALLED!!");
		
		String sql = "DELETE FROM tbl_board WHERE b_no = ?";
		
		int result = -1;
		
		try {
			
			result = jdbcTemplate.update(sql, b_no);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return result;
		
	}

	public BoardVo modify_form(int b_no) {
		System.out.println("[BoardDao] modify_form() CALLED!!");
		
		return body(b_no);
		
	}

	public int modify_confirm(BoardVo boardVo) {
		System.out.println("[BoardDao] modify_confirm() CALLED!!");
		
		String sql = "UPDATE tbl_board SET b_title = ?, b_body = ? WHERE b_no = ?";
		
		int result = -1;
		
		try {
			
			result = jdbcTemplate.update(sql, 
											boardVo.getB_title(), 
											boardVo.getB_body(), 
											boardVo.getB_no());
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return result;
		
	}

	public BoardVo reply_form(int b_no) {
		System.out.println("[BoardDao] reply_form() CALLED!!");
		
		return body(b_no);
		
	}

	public int reply_shape(BoardVo boardVo) {
		System.out.println("[BoardDao] reply_shape() CALLED!!");
		
		String sql =  "UPDATE tbl_board "
					+ "SET b_step = b_step + 1 "
					+ "WHERE b_group = ? AND b_step > ?";
		
		int result = -1;
		
		try {
			
			result = jdbcTemplate.update(sql, 
										 boardVo.getB_group(), 
										 boardVo.getB_step());
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return result;
		
	}

	public int reply_confirm(BoardVo boardVo) {
		System.out.println("[BoardDao] reply_confirm() CALLED!!");
		
		String sql = "INSERT INTO tbl_board(m_id, "
										 + "b_title, "
										 + "b_body, "
										 + "b_group, "
										 + "b_step, "
										 + "b_indent, "
										 + "b_reg_date, "
										 + "b_mod_date) VALUES(?, ?, ?, ?, ?, ?, NOW(), NOW())";
		
		int result = -1;
		
		try {
			
			result = jdbcTemplate.update(sql, 
										 boardVo.getM_id(), 
										 boardVo.getB_title(), 
										 boardVo.getB_body(), 
										 boardVo.getB_group(), 
										 boardVo.getB_step() + 1,
										 boardVo.getB_indent() + 1);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return result;
		
	}

	public int getTotalCnt(String m_id) {
		System.out.println("[BoardDao] getTotalCnt() CALLED!!");
		
		String sql = "SELECT COUNT(*) FROM tbl_board WHERE m_id = ?";
		
		int totalCnt = -1;
		
		try {
			
			totalCnt = jdbcTemplate.queryForObject(sql, Integer.class, m_id);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return totalCnt;
		
	}

}
