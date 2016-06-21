package com.kosta.zuplay.model.dto.board;

public class BoardDTO {
	int boardNo;			//게시글 번호
	String playerNickname;	//닉네임
	String boardTitle;		//글제목
	int boardLike;			//좋아요 갯수
	String boardContent;	//본문 내용
	String boardTime;		//작성시각
	int boardHits;			//조회수
	
	public BoardDTO() {}
	public BoardDTO(int boardNo, String playerNickname, String boardTitle, int boardLike, String boardContent,
			String boardTime, int boardHits) {
		super();
		this.boardNo = boardNo;
		this.playerNickname = playerNickname;
		this.boardTitle = boardTitle;
		this.boardLike = boardLike;
		this.boardContent = boardContent;
		this.boardTime = boardTime;
		this.boardHits = boardHits;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getPlayerNickname() {
		return playerNickname;
	}
	public void setPlayerNickname(String playerNickname) {
		this.playerNickname = playerNickname;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public int getBoardLike() {
		return boardLike;
	}
	public void setBoardLike(int boardLike) {
		this.boardLike = boardLike;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getBoardTime() {
		return boardTime;
	}
	public void setBoardTime(String boardTime) {
		this.boardTime = boardTime;
	}
	public int getBoardHits() {
		return boardHits;
	}
	public void setBoardHits(int boardHits) {
		this.boardHits = boardHits;
	}
	@Override
	public String toString() {
		return "BoardDTO [boardNo=" + boardNo + ", playerNickname=" + playerNickname + ", boardTitle=" + boardTitle
				+ ", boardLike=" + boardLike + ", boardContent=" + boardContent + ", boardTime=" + boardTime
				+ ", boardHits=" + boardHits + "]";
	}
	
	
}
