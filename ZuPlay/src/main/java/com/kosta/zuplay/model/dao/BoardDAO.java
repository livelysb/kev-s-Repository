package com.kosta.zuplay.model.dao;

import java.util.List;

import com.kosta.zuplay.model.dto.board.BoardCommentDTO;
import com.kosta.zuplay.model.dto.board.BoardDTO;

public interface BoardDAO {
	int insertBoard(BoardDTO dto);
	int updateBoard(BoardDTO dto);
	BoardDTO selectDetail(int boardNo);
	List<BoardDTO> selectAll();
	int deleteBoard(String playerNickname, int boardNo);
	String boardNicknameCheck(int boardNo);
	String CommentNicknameCheck(int bcSq);
	int insertComment(BoardCommentDTO dto);
	int updateComment(BoardCommentDTO dto);
	List<BoardCommentDTO> selectComment(int boardNo);
	int deleteComment(int bcSq);
}
