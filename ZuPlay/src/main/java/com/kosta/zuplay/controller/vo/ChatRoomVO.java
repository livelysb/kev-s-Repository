package com.kosta.zuplay.controller.vo;

import java.util.ArrayList;
import java.util.List;

public class ChatRoomVO {
	private int roomNo;
	private List<PlayerVO> playerList = new ArrayList<PlayerVO>();
	public int getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}
	public void addPlayer(PlayerVO playerVO) {
		playerList.add(playerVO);
	}
	public void removePlayer(PlayerVO playerVO) {
		for(PlayerVO playerVO2 : playerList) {
			if(playerVO.getPlayerNickname().equals(playerVO2.getPlayerNickname())){
				
			}
		}
	}
	
	
	
}
