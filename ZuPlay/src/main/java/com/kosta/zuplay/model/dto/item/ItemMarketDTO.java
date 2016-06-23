package com.kosta.zuplay.model.dto.item;

public class ItemMarketDTO {
	private int imSq;				//경매장 등록 코드
	private String playerNickname;	//경매 등록자 닉네임
	private String itemCode;		//아이템코드
	private int imPurchasePrice;	//경매가격
	private String imBidTime;		//입찰시각
	
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
