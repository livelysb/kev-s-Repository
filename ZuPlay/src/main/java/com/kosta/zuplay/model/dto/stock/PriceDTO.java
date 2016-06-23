package com.kosta.zuplay.model.dto.stock;

public class PriceDTO {
	private String mktStatTpCd; // 장상태구분코드
	private String trdTm; // 체결시각,거래시각
	private double trdvol; // 체결수량,거래량
	private double cmpprevddPrc; // 전일대비가격
	private double trdPrc; // 체결가격
	private double hgprc; // 고가
	private double lwprc; // 저가
	private String cmpprevddTpCd; // 전일대비구분코드
	private double opnprc; // 시가
	private String isuCd; // 종목코드

	public PriceDTO() {
	}

	public PriceDTO(String mktStatTpCd, String trdTm, double trdvol, double cmpprevddPrc, double trdPrc, double hgprc,
			double lwprc, String cmpprevddTpCd, double opnprc, String isuCd) {
		super();
		this.mktStatTpCd = mktStatTpCd;
		this.trdTm = trdTm;
		this.trdvol = trdvol;
		this.cmpprevddPrc = cmpprevddPrc;
		this.trdPrc = trdPrc;
		this.hgprc = hgprc;
		this.lwprc = lwprc;
		this.cmpprevddTpCd = cmpprevddTpCd;
		this.opnprc = opnprc;
		this.isuCd = isuCd;
	}

	public String getmktStatTpCd() {
		return mktStatTpCd;
	}

	public void setmktStatTpCd(String mktStatTpCd) {
		this.mktStatTpCd = mktStatTpCd;
	}

	public String getTrdTm() {
		return trdTm;
	}

	public void setTrdTm(String trdTm) {
		this.trdTm = trdTm;
	}

	public double getTrdvol() {
		return trdvol;
	}

	public void setTrdvol(double trdvol) {
		this.trdvol = trdvol;
	}

	public double getCmpprevddPrc() {
		return cmpprevddPrc;
	}

	public void setCmpprevddPrc(double cmpprevddPrc) {
		this.cmpprevddPrc = cmpprevddPrc;
	}

	public double getTrdPrc() {
		return trdPrc;
	}

	public void setTrdPrc(double trdPrc) {
		this.trdPrc = trdPrc;
	}

	public double getHgprc() {
		return hgprc;
	}

	public void setHgprc(double hgprc) {
		this.hgprc = hgprc;
	}

	public double getLwprc() {
		return lwprc;
	}

	public void setLwprc(double lwprc) {
		this.lwprc = lwprc;
	}

	public String getCmpprevddTpCd() {
		return cmpprevddTpCd;
	}

	public void setCmpprevddTpCd(String cmpprevddTpCd) {
		this.cmpprevddTpCd = cmpprevddTpCd;
	}

	public double getOpnprc() {
		return opnprc;
	}

	public void setOpnprc(double opnprc) {
		this.opnprc = opnprc;
	}

	public String getIsuCd() {
		return isuCd;
	}

	public void setIsuCd(String isuCd) {
		this.isuCd = isuCd;
	}

	@Override
	public String toString() {
		return "ClassPojo [mktStatTpCd = " + mktStatTpCd + ", trdTm = " + trdTm + ", trdvol = " + trdvol
				+ ", cmpprevddPrc = " + cmpprevddPrc + ", trdPrc = " + trdPrc + ", hgprc = " + hgprc + ", lwprc = "
				+ lwprc + ", cmpprevddTpCd = " + cmpprevddTpCd + ", opnprc = " + opnprc + ", isuCd = " + isuCd + "]";
	}
}