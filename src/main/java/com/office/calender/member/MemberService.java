package com.office.calender.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

	final static public int DATABASE_COMMUNICATION_TROUBLE = -1;
	final static public int INSERT_FAIL_AT_DATABASE = 0;
	final static public int INSERT_SUCCESS_AT_DATABASE = 1;
	
	@Autowired
	MemberDao memberDao;
	
	public int create_account_confirm(MemberVo memberVo) {
		System.out.println("[MemberService] create_account_confirm()");
		
		boolean isMember = memberDao.isMember(memberVo.getM_id());
		
		if (!isMember) {
			int result = memberDao.create_account_confirm(memberVo);
			
			switch (result) {
			case DATABASE_COMMUNICATION_TROUBLE:
				System.out.println("DATABASE COMMUNICATION TROUBLE!!");
				break;
				
			case INSERT_FAIL_AT_DATABASE:
				System.out.println("INSERT FAIL AT DATABASE!!");
				break;
			
			case INSERT_SUCCESS_AT_DATABASE:
				System.out.println("INSERT SUCCESS AT DATABASE!!");
				break;
			}
			
			return result;
			
		} else {
			return 0;
		}
		
	}

	public MemberVo member_login_confirm(MemberVo memberVo) {
		System.out.println("[MemberService] member_login_confirm()");
		
		return memberDao.member_login_confirm(memberVo);
		
	}

	public MemberVo member_modify_confirm(MemberVo memberVo) {
		System.out.println("[MemberService] member_modify_confirm()");
		
		int result = memberDao.member_modify_confirm(memberVo);
		if (result > 0)
			return memberDao.getLatestMemberInfo(memberVo);
		else
			return null;
		
	}

	public Map<String, Object> member_delete_confirm(String m_no) {
		System.out.println("[MemberService] member_delete_confirm()");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		int result = memberDao.member_delete_confirm(Integer.parseInt(m_no));
		
		switch (result) {
		case -1:
			System.out.println("DATABASE COMMUNICATION ERROR!!");
			break;
		
		case 0:
			System.out.println("DATABASE DELETE ERROR!!");
			break;
			
		case 1:
			System.out.println("DATABASE DELETE SUCCESS!!");
			break;
		}
		
		map.put("result", result);
		
		return map;
	}

}