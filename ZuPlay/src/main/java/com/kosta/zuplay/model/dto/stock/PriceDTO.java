package com.kosta.zuplay.model.dto.stock;

public class PriceDTO
{
    private String mkStatTpCd;		//?û•?ÉÅ?ÉúÍµ¨Î∂ÑÏΩîÎìú
    private String trdTm;			//Ï≤¥Í≤∞?ãúÍ∞?,Í±∞Îûò?ãúÍ∞?
    private double trdvol;			//Ï≤¥Í≤∞?àò?üâ,Í±∞Îûò?üâ
    private double cmpprevddPrc;	//?†Ñ?ùº??ÎπÑÍ?Í≤?
    private double trdPrc;			//Ï≤¥Í≤∞Í∞?Í≤?
    private double hgprc;			//Í≥†Í?
    private double lwprc;			//??Í∞?
    private String cmpprevddTpCd;	//?†Ñ?ùº??ÎπÑÍµ¨Î∂ÑÏΩî?ìú
    private double opnprc;			//?ãúÍ∞?
    private String isuCd;			//Ï¢ÖÎ™©ÏΩîÎìú

    public PriceDTO() {}
    public PriceDTO(String mkStatTpCd, String trdTm, double trdvol, double cmpprevddPrc, double trdPrc, double hgprc,
			double lwprc, String cmpprevddTpCd, double opnprc, String isuCd) {
		super();
		this.mkStatTpCd = mkStatTpCd;
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

	public String getMkStatTpCd ()
    {
        return mkStatTpCd;
    }

    public void setMkStatTpCd (String mkStatTpCd)
    {
        this.mkStatTpCd = mkStatTpCd;
    }

    public String getTrdTm ()
    {
        return trdTm;
    }

    public void setTrdTm (String trdTm)
    {
        this.trdTm = trdTm;
    }

    public double getTrdvol ()
    {
        return trdvol;
    }

    public void setTrdvol (double trdvol)
    {
        this.trdvol = trdvol;
    }

    public double getCmpprevddPrc ()
    {
        return cmpprevddPrc;
    }

    public void setCmpprevddPrc (double cmpprevddPrc)
    {
        this.cmpprevddPrc = cmpprevddPrc;
    }

    public double getTrdPrc ()
    {
        return trdPrc;
    }

    public void setTrdPrc (double trdPrc)
    {
        this.trdPrc = trdPrc;
    }

    public double getHgprc ()
    {
        return hgprc;
    }

    public void setHgprc (double hgprc)
    {
        this.hgprc = hgprc;
    }

    public double getLwprc ()
    {
        return lwprc;
    }

    public void setLwprc (double lwprc)
    {
        this.lwprc = lwprc;
    }

    public String getCmpprevddTpCd ()
    {
        return cmpprevddTpCd;
    }

    public void setCmpprevddTpCd (String cmpprevddTpCd)
    {
        this.cmpprevddTpCd = cmpprevddTpCd;
    }

    public double getOpnprc ()
    {
        return opnprc;
    }

    public void setOpnprc (double opnprc)
    {
        this.opnprc = opnprc;
    }

    public String getIsuCd ()
    {
        return isuCd;
    }

    public void setIsuCd (String isuCd)
    {
        this.isuCd = isuCd;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [mkStatTpCd = "+mkStatTpCd+", trdTm = "+trdTm+", trdvol = "+trdvol+", cmpprevddPrc = "+cmpprevddPrc+", trdPrc = "+trdPrc+", hgprc = "+hgprc+", lwprc = "+lwprc+", cmpprevddTpCd = "+cmpprevddTpCd+", opnprc = "+opnprc+", isuCd = "+isuCd+"]";
    }
}