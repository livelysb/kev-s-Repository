package com.kosta.zuplay.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.kosta.zuplay.controller.FriendController;
import com.kosta.zuplay.util.vo.PlayerVO;

/**
 * 
 */
@Component
public class EchoHandler extends TextWebSocketHandler {

	@Autowired
	private ServletContext application;
	@Autowired
	private FriendController friendController;

	@Override
	protected void handleTextMessage(WebSocketSession webSession, TextMessage message){
		String mes = message.getPayload();
		System.out.println(mes);
		String[] mesArr = mes.split("#/fuckWebSocket/#");
		String playerNickname = mesArr[1];
		if (mesArr[0].equals("open")) {
			application.setAttribute("#"+playerNickname, new PlayerVO(playerNickname, webSession));
			try{
			friendController.friendLogin(playerNickname);
			}catch(Exception e){
				e.printStackTrace();
			}
		} else if (mesArr[0].equals("friendSelect")) {
			friendController.friendSelect(playerNickname);
		} else if (mesArr[0].equals("friendAdd")) {
			try{
			friendController.friendAdd(playerNickname, mesArr[2]);// param :
																	// playerNickname,playerNickname2
			}catch(Exception e){
				e.printStackTrace();
			}
		} else if (mesArr[0].equals("friendSelectOnline")) {
			friendController.friendSelectOnline(playerNickname);
		} else if (mesArr[0].equals("friendDel")) {
			friendController.friendDel(playerNickname, Integer.parseInt(mesArr[2]));// param
																					// :
																					// playerNickname,friendSq
		} else if (mesArr[0].equals("friendAccept")) {
			friendController.friendAccept(playerNickname, mesArr[2], Integer.parseInt(mesArr[3]));// param
																									// :
																									// playerNickname,playerNickname,friendSq
		}

	}

}