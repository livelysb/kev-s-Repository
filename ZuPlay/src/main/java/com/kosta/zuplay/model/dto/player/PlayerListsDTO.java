package com.kosta.zuplay.model.dto.player;

import com.kosta.zuplay.model.dto.stock.MasterDTO;
import com.kosta.zuplay.model.dto.stock.PriceDTO;

public class PlayerListsDTO {
	private int plSq;				//시퀀스
	private String isuCd;			//종목코드
	private String playerNickname;	//닉네임
	private int plQuantity;			//수량
	
	private MasterDTO masterDTO; // 1:1조인
	
	
	public MasterDTO getMasterDTO() {
		return masterDTO;
	}

	public void setMasterDTO(MasterDTO masterDTO) {
		this.masterDTO = masterDTO;
	}

	public PlayerListsDTO() {}

	public PlayerListsDTO(int plSq, String isuCd, String playerNickname, int plQuantity) {
		super();
		this.plSq = plSq;
		this.isuCd = isuCd;
		this.playerNickname = playerNickname;
		this.plQuantity = plQuantity;
	}

	public int getPlSq() {
		return plSq;
	}

	public void setPlSq(int plSq) {
		this.plSq = plSq;
	}

	public String getIsuCd() {
		return isuCd;
	}

	public void setIsuCd(String isuCd) {
		this.isuCd = isuCd;
	}

	public String getPlayerNickname() {
		return playerNickname;
	}

	public void setPlayerNickname(String playerNickname) {
		this.playerNickname = playerNickname;
	}

	public int getPlQuantity() {
		return plQuantity;
	}

	public void setPlQuantity(int plQuantity) {
		this.plQuantity = plQuantity;
	}

	@Override
	public String toString() {
		return "PlayerListsDTO [plSq=" + plSq + ", isuCd=" + isuCd + ", playerNickname=" + playerNickname
				+ ", plQuantity=" + plQuantity + "]";
	}
	
	
	
}
