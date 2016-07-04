package com.kosta.zuplay.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.zuplay.model.dto.board.BoardCommentDTO;
import com.kosta.zuplay.model.dto.board.BoardDTO;
import com.kosta.zuplay.model.service.community.BoardService;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardServiceImpl;
	
	/**
	 * 게시글 작성 페이지 이동
	 */
	@RequestMapping("write")
	public String write(){
		return "writeBoard";
	}
	
	/**
	 * 게시판 글 작성
	 * @param dto
	 * @return
	 */
	@RequestMapping("insertBoard")
	public String insertBoard(HttpSession session,BoardDTO dto) throws Exception{
		dto.setPlayerNickname((String) session.getAttribute("playerNickname"));
		try {
			boardServiceImpl.insertBoard(dto);
		} catch (Exception e) {
			session.setAttribute("errorMsg", e.toString());
			e.printStackTrace();
			throw new Exception();
		}
		return "selectAll";
	}
	/**
	 * 게시판 글 수정
	 * @param dto
	 * @return
	 */
	@RequestMapping("updateBoard")
	@ResponseBody
	public boolean updateBoard(HttpSession session, BoardDTO dto) throws Exception{
		try {
			return boardServiceImpl.updateBoard(dto);
		} catch (Exception e) {
			session.setAttribute("errorMsg", e.toString());
			e.printStackTrace();
			throw new Exception();
		}
	}
	/**
	 * 게시판 디테일뷰
	 * @param boardNo
	 * @return
	 */
	@RequestMapping("selectDetail")
	@ResponseBody
	public BoardDTO selectDetail(HttpSession session, int boardNo) throws Exception{
		try {
			return boardServiceImpl.selectDetail(boardNo);
		} catch (Exception e) {
			session.setAttribute("errorMsg", e.toString());
			e.printStackTrace();
			throw new Exception();
		}
	}
	/**
	 * 게시판 리스트 뷰
	 * @return
	 */
	@RequestMapping("selectAll")
	public ModelAndView selectAll(HttpSession session) throws Exception{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("selectAll");
		try {
			mv.addObject("list", boardServiceImpl.selectAll());
		} catch (Exception e) {
			session.setAttribute("errorMsg", e.toString());
			e.printStackTrace();
			throw new Exception();
		}
		return mv;
	}
	/**
	 * 게시판 글 삭제
	 * @param playerNickname
	 * @param boardNo
	 * @return
	 */
	@RequestMapping("deleteBoard")
	@ResponseBody
	public boolean deleteBoard(HttpSession session, String playerNickname, int boardNo) throws Exception{
		try {
			return boardServiceImpl.deleteBoard(playerNickname, boardNo);
		} catch (Exception e) {
			session.setAttribute("errorMsg", e.toString());
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	/**
	 * 코멘트 쓰기
	 * @param boardCommentDTO
	 * @return
	 */
	@RequestMapping("insertComment")
	@ResponseBody
	public boolean insertComment(HttpSession session, BoardCommentDTO boardCommentDTO) throws Exception{
		try {
			return boardServiceImpl.insertComment(boardCommentDTO);
		} catch (Exception e) {
			session.setAttribute("errorMsg", e.toString());
			e.printStackTrace();
			throw new Exception();
		}
	}
	/**
	 * 코멘트 수정
	 * @param dto
	 * @return
	 */
	@RequestMapping("updateComment")
	@ResponseBody
	public boolean updateComment(HttpSession session, BoardCommentDTO dto) throws Exception{
		try {
			return boardServiceImpl.updateComment(dto);
		} catch (Exception e) {
			session.setAttribute("errorMsg", e.toString());
			e.printStackTrace();
			throw new Exception();
		}
	}
	/**
	 * 코멘트 읽기
	 * @param boardNo
	 * @return
	 */
	@RequestMapping("selectComment")
	@ResponseBody
	public List<BoardCommentDTO> selectComment(HttpSession session, int boardNo) throws Exception{
		try {
			return boardServiceImpl.selectComment(boardNo);
		} catch (Exception e) {
			session.setAttribute("errorMsg", e.toString());
			e.printStackTrace();
			throw new Exception();
		}
	}
	/**
	 * 코멘트 삭제
	 * @param playerNickname
	 * @param bcSq
	 * @return
	 */
	@RequestMapping("deleteComment")
	@ResponseBody
	public boolean deleteComment(HttpSession session, String playerNickname,int bcSq) throws Exception{
		try {
			return boardServiceImpl.deleteComment(playerNickname, bcSq);
		} catch (Exception e) {
			session.setAttribute("errorMsg", e.toString());
			e.printStackTrace();
			throw new Exception();
		}
	}
}
