package com.kosta.zuplay.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosta.zuplay.model.dto.player.PlayerDTO;
import com.kosta.zuplay.model.service.player.PlayerInfoService;

@Controller
public class PlayerController {

	@Autowired
	private PlayerInfoService playerInfoService;
	
	@RequestMapping("updatePI")
	@ResponseBody
	public PlayerDTO updatePI(HttpSession session) {
		String playerNickname = (String)session.getAttribute("playerNickname");
		return playerInfoService.getPlayer(playerNickname);
	}
	
	
}
