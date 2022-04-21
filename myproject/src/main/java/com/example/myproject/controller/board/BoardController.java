package com.example.myproject.controller.board;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.myproject.model.board.BoardDTO;
import com.example.myproject.model.board.ReplyDAO;
import com.example.myproject.service.board.BoardService;
import com.example.myproject.service.board.PageUtil;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	@Inject
	BoardService boardService;
	
	@Inject
	ReplyDAO replyDao;
	
	@RequestMapping("write.do")
	public String write() {
		return "board/write";
	}
	
	
	@RequestMapping("insert.do")
	public String insert(BoardDTO dto, HttpSession session) {
		String writer=(String)session.getAttribute("userid"); //세션변수 조회
		dto.setWriter(writer);
		boardService.insert(dto);
		return "redirect:/board/list.do";
	}
	
	@RequestMapping("list.do")
	public ModelAndView list(@RequestParam(defaultValue="1") int curPage, @RequestParam(defaultValue="all") String search_option, @RequestParam(defaultValue="") String keyword) {
		int count=boardService.count(search_option, keyword); //게시물 수
		PageUtil page_info=new PageUtil(count, curPage);
		int start=page_info.getPageBegin(); //레코드 시작번호
		int end=page_info.getPageEnd(); //레코드 끝번호
		List<BoardDTO> list=boardService.list(start, end, search_option, keyword);
		ModelAndView mav=new ModelAndView();
		mav.setViewName("board/list");
		Map<String,Object> map=new HashMap<>();
		map.put("list", list);
		map.put("count", count);
		map.put("search_option", search_option);
		map.put("keyword", keyword);
		map.put("page_info", page_info);
		mav.addObject("map", map);
		return mav;
	}
	
	@RequestMapping("detail.do")
	public ModelAndView detail(int idx, int cur_page, String search_option, String keyword) {
		boardService.increase_hit(idx); //조회수 증가 처리
		ModelAndView mav=new ModelAndView();
		mav.setViewName("board/view");
		mav.addObject("dto", boardService.detail(idx));
		mav.addObject("count", replyDao.count(idx)); //댓글 개수
		mav.addObject("cur_page", cur_page);
		mav.addObject("search_option", search_option);
		mav.addObject("keyword", keyword);
		return mav;
	}
	
	@RequestMapping("update.do")
	public String update(BoardDTO dto) {
		boardService.update(dto);
		return "redirect:/board/list.do";
	}
	
	@RequestMapping("delete.do")
	public String delete(int idx) {
		boardService.delete(idx);
		return "redirect:/board/list.do";
	}
	
	@RequestMapping("list_attach/{idx}")
	@ResponseBody // 뷰가 아닌 데이터 리턴 
	public List<String> list_attach(@PathVariable("idx") int idx){
		return boardService.list_attach(idx); //첨부파일 리스트
	}
}
