package com.kosta.zuplay.util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.kosta.zuplay.controller.FriendController;
import com.kosta.zuplay.model.service.community.ChattingService;
import com.kosta.zuplay.model.service.player.FriendService;
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
	@Autowired
	private FriendService friendServiceImpl;
	@Autowired
	private ChattingService chattingServiceImpl;

	@Override
	protected void handleTextMessage(WebSocketSession webSession, TextMessage message) {
		try {
			String mes = message.getPayload();
			System.out.println(mes);
			String[] mesArr = mes.split("#/fuckWebSocket/#");
			String playerNickname = mesArr[1];
			if (mesArr[0].equals("open")) {
				application.setAttribute("#" + playerNickname, new PlayerVO(playerNickname, webSession));
				try {
					friendController.friendLogin(playerNickname);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (mesArr[0].equals("friendSelect")) {
				friendController.friendSelect(playerNickname);
			} else if (mesArr[0].equals("friendAdd")) {
				try {
					friendController.friendAdd(playerNickname, mesArr[2]);// param
																			// :
																			// playerNickname,playerNickname2
				} catch (Exception e) {
					e.printStackTrace();
				}
				// else if (mesArr[0].equals("friendSelectOnline")) {
				// friendController.friendSelectOnline(playerNickname);
			} else if (mesArr[0].equals("friendDel")) {
				friendController.friendDel(playerNickname, Integer.parseInt(mesArr[2]));// param
																						// :
																						// playerNickname,friendSq
			} else if (mesArr[0].equals("friendAccept")) {
				friendController.friendAccept(playerNickname, mesArr[2], Integer.parseInt(mesArr[3]));// param
																										// :
																										// playerNickname,playerNickname,friendSq
			} else if (mesArr[0].equals("chatOneByOne")) {
				chattingServiceImpl.chatOnebyOne(playerNickname, mesArr[2], mesArr[3]); // param
																						// :
																						// sender
																						// /
																						// receiver
																						// /
																						// message
			} else if (mesArr[0].equals("chatRoomCreate")) {
				chattingServiceImpl.chatRoomCreate(playerNickname, mesArr[2], mesArr[3], Integer.parseInt(mesArr[4])); // param
				// :
				// sender
				// /
				// roomname
				// /
				// password
				// /
				// maxNum
			} else if (mesArr[0].equals("chatRoomJoin")) {
				if(mesArr.length==4)
					chattingServiceImpl.chatRoomJoin(playerNickname, Integer.parseInt(mesArr[2]), mesArr[3]); // param
				else
					chattingServiceImpl.chatRoomJoin(playerNickname, Integer.parseInt(mesArr[2]), "");
				// :
				// sender
				// /
				// roomNum
				// /
				// password
			} else if (mesArr[0].equals("chatRoomChat")) {
				chattingServiceImpl.chatRoomChat(playerNickname, Integer.parseInt(mesArr[2]), mesArr[3]); // param
																											// :
																											// sender
																											// /
																											// roomNum
																											// /
																											// message
			} else if (mesArr[0].equals("chatRoomOut")) {
				chattingServiceImpl.chatRoomOut(playerNickname, Integer.parseInt(mesArr[2]), false); // param
				// :
				// sender
				// /
				// roomNm
			} else if (mesArr[0].equals("chatRoomSelect")) {
				chattingServiceImpl.chatRoomSelect(playerNickname, Integer.parseInt(mesArr[2]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		Enumeration<String> enumr = application.getAttributeNames();
		List<String> appliName = new ArrayList<>();
		while (enumr.hasMoreElements()) {
			String sharp = enumr.nextElement();
			if (sharp.charAt(0) == '#') {
				appliName.add(sharp.substring(1));
			}
		}
		for(String name : appliName) {
			System.out.println(name);
		}
		System.out.println(appliName.size());
		for (int i = 0; i < appliName.size(); i++) {
			PlayerVO pv = (PlayerVO) application.getAttribute("#" + appliName.get(i));
			if (application.getAttribute("#" + appliName.get(i)) != null) {
				WebSocketSession session2 = pv.getSession();
				if (session == session2) {
					chattingServiceImpl.allChatRoomOut(appliName.get(i));
					List<String> list = friendServiceImpl.friendSelectOnlyNickname(appliName.get(i));
					String json = "{\"type\":\"notiFriendLogout\",\"data\":\"" + appliName.get(i) + "\"}";
					for (int j = 0; j < list.size(); j++) {
						PlayerVO pv2 = (PlayerVO) application.getAttribute("#" + list.get(j));
						pv2.getSession().sendMessage(new TextMessage(json));
					}
					application.removeAttribute("#" + appliName.get(i));
				}
			}
		}
	}

}