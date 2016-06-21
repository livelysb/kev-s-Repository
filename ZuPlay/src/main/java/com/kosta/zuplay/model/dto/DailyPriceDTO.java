package com.kosta.zuplay.model.dto;

public class DailyPriceDTO {
	int dpSq;
	String isuCd;
	int dpClsprc;
	String dpDate;
	
	public DailyPriceDTO() {}
	public DailyPriceDTO(int dpSq, String isuCd, int dpClsprc, String dpDate) {
		super();
		this.dpSq = dpSq;
		this.isuCd = isuCd;
		this.dpClsprc = dpClsprc;
		this.dpDate = dpDate;
	}
	public int getDpSq() {
		return dpSq;
	}
	public void setDpSq(int dpSq) {
		this.dpSq = dpSq;
	}
	public String getIsuCd() {
		return isuCd;
	}
	public void setIsuCd(String isuCd) {
		this.isuCd = isuCd;
	}
	public int getDpClsprc() {
		return dpClsprc;
	}
	public void setDpClsprc(int dpClsprc) {
		this.dpClsprc = dpClsprc;
	}
	public String getDpDate() {
		return dpDate;
	}
	public void setDpDate(String dpDate) {
		this.dpDate = dpDate;
	}
	@Override
	public String toString() {
		return "DailyPriceDTO [dpSq=" + dpSq + ", isuCd=" + isuCd + ", dpClsprc=" + dpClsprc + ", dpDate=" + dpDate
				+ "]";
	}

	
}
