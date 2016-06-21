package com.kosta.zuplay.model.dto;

public class RealTimePriceDTO {
	int rpSq;
	String isuCd;
	int rpTrdPrc;
	String rpTrdTm;
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
	@Override
	public String toString() {
		return "RealTimePriceDTO [rpSq=" + rpSq + ", isuCd=" + isuCd + ", rpTrdPrc=" + rpTrdPrc + ", rpTrdTm=" + rpTrdTm
				+ "]";
	}
	
}
