package com.kosta.zuplay.model.dto.stock;

public class DailyPriceDTO {
	private int dpSq;		//db코드
	private String isuCd;	//종목코드
	private int dpClsprc;	//종가
	private String dpDate;	//일자
	private long dpDate2;
	
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
	
	
	public long getDpDate2() {
		return dpDate2;
	}
	public void setDpDate2(long dpDate2) {
		this.dpDate2 = dpDate2;
	}
	@Override
	public String toString() {
		return "DailyPriceDTO [dpSq=" + dpSq + ", isuCd=" + isuCd + ", dpClsprc=" + dpClsprc + ", dpDate=" + dpDate
				+ "]";
	}

	
}
