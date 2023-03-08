package com.office.calender.organizer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.office.calender.member.MemberVo;
import com.office.calender.organizer.util.OrganizerDefine;

@Service
public class OrganizerService {

	@Autowired
	OrganizerDao organizerDao;

	public Object writeOrganize(OrganizerVo organizerVo) {
		System.out.println("[OrganizerService] writeOrganize()");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		int result = organizerDao.writeOrganize(organizerVo);
		if (result <= 0) {
			System.out.println("[OrganizerService] writeOrganize() FAIL!!");
			
		} else {
			System.out.println("[OrganizerService] writeOrganize() SUCCESS!!");
			
			int maxNo = organizerDao.getMaxNo();
			
			result = organizerDao.updateOriNo(maxNo);
			
		}
		
		map.put("result", result);
		
		return map;
		
	}

	public Map<String, Object> getPlans(Map<String, String> msgMap) {
		System.out.println("[OrganizerService] getPlans()");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<OrganizerVo> organizerVos = organizerDao.getPlans(msgMap);
		map.put("organizerVos", organizerVos);
		
		return map;
		
	}

	public Map<String, Object> getPlan(Map<String, String> msgMap) {
		System.out.println("[OrganizerService] getPlan()");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		OrganizerVo organizerVo = organizerDao.getPlan(msgMap);
		map.put("organizerVo", organizerVo);
		
		return map;
		
	}

	public Map<String, Object> removePlan(Map<String, String> msgMap) {
		System.out.println("[OrganizerService] removePlan()");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		int result = organizerDao.removePlan(msgMap);
		if (result > 0)
			System.out.println("[OrganizerService] removePlan() SUCCESS");
		else
			System.out.println("[OrganizerService] removePlan() FAIL");
		
		map.put("result", result);
		
		return map;
		
	}

	public Object modifyPlan(OrganizerVo organizerVo) {
		System.out.println("[OrganizerService] modifyPlan()");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		int result = organizerDao.modifyPlan(organizerVo);
		
		if (result > 0)
			System.out.println("[OrganizerService] modifyPlan() SUCCESS");
		else
			System.out.println("[OrganizerService] modifyPlan() FAIL");
		
		map.put("result", result);
		
		return map;
		
	}

	public Map<String, Object> search_friend(Map<String, String> msgMap) {
		System.out.println("[OrganizerService] search_friend()");
		
		Map<String , Object> map = new HashMap<String , Object>();
		
		List<MemberVo> memberVos = organizerDao.search_friend(msgMap.get("friend_name"));
		
		map.put("memberVos", memberVos);
		
		return map;
	}

	public Map<String, Object> share_plan(Map<String, String> msgMap) {
		System.out.println("[OrganizerService] share_plan()");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		OrganizerVo organizerVo = organizerDao.getPlan(msgMap);
		
		boolean isSharedPlan = organizerDao.isSharedPlan(organizerVo, msgMap.get("m_id"));
		
		int result = OrganizerDefine.SHARE_PLAN_FAIL;
		if (!isSharedPlan) {
			result = organizerDao.share_plan(organizerVo, msgMap.get("m_id"));
			
			switch (result) {
			case OrganizerDefine.SHARE_PLAN_FAIL:
				System.out.println("[OrganizerService] SHARE PLAN FAIL!!");
				result = OrganizerDefine.SHARE_PLAN_FAIL;
				break;

			case OrganizerDefine.SHARE_PLAN_SUCCESS:
				System.out.println("[OrganizerService] SHARE PLAN SUCCESS!!");
				result = OrganizerDefine.SHARE_PLAN_SUCCESS;
				break;
				
			}
			
		} else {
			System.out.println("[OrganizerService] ALREADY SHARED PLAN!!");
			result = OrganizerDefine.ALREADY_SHARED_PLAN;
			
		}
		
		map.put("result", result);
		
		return map;
		
	}
	
}
