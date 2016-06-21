package com.kosta.zuplay.model.dto;

public class StockDealHistoryDTO {
	int sdhSq;				//주식거래기록코드
	String isuCd;			//종목코드
	String playerNickname;	//닉네임
	int sdhDealPrice;		//거래가
	String sdhDealTime;		//거래시각
	int sdhQuantity;		//수량
	String sdhBuySell;		//Buy or Sell
	
	public StockDealHistoryDTO() {}

	public StockDealHistoryDTO(int sdhSq, String isuCd, String playerNickname, int sdhDealPrice, String sdhDealTime,
			int sdhQuantity, String sdhBuySell) {
		super();
		this.sdhSq = sdhSq;
		this.isuCd = isuCd;
		this.playerNickname = playerNickname;
		this.sdhDealPrice = sdhDealPrice;
		this.sdhDealTime = sdhDealTime;
		this.sdhQuantity = sdhQuantity;
		this.sdhBuySell = sdhBuySell;
	}

	public int getSdhSq() {
		return sdhSq;
	}

	public void setSdhSq(int sdhSq) {
		this.sdhSq = sdhSq;
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

	public int getSdhDealPrice() {
		return sdhDealPrice;
	}

	public void setSdhDealPrice(int sdhDealPrice) {
		this.sdhDealPrice = sdhDealPrice;
	}

	public String getSdhDealTime() {
		return sdhDealTime;
	}

	public void setSdhDealTime(String sdhDealTime) {
		this.sdhDealTime = sdhDealTime;
	}

	public int getSdhQuantity() {
		return sdhQuantity;
	}

	public void setSdhQuantity(int sdhQuantity) {
		this.sdhQuantity = sdhQuantity;
	}

	public String getSdhBuySell() {
		return sdhBuySell;
	}

	public void setSdhBuySell(String sdhBuySell) {
		this.sdhBuySell = sdhBuySell;
	}

	@Override
	public String toString() {
		return "StockDealHistoryDTO [sdhSq=" + sdhSq + ", isuCd=" + isuCd + ", playerNickname=" + playerNickname
				+ ", sdhDealPrice=" + sdhDealPrice + ", sdhDealTime=" + sdhDealTime + ", sdhQuantity=" + sdhQuantity
				+ ", sdhBuySell=" + sdhBuySell + "]";
	}
	
	
}
