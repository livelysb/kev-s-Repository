package com.kosta.zuplay.model.dto.player;

public class PlayerListsDTO {
	int plSq;				//ë³´ìœ ì£¼ì‹ì½”ë“œ
	String isuCd;			//ì¢…ëª©ì½”ë“œ
	String playerNickname;	//?‹‰?„¤?„
	int plQuantity;			//?ˆ˜?Ÿ‰
	
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
