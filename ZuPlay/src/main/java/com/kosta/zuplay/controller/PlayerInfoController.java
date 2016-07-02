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
	private PlayerInfoService playerInfoServiceImpl;
	@RequestMapping("playerInfoSelectAll")
	@ResponseBody
	public String playerInfoSelectAll(HttpSession session, String keyword){
		List<PlayerDTO> list = playerInfoServiceImpl.playerInfoSelectAll(keyword);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		return json;
	}
}
