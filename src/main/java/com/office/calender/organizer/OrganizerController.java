package com.office.calender.organizer;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.office.calender.member.MemberVo;
import com.office.calender.organizer.util.UploadFileService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/organizer")
public class OrganizerController {

	@Autowired
	OrganizerService organizerService;
	
	@GetMapping(value = {"", "/"})
	public String home(HttpSession session) {
		System.out.println("[OrganizerController] home()");
		
		String nextPage = "organizer/home";
		
		MemberVo logined_memberVo = 
				(MemberVo) session.getAttribute("logined_memberVo");
		if (logined_memberVo == null)
			return "redirect:/";
		
		return nextPage;
		
	}
	
	@PostMapping(value = "/writeOrganize")
	@ResponseBody
	public Object writeOrganize(HttpSession session, 
								OrganizerVo organizerVo,
								@RequestParam("file") MultipartFile file) {
		System.out.println("[OrganizerController] writeOrganize()");
		
		MemberVo logined_memberVo = 
				(MemberVo) session.getAttribute("logined_memberVo");
		if (logined_memberVo == null)
			return "redirect:/";
		
		// SAVE FILE
		UploadFileService uploadFileService = new UploadFileService();
		String savedFileName = 
				uploadFileService.upload(logined_memberVo.getM_id(), file);
		
		if (savedFileName != null) {
			// REGISTER IN DATABASE
			
			organizerVo.setM_id(logined_memberVo.getM_id());
			organizerVo.setOr_img_name(savedFileName);
			
			return organizerService.writeOrganize(organizerVo);
			
		} else {
			return null;
			
		}
		
	}
	
	@PostMapping(value = "/getPlans")
	@ResponseBody
	public Object getPlans(HttpSession session, 
						   @RequestBody Map<String, String> msgMap) {
		System.out.println("[OrganizerController] getPlans()");
		
		MemberVo logined_memberVo = 
				(MemberVo) session.getAttribute("logined_memberVo");
		if (logined_memberVo == null)
			return "redirect:/";
		
		msgMap.put("m_id", logined_memberVo.getM_id());
		Map<String, Object> map = organizerService.getPlans(msgMap);
		
		return map;
		
	}
	
	@PostMapping(value = "/getPlan")
	@ResponseBody
	public Object getPlan(HttpSession session, 
			   			  @RequestBody Map<String, String> msgMap) {
		System.out.println("[OrganizerController] getPlan()");
		
		MemberVo logined_memberVo = 
				(MemberVo) session.getAttribute("logined_memberVo");
		if (logined_memberVo == null)
			return "redirect:/";
		
		Map<String, Object> map = organizerService.getPlan(msgMap);
		
		return map;
		
	}
	
	@PostMapping("/removePlan")
	@ResponseBody
	public Object removePlan(HttpSession session, 
			@RequestBody Map<String, String> msgMap) {
		System.out.println("[OrganizerController] removePlan()");
		
		MemberVo logined_memberVo = 
				(MemberVo) session.getAttribute("logined_memberVo");
		if (logined_memberVo == null)
			return "redirect:/";
		
		Map<String, Object> map = organizerService.removePlan(msgMap);
		
		return map;
		
	}
	
	@PostMapping(value = "/modifyPlan")
	@ResponseBody
	public Object modifyPlan(HttpSession session, 
								OrganizerVo organizerVo,
								@RequestParam(value = "file", required = false) MultipartFile file) {
		System.out.println("[OrganizerController] modifyPlan()");
		
		MemberVo logined_memberVo = 
				(MemberVo) session.getAttribute("logined_memberVo");
		if (logined_memberVo == null)
			return "redirect:/";
		
		if (file != null) {
			// SAVE FILE
			UploadFileService uploadFileService = new UploadFileService();
			String savedFileName = 
					uploadFileService.upload(logined_memberVo.getM_id(), file);
			
			if (savedFileName != null) {
				// REGISTER IN DATABASE
				organizerVo.setOr_img_name(savedFileName);
				return organizerService.modifyPlan(organizerVo);
				
			} else {
				return null;
				
			}
			
		} else {
			// REGISTER IN DATABASE
			return organizerService.modifyPlan(organizerVo);
			
		}
		
	}
	
	@RequestMapping("/search_friend")
	@ResponseBody
	public Object search_friend(@RequestBody Map<String, String> msgMap) {
		System.out.println("[OrganizerController] search_friend()");
		
		Map<String, Object> map = organizerService.search_friend(msgMap);
		
		return map;
		
	}
	
	@RequestMapping("/share_plan")
	@ResponseBody
	public Object share_plan(@RequestBody Map<String, String> msgMap) {
		System.out.println("[OrganizerController] share_plan()");
		
		Map<String, Object> map = organizerService.share_plan(msgMap);
		
		return map;
		
	}
}
