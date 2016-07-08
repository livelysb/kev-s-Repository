package com.kosta.zuplay.model.dto.player;

import java.util.List;

public class FriendVO {
	private int friendSq; // 친구코드
	private String playerNickname; // 닉네임1
	private String playerNickname2; //신청자
	private String friendIsAccepted; // 친구 수락여부(T=친구/F=친구수락대기중)
	private String friendDate; // 친구신청일자
	private boolean onOrOff; //접속여부
	private List<PlayerItemDTO> playerItemDTO;

	
	public FriendVO() {}
	

	public FriendVO(int friendSq, String playerNickname, String playerNickname2, String friendIsAccepted,
			String friendDate, boolean onOrOff, List<PlayerItemDTO> playerItemDTO) {
		super();
		this.friendSq = friendSq;
		this.playerNickname = playerNickname;
		this.playerNickname2 = playerNickname2;
		this.friendIsAccepted = friendIsAccepted;
		this.friendDate = friendDate;
		this.onOrOff = onOrOff;
		this.playerItemDTO = playerItemDTO;
	}


	public String getPlayerNickname2() {
		return playerNickname2;
	}


	public void setPlayerNickname2(String playerNickname2) {
		this.playerNickname2 = playerNickname2;
	}


	public int getFriendSq() {
		return friendSq;
	}
	public void setFriendSq(int friendSq) {
		this.friendSq = friendSq;
	}
	public String getPlayerNickname() {
		return playerNickname;
	}
	public void setPlayerNickname(String playerNickname) {
		this.playerNickname = playerNickname;
	}
	public String getFriendIsAccepted() {
		return friendIsAccepted;
	}
	public void setFriendIsAccepted(String friendIsAccepted) {
		this.friendIsAccepted = friendIsAccepted;
	}
	public String getFriendDate() {
		return friendDate;
	}
	public void setFriendDate(String friendDate) {
		this.friendDate = friendDate;
	}
	public boolean isOnOrOff() {
		return onOrOff;
	}
	public void setOnOrOff(boolean onOrOff) {
		this.onOrOff = onOrOff;
	}
	
	public List<PlayerItemDTO> getPlayerItemDTO() {
		return playerItemDTO;
	}

	public void setPlayerItemDTO(List<PlayerItemDTO> playerItemDTO) {
		this.playerItemDTO = playerItemDTO;
	}


	@Override
	public String toString() {
		return "FriendVO [friendSq=" + friendSq + ", playerNickname=" + playerNickname + ", playerNickname2="
				+ playerNickname2 + ", friendIsAccepted=" + friendIsAccepted + ", friendDate=" + friendDate
				+ ", onOrOff=" + onOrOff + ", playerItemDTO=" + playerItemDTO + "]";
	}

	

	
	
}
