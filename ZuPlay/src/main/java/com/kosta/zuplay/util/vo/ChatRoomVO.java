package com.kosta.zuplay.util.vo;

import java.util.ArrayList;
import java.util.List;

import com.kosta.zuplay.model.dto.player.PlayerDTO;

public class ChatRoomVO {

	private int roomNo;
	private String roomName;
	private String password;
	private int maxNum;
	
	private List<PlayerDTO> playerList = new ArrayList<PlayerDTO>();
	
	public ChatRoomVO() {
	}
	
	public ChatRoomVO(int roomNo, String roomName, String password, List<PlayerDTO> playerList, int maxNum) {
		super();
		this.roomNo = roomNo;
		this.roomName = roomName;
		this.password = password;
		this.playerList = playerList;
		this.maxNum = maxNum;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<PlayerDTO> getPlayerList() {
		return playerList;
	}

	public void setPlayerList(List<PlayerDTO> playerList) {
		this.playerList = playerList;
	}

	public int getMaxNum() {
		return maxNum;
	}

	public void setMaxNum(int maxNum) {
		this.maxNum = maxNum;
	}
	
	
	
	
	
}