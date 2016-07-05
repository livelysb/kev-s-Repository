package com.kosta.zuplay.model.dto.player;

import org.springframework.stereotype.Component;


@Component("playerDTO")
public class PlayerDTO {
	private String playerNickname;	//닉네임
	private String playerNaverId;	//네이버아이디
	private String playerGender;	//성별
	private String playerAge;		//연령대
	private int playerMoney;		//게임머니
	private int playerPreMoney;		//전일 게임머니
	private int playerRuby;			//루비(캐쉬)
	private String playerLastAccess;//최종접속일
	private String playerGrade;		//계급
	private int playerDailyRank;	//일간랭킹
	private int playerSeasonRank;	//시즌랭킹
	
	private int totalMoney;
	private double earningRate; //수익률
	private PlayerItemDTO playerItemDTO;
	
	
	public PlayerDTO() {}
	
	public PlayerDTO(String playerNickname, String playerNaverId, String playerGender, String playerAge,
			int playerMoney, int playerPreMoney, int playerRuby, String playerGrade, int playerDailyRank,
			int playerWeeklyRank, int playerSeasonRank, int playerTotalRank) {
		super();
		this.playerNickname = playerNickname;
		this.playerNaverId = playerNaverId;
		this.playerGender = playerGender;
		this.playerAge = playerAge;
		this.playerMoney = playerMoney;
		this.playerPreMoney = playerPreMoney;
		this.playerRuby = playerRuby;
		this.playerGrade = playerGrade;
		this.playerDailyRank = playerDailyRank;
		this.playerSeasonRank = playerSeasonRank;
	}
	
	
	public int getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(int totalMoney) {
		this.totalMoney = totalMoney;
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

	public int getPlayerMoney() {
		return playerMoney;
	}
	public void setPlayerMoney(int playerMoney) {
		this.playerMoney = playerMoney;
	}
	public int getPlayerPreMoney() {
		return playerPreMoney;
	}
	public void setPlayerPreMoney(int playerPreMoney) {
		this.playerPreMoney = playerPreMoney;
	}
	public int getPlayerRuby() {
		return playerRuby;
	}
	public void setPlayerRuby(int playerRuby) {
		this.playerRuby = playerRuby;
	}
	public String getPlayerLastAccess() {
		return playerLastAccess;
	}
	public void setPlayerLastAccess(String playerLastAccess) {
		this.playerLastAccess = playerLastAccess;
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
	public int getPlayerSeasonRank() {
		return playerSeasonRank;
	}
	public void setPlayerSeasonRank(int playerSeasonRank) {
		this.playerSeasonRank = playerSeasonRank;
	}
	public double getEarningRate() {
		return earningRate;
	}
	public void setEarningRate(double earningRate) {
		this.earningRate = earningRate;
	}

	public PlayerItemDTO getPlayerItemDTO() {
		return playerItemDTO;
	}

	public void setPlayerItemDTO(PlayerItemDTO playerItemDTO) {
		this.playerItemDTO = playerItemDTO;
	}

	
}
