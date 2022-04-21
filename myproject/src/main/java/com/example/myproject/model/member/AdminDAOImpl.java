package com.example.myproject.model.member;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository //서버가 켜질 때 메모리에 올라감  
public class AdminDAOImpl implements AdminDAO {

	@Inject
	SqlSession sqlSession; // root-context.xml에 선언한 sqlSession bean 연결
	
	@Override
	public String login(MemberDTO dto) {
		//네임스페이스 admin, id : login인 쿼리 호출 
		return sqlSession.selectOne("admin.login", dto);
	}

}
