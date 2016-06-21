package com.kosta.zuplay.model.dto;

public class FriendDTO {
	int friendSq;
	String playerNickname;
	String playerNickname2;
	boolean friendIsAccepted;
	String friednDate;
	
	public FriendDTO() {}
	public FriendDTO(int friendSq, String playerNickname, String playerNickname2, boolean friendIsAccepted,
			String friednDate) {
		super();
		this.friendSq = friendSq;
		this.playerNickname = playerNickname;
		this.playerNickname2 = playerNickname2;
		this.friendIsAccepted = friendIsAccepted;
		this.friednDate = friednDate;
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
	public String getPlayerNickname2() {
		return playerNickname2;
	}
	public void setPlayerNickname2(String playerNickname2) {
		this.playerNickname2 = playerNickname2;
	}
	public boolean isFriendIsAccepted() {
		return friendIsAccepted;
	}
	public void setFriendIsAccepted(boolean friendIsAccepted) {
		this.friendIsAccepted = friendIsAccepted;
	}
	public String getFriednDate() {
		return friednDate;
	}
	public void setFriednDate(String friednDate) {
		this.friednDate = friednDate;
	}
	@Override
	public String toString() {
		return "FriendDTO [friendSq=" + friendSq + ", playerNickname=" + playerNickname + ", playerNickname2="
				+ playerNickname2 + ", friendIsAccepted=" + friendIsAccepted + ", friednDate=" + friednDate + "]";
	}
	
	
}
