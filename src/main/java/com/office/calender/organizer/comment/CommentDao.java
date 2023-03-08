package com.office.calender.organizer.comment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class CommentDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public int regist_comment(Map<String, String> msgMap) {
		System.out.println("[CommentDao] regist_comment()");
		
		String sql = "INSERT INTO tbl_comment("
											+ "or_no, "
											+ "m_id, "
											+ "c_txt,"
											+ "c_reg_date, "
											+ "c_mod_date) VALUES(?, ?, ?, NOW(),NOW())";
		
		int result = -1;
		
		try {
			
			result = jdbcTemplate.update(sql, 
										 msgMap.get("or_ori_no"), 
										 msgMap.get("m_id"), 
										 msgMap.get("c_txt"));
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return result;
		
	}

	public List<CommentVo> getComments(Map<String, String> msgMap) {
		System.out.println("[CommentDao] getComments()");
		
		String sql =  "SELECT * FROM tbl_comment "
					+ "WHERE or_no = ? "
					+ "ORDER BY c_no DESC";
		
		List<CommentVo> commentVos = new ArrayList<CommentVo>();
		
		try {
			
			commentVos = jdbcTemplate.query(sql, new RowMapper<CommentVo>() {

				@Override
				public CommentVo mapRow(ResultSet rs, int rowNum) throws SQLException {
					
					CommentVo commentVo = new CommentVo();
					
					commentVo.setC_no(rs.getInt("c_no"));
					commentVo.setOr_no(rs.getInt("or_no"));
					commentVo.setM_id(rs.getString("m_id"));
					commentVo.setC_txt(rs.getString("c_txt"));
					commentVo.setC_reg_date(rs.getString("c_reg_date"));
					commentVo.setC_mod_date(rs.getString("c_mod_date"));
					
					return commentVo;
				}
				
			}, msgMap.get("or_ori_no"));
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return commentVos;
		
	}
	
}
