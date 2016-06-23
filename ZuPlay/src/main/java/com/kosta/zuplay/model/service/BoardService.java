package com.kosta.zuplay.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kosta.zuplay.model.dto.board.BoardCommentDTO;
import com.kosta.zuplay.model.dto.board.BoardDTO;

public interface BoardService {
	
	boolean insertBoard(BoardDTO dto);
	boolean updateBoard(BoardDTO dto);
	BoardDTO selectDetail(int boardNo);
	List<BoardDTO> selectAll();
	boolean deleteBoard(String playerNickname, int boardNo);
	boolean insertComment(BoardCommentDTO dto);
	boolean updateComment(BoardCommentDTO dto);
	List<BoardCommentDTO> selectComment(int boardNo);
	boolean deleteComment(String playerNickname,int bcSq);
}
