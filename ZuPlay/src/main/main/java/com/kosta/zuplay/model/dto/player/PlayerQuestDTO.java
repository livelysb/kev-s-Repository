package com.kosta.zuplay.model.dto.player;

public class PlayerQuestDTO {
	int pqSq;				//보유?�스?�� 코드
	String questCode;		//?�스?��코드
	String playerNickname;	//?��?��?��
	int pqDone;				//?��?��?��?��
	
	public PlayerQuestDTO() {}
	public PlayerQuestDTO(int pqSq, String questCode, String playerNickname, int pqDone) {
		super();
		this.pqSq = pqSq;
		this.questCode = questCode;
		this.playerNickname = playerNickname;
		this.pqDone = pqDone;
	}
	public int getPqSq() {
		return pqSq;
	}
	public void setPqSq(int pqSq) {
		this.pqSq = pqSq;
	}
	public String getQuestCode() {
		return questCode;
	}
	public void setQuestCode(String questCode) {
		this.questCode = questCode;
	}
	public String getPlayerNickname() {
		return playerNickname;
	}
	public void setPlayerNickname(String playerNickname) {
		this.playerNickname = playerNickname;
	}
	public int getPqDone() {
		return pqDone;
	}
	public void setPqDone(int pqDone) {
		this.pqDone = pqDone;
	}
	@Override
	public String toString() {
		return "PlayerQuestDTO [pqSq=" + pqSq + ", questCode=" + questCode + ", playerNickname=" + playerNickname
				+ ", pqDone=" + pqDone + "]";
	}
	
	
}