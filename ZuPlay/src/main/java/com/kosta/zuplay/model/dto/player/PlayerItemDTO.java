package com.kosta.zuplay.model.dto.player;

import com.kosta.zuplay.model.dto.item.ItemDTO;

public class PlayerItemDTO {
	public PlayerItemDTO(int piSq, String playerNickname, String itemCode, int piIndex, ItemDTO itemDTO) {
		super();
		this.piSq = piSq;
		this.playerNickname = playerNickname;
		this.itemCode = itemCode;
		this.piIndex = piIndex;
		this.itemDTO = itemDTO;
	}

	private int piSq;				//보유아이템코드
	private String playerNickname;	//닉네임
	private String itemCode;		//아이템코드
	private int piIndex;			//인벤토리 위치 인덱스
	
	private ItemDTO itemDTO; //1:1 조인
	
	public PlayerItemDTO() {}

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

	public int getPiIndex() {
		return piIndex;
	}

	public void setPiIndex(int piIndex) {
		this.piIndex = piIndex;
	}

	public ItemDTO getItemDTO() {
		return itemDTO;
	}

	public void setItemDTO(ItemDTO itemDTO) {
		this.itemDTO = itemDTO;
	}

	@Override
	public String toString() {
		return "PlayerItemDTO [piSq=" + piSq + ", playerNickname=" + playerNickname + ", itemCode=" + itemCode
				+ ", piIndex=" + piIndex + ", itemDTO=" + itemDTO + "]";
	}
	
	
	
}
