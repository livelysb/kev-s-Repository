package com.kosta.zuplay.model.dto.player;

public class FriendDTO {
	int friendSq;				//ÏπúÍµ¨ÏΩîÎìú
	String playerNickname;		//?ãâ?Ñ§?ûÑ1
	String playerNickname2;		//?ãâ?Ñ§?ûÑ2
	boolean friendIsAccepted;	//?àò?ùΩ(T=ÏπúÍµ¨Í¥?Í≥?/F=?ã†Ï≤?Îß?)
	String friendDate;			//ÏπúÍµ¨?ã†Ï≤??ãúÍ∞?
	
	public FriendDTO() {}
	public FriendDTO(int friendSq, String playerNickname, String playerNickname2, boolean friendIsAccepted,
			String friendDate) {
		super();
		this.friendSq = friendSq;
		this.playerNickname = playerNickname;
		this.playerNickname2 = playerNickname2;
		this.friendIsAccepted = friendIsAccepted;
		this.friendDate = friendDate;
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
	public String getfriendDate() {
		return friendDate;
	}
	public void setfriendDate(String friendDate) {
		this.friendDate = friendDate;
	}
	@Override
	public String toString() {
		return "FriendDTO [friendSq=" + friendSq + ", playerNickname=" + playerNickname + ", playerNickname2="
				+ playerNickname2 + ", friendIsAccepted=" + friendIsAccepted + ", friendDate=" + friendDate + "]";
	}
	
	
}
