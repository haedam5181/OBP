package com.office.calender.board;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.office.calender.board.page.PageMakerVo;
import com.office.calender.member.MemberVo;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	BoardService boardService;
	
	@GetMapping(value = {"", "/"})
	public String home() {
		System.out.println("[BoardController] home() CALLED!!");
		
		return "redirect:/board/list";
		
	}
	
	@GetMapping("/list")
	public String list(HttpSession session, 
					   Model model, 
					   @RequestParam(value="pageNum", required = false, defaultValue = "1") int pageNum, 
					   @RequestParam(value="amount", required = false, defaultValue = "10") int amount) {
		System.out.println("[BoardController] list() CALLED!!");
		
		String nextPage = "board/list";
		
		MemberVo logined_memberVo =
				(MemberVo) session.getAttribute("logined_memberVo");
		if (logined_memberVo == null)
			return "redirect:/";
		
		Map<String, Object> map = boardService.list(logined_memberVo.getM_id(), pageNum, amount);
		
		List<BoardVo> boardVos = (List<BoardVo>) map.get("boardVos");
		PageMakerVo pageMakerVo = (PageMakerVo) map.get("pageMakerVo");
		
		model.addAttribute("boardVos", boardVos);
		model.addAttribute("pageMakerVo", pageMakerVo);
		
		return nextPage;
		
	}
	
	@GetMapping("/write_form")
	public String write_form(HttpSession session) {
		System.out.println("[BoardController] write_form() CALLED!!");
		
		String nextPage = "board/write_form";
		
		MemberVo logined_memberVo =
				(MemberVo) session.getAttribute("logined_memberVo");
		if (logined_memberVo == null)
			return "redirect:/";
		
		return nextPage;
		
	}
	
	@PostMapping("/write_confirm")
	public String write_confirm(HttpSession session, BoardVo boardVo) {
		System.out.println("[BoardController] write_confirm() CALLED!!");
		
		String nextPage = "/board/write_success";
		
		MemberVo logined_memberVo =
				(MemberVo) session.getAttribute("logined_memberVo");
		if (logined_memberVo == null)
			return "redirect:/";
		
		boardVo.setM_id(logined_memberVo.getM_id());
		
		int result = boardService.write_confirm(boardVo);
		if (result <= 0)
			nextPage = "/board/write_fail";
		
		return nextPage;
		
	}
	
	@GetMapping("/body")
	public String body(HttpSession session, BoardVo boardVo, Model modle) {
		System.out.println("[BoardController] body() CALLED!!");
		
		String nextPage = "board/body";
		
		MemberVo logined_memberVo =
				(MemberVo) session.getAttribute("logined_memberVo");
		if (logined_memberVo == null)
			return "redirect:/";
		
		boardVo = boardService.body(boardVo);
		
		modle.addAttribute("boardVo", boardVo);
		
		return nextPage;
		
	}
	
	@GetMapping("/delete")
	public String delete(HttpSession session, BoardVo boardVo) {
		System.out.println("[BoardController] delete() CALLED!!");
		
		String nextPage = "board/delete_success";
		
		MemberVo logined_memberVo = 
				(MemberVo) session.getAttribute("logined_memberVo");
		if (logined_memberVo == null)
			return "redirect:/";
		
		int result = boardService.delete(boardVo);
		
		if(result <=  0)
			nextPage = "board/delete_fail";
		
		return nextPage;
		
	}
	
	@GetMapping("/modify_form")
	public String modify_form(HttpSession session, BoardVo boardVo, Model model) {
		System.out.println("[BoardController] modify_form() CALLED!!");
		
		String nextPage = "board/modify_form";
		
		MemberVo logined_memberVo = 
				(MemberVo) session.getAttribute("logined_memberVo");
		if (logined_memberVo == null)
			return "redirect:/";
		
		boardVo = boardService.modify_form(boardVo);
		
		model.addAttribute("boardVo", boardVo);
		
		return nextPage;
		
	}
	
	@PostMapping("/modify_confirm")
	public String modify_confirm(HttpSession session, BoardVo boardVo) {
		System.out.println("[BoardController] modify_confirm() CALLED!!");
		
		String nextPage = "board/modify_success";
		
		MemberVo logined_memberVo = 
				(MemberVo) session.getAttribute("logined_memberVo");
		if (logined_memberVo == null)
			return "redirect:/";
	
		int result = boardService.modify_confirm(boardVo);
		if (result <= 0)
			nextPage = "board/modify_fail";
		
		return nextPage;
		
	}
	
	@GetMapping("/reply_form")
	public String reply_form(HttpSession session, BoardVo boardVo, Model model) {
		System.out.println("[BoardController] reply_form() CALLED!!");
		
		String nextPage = "board/reply_form";
		
		MemberVo logined_memberVo = 
				(MemberVo) session.getAttribute("logined_memberVo");
		if (logined_memberVo == null)
			return "redirect:/";
		
		boardVo = boardService.reply_form(boardVo);
		
		model.addAttribute("boardVo", boardVo);
		
		return nextPage;
		
	}
	
	@PostMapping("/reply_confirm")
	public String reply_confirm(HttpSession session, BoardVo boardVo) {
		System.out.println("[BoardController] reply_confirm() CALLED!!");
		
		String nextPage = "board/reply_success";
		
		MemberVo logined_memberVo = 
				(MemberVo) session.getAttribute("logined_memberVo");
		if (logined_memberVo == null)
			return "redirect:/";
		
		boardVo.setM_id(logined_memberVo.getM_id());
		
		int result = boardService.reply_confirm(boardVo);
		if (result <= 0)
			nextPage = "board/reply_fail";
		
		return nextPage;
		
	}
	
}
