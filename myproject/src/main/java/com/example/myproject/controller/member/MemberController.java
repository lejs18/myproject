package com.example.myproject.controller.member;

import javax.inject.Inject;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.example.myproject.model.member.MemberDAO;
import com.example.myproject.model.member.MemberDTO;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	@Inject
	MemberDAO memberDao;
	
	@RequestMapping("login.do")
	public String login() {
		return "member/login";
	}
	
	@RequestMapping("login_check.do")
	public ModelAndView login_check(MemberDTO dto, HttpSession session) {
		String name = memberDao.login(dto);
		if(name != null) {			
			session.setAttribute("userid", dto.getUserid());
			session.setAttribute("name", name);			
		}
		ModelAndView mav = new ModelAndView();
		if(name!=null) {
			mav.setViewName("home");
		} else {
			mav.setViewName("member/login");
			mav.addObject("message", "error");
		}
		return mav;
	}
	
	@RequestMapping("logout.do")
	public ModelAndView logout(HttpSession session, ModelAndView mav) {
		session.invalidate();
		mav.setViewName("member/login");
		mav.addObject("message", "logout");
		return mav;
	}
}
