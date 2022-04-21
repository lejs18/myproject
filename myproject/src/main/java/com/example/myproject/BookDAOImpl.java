package com.example.myproject;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class BookDAOImpl implements BookDAO {
	
	@Inject
	SqlSession sqlSession;

	@Override
	public List<BookDTO> list() {		
		return sqlSession.selectList("book.list");
	}

}
