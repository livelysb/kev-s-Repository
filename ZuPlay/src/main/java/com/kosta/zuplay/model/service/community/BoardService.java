package com.kosta.zuplay.model.service.community;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kosta.zuplay.model.dto.board.BoardCommentDTO;
import com.kosta.zuplay.model.dto.board.BoardDTO;

public interface BoardService {
	
	boolean insertBoard(BoardDTO dto) throws Exception;
	boolean updateBoard(BoardDTO dto) throws Exception;
	BoardDTO selectDetail(int boardNo) throws Exception;
	List<BoardDTO> selectAll() throws Exception;
	boolean deleteBoard(String playerNickname, int boardNo) throws Exception;
	boolean insertComment(BoardCommentDTO dto) throws Exception;
	boolean updateComment(BoardCommentDTO dto) throws Exception;
	List<BoardCommentDTO> selectComment(int boardNo) throws Exception;
	boolean deleteComment(String playerNickname,int bcSq) throws Exception;
}
