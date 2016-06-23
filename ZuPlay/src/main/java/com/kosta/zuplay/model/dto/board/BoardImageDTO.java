package com.kosta.zuplay.model.dto.board;

public class BoardImageDTO {
	private int biSq;			//이미지 고유 코드
	private int boardNo;		//이미지 등록글 번호
	private String biOrigin;	//원본 이미지 파일명
	private String biName;		//이미지 url
	
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
