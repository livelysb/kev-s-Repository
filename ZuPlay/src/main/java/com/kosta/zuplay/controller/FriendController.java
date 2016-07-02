package com.kosta.zuplay.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.google.gson.Gson;
import com.kosta.zuplay.model.dto.player.FriendDTO;
import com.kosta.zuplay.model.service.player.FriendService;
import com.kosta.zuplay.util.vo.PlayerVO;

@Controller
public class FriendController {

	@Autowired
	private FriendService friendServiceImpl;
	@Autowired
	private ServletContext application;

	public void friendSelect(String playerNickname) {
		PlayerVO pv = (PlayerVO) application.getAttribute(playerNickname);
		WebSocketSession session = pv.getSession();
		List<FriendDTO> list = friendServiceImpl.friendSelect(playerNickname);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		json = "{type:'friendSelect',data:" + json + "}";
		System.out.println(json);
		TextMessage tx = new TextMessage(json);
		System.out.println(tx);
		try {
			session.sendMessage(tx);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void friendSelectOnline(String playerNickname) {
		PlayerVO pv = (PlayerVO) application.getAttribute(playerNickname);
		WebSocketSession session = pv.getSession();
		Gson gson = new Gson();
		List<FriendDTO> list = friendServiceImpl.friendSelectOnline(playerNickname);
		String json = "{type:'friendSelectOnline',data:" + gson.toJson(list) + "}";
		TextMessage tx = new TextMessage(json);
		try {
			session.sendMessage(tx);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void friendAdd(String playerNickname, String playerNickname2) {
		PlayerVO pv = (PlayerVO) application.getAttribute(playerNickname2);
		WebSocketSession session = pv.getSession();
		FriendDTO dto = friendServiceImpl.friendAdd(playerNickname, playerNickname2);
		Gson gson = new Gson();
		String json = gson.toJson(dto);
		json = "{type:'notiFriendAdd',data:" + json + "}";
		System.out.println(json);
		TextMessage tx = new TextMessage(json);
		System.out.println(tx);
		try {
			session.sendMessage(tx);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void friendDel(String playerNickname, int friendSq) {
		PlayerVO pv = (PlayerVO) application.getAttribute(playerNickname);
		WebSocketSession session = pv.getSession();
		boolean result = friendServiceImpl.friendDel(friendSq);
		Gson gson = new Gson();
		String json = "{type:'friendDel',data:" + gson.toJson(result) + "}";
		TextMessage tx = new TextMessage(json);
		try {
			session.sendMessage(tx);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void friendAccept(String playerNickname, String playerNickname2, int friendSq) {
		PlayerVO pv = (PlayerVO) application.getAttribute(playerNickname);
		PlayerVO pv2 = (PlayerVO) application.getAttribute(playerNickname2);

		WebSocketSession session = pv.getSession();
		WebSocketSession session2 = pv2.getSession();

		boolean result = friendServiceImpl.friendAccept(friendSq);
		if (result) {
			Gson gson = new Gson();
			String json = "{type:'notiFriendAcceptMe',data:" + gson.toJson(result) + "}";
			String json2 = "{type:'notiFriendAcceptYou',data:" + gson.toJson(result) + "}";

			try {
				session.sendMessage(new TextMessage(json));
				session2.sendMessage(new TextMessage(json2));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
