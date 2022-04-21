package com.example.myproject.model.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Inject
	SqlSession sqlSession;
	
	@Override
	public List<BoardDTO> list(int start, int end, String search_option, String keyword) {
		Map<String,Object> map=new HashMap<>();
		map.put("search_option", search_option); //검색옵션
		map.put("keyword", keyword); //검색키워드
		map.put("start", start); //레코드 시작번호
		map.put("end", end); //레코드 끝번호
		return sqlSession.selectList("board.list", map);
	}

	@Override
	public void insert(BoardDTO dto) {
		sqlSession.insert("board.insert", dto);
	}

	@Override
	public BoardDTO detail(int idx) {
		return sqlSession.selectOne("board.detail", idx);
	}

	@Override
	public void increase_hit(int idx) {
		sqlSession.update("board.increase_hit", idx); //조회수 증가처리
	}

	@Override
	public void update(BoardDTO dto) {
		sqlSession.update("board.update", dto);
	}

	@Override
	public void delete(int idx) {
		sqlSession.delete("board.delete", idx);
	}

	@Override
	public int count(String search_option, String keyword) {
		Map<String,Object> map=new HashMap<>();
		map.put("search_option", search_option);
		map.put("keyword", keyword);
		return sqlSession.selectOne("board.count", map); //레코드 개수 리턴 
	}

	@Override
	public List<String> list_attach(int idx) {
		return sqlSession.selectList("board.list_attach", idx); //첨부파일 목록 
	}

	@Override
	public void insert_attach(String file_name) {
	//public void insert_attach(String file_name, int idx) {
//		Map<String,Object> map=new HashMap<>();
//		map.put("file_name", file_name);
//		map.put("idx", idx);
		//System.out.println("map:"+map);
		//sqlSession.insert("board.insert_attach", map); //첨부파일 추가 
		sqlSession.insert("board.insert_attach",file_name); //첨부파일 추가
	}

	@Override
	public void update_attach(String file_name, int idx) {
		Map<String,Object> map=new HashMap<>();
		map.put("file_name", file_name);
		map.put("idx", idx);
		sqlSession.insert("board.update_attach", map); //첨부파일 추가 
	}

	@Override
	public void delete_attach(String file_name) {
		sqlSession.delete("board.delete_attach", file_name);
	}

	@Override
	public int getCurrentIdx() {
		return sqlSession.selectOne("board.get_current_idx");
	}

}
