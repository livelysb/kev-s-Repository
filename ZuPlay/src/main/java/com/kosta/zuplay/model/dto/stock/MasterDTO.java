package com.kosta.zuplay.model.dto.stock;

public class MasterDTO {
	private String krxBioSectidxYn;			//KRX바이?��?��?�� �??��?���?
	private String mktcapScaleCd;			//?���?총액규모코드
	private double lwlmtprc;				//?��?���?
	private String krxSteelSectidxYn;		//KRX철강?��?�� �??��?���?
	private String krxInsuSectidxYn;		//KRX?��?���??�� 보험?���?
	private double uplmtprc;				//?��?���?
	private double prevddAccTrdvol;			//?��?��?��?��체결?��?��
	private String krxEnergyChemSectidxYn;	//KRX?��?���??��?��?��?�� �??��?���?
	private String haltYn;					//거래?���??���?
	private String krxTransSectidxYn;		//KRX?��?���??�� ?��?��?���?
	private String isuSrtCd;				//종목?��축코?��
	private String govncExcelYn;			//�?배구조우?��?���?
	private double parval;					//?��면�?
	private String krxConstrSectidxYn;		//KRX건설?��?�� �??��?���?
	private String kospiYn;					//코스?�� ?���?
	private String krxConsgoodSectidxYn;	//KRX?��비재?��?�� �??��?���?
	private double listShrs;				//?��?��주식?��
	private String krxSecuSectidxYn;		//KRX?��?���??�� 증권?���?
	private String krxInfoCommSectidxYn;	//KRX?��보통?��?��?�� �??��?���?
	private double prevddAccTrdval;			//?��?��?��?��거래??�?
	private String krxRetailSectidxYn;		//KRX?��?���??��?��비자?��?��?���?
	private String krxSemiconSectidxYn;		//KRX반도체섹?�� �??��?���?
	private String mfindYn;					//?��조업?���?
	private String krxLeisureSectidxYn;		//KRX?��?���??��?��???��?��?��?��먼트 ?���?
	private String krxAutosSectidxYn;		//KRX?��?��차섹?�� �??��?���?
	private String isuKorAbbrv;				//종목?���??���?
	private String krxFncSectidxYn;			//KRX금융?��?�� �??��?���?
	private String krxShipSectidxYn;		//KRX?��?���??�� ?��박여�?
	private String krxFncSvcSectidxYn;		//KRX금융?��비스?��?�� �??��?���?
	private String krxMediaCommSectidxYn;	//KRX미디?��?��?��?��?�� �??��?���?
	private String isuCd;					//종목코드

	public MasterDTO() {}
	public MasterDTO(String krxBioSectidxYn, String mktcapScaleCd, double lwlmtprc, String krxSteelSectidxYn,
			String krxInsuSectidxYn, double uplmtprc, double prevddAccTrdvol, String krxEnergyChemSectidxYn,
			String haltYn, String krxTransSectidxYn, String isuSrtCd, String govncExcelYn, double parval,
			String krxConstrSectidxYn, String kospiYn, String krxConsgoodSectidxYn, double listShrs,
			String krxSecuSectidxYn, String krxInfoCommSectidxYn, double prevddAccTrdval, String krxRetailSectidxYn,
			String krxSemiconSectidxYn, String mfindYn, String krxLeisureSectidxYn, String krxAutosSectidxYn,
			String isuKorAbbrv, String krxFncSectidxYn, String krxShipSectidxYn, String krxFncSvcSectidxYn,
			String krxMediaCommSectidxYn, String isuCd) {
		super();
		this.krxBioSectidxYn = krxBioSectidxYn;
		this.mktcapScaleCd = mktcapScaleCd;
		this.lwlmtprc = lwlmtprc;
		this.krxSteelSectidxYn = krxSteelSectidxYn;
		this.krxInsuSectidxYn = krxInsuSectidxYn;
		this.uplmtprc = uplmtprc;
		this.prevddAccTrdvol = prevddAccTrdvol;
		this.krxEnergyChemSectidxYn = krxEnergyChemSectidxYn;
		this.haltYn = haltYn;
		this.krxTransSectidxYn = krxTransSectidxYn;
		this.isuSrtCd = isuSrtCd;
		this.govncExcelYn = govncExcelYn;
		this.parval = parval;
		this.krxConstrSectidxYn = krxConstrSectidxYn;
		this.kospiYn = kospiYn;
		this.krxConsgoodSectidxYn = krxConsgoodSectidxYn;
		this.listShrs = listShrs;
		this.krxSecuSectidxYn = krxSecuSectidxYn;
		this.krxInfoCommSectidxYn = krxInfoCommSectidxYn;
		this.prevddAccTrdval = prevddAccTrdval;
		this.krxRetailSectidxYn = krxRetailSectidxYn;
		this.krxSemiconSectidxYn = krxSemiconSectidxYn;
		this.mfindYn = mfindYn;
		this.krxLeisureSectidxYn = krxLeisureSectidxYn;
		this.krxAutosSectidxYn = krxAutosSectidxYn;
		this.isuKorAbbrv = isuKorAbbrv;
		this.krxFncSectidxYn = krxFncSectidxYn;
		this.krxShipSectidxYn = krxShipSectidxYn;
		this.krxFncSvcSectidxYn = krxFncSvcSectidxYn;
		this.krxMediaCommSectidxYn = krxMediaCommSectidxYn;
		this.isuCd = isuCd;
	}

	public String getKrxBioSectidxYn() {
		return krxBioSectidxYn;
	}

	public void setKrxBioSectidxYn(String krxBioSectidxYn) {
		this.krxBioSectidxYn = krxBioSectidxYn;
	}

	public String getMktcapScaleCd() {
		return mktcapScaleCd;
	}

	public void setMktcapScaleCd(String mktcapScaleCd) {
		this.mktcapScaleCd = mktcapScaleCd;
	}

	public double getLwlmtprc() {
		return lwlmtprc;
	}

	public void setLwlmtprc(double lwlmtprc) {
		this.lwlmtprc = lwlmtprc;
	}

	public String getKrxSteelSectidxYn() {
		return krxSteelSectidxYn;
	}

	public void setKrxSteelSectidxYn(String krxSteelSectidxYn) {
		this.krxSteelSectidxYn = krxSteelSectidxYn;
	}

	public String getKrxInsuSectidxYn() {
		return krxInsuSectidxYn;
	}

	public void setKrxInsuSectidxYn(String krxInsuSectidxYn) {
		this.krxInsuSectidxYn = krxInsuSectidxYn;
	}

	public double getUplmtprc() {
		return uplmtprc;
	}

	public void setUplmtprc(double uplmtprc) {
		this.uplmtprc = uplmtprc;
	}

	public double getprevddClsprc() {
		return prevddAccTrdvol;
	}

	public void setPrevddAccTrdvol(double prevddAccTrdvol) {
		this.prevddAccTrdvol = prevddAccTrdvol;
	}

	public String getKrxEnergyChemSectidxYn() {
		return krxEnergyChemSectidxYn;
	}

	public void setKrxEnergyChemSectidxYn(String krxEnergyChemSectidxYn) {
		this.krxEnergyChemSectidxYn = krxEnergyChemSectidxYn;
	}

	public String getHaltYn() {
		return haltYn;
	}

	public void setHaltYn(String haltYn) {
		this.haltYn = haltYn;
	}

	public String getKrxTransSectidxYn() {
		return krxTransSectidxYn;
	}

	public void setKrxTransSectidxYn(String krxTransSectidxYn) {
		this.krxTransSectidxYn = krxTransSectidxYn;
	}

	public String getIsuSrtCd() {
		return isuSrtCd;
	}

	public void setIsuSrtCd(String isuSrtCd) {
		this.isuSrtCd = isuSrtCd;
	}

	public String getGovncExcelYn() {
		return govncExcelYn;
	}

	public void setGovncExcelYn(String govncExcelYn) {
		this.govncExcelYn = govncExcelYn;
	}

	public double getParval() {
		return parval;
	}

	public void setParval(double parval) {
		this.parval = parval;
	}

	public String getKrxConstrSectidxYn() {
		return krxConstrSectidxYn;
	}

	public void setKrxConstrSectidxYn(String krxConstrSectidxYn) {
		this.krxConstrSectidxYn = krxConstrSectidxYn;
	}

	public String getKospiYn() {
		return kospiYn;
	}

	public void setKospiYn(String kospiYn) {
		this.kospiYn = kospiYn;
	}

	public String getKrxConsgoodSectidxYn() {
		return krxConsgoodSectidxYn;
	}

	public void setKrxConsgoodSectidxYn(String krxConsgoodSectidxYn) {
		this.krxConsgoodSectidxYn = krxConsgoodSectidxYn;
	}

	public double getListShrs() {
		return listShrs;
	}

	public void setListShrs(double listShrs) {
		this.listShrs = listShrs;
	}

	public String getKrxSecuSectidxYn() {
		return krxSecuSectidxYn;
	}

	public void setKrxSecuSectidxYn(String krxSecuSectidxYn) {
		this.krxSecuSectidxYn = krxSecuSectidxYn;
	}

	public String getKrxInfoCommSectidxYn() {
		return krxInfoCommSectidxYn;
	}

	public void setKrxInfoCommSectidxYn(String krxInfoCommSectidxYn) {
		this.krxInfoCommSectidxYn = krxInfoCommSectidxYn;
	}

	public double getPrevddAccTrdval() {
		return prevddAccTrdval;
	}

	public void setPrevddAccTrdval(double prevddAccTrdval) {
		this.prevddAccTrdval = prevddAccTrdval;
	}

	public String getKrxRetailSectidxYn() {
		return krxRetailSectidxYn;
	}

	public void setKrxRetailSectidxYn(String krxRetailSectidxYn) {
		this.krxRetailSectidxYn = krxRetailSectidxYn;
	}

	public String getKrxSemiconSectidxYn() {
		return krxSemiconSectidxYn;
	}

	public void setKrxSemiconSectidxYn(String krxSemiconSectidxYn) {
		this.krxSemiconSectidxYn = krxSemiconSectidxYn;
	}

	public String getMfindYn() {
		return mfindYn;
	}

	public void setMfindYn(String mfindYn) {
		this.mfindYn = mfindYn;
	}

	public String getKrxLeisureSectidxYn() {
		return krxLeisureSectidxYn;
	}

	public void setKrxLeisureSectidxYn(String krxLeisureSectidxYn) {
		this.krxLeisureSectidxYn = krxLeisureSectidxYn;
	}

	public String getKrxAutosSectidxYn() {
		return krxAutosSectidxYn;
	}

	public void setKrxAutosSectidxYn(String krxAutosSectidxYn) {
		this.krxAutosSectidxYn = krxAutosSectidxYn;
	}

	public String getIsuKorAbbrv() {
		return isuKorAbbrv;
	}

	public void setIsuKorAbbrv(String isuKorAbbrv) {
		this.isuKorAbbrv = isuKorAbbrv;
	}

	public String getKrxFncSectidxYn() {
		return krxFncSectidxYn;
	}

	public void setKrxFncSectidxYn(String krxFncSectidxYn) {
		this.krxFncSectidxYn = krxFncSectidxYn;
	}

	public String getKrxShipSectidxYn() {
		return krxShipSectidxYn;
	}

	public void setKrxShipSectidxYn(String krxShipSectidxYn) {
		this.krxShipSectidxYn = krxShipSectidxYn;
	}

	public String getKrxFncSvcSectidxYn() {
		return krxFncSvcSectidxYn;
	}

	public void setKrxFncSvcSectidxYn(String krxFncSvcSectidxYn) {
		this.krxFncSvcSectidxYn = krxFncSvcSectidxYn;
	}

	public String getKrxMediaCommSectidxYn() {
		return krxMediaCommSectidxYn;
	}

	public void setKrxMediaCommSectidxYn(String krxMediaCommSectidxYn) {
		this.krxMediaCommSectidxYn = krxMediaCommSectidxYn;
	}

	public String getIsuCd() {
		return isuCd;
	}

	public void setIsuCd(String isuCd) {
		this.isuCd = isuCd;
	}

	@Override
	public String toString() {
		return "ClassPojo [krxBioSectidxYn = " + krxBioSectidxYn + ", mktcapScaleCd = " + mktcapScaleCd
				+ ", lwlmtprc = " + lwlmtprc + ", krxSteelSectidxYn = " + krxSteelSectidxYn + ", krxInsuSectidxYn = "
				+ krxInsuSectidxYn + ", uplmtprc = " + uplmtprc + ", prevddAccTrdvol = " + prevddAccTrdvol
				+ ", krxEnergyChemSectidxYn = " + krxEnergyChemSectidxYn + ", haltYn = " + haltYn
				+ ", krxTransSectidxYn = " + krxTransSectidxYn + ", isuSrtCd = " + isuSrtCd + ", govncExcelYn = "
				+ govncExcelYn + ", parval = " + parval + ", krxConstrSectidxYn = " + krxConstrSectidxYn
				+ ", kospiYn = " + kospiYn + ", krxConsgoodSectidxYn = " + krxConsgoodSectidxYn + ", listShrs = "
				+ listShrs + ", krxSecuSectidxYn = " + krxSecuSectidxYn + ", krxInfoCommSectidxYn = "
				+ krxInfoCommSectidxYn + ", prevddAccTrdval = " + prevddAccTrdval + ", krxRetailSectidxYn = "
				+ krxRetailSectidxYn + ", krxSemiconSectidxYn = " + krxSemiconSectidxYn + ", mfindYn = " + mfindYn
				+ ", krxLeisureSectidxYn = " + krxLeisureSectidxYn + ", krxAutosSectidxYn = " + krxAutosSectidxYn
				+ ", isuKorAbbrv = " + isuKorAbbrv + ", krxFncSectidxYn = " + krxFncSectidxYn + ", krxShipSectidxYn = "
				+ krxShipSectidxYn + ", krxFncSvcSectidxYn = " + krxFncSvcSectidxYn + ", krxMediaCommSectidxYn = "
				+ krxMediaCommSectidxYn + ", isuCd = " + isuCd + "]";
	}
}