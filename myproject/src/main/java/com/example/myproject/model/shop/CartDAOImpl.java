package com.example.myproject.model.shop;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class CartDAOImpl implements CartDAO {

	@Inject
	SqlSession sqlSession;
	
	@Override
	public List<CartDTO> cart_money() {
		return sqlSession.selectList("cart.cart_money"); //품목별 금액
	}

	@Override
	public void insert(CartDTO dto) {
		sqlSession.insert("cart.insert", dto); //장바구니 추가
	}

	@Override
	public List<CartDTO> list(String userid) {
		return sqlSession.selectList("cart.list", userid); //목록
	}

	@Override
	public void delete(int cart_id) {
		sqlSession.delete("cart.delete", cart_id); //개별삭제
	}

	@Override
	public void delete_all(String userid) {
		sqlSession.delete("cart.delete_all", userid); //장바구니 비우기
	}

	@Override
	public int sum_money(String userid) {
		return sqlSession.selectOne("cart.sum_money", userid); //합계금액
	}

	@Override
	public void modify(CartDTO dto) {
		sqlSession.update("cart.modify", dto); //수량 변경
	}

}
