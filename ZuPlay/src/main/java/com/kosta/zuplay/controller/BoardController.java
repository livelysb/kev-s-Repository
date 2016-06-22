package com.kosta.zuplay.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosta.zuplay.model.dto.board.BoardDTO;

@Controller
public class BoardController {
	/**
	 * 게시판 글 작성
	 * @param dto
	 * @return
	 */
	@RequestMapping("insertBoard")
	@ResponseBody
	public boolean insertBoard(BoardDTO dto){
		return true;
	}
	/**
	 * 게시판 글 수정
	 * @param dto
	 * @return
	 */
	@RequestMapping("updateBoard")
	@ResponseBody
	public boolean updateBoard(BoardDTO dto){
		return true;
	}
	/**
	 * 게시판 디테일뷰
	 * @param boardNo
	 * @return
	 */
	@RequestMapping("selectDetail")
	@ResponseBody
	public BoardDTO selectDetail(int boardNo){
		return null;
	}
	/**
	 * 게시판 리스트 뷰
	 * @return
	 */
	@RequestMapping("selectAll")
	@ResponseBody
	public List<BoardDTO> selectAll(){
		return null;
	}
	/**
	 * 게시판 글 삭제
	 * @param playerNickname
	 * @param boardNo
	 * @return
	 */
	@RequestMapping("deleteBoard")
	@ResponseBody
	public boolean deleteBoard(String playerNickname, int boardNo){
		return true;
	}
}
