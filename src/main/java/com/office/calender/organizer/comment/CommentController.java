package com.office.calender.organizer.comment;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.office.calender.member.MemberVo;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	CommentService commentService;
	
	@PostMapping("/regist_comment")
	public Object regist_comment(HttpSession session, @RequestBody Map<String, String> msgMap) {
		System.out.println("[CommentController] regist_comment()");
		
		MemberVo logined_memberVo = 
				(MemberVo) session.getAttribute("logined_memberVo");
		
		msgMap.put("m_id", logined_memberVo.getM_id());
		
		return commentService.regist_comment(msgMap);
		
	}
	
	@PostMapping("/get_comments")
	public Object get_comments(@RequestBody Map<String, String> msgMap) {
		System.out.println("[CommentController] get_comments()");
		
		return commentService.get_comments(msgMap);
		
	}
	
}
