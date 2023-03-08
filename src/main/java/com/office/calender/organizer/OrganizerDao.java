package com.office.calender.organizer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.office.calender.member.MemberVo;
import com.office.calender.organizer.util.OrganizerDefine;

@Component
public class OrganizerDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public int writeOrganize(OrganizerVo organizerVo) {
		System.out.println("[OrganizerDao] writeOrganize()");
		
		String sql = "INSERT INTO tbl_organizer("
											 + "or_ori_owner_id, "
											 + "m_id, "
											 + "or_year, "
											 + "or_month, "
											 + "or_date, "
											 + "or_img_name, "
											 + "or_title, "
											 + "or_body, "
											 + "or_reg_date, "
											 + "or_mod_date) VALUES(?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";
		
		int result = -1;
		
		try {
			
			result = jdbcTemplate.update(sql, 
										 organizerVo.getM_id(),
										 organizerVo.getM_id(), 
										 organizerVo.getOr_year(), 
										 organizerVo.getOr_month(), 
										 organizerVo.getOr_date(), 
										 organizerVo.getOr_img_name(), 
										 organizerVo.getOr_title(), 
										 organizerVo.getOr_body());
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return result;
		
	}

	public List<OrganizerVo> getPlans(Map<String, String> msgMap) {
		System.out.println("[OrganizerDao] getPlans()");
		   
		String sql = "SELECT * FROM tbl_organizer "
					+ "WHERE m_id = ? "
					+ "AND or_year = ? "
					+ "AND or_month = ?";
		
		List<OrganizerVo> organizerVos = new ArrayList<OrganizerVo>();
		
		try {
			
			organizerVos = jdbcTemplate.query(sql, new RowMapper<OrganizerVo>() {

				@Override
				public OrganizerVo mapRow(ResultSet rs, int rowNum) throws SQLException {
					
					OrganizerVo organizerVo = new OrganizerVo();
					
					organizerVo.setOr_no(rs.getInt("or_no"));
					organizerVo.setOr_ori_no(rs.getInt("or_ori_no"));
					organizerVo.setM_id(rs.getString("m_id"));
					organizerVo.setOr_year(rs.getInt("or_year"));
					organizerVo.setOr_month(rs.getInt("or_month"));
					organizerVo.setOr_date(rs.getInt("or_date"));
					organizerVo.setOr_img_name(rs.getString("or_img_name"));
					organizerVo.setOr_title(rs.getString("or_title"));
					organizerVo.setOr_body(rs.getString("or_body"));
					organizerVo.setOr_reg_date(rs.getString("or_reg_date"));
					organizerVo.setOr_mod_date(rs.getString("or_mod_date"));
					
					return organizerVo;
					
				}
				
			}, msgMap.get("m_id"), 
			   msgMap.get("year"), 
			   msgMap.get("month"));
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return organizerVos;
		
	}

	public OrganizerVo getPlan(Map<String, String> msgMap) {
		System.out.println("[OrganizerDao] getPlan()");
		
		String sql = "SELECT * FROM tbl_organizer "
				   + "WHERE or_no = ? ";
	
		List<OrganizerVo> organizerVos = new ArrayList<OrganizerVo>();
		
		try {
			
			organizerVos = jdbcTemplate.query(sql, new RowMapper<OrganizerVo>() {
	
				@Override
				public OrganizerVo mapRow(ResultSet rs, int rowNum) throws SQLException {
					
					OrganizerVo organizerVo = new OrganizerVo();
					
					organizerVo.setOr_no(rs.getInt("or_no"));
					organizerVo.setOr_ori_no(rs.getInt("or_ori_no"));
					organizerVo.setOr_ori_owner_id(rs.getString("or_ori_owner_id"));    
					organizerVo.setM_id(rs.getString("m_id"));
					organizerVo.setOr_year(rs.getInt("or_year"));
					organizerVo.setOr_month(rs.getInt("or_month"));
					organizerVo.setOr_date(rs.getInt("or_date"));
					organizerVo.setOr_img_name(rs.getString("or_img_name"));
					organizerVo.setOr_title(rs.getString("or_title"));
					organizerVo.setOr_body(rs.getString("or_body"));
					organizerVo.setOr_reg_date(rs.getString("or_reg_date"));
					organizerVo.setOr_mod_date(rs.getString("or_mod_date"));
					
					return organizerVo;
					
				}
				
			}, msgMap.get("or_no"));
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	
		return organizerVos.size() > 0 ? organizerVos.get(0) : null;
	
	}

	public int removePlan(Map<String, String> msgMap) {
		System.out.println("[OrganizerDao] removePlan()");
		
		String sql =  "DELETE o, c FROM tbl_organizer o "
					+ "JOIN tbl_comment c "
					+ "ON o.or_ori_no = c.or_no "
					+ "WHERE o.or_ori_no = ?";
		
		int result = -1;
		
		try {
			
			result = jdbcTemplate.update(sql, msgMap.get("or_no"));
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return result;
		
	}

	public int modifyPlan(OrganizerVo organizerVo) {
		System.out.println("[OrganizerDao] removePlan()");
		
		List<String> args = new ArrayList<String>();
		
		String sql = "UPDATE tbl_organizer ";
			  sql += "SET or_year = ?";
			  sql += ", or_month = ?";
			  sql += ", or_date = ?";
			  sql += ", or_title = ?";
			  sql += ", or_body = ?";
			  
			  args.add(String.valueOf(organizerVo.getOr_year()));
			  args.add(String.valueOf(organizerVo.getOr_month()));
			  args.add(String.valueOf(organizerVo.getOr_date()));
			  args.add(organizerVo.getOr_title());
			  args.add(organizerVo.getOr_body());
			  
			  if (organizerVo.getOr_img_name() != null) {
				  sql += ", or_img_name = ?";
				  args.add(organizerVo.getOr_img_name());
			  }
			  
			  sql += " WHERE or_ori_no = ?";
			  args.add(String.valueOf(organizerVo.getOr_no()));
			  
		int result = -1;
		
		try {
			
			result = jdbcTemplate.update(sql, args.toArray());
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
			  
		return result;
		
	}

	public int getMaxNo() {
		System.out.println("[OrganizerDao] getMaxNo()");
		
		String sql = "SELECT MAX(or_no) FROM tbl_organizer";
		
		int maxNo = 0;
		
		try {
			
			maxNo = jdbcTemplate.queryForObject(sql, Integer.class);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return maxNo;
		
	}

	public int updateOriNo(int maxNo) {
		System.out.println("[OrganizerDao] updateOriNo()");
		
		String sql =  "UPDATE tbl_organizer "
					+ "SET or_ori_no = ? "
					+ "WHERE or_no = ?";
		
		int result = -1;
		
		try {
			
			result = jdbcTemplate.update(sql, maxNo, maxNo);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return result;
		
	}

	public List<MemberVo> search_friend(String friend_name) {
		System.out.println("[OrganizerDao] search_friend()");
		
		String sql =  "SELECT m_no, m_id FROM tbl_member "
					+ "WHERE m_id LIKE ? "
					+ "ORDER BY m_id ASC";
		
		List<MemberVo> memberVos = new ArrayList<MemberVo>();
		
		try {
			
			memberVos = jdbcTemplate.query(sql, new RowMapper<MemberVo>() {

				@Override
				public MemberVo mapRow(ResultSet rs, int rowNum) throws SQLException {
					
					MemberVo memberVo = new MemberVo();
					
					memberVo.setM_no(rs.getInt("m_no"));
					memberVo.setM_id(rs.getString("m_id"));
					
					return memberVo;
				}
				
			}, "%" + friend_name + "%");
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return memberVos;
		
	}

	public boolean isSharedPlan(OrganizerVo organizerVo, String m_id) {
		System.out.println("[OrganizerDao] isSharedPlan()");
		
		String sql =  "SELECT COUNT(*) FROM tbl_organizer "
					+ "WHERE or_ori_no = ? AND m_id = ?";
		
		boolean isSharedPlan = false;
		
		try {
			
			int result = jdbcTemplate.queryForObject(sql, 
														Integer.class, 
														organizerVo.getOr_ori_no(), 
														m_id);
			if (result > 0)
				isSharedPlan = true;
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return isSharedPlan;
		
	}

	public int share_plan(OrganizerVo organizerVo, String m_id) {
		System.out.println("[OrganizerDao] share_plan()");
		
		String sql = "INSERT INTO tbl_organizer("
											  + "or_ori_no, "
											  + "or_ori_owner_id, "
											  + "m_id, "
											  + "or_year, "
											  + "or_month, "
											  + "or_date, "
											  + "or_img_name, "
											  + "or_title, "
											  + "or_body, "
											  + "or_reg_date, "
											  + "or_mod_date) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";
		
		int result = -1;
		
		try {
			
			result = jdbcTemplate.update(sql,
											organizerVo.getOr_ori_no(), 
											organizerVo.getOr_ori_owner_id(), 
											m_id, 
											organizerVo.getOr_year(), 
											organizerVo.getOr_month(), 
											organizerVo.getOr_date(), 
											organizerVo.getOr_img_name(), 
											organizerVo.getOr_title(), 
											organizerVo.getOr_body());
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return result > 0 ? OrganizerDefine.SHARE_PLAN_SUCCESS : OrganizerDefine.SHARE_PLAN_FAIL;
		
	}

	public int removeComment(Map<String, String> msgMap) {
		System.out.println("[OrganizerDao] removeComment()");
		
		String sql =  "DELETE FROM tbl_comment "
					+ "WHERE or_no = ?";
		
		int result = -1;
		
		try {
			
			result = jdbcTemplate.update(sql, msgMap.get("or_no"));
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return result;
		
	}

}




