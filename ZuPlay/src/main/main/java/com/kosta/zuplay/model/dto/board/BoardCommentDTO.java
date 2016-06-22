package com.kosta.zuplay.model.dto.board;

public class BoardCommentDTO {
	int bcSq;					//코멘?�� 번호
	String playerNickname;		//?��?��?��
	int boardNo;				//본문 �?번호
	String bcCommentTime;		//코멘?�� ?��?��?���?
	String bcCommentContent;	//코멘?�� ?��?��
	
	public BoardCommentDTO() {}
	
	public BoardCommentDTO(int bcSq, String playerNickname, int boardNo, String bcCommentTime,
			String bcCommentContent) {
		super();
		this.bcSq = bcSq;
		this.playerNickname = playerNickname;
		this.boardNo = boardNo;
		this.bcCommentTime = bcCommentTime;
		this.bcCommentContent = bcCommentContent;
	}

	public int getBcSq() {
		return bcSq;
	}
	public void setBcSq(int bcSq) {
		this.bcSq = bcSq;
	}
	public String getPlayerNickname() {
		return playerNickname;
	}
	public void setPlayerNickname(String playerNickname) {
		this.playerNickname = playerNickname;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getBcCommentTime() {
		return bcCommentTime;
	}
	public void setBcCommentTime(String bcCommentTime) {
		this.bcCommentTime = bcCommentTime;
	}
	public String getBcCommentContent() {
		return bcCommentContent;
	}
	public void setBcCommentContent(String bcCommentContent) {
		this.bcCommentContent = bcCommentContent;
	}

	@Override
	public String toString() {
		return "BoardCommentDTO [bcSq=" + bcSq + ", playerNickname=" + playerNickname + ", boardNo=" + boardNo
				+ ", bcCommentTime=" + bcCommentTime + ", bcCommentContent=" + bcCommentContent + "]";
	}
	
	
}