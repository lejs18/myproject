package com.example.myproject.controller.board;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.myproject.model.board.ReplyDAO;
import com.example.myproject.model.board.ReplyDTO;
import com.example.myproject.service.board.PageUtil;

@RestController // view와 data를 리턴할 수 있는 컨트롤러
@RequestMapping("/reply/*")
public class ReplyController {
	@Inject
	ReplyDAO replyDao;
	
	@RequestMapping("insert.do")
	public void insert(ReplyDTO dto, HttpSession session) { //댓글 저장
		String userid=(String)session.getAttribute("userid"); //세션 변수 조회
		dto.setReplyer(userid); //댓글 작성자
		replyDao.insert(dto);
	}
	
	@RequestMapping("/delete/{idx}")
	public ResponseEntity<String> delete(@PathVariable("idx") int idx){ //댓글 삭제
		ResponseEntity<String> entity=null; // 데이터와 상태코드를 리턴하는 객체
		try {
			replyDao.delete(idx); //댓글 삭제
			entity=new ResponseEntity<>("success",HttpStatus.OK); // success 코드 리턴
		} catch (Exception e) {
			e.printStackTrace();
			entity=new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST); // 에러코드 400
		}
		return entity;
	}
	
	@RequestMapping("/detail/{idx}")
	public ModelAndView detail(@PathVariable("idx") int idx, ModelAndView mav) { //댓글 상세
		ReplyDTO dto=replyDao.detail(idx);
		mav.setViewName("board/reply_detail"); //출력 페이지의 이름
		mav.addObject("dto", dto);
		return mav;
	}
	
	@RequestMapping("list.do")
	public ModelAndView list(int board_idx, @RequestParam(defaultValue="1") int curPage, ModelAndView mav) {
		int count=replyDao.count(board_idx); //댓글수
		PageUtil page_info=new PageUtil(count,curPage);
		int start=page_info.getPageBegin(); //레코드 시작번호
		int end=page_info.getPageEnd(); //레코드 끝번호
		List<ReplyDTO> list=replyDao.list(board_idx, start, end);
		mav.setViewName("board/reply_list");
		mav.addObject("list", list);
		mav.addObject("page_info", page_info);
		return mav;
	}
	
	@RequestMapping("list_json.do")
	public @ResponseBody List<ReplyDTO> list_json(@RequestParam(defaultValue="1") int curPage, @RequestParam int idx){ // json 형식으로 댓글 목록을 리턴 
		int count=replyDao.count(idx);
		PageUtil page_info=new PageUtil(count,curPage);
		int start=page_info.getPageBegin();
		int end=page_info.getPageEnd();
		List<ReplyDTO> list=replyDao.list(idx, start, end);
		return list;
	}
	
	// @RequestBody json => dto로 변환하는 기능 
	@RequestMapping("/update/{idx}")
	public ResponseEntity<String> update(@PathVariable("idx") int idx, @RequestBody ReplyDTO dto){
		ResponseEntity<String> entity=null;
		try {
			dto.setIdx(idx);
			replyDao.update(dto); //댓글 수정
			entity=new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity=new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
