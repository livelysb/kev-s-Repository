package com.kosta.zuplay.model.service.community;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.zuplay.model.dao.BoardDAO;
import com.kosta.zuplay.model.dto.board.BoardCommentDTO;
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
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		int result = dao.insertBoard(dto);
		if (result == 0) {
			return false;
		}
		return true;
	}

	/**
	 * 게시글 수정
	 */
	@Override
	public boolean updateBoard(BoardDTO dto) {
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		String nicknameInDB = dao.boardNicknameCheck(dto.getBoardNo());
		System.out.println("[ LOG ] : " + dto.getBoardNo() + " 번 게시글의 작성자 : " + nicknameInDB + " 서비스 요청자 : "
				+ dto.getPlayerNickname());
		if (dto.getPlayerNickname().equals(nicknameInDB)) {
			int result = dao.updateBoard(dto);
			if (result != 0) {
				System.out.println("[ LOG ] : 게시글 수정 성공");
				return true;
			}
			System.out.println("[ LOG ] : 게시글 수정 실패");
		} else {
			System.out.println("[ LOG ] : 게시글 작성자가 아닙니다.");

		}
		return false;
	}

	/**
	 * 게시글 디테일 뷰
	 */
	@Override
	public BoardDTO selectDetail(int boardNo) {
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		return dao.selectDetail(boardNo);
	}

	/**
	 * 게시글 리스트 뷰
	 */
	@Override
	public List<BoardDTO> selectAll() {
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		List<BoardDTO> list = dao.selectAll();
		System.out.println(list);
		return list;
	}

	/**
	 * 게시글 삭제
	 */
	@Override
	public boolean deleteBoard(String playerNickname, int boardNo) {
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		String nicknameInDB = dao.boardNicknameCheck(boardNo);
		System.out.println("[ LOG ] : " + boardNo + " 번 게시글의 작성자 : " + nicknameInDB + " 서비스 요청자 : " + playerNickname);
		if (playerNickname.equals(nicknameInDB)) {
			int result = dao.deleteBoard(playerNickname, boardNo);
			if (result != 0) {
				System.out.println("[ LOG ] : 게시글 삭제 성공");
				return true;
			}
			System.out.println("[ LOG ] : 게시글 삭제 실패");
		} else {
			System.out.println("[ LOG ] : 게시글 작성자가 아닙니다.");
		}
		return false;

	}

	/**
	 * 코멘트 삽입
	 */
	@Override
	public boolean insertComment(BoardCommentDTO dto) {
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		int result = dao.insertComment(dto);
		if (result == 0) {
			return false;
		}
		return true;
	}

	/**
	 * 코멘트 수정
	 */
	@Override
	public boolean updateComment(BoardCommentDTO dto) {
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		String nicknameInDB = dao.CommentNicknameCheck(dto.getBcSq());
		System.out.println(
				"[ LOG ] : " + dto.getBcSq() + " 번 댓글 작성자 : " + nicknameInDB + " 서비스 요청자 : " + dto.getPlayerNickname());
		if (dto.getPlayerNickname().equals(nicknameInDB)) {
			int result = dao.updateComment(dto);
			if (result != 0) {
				System.out.println("[ LOG ] : 댓글 수정 성공");
				return true;
			}
			System.out.println("[ LOG ] : 댓글 수정 실패");
		} else {
			System.out.println("[ LOG ] : 댓글 작성자가 아닙니다.");

		}
		return false;

	}

	/**
	 * 코멘트 조회
	 */
	@Override
	public List<BoardCommentDTO> selectComment(int boardNo) {
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		return dao.selectComment(boardNo);
	}

	/**
	 * 코멘트 삭제
	 */
	@Override
	public boolean deleteComment(String playerNickname, int bcSq) {
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		String nicknameInDB=dao.CommentNicknameCheck(bcSq);
		System.out.println("[ LOG ] : " + bcSq + " 번 댓글 작성자 : " + nicknameInDB + " 서비스 요청자 : " + playerNickname);
		if(playerNickname.equals(nicknameInDB)){
			int result=dao.deleteComment(bcSq);
			if(result!=0){
				System.out.println("[ LOG ] : 댓글 삭제 성공");
				return true;
			}
			System.out.println("[ LOG ] : 댓글 삭제 실패");
		}else{
			System.out.println("[ LOG ] : 댓글 작성자가 아닙니다.");
		}
		
		return false;
	}

}
