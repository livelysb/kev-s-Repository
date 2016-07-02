package com.kosta.zuplay.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kosta.zuplay.model.dto.player.PlayerDTO;
import com.kosta.zuplay.model.service.player.PlayerInfoService;

@Controller
public class PlayerController {

	@Autowired
	private PlayerInfoService playerInfoService;
	
	@RequestMapping("updatePI")
	@ResponseBody
	public String updatePI(HttpSession session) {
		String playerNickname = (String)session.getAttribute("playerNickname");
		PlayerDTO playerDTO = playerInfoService.getPlayer(playerNickname);
		Gson gson = new Gson();
		String json = gson.toJson(playerDTO);
		return json;
	}
	
	
}
