package com.kosta.zuplay.util.vo;

import org.springframework.web.socket.WebSocketSession;

public class PlayerVO {
		
	private String playerNickname;
	private WebSocketSession session;
	
	
	public PlayerVO() {
		
	}
	
	public PlayerVO(String playerNickname, WebSocketSession session) {
		this.playerNickname = playerNickname;
		this.session = session;
	}
	
	
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
}

