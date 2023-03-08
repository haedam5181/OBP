package com.office.calender.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.office.calender.board.page.Criteria;
import com.office.calender.board.page.PageMakerVo;

@Service
public class BoardService {

	@Autowired
	BoardDao boardDao;
	
	public int write_confirm(BoardVo boardVo) {
		System.out.println("[BoardService] write_confirm() CALLED!!");
		
		int result = boardDao.write_confirm(boardVo);
		
		if (result <= 0) {
			System.out.println("[BoardService] WRITE FAIL!!");
			
		} else {
			System.out.println("[BoardService] WRITE SUCCESS!!");
			
			int maxNo = boardDao.getMaxNo();
			
			result = boardDao.updateGroup(maxNo);
			
		}
		
		return result;
	}

	public Map<String, Object> list(String m_id, int pageNum, int amount) {
		System.out.println("[BoardService] list() CALLED!!");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		Criteria criteria = new Criteria(pageNum, amount);
		
		List<BoardVo> boardVos = boardDao.list(m_id, criteria);
		
		int totalCnt = boardDao.getTotalCnt(m_id);
		PageMakerVo pageMakerVo = new PageMakerVo(criteria, totalCnt);
		
		map.put("boardVos", boardVos);
		map.put("pageMakerVo", pageMakerVo);
		
		return map;
		
	}

	public BoardVo body(BoardVo boardVo) {
		System.out.println("[BoardService] body() CALLED!!");
		
		return boardDao.body(boardVo.getB_no());
		
	}

	public int delete(BoardVo boardVo) {
		System.out.println("[BoardService] delete() CALLED!!");
		
		return boardDao.delete(boardVo.getB_no());
		
	}

	public BoardVo modify_form(BoardVo boardVo) {
		System.out.println("[BoardService] modify_form() CALLED!!");
		
		return boardDao.modify_form(boardVo.getB_no());
		
	}

	public int modify_confirm(BoardVo boardVo) {
		System.out.println("[BoardService] modify_confirm() CALLED!!");
		
		return boardDao.modify_confirm(boardVo);
		
	}

	public BoardVo reply_form(BoardVo boardVo) {
		System.out.println("[BoardService] reply_form() CALLED!!");
		
		return boardDao.reply_form(boardVo.getB_no());
		
	}

	public int reply_confirm(BoardVo boardVo) {
		System.out.println("[BoardService] reply_confirm() CALLED!!");
		
		int result1, result2 = 0;
		
		result1 = boardDao.reply_shape(boardVo);
		result2 = boardDao.reply_confirm(boardVo);
		
		return result2;
		
	}

}
