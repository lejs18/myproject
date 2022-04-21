package com.example.myproject.model.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class ReplyDAOImpl implements ReplyDAO {

	@Inject
	SqlSession sqlSession;
	
	@Override
	public List<ReplyDTO> list(int board_idx, int start, int end) {
		Map<String,Object> map=new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		map.put("board_idx", board_idx);
		List<ReplyDTO> items=sqlSession.selectList("reply.list", map);
		return items;
	}

	@Override
	public int count(int board_idx) {
		return sqlSession.selectOne("reply.count", board_idx);
	}

	@Override
	public void insert(ReplyDTO dto) {
		sqlSession.insert("reply.insert", dto);
	}

	@Override
	public void update(ReplyDTO dto) {
		sqlSession.update("reply.update", dto);
	}

	@Override
	public void delete(int idx) {
		sqlSession.delete("reply.delete", idx);
	}

	@Override
	public ReplyDTO detail(int idx) {
		return sqlSession.selectOne("reply.detail", idx);
	}

}
