package com.kosta.zuplay.util.vo;

import java.util.ArrayList;
import java.util.List;

public class ChatRoomVO {

	private int roomNo;
	private String roomName;
	private boolean oneByOne;
	private String password;
	private int maxNum;
	
	private List<String> playerList = new ArrayList<String>();
	
	public ChatRoomVO() {
	}
	
	public ChatRoomVO(int roomNo, String roomName, boolean oneByOne, String password, List<String> playerList, int maxNum) {
		super();
		this.roomNo = roomNo;
		this.roomName = roomName;
		this.oneByOne = oneByOne;
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

	public boolean isOneByOne() {
		return oneByOne;
	}

	public void setOneByOne(boolean oneByOne) {
		this.oneByOne = oneByOne;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getPlayerList() {
		return playerList;
	}

	public void setPlayerList(List<String> playerList) {
		this.playerList = playerList;
	}

	public int getMaxNum() {
		return maxNum;
	}

	public void setMaxNum(int maxNum) {
		this.maxNum = maxNum;
	}
	
	
	
	
	
}