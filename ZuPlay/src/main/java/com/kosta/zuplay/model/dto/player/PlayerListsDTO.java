package com.kosta.zuplay.model.dto.player;

public class PlayerListsDTO {
	int plSq;				//보유주식코드
	String isuCd;			//종목코드
	String playerNickname;	//닉네임
	int plQuantity;			//수량
	
	public PlayerListsDTO() {}

	public PlayerListsDTO(int plSq, String isuCd, String playerNickname, int plQuantity) {
		super();
		this.plSq = plSq;
		this.isuCd = isuCd;
		this.playerNickname = playerNickname;
		this.plQuantity = plQuantity;
	}

	public int getPlSq() {
		return plSq;
	}

	public void setPlSq(int plSq) {
		this.plSq = plSq;
	}

	public String getIsuCd() {
		return isuCd;
	}

	public void setIsuCd(String isuCd) {
		this.isuCd = isuCd;
	}

	public String getPlayerNickname() {
		return playerNickname;
	}

	public void setPlayerNickname(String playerNickname) {
		this.playerNickname = playerNickname;
	}

	public int getPlQuantity() {
		return plQuantity;
	}

	public void setPlQuantity(int plQuantity) {
		this.plQuantity = plQuantity;
	}

	@Override
	public String toString() {
		return "PlayerListsDTO [plSq=" + plSq + ", isuCd=" + isuCd + ", playerNickname=" + playerNickname
				+ ", plQuantity=" + plQuantity + "]";
	}
	
	
	
}
