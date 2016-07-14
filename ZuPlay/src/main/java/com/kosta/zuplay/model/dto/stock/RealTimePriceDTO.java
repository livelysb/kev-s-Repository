package com.kosta.zuplay.model.dto.stock;

public class RealTimePriceDTO {
	private int rpSq;		//실시간 주가 코드
	private String isuCd;	//종목코드
	private int rpTrdPrc;	//체결가격
	private String rpTrdTm;	//체결시각,거래시각
	private long rpTrdTm2;
	
	public RealTimePriceDTO() {}
	public RealTimePriceDTO(int rpSq, String isuCd, int rpTrdPrc, String rpTrdTm) {
		super();
		this.rpSq = rpSq;
		this.isuCd = isuCd;
		this.rpTrdPrc = rpTrdPrc;
		this.rpTrdTm = rpTrdTm;
	}
	public int getRpSq() {
		return rpSq;
	}
	public void setRpSq(int rpSq) {
		this.rpSq = rpSq;
	}
	public String getIsuCd() {
		return isuCd;
	}
	public void setIsuCd(String isuCd) {
		this.isuCd = isuCd;
	}
	public int getRpTrdPrc() {
		return rpTrdPrc;
	}
	public void setRpTrdPrc(int rpTrdPrc) {
		this.rpTrdPrc = rpTrdPrc;
	}
	public String getRpTrdTm() {
		return rpTrdTm;
	}
	public void setRpTrdTm(String rpTrdTm) {
		this.rpTrdTm = rpTrdTm;
	}
	
	
	public long getRpTrdTm2() {
		return rpTrdTm2;
	}
	public void setRpTrdTm2(long rpTrdTm2) {
		this.rpTrdTm2 = rpTrdTm2;
	}
	@Override
	public String toString() {
		return "RealTimePriceDTO [rpSq=" + rpSq + ", isuCd=" + isuCd + ", rpTrdPrc=" + rpTrdPrc + ", rpTrdTm=" + rpTrdTm
				+ "]";
	}
	
}
