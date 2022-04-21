package com.example.myproject.controller.member;

import javax.inject.Inject;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.myproject.model.member.AdminDAO;
import com.example.myproject.model.member.MemberDTO;

@Controller
@RequestMapping("/admin/*")	//공통적인 url pattern
public class AdminController {
	@Inject
	AdminDAO adminDao;
	@RequestMapping("login.do") //세부적인 url pattern
	public String login() {
		return "admin/login";
	}
	@RequestMapping("login_check.do")
	public ModelAndView login_check(MemberDTO dto, HttpSession session, ModelAndView mav) {
		String name = adminDao.login(dto);
		System.out.println("dto:"+dto);
		if (name != null) { //로그인 성공
			session.setAttribute("admin_userid", dto.getUserid()); //세션변수 저장
			session.setAttribute("admin_name", name);
			session.setAttribute("userid", dto.getUserid());
			session.setAttribute("name", name);
			mav.setViewName("admin/admin"); // views/admin/admin.jsp
			mav.addObject("message", "success"); // 변수에 값 저장
		} else { //로그인 실패
			mav.setViewName("admin/login");
			mav.addObject("message", "error");
		}
		return mav;
	}
	@RequestMapping("logout.do")
	public String logout(HttpSession session) {
		session.invalidate();	//세션 초기화 
		return "redirect:/admin/login.do?message=logout";
	}
}


