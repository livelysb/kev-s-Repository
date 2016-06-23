package com.kosta.zuplay.model.dto.player;

import org.springframework.stereotype.Component;

@Component("playerDTO")
public class PlayerDTO {
	private String playerNickname;	//닉네임
	private String playerNaverId;	//네이버아이디
	private String playerGender;	//성별
	private String playerAge;		//연령대
	private int playerLike;			//캐릭터 좋아요 갯수
	private int playerMoney;		//게임머니
	private int playerRuby;			//루비(캐쉬)
	private String playerGrade;		//계급
	private int playerDailyRank;	//일간랭킹
	private int playerWeeklyRank;	//주간랭킹
	private int playerSeasonRank;	//시즌랭킹
	private int playerTotalRank;	//종합랭킹
	
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
