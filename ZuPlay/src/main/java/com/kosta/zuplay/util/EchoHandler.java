package com.kosta.zuplay.util;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.kosta.zuplay.util.vo.PlayerVO;

/**
 * 
 */
@Component
public class EchoHandler extends TextWebSocketHandler {
	
	@Autowired
	private ServletContext application;
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) {
		String mes=message.getPayload();
		System.out.println(mes);
		String [] mesArr=mes.split("#/fuckWebSocket/#");
		String playerNickname=mesArr[1];
		if(mesArr[0].equals("open")){
			System.out.println(playerNickname);
			application.setAttribute(playerNickname, new PlayerVO(playerNickname, session));
		}else if(mesArr[1].equals("friend")){
					}
		
		

		
	}
	


}