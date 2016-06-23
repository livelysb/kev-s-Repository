package com.kosta.zuplay.model.dto.board;

public class BoardCommentDTO {
	private int bcSq;					//코멘트번호
	private String playerNickname;		//닉네임
	private int boardNo;				//본문 글번호
	private String bcCommentTime;		//코멘트 작성시각
	private String bcCommentContent;	//코멘트 내용
	
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
