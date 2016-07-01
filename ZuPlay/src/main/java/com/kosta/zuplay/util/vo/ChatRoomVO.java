package com.kosta.zuplay.util.vo;

import java.util.ArrayList;
import java.util.List;

public class ChatRoomVO {
	
	private int roomNo;
	private List<String> playerList = new ArrayList<String>();
	
	public ChatRoomVO(int roomNo, String playerNickname) {
		this.roomNo = roomNo;
		
	}
	
	public ChatRoomVO() {
	}
	
	
	
	
	public int getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	
	
	
}
