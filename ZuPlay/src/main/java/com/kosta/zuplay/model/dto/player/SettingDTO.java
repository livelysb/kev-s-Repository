package com.kosta.zuplay.model.dto.player;

public class SettingDTO {
	
	private int plSq;
	private String playerNickname;
	private int bgmSound;
	private String myPage;
	private String chatting;
	private String friendAdd;
	
	public SettingDTO() {}
	
	public SettingDTO(int plSq, String playerNickname, int bgmSound, String myPage, String chatting, String friendAdd) {
		super();
		this.plSq = plSq;
		this.playerNickname = playerNickname;
		this.bgmSound = bgmSound;
		this.myPage = myPage;
		this.chatting = chatting;
		this.friendAdd = friendAdd;
	}

	public int getPlSq() {
		return plSq;
	}

	public void setPlSq(int plSq) {
		this.plSq = plSq;
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
		return "SettingDTO [plSq=" + plSq + ", playerNickname=" + playerNickname + ", bgmSound=" + bgmSound
				+ ", myPage=" + myPage + ", chatting=" + chatting + ", friendAdd=" + friendAdd + "]";
	}
	
	
	
}
