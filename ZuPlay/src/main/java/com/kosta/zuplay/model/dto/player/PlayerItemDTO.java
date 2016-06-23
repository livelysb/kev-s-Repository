package com.kosta.zuplay.model.dto.player;

public class PlayerItemDTO {
	private int piSq;				//보유아이템코드
	private String playerNickname;	//닉네임
	private String itemCode;		//아이템코드
	private String piIsused;		//착용여부
	private int piIndex;			//인벤토리 위치 인덱스
	
	public PlayerItemDTO() {}
	public PlayerItemDTO(int piSq, String playerNickname, String itemCode, String piIsused, int piIndex) {
		super();
		this.piSq = piSq;
		this.playerNickname = playerNickname;
		this.itemCode = itemCode;
		this.piIsused = piIsused;
		this.piIndex = piIndex;
	}
	public int getPiSq() {
		return piSq;
	}
	public void setPiSq(int piSq) {
		this.piSq = piSq;
	}
	public String getPlayerNickname() {
		return playerNickname;
	}
	public void setPlayerNickname(String playerNickname) {
		this.playerNickname = playerNickname;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getPiIsused() {
		return piIsused;
	}
	public void setPiIsused(String piIsused) {
		this.piIsused = piIsused;
	}
	public int getPiIndex() {
		return piIndex;
	}
	public void setPiIndex(int piIndex) {
		this.piIndex = piIndex;
	}
	@Override
	public String toString() {
		return "PlayerItemDTO [piSq=" + piSq + ", playerNickname=" + playerNickname + ", itemCode=" + itemCode
				+ ", piIsused=" + piIsused + ", piIndex=" + piIndex + "]";
	}
	
	
}
