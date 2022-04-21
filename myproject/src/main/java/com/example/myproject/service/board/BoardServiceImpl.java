package com.example.myproject.service.board;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.example.myproject.model.board.BoardDAO;
import com.example.myproject.model.board.BoardDTO;

@Service // service bean
public class BoardServiceImpl implements BoardService {

	@Inject
	BoardDAO boardDao;
	
	@Override
	public List<BoardDTO> list(int start, int end, String search_option, String keyword) {
		return boardDao.list(start, end, search_option, keyword);
	}

	@Transactional //트랜잭션 처리 
	@Override
	public void insert(BoardDTO dto) {
		boardDao.insert(dto); //board 저장
		String[] files=dto.getFiles();
		if(files==null) return;
		for(String name : files) {
			boardDao.insert_attach(name); //attach 저장
		}
	}

	@Override
	public BoardDTO detail(int idx) {
		return boardDao.detail(idx);
	}

	@Override
	public void increase_hit(int idx) {
		boardDao.increase_hit(idx);
	}

	@Transactional
	@Override
	public void update(BoardDTO dto) {
		boardDao.update(dto);
		String[] files=dto.getFiles();
		if(files==null) return;
		for(String name : files) {
			boardDao.update_attach(name, dto.getIdx());
		}
	}

	@Override
	public void delete(int idx) {
		boardDao.delete(idx);
	}

	@Override
	public int count(String search_option, String keyword) {
		return boardDao.count(search_option, keyword);
	}

	@Override
	public List<String> list_attach(int idx) {
		return boardDao.list_attach(idx);
	}

	@Override
	public void delete_attach(String file_name) {
		boardDao.delete_attach(file_name);
	}

}
