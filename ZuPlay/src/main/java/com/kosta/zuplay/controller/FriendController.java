package com.kosta.zuplay.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.google.gson.Gson;
import com.kosta.zuplay.model.dto.player.FriendDTO;
import com.kosta.zuplay.model.dto.player.FriendVO;
import com.kosta.zuplay.model.dto.player.PlayerDTO;
import com.kosta.zuplay.model.service.player.FriendService;
import com.kosta.zuplay.util.vo.PlayerVO;

@Controller
public class FriendController {

	@Autowired
	private FriendService friendServiceImpl;
	
	@Autowired
	private ServletContext application;

	public void friendLogin(String playerNickname) {
		List<String> list = new ArrayList<>();
		WebSocketSession webSocketSession = null;

		try {
			list = friendServiceImpl.friendSelectOnlyNickname(playerNickname);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String json = "{\"type\":\"notiFriendLogin\",\"data\":\"" + playerNickname + "\"}";
		TextMessage tx = new TextMessage(json);
		PlayerVO pv = null;
		for (int i = 0; i < list.size(); i++) {
			String playerNickname1 = list.get(i);
			try {
				pv = (PlayerVO) application.getAttribute("#" + playerNickname1);
				webSocketSession = pv.getSession();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				webSocketSession.sendMessage(tx);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	public void friendSelect(String playerNickname) {
		PlayerVO pv = (PlayerVO) application.getAttribute("#" + playerNickname);
		WebSocketSession webSession = pv.getSession();
		List<FriendVO> list = null;
		try {
			list = friendServiceImpl.friendSelect(playerNickname);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String json = gson.toJson(list);
		json = "{\"type\":\"friendSelect\",\"data\": " + json + "}";
		TextMessage tx = new TextMessage(json);
		try {
			webSession.sendMessage(tx);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//
	// public void friendSelectOnline(String playerNickname) {
	// PlayerVO pv = (PlayerVO) application.getAttribute("#" + playerNickname);
	// WebSocketSession webSession = pv.getSession();
	// Gson gson = new Gson();
	// List<FriendDTO> list = null;
	// try {
	// list = friendServiceImpl.friendSelectOnline(playerNickname);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// String json = "{\"type\":\"friendSelectOnline\",\"data\":" +
	// gson.toJson(list) + "}";
	// TextMessage tx = new TextMessage(json);
	// try {
	// webSession.sendMessage(tx);
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }

	public void friendAdd(String playerNickname, String playerNickname2) {
		PlayerVO pv = null;
		WebSocketSession webSession = null;
		if (application.getAttribute("#" + playerNickname2) != null) {
			pv = (PlayerVO) application.getAttribute("#" + playerNickname2);
			webSession = pv.getSession();

		}
		FriendDTO dto = null;
		dto = friendServiceImpl.friendAdd(playerNickname, playerNickname2);
		Gson gson = new Gson();
		String json = gson.toJson(dto);
		json = "{\"type\":\"notiFriendAdd\",\"data\":" + json + "}";
		TextMessage tx = new TextMessage(json);
		if (application.getAttribute("#" + playerNickname2) != null) {
			try {
				webSession.sendMessage(tx);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void friendDel(String playerNickname, int friendSq) {
		PlayerVO pv = (PlayerVO) application.getAttribute("#" + playerNickname);
		WebSocketSession webSession = pv.getSession();
		boolean result = false;
		result = friendServiceImpl.friendDel(friendSq);
		Gson gson = new Gson();
		String json = "{\"type\":\"friendDel\",\"data\":" + gson.toJson(result) + "}";
		TextMessage tx = new TextMessage(json);
		try {
			webSession.sendMessage(tx);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void friendAccept(String playerNickname, String playerNickname2, int friendSq) {
		PlayerVO pv = (PlayerVO) application.getAttribute("#" + playerNickname);
		PlayerVO pv2 = null;
		WebSocketSession webSession2 = null;
		if (application.getAttribute("#" + playerNickname2) != null) {
			pv2 = (PlayerVO) application.getAttribute("#" + playerNickname2);
			webSession2 = pv2.getSession();
		}
		WebSocketSession webSession = pv.getSession();

		boolean result = true;
		try {
			result = friendServiceImpl.friendAccept(friendSq);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result) {
			Gson gson = new Gson();
			String json = "{\"type\":\"notiFriendAcceptMe\",\"data\":\"" + result + "\"}";
			String json2 = "{\"type\":\"notiFriendAcceptYou\",\"data\":\"" + playerNickname + "\"}";

			try {
				webSession.sendMessage(new TextMessage(json));
				if (application.getAttribute("#" + playerNickname2) != null) {
					webSession2.sendMessage(new TextMessage(json2));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
