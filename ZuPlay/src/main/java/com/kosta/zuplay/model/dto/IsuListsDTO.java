package com.kosta.zuplay.model.dto;

public class IsuListsDTO {
	private String isuSrtCd;
	private String isuKorAbbrv;
	private String isuKorNm;
	private String mktTpCd;
	private String isuCd;

	public IsuListsDTO() {}
	public IsuListsDTO(String isuSrtCd, String isuKorAbbrv, String isuKorNm, String mktTpCd, String isuCd) {
		super();
		this.isuSrtCd = isuSrtCd;
		this.isuKorAbbrv = isuKorAbbrv;
		this.isuKorNm = isuKorNm;
		this.mktTpCd = mktTpCd;
		this.isuCd = isuCd;
	}

	public String getIsuSrtCd() {
		return isuSrtCd;
	}

	public void setIsuSrtCd(String isuSrtCd) {
		this.isuSrtCd = isuSrtCd;
	}

	public String getIsuKorAbbrv() {
		return isuKorAbbrv;
	}

	public void setIsuKorAbbrv(String isuKorAbbrv) {
		this.isuKorAbbrv = isuKorAbbrv;
	}

	public String getIsuKorNm() {
		return isuKorNm;
	}

	public void setIsuKorNm(String isuKorNm) {
		this.isuKorNm = isuKorNm;
	}

	public String getMktTpCd() {
		return mktTpCd;
	}

	public void setMktTpCd(String mktTpCd) {
		this.mktTpCd = mktTpCd;
	}

	public String getIsuCd() {
		return isuCd;
	}

	public void setIsuCd(String isuCd) {
		this.isuCd = isuCd;
	}

	@Override
	public String toString() {
		return "ClassPojo [isuSrtCd = " + isuSrtCd + ", isuKorAbbrv = " + isuKorAbbrv + ", isuKorNm = " + isuKorNm
				+ ", mktTpCd = " + mktTpCd + ", isuCd = " + isuCd + "]";
	}
}
