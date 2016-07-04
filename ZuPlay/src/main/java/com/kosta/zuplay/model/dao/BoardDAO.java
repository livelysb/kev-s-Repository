package com.kosta.zuplay.model.dao;

import java.util.List;

import com.kosta.zuplay.model.dto.board.BoardCommentDTO;
import com.kosta.zuplay.model.dto.board.BoardDTO;

public interface BoardDAO {
	int insertBoard(BoardDTO dto) throws Exception;
	int updateBoard(BoardDTO dto) throws Exception;
	BoardDTO selectDetail(int boardNo) throws Exception;
	List<BoardDTO> selectAll() throws Exception;
	int deleteBoard(String playerNickname, int boardNo) throws Exception;
	String boardNicknameCheck(int boardNo) throws Exception;
	String CommentNicknameCheck(int bcSq) throws Exception;
	int insertComment(BoardCommentDTO dto) throws Exception;
	int updateComment(BoardCommentDTO dto) throws Exception;
	List<BoardCommentDTO> selectComment(int boardNo) throws Exception;
	int deleteComment(int bcSq) throws Exception;
}
