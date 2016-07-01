package com.kosta.zuplay.util.vo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;

public class ChatRoomVO {
	
	@Autowired
	private ServletContext application;
	
	private int roomNo;
	private List<PlayerVO> playerList = new ArrayList<PlayerVO>();
	
	public ChatRoomVO() {
	}
	
	public ChatRoomVO(int roomNo, String playerNickname) {
		this.roomNo = roomNo;
		playerList.add((PlayerVO)application.getAttribute(playerNickname));
	}
	
	public int getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public List<PlayerVO> getPlayerList() {
		return playerList;
	}
}