package com.kosta.zuplay.model.dto.player;

public class SettingDTO {
	
	private int psSq;
	private String playerNickname;
	private int bgmSound;
	private String myPage;
	private String chatting;
	private String friendAdd;
	public SettingDTO() {}
	public SettingDTO(int psSq, String playerNickname, int bgmSound, String myPage, String chatting, String friendAdd) {
		super();
		this.psSq = psSq;
		this.playerNickname = playerNickname;
		this.bgmSound = bgmSound;
		this.myPage = myPage;
		this.chatting = chatting;
		this.friendAdd = friendAdd;
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
	public int getBgmSound() {
		return bgmSound;
	}
	public void setBgmSound(int bgmSound) {
		this.bgmSound = bgmSound;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getChatting() {
		return chatting;
	}
	public void setChatting(String chatting) {
		this.chatting = chatting;
	}
	public String getFriendAdd() {
		return friendAdd;
	}
	public void setFriendAdd(String friendAdd) {
		this.friendAdd = friendAdd;
	}
	@Override
	public String toString() {
		return "SettingDTO [psSq=" + psSq + ", playerNickname=" + playerNickname + ", bgmSound=" + bgmSound
				+ ", myPage=" + myPage + ", chatting=" + chatting + ", friendAdd=" + friendAdd + "]";
	}
	
	
}