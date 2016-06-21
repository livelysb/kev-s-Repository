package com.kosta.zuplay.model.dto.player;

public class PlayerDTO {
	String playerNickname;	//?‹‰?„¤?„
	String playerNaverId;	//?„¤?´ë²„ì•„?´?””
	String playerGender;	//?„±ë³?
	String playerAge;		//?—°? ¹??
	int playerLike;			//?”Œ? ˆ?´?–´ ì¶”ì²œ?ˆ˜
	int playerMoney;		//ê²Œì„ë¨¸ë‹ˆ
	int playerRuby;			//ë£¨ë¹„(ìºì‰¬)
	String playerGrade;		//ê³„ê¸‰
	int playerDailyRank;	//?¼ê°„ë­?¬
	int playerWeeklyRank;	//ì£¼ê°„?­?¬
	int playerSeasonRank;	//?‹œì¦Œë­?¬
	int playerTotalRank;	//ì¢…í•©?­?¬
	
	public PlayerDTO() {}
	public PlayerDTO(String playerNickname, String playerNaverId, String playerGender, String playerAge, int playerLike,
			int playerMoney, int playerRuby, String playerGrade, int playerDailyRank, int playerWeeklyRank,
			int playerSeasonRank, int playerTotalRank) {
		super();
		this.playerNickname = playerNickname;
		this.playerNaverId = playerNaverId;
		this.playerGender = playerGender;
		this.playerAge = playerAge;
		this.playerLike = playerLike;
		this.playerMoney = playerMoney;
		this.playerRuby = playerRuby;
		this.playerGrade = playerGrade;
		this.playerDailyRank = playerDailyRank;
		this.playerWeeklyRank = playerWeeklyRank;
		this.playerSeasonRank = playerSeasonRank;
		this.playerTotalRank = playerTotalRank;
	}
	public String getPlayerNickname() {
		return playerNickname;
	}
	public void setPlayerNickname(String playerNickname) {
		this.playerNickname = playerNickname;
	}
	public String getPlayerNaverId() {
		return playerNaverId;
	}
	public void setPlayerNaverId(String playerNaverId) {
		this.playerNaverId = playerNaverId;
	}
	public String getPlayerGender() {
		return playerGender;
	}
	public void setPlayerGender(String playerGender) {
		this.playerGender = playerGender;
	}
	public String getPlayerAge() {
		return playerAge;
	}
	public void setPlayerAge(String playerAge) {
		this.playerAge = playerAge;
	}
	public int getPlayerLike() {
		return playerLike;
	}
	public void setPlayerLike(int playerLike) {
		this.playerLike = playerLike;
	}
	public int getPlayerMoney() {
		return playerMoney;
	}
	public void setPlayerMoney(int playerMoney) {
		this.playerMoney = playerMoney;
	}
	public int getPlayerRuby() {
		return playerRuby;
	}
	public void setPlayerRuby(int playerRuby) {
		this.playerRuby = playerRuby;
	}
	public String getPlayerGrade() {
		return playerGrade;
	}
	public void setPlayerGrade(String playerGrade) {
		this.playerGrade = playerGrade;
	}
	public int getPlayerDailyRank() {
		return playerDailyRank;
	}
	public void setPlayerDailyRank(int playerDailyRank) {
		this.playerDailyRank = playerDailyRank;
	}
	public int getPlayerWeeklyRank() {
		return playerWeeklyRank;
	}
	public void setPlayerWeeklyRank(int playerWeeklyRank) {
		this.playerWeeklyRank = playerWeeklyRank;
	}
	public int getPlayerSeasonRank() {
		return playerSeasonRank;
	}
	public void setPlayerSeasonRank(int playerSeasonRank) {
		this.playerSeasonRank = playerSeasonRank;
	}
	public int getPlayerTotalRank() {
		return playerTotalRank;
	}
	public void setPlayerTotalRank(int playerTotalRank) {
		this.playerTotalRank = playerTotalRank;
	}
	@Override
	public String toString() {
		return "PlayerDTO [playerNickname=" + playerNickname + ", playerNaverId=" + playerNaverId + ", playerGender="
				+ playerGender + ", playerAge=" + playerAge + ", playerLike=" + playerLike + ", playerMoney="
				+ playerMoney + ", playerRuby=" + playerRuby + ", playerGrade=" + playerGrade + ", playerDailyRank="
				+ playerDailyRank + ", playerWeeklyRank=" + playerWeeklyRank + ", playerSeasonRank=" + playerSeasonRank
				+ ", playerTotalRank=" + playerTotalRank + "]";
	}
	
	
}
