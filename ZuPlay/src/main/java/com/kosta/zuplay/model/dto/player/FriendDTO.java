package com.kosta.zuplay.model.dto.player;

import java.util.List;

public class FriendDTO {
	private int friendSq; // 친구코드
	private String playerNickname; // 닉네임1
	private String playerNickname2; // 닉네임2
	private String friendIsAccepted; // 친구 수락여부(T=친구/F=친구수락대기중)
	private String friendDate; // 친구신청일자

	private List<PlayerItemDTO> playerItemDTO;
	private PlayerDTO playerDTO;

	public FriendDTO() {}


	public FriendDTO(int friendSq, String playerNickname, String playerNickname2, String friendIsAccepted,
			String friendDate, List<PlayerItemDTO> playerItemDTO, PlayerDTO playerDTO) {
		super();
		this.friendSq = friendSq;
		this.playerNickname = playerNickname;
		this.playerNickname2 = playerNickname2;
		this.friendIsAccepted = friendIsAccepted;
		this.friendDate = friendDate;
		this.playerItemDTO = playerItemDTO;
		this.playerDTO = playerDTO;
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

	public List<PlayerItemDTO> getList() {
		return playerItemDTO;
	}

	public void setList(List<PlayerItemDTO> playerItemDTO) {
		this.playerItemDTO = playerItemDTO;
	}

	public List<PlayerItemDTO> getPlayerItemDTO() {
		return playerItemDTO;
	}


	public void setPlayerItemDTO(List<PlayerItemDTO> playerItemDTO) {
		this.playerItemDTO = playerItemDTO;
	}


	public PlayerDTO getPlayerDTO() {
		return playerDTO;
	}


	public void setPlayerDTO(PlayerDTO playerDTO) {
		this.playerDTO = playerDTO;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FriendDTO [friendSq=" + friendSq + ", playerNickname=" + playerNickname + ", playerNickname2="
				+ playerNickname2 + ", friendIsAccepted=" + friendIsAccepted + ", friendDate=" + friendDate
				+ ", playerItemDTO=" + playerItemDTO + ", playerDTO=" + playerDTO + "]";
	}



}