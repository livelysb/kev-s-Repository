package com.kosta.zuplay.controller.vo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.socket.WebSocketSession;

public class PlayerVO {
	
	private String playerNickname;
	private WebSocketSession session;
	private List<ChatRoomVO> chatRoomList = new ArrayList<>();
	
	public String getPlayerNickname() {
		return playerNickname;
	}
	public void setPlayerNickname(String playerNickname) {
		this.playerNickname = playerNickname;
	}
	public WebSocketSession getSession() {
		return session;
	}
	public void setSession(WebSocketSession session) {
		this.session = session;
	}
	
	public void AddChatRoom(ChatRoomVO chatRoomVO) {
		chatRoomList.add(chatRoomVO);
	}

}
