package com.office.calender.organizer.comment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

	@Autowired
	CommentDao commentDao;

	public Object regist_comment(Map<String, String> msgMap) {
		System.out.println("[CommentService] regist_comment()");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		int result = commentDao.regist_comment(msgMap);
		List<CommentVo> commentVos = null;
		
		if (result > 0) {
			System.out.println("[CommentService] REGIST COMMENT SUCCESS!!");
			
			commentVos =commentDao.getComments(msgMap);
			
		} else {
			System.out.println("[CommentService] REGIST COMMENT FAIL!!");
			
		}
		
		map.put("result", result);
		map.put("commentVos", commentVos);
		
		return map;
		
	}

	public Object get_comments(Map<String, String> msgMap) {
		System.out.println("[CommentService] get_comments()");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<CommentVo> commentVos = commentDao.getComments(msgMap);
		
		map.put("commentVos", commentVos);
		
		return map;
		
	}
	
}
