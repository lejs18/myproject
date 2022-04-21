package com.example.myproject.model.member;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class SignupDAOImpl implements SignupDAO {
	
	@Inject
	SqlSession sqlSession;

	@Override
	public void insert(MemberDTO dto) {		
		sqlSession.insert("signup.insert", dto);
	}

}
