package com.office.calender.member;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	MemberService memberService;
	
	/*
	 * CREATE ACCOUNT FORM
	 */
	@GetMapping("/create_account_form")
	public String create_account_form() {
		System.out.println("[MemberController] create_account_form()");
		
		String nextPage = "member/create_account_form";
		
		return nextPage;
		
	}
	
	/*
	 * CREATE ACCOUNT CONFIRM
	 */
	@PostMapping("/create_account_confirm")
	public String create_account_confirm(MemberVo memberVo) {
		System.out.println("[MemberController] create_account_confirm()");
		
		String nextPage = "member/create_account_success";
		
		int result = memberService.create_account_confirm(memberVo);
		
		if (result <= MemberService.INSERT_FAIL_AT_DATABASE)
			nextPage = "member/create_account_fail";
			
		return nextPage;
		
	}
	
	/*
	 * MEMBER LOGIN FORM
	 */
	@GetMapping("/member_login_form")
	public String member_login_form() {
		System.out.println("[MemberController] member_login_form()");
		
		String nextPage = "/member/member_login_form";
		
		return nextPage;
		
	}
	
	/*
	 * MEMBER LOGIN CONFIRM
	 */
	@PostMapping("/member_login_confirm")
	public String member_login_confirm(MemberVo memberVo, HttpSession session) {
		System.out.println("[MemberController] member_login_confirm()");
		
		String nextPage = "/member/member_login_success";
		
		MemberVo logined_memberVo = memberService.member_login_confirm(memberVo);
		
		if (logined_memberVo != null) {
			session.setAttribute("logined_memberVo", logined_memberVo);
			session.setMaxInactiveInterval(60 * 30);
			
		} else {
			nextPage = "/member/member_login_fail";
			
		}
		
		return nextPage;
		
	}
	
	/*
	 * MEMBER LOGOUT CONFIRM
	 */
	@GetMapping("/member_logout_confirm")
	public String member_logout_confirm(HttpSession session) {
		System.out.println("[MemberController] member_logout_confirm()");
		
		String nextPage = "redirect:/";
		
		session.removeAttribute("logined_memberVo");
		
		return nextPage;
		
	}
	
	/*
	 * MEMBER MODIFY FORM
	 */
	@GetMapping("/member_modify_form")
	public String member_modify_form(HttpSession session) {
		System.out.println("[MemberController] member_modify_form()");
		
		String nextPage = "member/member_modify_form";
		
		MemberVo  logined_memberVo =
				(MemberVo) session.getAttribute("logined_memberVo");
		if (logined_memberVo == null)
			nextPage = "redirect:/member/member_login_form";
		
		return nextPage;
		
	}
	
	/*
	 * MEMBER MODIFY CONFIRM
	 */
	@PostMapping("/member_modify_confirm")
	public String member_modify_confirm(MemberVo memberVo, HttpSession session) {
		System.out.println("[MemberController] member_modify_confirm()");
		
		String nextPage = "member/member_modify_success";
		
		MemberVo logined_memberVo = memberService.member_modify_confirm(memberVo);
		
		if (logined_memberVo != null) {
			session.setAttribute("logined_memberVo", logined_memberVo);
			session.setMaxInactiveInterval(60 * 30);
			
		} else {
			nextPage = "member/member_modify_fail";
			
		}
		
		return nextPage;
		
	}
	
	/*
	 * MEMBER DELETE CONFIRM
	 */
	@PostMapping("/member_delete_confirm")
	@ResponseBody
	public Object member_delete_confirm(HttpSession session, @RequestBody Map<String, String> msgMap) {
		System.out.println("[MemberController] member_delete_confirm()");
		
		MemberVo logined_memberVo = 
				(MemberVo) session.getAttribute("logined_memberVo");
		if (logined_memberVo == null)
			return null;
		
		Map<String, Object> map = memberService.member_delete_confirm(msgMap.get("m_no"));
		
		if (((int) map.get("result")) > 0)
			session.removeAttribute("logined_memberVo");
		
		return map;
		
	}
	
}
