package com.kosta.zuplay.model.dto.item;

public class ItemMarketDTO {
	int imSq;				//경매?�� ?��록코?��
	String playerNickname;	//?��록자
	String itemCode;		//?��?��?�� 코드
	int imPurchasePrice;	//?��매�?
	String imBidTime;		//?��찰시�?
	
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
