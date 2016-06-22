package com.kosta.zuplay.model.dto.board;

public class BoardImageDTO {
	int biSq;			//?´ë¯¸ì? ë²ˆí˜¸
	int boardNo;		//?´ë¯¸ì? ?‚½?…?œ ê¸?ë²ˆí˜¸
	String biOrigin;	//?›ë³¸ì´ë¯¸ì? ?ŒŒ?¼ëª?
	String biName;		//ë³?ê²½ì´ë¯¸ì? ?ŒŒ?¼ëª?
	
	public BoardImageDTO() {}
	public BoardImageDTO(int biSq, int boardNo, String biOrigin, String biName) {
		super();
		this.biSq = biSq;
		this.boardNo = boardNo;
		this.biOrigin = biOrigin;
		this.biName = biName;
	}
	public int getBiSq() {
		return biSq;
	}
	public void setBiSq(int biSq) {
		this.biSq = biSq;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getBiOrigin() {
		return biOrigin;
	}
	public void setBiOrigin(String biOrigin) {
		this.biOrigin = biOrigin;
	}
	public String getBiName() {
		return biName;
	}
	public void setBiName(String biName) {
		this.biName = biName;
	}
	@Override
	public String toString() {
		return "BoardImageDTO [biSq=" + biSq + ", boardNo=" + boardNo + ", biOrigin=" + biOrigin + ", biName=" + biName
				+ "]";
	}
	
	
	
}
