package com.kosta.zuplay.model.dto;

public class BoardImageDTO {
	int biSq;			//이미지 번호
	int boardNo;		//이미지 삽입된 글번호
	String biOrigin;	//원본이미지 파일명
	String biName;		//변경이미지 파일명
	
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
