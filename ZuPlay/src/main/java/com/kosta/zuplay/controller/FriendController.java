package com.kosta.zuplay.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

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

	public void friendSelect(HttpSession session, String playerNickname) throws Exception{
		PlayerVO pv = (PlayerVO) application.getAttribute(playerNickname);
		WebSocketSession webSession = pv.getSession();
		List<FriendDTO> list = null;
		try {
			list = friendServiceImpl.friendSelect(playerNickname);
		} catch (Exception e1) {
			session.setAttribute("errorMsg", e1.toString());
			e1.printStackTrace();
			throw new Exception();
		}
		Gson gson = new Gson();
		String json = gson.toJson(list);
		json = "{type:'friendSelect',data:" + json + "}";
		System.out.println(json);
		TextMessage tx = new TextMessage(json);
		System.out.println(tx);
		try {
			webSession.sendMessage(tx);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void friendSelectOnline(HttpSession session, String playerNickname) throws Exception{
		PlayerVO pv = (PlayerVO) application.getAttribute(playerNickname);
		WebSocketSession webSession = pv.getSession();
		Gson gson = new Gson();
		List<FriendDTO> list;
		try {
			list = friendServiceImpl.friendSelectOnline(playerNickname);
		} catch (Exception e1) {
			session.setAttribute("errorMsg", e1.toString());
			e1.printStackTrace();
			throw new Exception();
		}
		String json = "{type:'friendSelectOnline',data:" + gson.toJson(list) + "}";
		TextMessage tx = new TextMessage(json);
		try {
			webSession.sendMessage(tx);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void friendAdd(HttpSession session, String playerNickname, String playerNickname2) throws Exception{
		PlayerVO pv = (PlayerVO) application.getAttribute(playerNickname2);
		WebSocketSession webSession = pv.getSession();
		FriendDTO dto = null;
		try {
			dto = friendServiceImpl.friendAdd(playerNickname, playerNickname2);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			session.setAttribute("errorMsg", e1.toString());
			e1.printStackTrace();
			throw new Exception();
		}
		Gson gson = new Gson();
		String json = gson.toJson(dto);
		json = "{type:'notiFriendAdd',data:" + json + "}";
		System.out.println(json);
		TextMessage tx = new TextMessage(json);
		System.out.println(tx);
		try {
			webSession.sendMessage(tx);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void friendDel(HttpSession session, String playerNickname, int friendSq) throws Exception{
		PlayerVO pv = (PlayerVO) application.getAttribute(playerNickname);
		WebSocketSession webSession = pv.getSession();
		boolean result = false;
		try {
			result = friendServiceImpl.friendDel(friendSq);
		} catch (Exception e1) {
			session.setAttribute("errorMsg", e1.toString());
			e1.printStackTrace();
			throw new Exception();
		}
		Gson gson = new Gson();
		String json = "{type:'friendDel',data:" + gson.toJson(result) + "}";
		TextMessage tx = new TextMessage(json);
		try {
			webSession.sendMessage(tx);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void friendAccept(HttpSession session, String playerNickname, String playerNickname2, int friendSq) throws Exception{
		PlayerVO pv = (PlayerVO) application.getAttribute(playerNickname);
		PlayerVO pv2 = (PlayerVO) application.getAttribute(playerNickname2);

		WebSocketSession webSession = pv.getSession();
		WebSocketSession webSession2 = pv2.getSession();

		boolean result;
		try {
			result = friendServiceImpl.friendAccept(friendSq);
		} catch (Exception e1) {
			session.setAttribute("errorMsg", e1.toString());
			e1.printStackTrace();
			throw new Exception();
		}
		if (result) {
			Gson gson = new Gson();
			String json = "{type:'notiFriendAcceptMe',data:" + gson.toJson(result) + "}";
			String json2 = "{type:'notiFriendAcceptYou',data:" + gson.toJson(result) + "}";

			try {
				webSession.sendMessage(new TextMessage(json));
				webSession2.sendMessage(new TextMessage(json2));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
