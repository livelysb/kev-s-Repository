package com.kosta.zuplay.model.dto.stock;

public class EarningRateHistoryDTO {
	private int pehSq;				//수익률코드
	private String playerNickname;	//닉네임
	private double pehPe;				//수익률
	private String pehDate;			//일자
	private long pehDate2;
	
	public EarningRateHistoryDTO() {}

	public EarningRateHistoryDTO(int pehSq, String playerNickname, double pehPe, String pehDate) {
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

	public double getPehPe() {
		return pehPe;
	}

	public void setPehPe(double pehPe) {
		this.pehPe = pehPe;
	}

	public String getPehDate() {
		return pehDate;
	}

	public void setPehDate(String pehDate) {
		this.pehDate = pehDate;
	}
	

	public long getPehDate2() {
		return pehDate2;
	}

	public void setPehDate2(long pehDate2) {
		this.pehDate2 = pehDate2;
	}

	@Override
	public String toString() {
		return "EarningRateHistory [pehSq=" + pehSq + ", playerNickname=" + playerNickname + ", pehPe=" + pehPe
				+ ", pehDate=" + pehDate + "]";
	}
	
	
}
