package com.office.calender.member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MemberDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public boolean isMember(String m_id) {
		System.out.println("[MemberDao] isMember()");
		
		String sql = "SELECT COUNT(*) FROM tbl_member WHERE m_id = ?";
		
		boolean isMember = false;
		
		try {
			
			int result = jdbcTemplate.queryForObject(sql, Integer.class, m_id);
			
			if (result > 0)
				isMember = true;
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return isMember;
		
	}

	public int create_account_confirm(MemberVo memberVo) {
		System.out.println("[MemberDao] create_account_confirm()");
		
		String sql = "INSERT INTO tbl_member("
										   + "m_id, "
										   + "m_pw, "
										   + "m_mail, "
										   + "m_phone, "
										   + "m_reg_date, "
										   + "m_mod_date) VALUES(?, ?, ?, ?, NOW(), NOW())";
		
		int result = MemberService.DATABASE_COMMUNICATION_TROUBLE;
		
		try {
			
			result = jdbcTemplate.update(sql, 
											  memberVo.getM_id(), 
											  passwordEncoder.encode(memberVo.getM_pw()), 
											  memberVo.getM_mail(), 
											  memberVo.getM_phone());
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return result;
	}

	public MemberVo member_login_confirm(MemberVo memberVo) {
		System.out.println("[MemberDao] member_login_confirm()");
		
		String sql = "SELECT * FROM tbl_member WHERE m_id = ?";
		
		List<MemberVo> memberVos = new ArrayList<>();
		
		try {
			
			memberVos = jdbcTemplate.query(sql, new RowMapper<MemberVo>() {

				@Override
				public MemberVo mapRow(ResultSet rs, int rowNum) throws SQLException {
					
					MemberVo memberVo = new MemberVo();
					
					memberVo.setM_no(rs.getInt("m_no"));
					memberVo.setM_id(rs.getString("m_id"));
					memberVo.setM_pw(rs.getString("m_pw"));
					memberVo.setM_mail(rs.getString("m_mail"));
					memberVo.setM_phone(rs.getString("m_phone"));
					memberVo.setM_reg_date(rs.getString("m_reg_date"));
					memberVo.setM_mod_date(rs.getString("m_mod_date"));
					
					return memberVo;
				}
				
			}, memberVo.getM_id());
			
			if (!passwordEncoder.matches(memberVo.getM_pw(), memberVos.get(0).getM_pw())) {
				memberVos.clear();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return memberVos.size() > 0 ? memberVos.get(0) : null;
		
	}

	public int member_modify_confirm(MemberVo memberVo) {
		System.out.println("[MemberDao] member_modify_confirm()");
		
		String sql = "UPDATE tbl_member "
				   + "SET m_mail = ?, m_phone = ?, m_mod_date = NOW() "
				   + "WHERE m_no = ?";
		
		int result = -1;
		
		try {
			
			result = jdbcTemplate.update(sql, 
										 memberVo.getM_mail(), 
										 memberVo.getM_phone(), 
										 memberVo.getM_no());
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return result;
		
	}

	public MemberVo getLatestMemberInfo(MemberVo memberVo) {
		System.out.println("[MemberDao] getLatestMemberInfo()");
		
		String sql = "SELECT * FROM tbl_member WHERE m_no = ?";
		
		List<MemberVo> memberVos = new ArrayList<>();
		
		try {
			
			memberVos = jdbcTemplate.query(sql, new RowMapper<MemberVo>() {

				@Override
				public MemberVo mapRow(ResultSet rs, int rowNum) throws SQLException {
					
					MemberVo memberVo = new MemberVo();
					
					memberVo.setM_no(rs.getInt("m_no"));
					memberVo.setM_id(rs.getString("m_id"));
					memberVo.setM_pw(rs.getString("m_pw"));
					memberVo.setM_mail(rs.getString("m_mail"));
					memberVo.setM_phone(rs.getString("m_phone"));
					memberVo.setM_reg_date(rs.getString("m_reg_date"));
					memberVo.setM_mod_date(rs.getString("m_mod_date"));
					
					return memberVo;
				}
				
			}, memberVo.getM_no());
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return memberVos.size() > 0 ? memberVos.get(0) : null;
		
	}

	public int member_delete_confirm(int m_no) {
		System.out.println("[MemberDao] member_delete_confirm()");
		
		String sql = "DELETE FROM tbl_member WHERE m_no = ?";
		
		int result = -1;
		
		try {
			
			result = jdbcTemplate.update(sql, m_no);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return result;
	}

}
