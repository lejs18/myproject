package com.example.myproject.model.member;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Inject
	SqlSession sqlSession;
	
	@Override
	public String login(MemberDTO dto) {		
		return sqlSession.selectOne("member.login", dto);
	}
	
	

}
