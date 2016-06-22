package com.kosta.zuplay.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.zuplay.model.dao.BoardDAO;
import com.kosta.zuplay.model.dto.board.BoardDTO;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private SqlSession sqlSession;
	
	/**
	 * 게시글 삽입
	 */
	@Override
	public boolean insertBoard(BoardDTO dto) {
		BoardDAO dao=sqlSession.getMapper(BoardDAO.class);
		int result=dao.insertBoard(dto);
		if(result==0){
			return false;
		}
		return true;
	}
	/**
	 * 게시글 수정
	 */
	@Override
	public boolean updateBoard(BoardDTO dto) {
		BoardDAO dao=sqlSession.getMapper(BoardDAO.class);
		int result=dao.updateBoard(dto);
		if(result==0){
			return false;
		}
		return true;
	}
	/**
	 * 게시글 디테일 뷰
	 */
	@Override
	public BoardDTO selectDetail(int boardNo) {
		BoardDAO dao=sqlSession.getMapper(BoardDAO.class);
		return dao.selectDetail(boardNo);
	}
	/**
	 * 게시글 리스트 뷰
	 */
	@Override
	public List<BoardDTO> selectAll() {
		BoardDAO dao=sqlSession.getMapper(BoardDAO.class);
		return dao.selectAll();
	}
	/**
	 * 게시글 삭제
	 */
	@Override
	public boolean deleteBoard(String playerNickname, int boardNo) {
		BoardDAO dao=sqlSession.getMapper(BoardDAO.class);
		int result=dao.deleteBoard(playerNickname, boardNo);
		if(result==0){
			return false;
		}
		return true;
	}

}
