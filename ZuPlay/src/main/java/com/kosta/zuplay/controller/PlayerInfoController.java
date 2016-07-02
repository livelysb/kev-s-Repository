package com.kosta.zuplay.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kosta.zuplay.model.dto.player.PlayerDTO;
import com.kosta.zuplay.model.service.player.PlayerInfoService;

@Controller
public class PlayerInfoController {

	
	@Autowired
	private PlayerInfoService playerInfoService;
	
	
	@Autowired
	private PlayerInfoService playerInfoServiceImpl;
	@RequestMapping(value={"playerInfoSelectAll"}, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String playerInfoSelectAll(HttpSession session, String keyword) throws Exception{
		System.out.println("키워드 : " + keyword);
		List<PlayerDTO> list;
		try {
			list = playerInfoServiceImpl.playerInfoSelectAll(keyword);
		} catch (Exception e) {
			session.setAttribute("errorMsg", e.toString());
			throw new Exception();
		}
		Gson gson = new Gson();
		String json = gson.toJson(list);
		System.out.println(json);
		return json;
	}
	
	@RequestMapping(value={"updatePI"}, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String updatePI(HttpSession session) throws Exception {
		try {
			String playerNickname = (String) session.getAttribute("playerNickname");
			System.out.println("came dddd" + playerNickname);
			PlayerDTO playerDTO = playerInfoService.getPlayer(playerNickname);
			Gson gson = new Gson();
			String json = gson.toJson(playerDTO);
			System.out.println("updatePI json" + json);
			return json;
		} catch (Exception e) {
			session.setAttribute("errorMsg", e.getMessage());
			throw new Exception();
		}
	}
}
