package com.kosta.zuplay.model.dto.player;

public class SettingDTO {
	
	private int psSq;
	private String playerNickname;
	private int psBgmSound;
	private String psMyPage;
	private String psChatting;
	private String psFriendAdd;
	private String psTheme;
	
	public SettingDTO() {}
	public SettingDTO(int psSq, String playerNickname, int psBgmSound, String psMyPage, String psChatting,
			String psFriendAdd, String psTheme) {
		super();
		this.psSq = psSq;
		this.playerNickname = playerNickname;
		this.psBgmSound = psBgmSound;
		this.psMyPage = psMyPage;
		this.psChatting = psChatting;
		this.psFriendAdd = psFriendAdd;
		this.psTheme = psTheme;
	}
	public int getPsSq() {
		return psSq;
	}
	public void setPsSq(int psSq) {
		this.psSq = psSq;
	}
	public String getPlayerNickname() {
		return playerNickname;
	}
	public void setPlayerNickname(String playerNickname) {
		this.playerNickname = playerNickname;
	}
	public int getPsBgmSound() {
		return psBgmSound;
	}
	public void setPsBgmSound(int psBgmSound) {
		this.psBgmSound = psBgmSound;
	}
	public String getPsMyPage() {
		return psMyPage;
	}
	public void setPsMyPage(String psMyPage) {
		this.psMyPage = psMyPage;
	}
	public String getPsChatting() {
		return psChatting;
	}
	public void setPsChatting(String psChatting) {
		this.psChatting = psChatting;
	}
	public String getPsFriendAdd() {
		return psFriendAdd;
	}
	public void setPsFriendAdd(String psFriendAdd) {
		this.psFriendAdd = psFriendAdd;
	}
	public String getPsTheme() {
		return psTheme;
	}
	public void setPsTheme(String psTheme) {
		this.psTheme = psTheme;
	}
	@Override
	public String toString() {
		return "SettingDTO [psSq=" + psSq + ", playerNickname=" + playerNickname + ", psBgmSound=" + psBgmSound
				+ ", psMyPage=" + psMyPage + ", psChatting=" + psChatting + ", psFriendAdd=" + psFriendAdd
				+ ", psTheme=" + psTheme + "]";
	}
	
}