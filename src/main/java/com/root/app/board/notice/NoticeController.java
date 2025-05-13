package com.root.app.board.notice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.root.app.board.BoardFileVO;
import com.root.app.board.BoardVO;
import com.root.app.user.UserVO;
import com.root.app.util.Pager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/notice/*")
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@Value("${menu.board.notice.name}")
	private String name;
	
	@ModelAttribute("kind")
	public String getName() {
		return this.name;
	}
	
	@GetMapping("list")
	@CrossOrigin
	public Map<String, Object> getList(Model model, Pager pager) throws Exception {
		List<BoardVO> ar = noticeService.getList(pager);
		
		Map<String, Object> map = new HashMap<>();
		map.put("list", ar);
		map.put("pager", pager);
		
		return map;
	}
	
	@GetMapping("detail")
	@CrossOrigin
	public BoardVO getDetail(BoardVO boardVO, Model model) throws Exception {
		
		boardVO = noticeService.getDetail(boardVO);
		
		return boardVO;
	}
	
	@GetMapping("fileDown")
	public String getFileDetail(BoardFileVO boardFileVO, Model model) throws Exception {
		boardFileVO = noticeService.getFileDetail(boardFileVO);
		
		model.addAttribute("fileVO", boardFileVO);
		
		return "fileDownView";
	}
	
	@GetMapping("add")
	public String add() throws Exception {
		return "board/add";
	}
	
	@PostMapping("add")
	public String add(BoardVO boardVO, @RequestParam(name = "attaches") MultipartFile [] attaches, @AuthenticationPrincipal UserVO userVO) throws Exception {
		
		boardVO.setUserName(userVO.getUsername());
		int result = noticeService.add(boardVO, attaches);
		return "redirect:./list";
	}
	
	@GetMapping("update")
	public String update(BoardVO boardVO, Model model) throws Exception {
		boardVO = noticeService.getDetail(boardVO);
		model.addAttribute("vo", boardVO);
		return "board/update";
	}
	
	@PostMapping("update")
	public String update(BoardVO boardVO) throws Exception {
		noticeService.update(boardVO);
		
		return "redirect:./detail?boardNum="+boardVO.getBoardNum();
	}
	
	@PostMapping("delete")
	public String delete(BoardVO boardVO) throws Exception {
		
		noticeService.delete(boardVO);
		
		return "redirect:./list";
	}

}
