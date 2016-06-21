package com.kosta.zuplay.model.dto;

public class ItemMarketDTO {
	int imSq;				//경매장 등록코드
	String playerNickname;	//등록자
	String itemCode;		//아이템 코드
	int imPurchasePrice;	//판매가
	String imBidTime;		//입찰시간
	
	public ItemMarketDTO() {}
	public ItemMarketDTO(int imSq, String playerNickname, String itemCode, int imPurchasePrice, String imBidTime) {
		super();
		this.imSq = imSq;
		this.playerNickname = playerNickname;
		this.itemCode = itemCode;
		this.imPurchasePrice = imPurchasePrice;
		this.imBidTime = imBidTime;
	}
	public int getImSq() {
		return imSq;
	}
	public void setImSq(int imSq) {
		this.imSq = imSq;
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
	public int getImPurchasePrice() {
		return imPurchasePrice;
	}
	public void setImPurchasePrice(int imPurchasePrice) {
		this.imPurchasePrice = imPurchasePrice;
	}
	public String getImBidTime() {
		return imBidTime;
	}
	public void setImBidTime(String imBidTime) {
		this.imBidTime = imBidTime;
	}
	@Override
	public String toString() {
		return "ItemMarketDTO [imSq=" + imSq + ", playerNickname=" + playerNickname + ", itemCode=" + itemCode
				+ ", imPurchasePrice=" + imPurchasePrice + ", imBidTime=" + imBidTime + "]";
	}
	
	
	
}
