package com.example.myproject.controller.member;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.myproject.model.member.MemberDTO;
import com.example.myproject.model.member.SignupDAO;

@Controller
@RequestMapping("/signup/*")
public class SignupController {
	
	@Inject
	SignupDAO signupDao;
	@RequestMapping("signup.do")
	public String signup() {
		return "/signup/write";
	}
	
	@RequestMapping("insert.do")
	public String insert(@ModelAttribute MemberDTO dto) {
		signupDao.insert(dto);
		return "member/login";
	}
	@RequestMapping("signup/write.do")
	public String write() {
		return "signup/write";
	}
}
