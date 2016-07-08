package com.kosta.zuplay.util.vo;

import java.util.List;

import com.kosta.zuplay.model.dto.player.PlayerItemDTO;

public class ChatMsgVO {
	
	private String sender;
	private String action; //in, out, msg
	private String roomNo;
	private String time;
	private String msg;
	private String gender;
	private List<PlayerItemDTO> playerItem;
	
	
	public ChatMsgVO() {
		super();
	}


	public ChatMsgVO(String sender, String action, String roomNo, String time, String msg, String gender,
			List<PlayerItemDTO> playerItem) {
		super();
		this.sender = sender;
		this.action = action;
		this.roomNo = roomNo;
		this.time = time;
		this.msg = msg;
		this.gender = gender;
		this.playerItem = playerItem;
	}


	public String getSender() {
		return sender;
	}


	public void setSender(String sender) {
		this.sender = sender;
	}


	public String getAction() {
		return action;
	}


	public void setAction(String action) {
		this.action = action;
	}


	public String getRoomNo() {
		return roomNo;
	}


	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public List<PlayerItemDTO> getPlayerItem() {
		return playerItem;
	}


	public void setPlayerItem(List<PlayerItemDTO> playerItem) {
		this.playerItem = playerItem;
	}
	
	
	
	
}
