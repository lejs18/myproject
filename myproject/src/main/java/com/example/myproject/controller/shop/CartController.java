package com.example.myproject.controller.shop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.myproject.model.shop.CartDAO;
import com.example.myproject.model.shop.CartDTO;

@Controller
@RequestMapping("/shop/cart/*")
public class CartController {

	@Inject
	CartDAO cartDao;

	@RequestMapping("delete.do")
	public String delete(int cart_id) {
		cartDao.delete(cart_id); //레코드 개별 삭제
		return "redirect:/shop/cart/list.do"; //목록으로 이동
	}

	@RequestMapping("deleteAll.do")
	public String deleteAll(HttpSession session) {
		String userid = (String) session.getAttribute("userid"); 
		if (userid != null) { //세션 체크
			cartDao.delete_all(userid); //장바구니 비우기
		}
		return "redirect:/shop/cart/list.do";
	}

	@RequestMapping("update.do")
	public String update(int[] amount, int[] cart_id, HttpSession session) {
		String userid = (String) session.getAttribute("userid");
		if (userid == null) {
			return "redirect:/member/login.do";
		}
		for (int i = 0; i < cart_id.length; i++) { // 배열 사이즈만큼 반복 처리
			if (amount[i] == 0) {
				cartDao.delete(cart_id[i]); //수량을 0으로 고치면 레코드 삭제
			} else {
				CartDTO dto = new CartDTO();
				dto.setUserid(userid);
				dto.setCart_id(cart_id[i]);
				dto.setAmount(amount[i]);
				cartDao.modify(dto);
			}
		}
		return "redirect:/shop/cart/list.do";
	}

	@RequestMapping("list.do")
	public ModelAndView list(HttpSession session, ModelAndView mav) {
		Map<String, Object> map = new HashMap<>();
		String userid = (String) session.getAttribute("userid"); //로그인한 사용자 아이디
		if (userid != null) { //로그인중이면
			List<CartDTO> list = cartDao.list(userid);
			int sumMoney = cartDao.sum_money(userid); //금액 합계 
			int fee = sumMoney >= 30000 ? 0 : 2500; //배송료 계산
			//맵에 자료 저장
			map.put("sumMoney", sumMoney);
			map.put("fee", fee);
			map.put("sum", sumMoney + fee);
			map.put("list", list);
			map.put("count", list.size());
			mav.setViewName("shop/cart_list"); //출력 페이지의 이름
			mav.addObject("map", map); //출력 페이지에 넘길 데이터
			return mav;
		} else { //로그아웃 상태이면
			return new ModelAndView("member/login"); //로그인 페이지로 이동
		}
	}

	@RequestMapping("insert.do")
	public String insert(CartDTO dto, HttpSession session) {
		String userid = (String) session.getAttribute("userid");
		if (userid == null) {
			return "redirect:/member/login.do";
		}
		dto.setUserid(userid);
		cartDao.insert(dto);
		return "redirect:/shop/cart/list.do";
	}
}
