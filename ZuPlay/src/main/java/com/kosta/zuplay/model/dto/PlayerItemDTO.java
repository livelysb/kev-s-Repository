package com.kosta.zuplay.model.dto;

public class PlayerItemDTO {
	int piSq;
	String playerNickname;
	String itemCode;
	String piIsused;
	int piIndex;
	
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
