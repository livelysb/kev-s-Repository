package com.kosta.zuplay.model.dto.stock;

public class PriceEarningHistory {
	int pehSq;				//수익률코드
	String playerNickname;	//닉네임
	int pehPe;				//수익률
	String pehDate;			//일자
	
	public PriceEarningHistory() {}

	public PriceEarningHistory(int pehSq, String playerNickname, int pehPe, String pehDate) {
		super();
		this.pehSq = pehSq;
		this.playerNickname = playerNickname;
		this.pehPe = pehPe;
		this.pehDate = pehDate;
	}

	public int getPehSq() {
		return pehSq;
	}

	public void setPehSq(int pehSq) {
		this.pehSq = pehSq;
	}

	public String getPlayerNickname() {
		return playerNickname;
	}

	public void setPlayerNickname(String playerNickname) {
		this.playerNickname = playerNickname;
	}

	public int getPehPe() {
		return pehPe;
	}

	public void setPehPe(int pehPe) {
		this.pehPe = pehPe;
	}

	public String getPehDate() {
		return pehDate;
	}

	public void setPehDate(String pehDate) {
		this.pehDate = pehDate;
	}

	@Override
	public String toString() {
		return "PriceEarningHistory [pehSq=" + pehSq + ", playerNickname=" + playerNickname + ", pehPe=" + pehPe
				+ ", pehDate=" + pehDate + "]";
	}
	
	
}
