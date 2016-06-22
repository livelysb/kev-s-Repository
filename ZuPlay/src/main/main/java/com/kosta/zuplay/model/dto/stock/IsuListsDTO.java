package com.kosta.zuplay.model.dto.stock;

public class IsuListsDTO {
	private String isuSrtCd;	//종목?��축코?��
	private String isuKorAbbrv;	//종목 ?���? ?���?
	private String isuKorNm;	//종목?���?�?
	private String mktTpCd;		//?��?��구분코드
	private String isuCd;		//종목코드

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